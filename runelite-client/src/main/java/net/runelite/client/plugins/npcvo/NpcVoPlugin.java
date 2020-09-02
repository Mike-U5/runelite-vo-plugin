package net.runelite.client.plugins.npcvo;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.Map;

@PluginDescriptor(
    name = "Voice Acting Plugin",
    description = "W.I.P. I promise nothing and will deliver even less.",
    tags = {"voice", "lines", "npcs", "sound"},
    enabledByDefault = false
)

public class NpcVoPlugin extends Plugin {
    @Inject
    private Client client;
    @Inject
    private ChatMessageManager chatMessageManager;
    private Thread sndThread;
    private RunnableSndPlayer sndPlayer;
    private boolean newDialogueOpened = false;

    @Override
    protected void startUp() {
        //this.printMap(NpcList.actors.get(2560));
    }

    private void printMap(final Map map) {
        final Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry pair = (Map.Entry)it.next();
            this.debugChatMessage((String) pair.getKey());
            it.remove();
        }
        this.debugChatMessage("~~ " + map.size() + " ~~");
    }

    // Tracks if a new NPC Dialogue box was opened
    // Calling getWidget here doesn't work, so we do that in onGameTick
    @Subscribe
    public void onWidgetLoaded(final WidgetLoaded event) {
        if (event.getGroupId() == 231) { // Npc Dialogue Box
            this.newDialogueOpened = true;
        }
    }

    // We need to check if a widget was opened or closed
    @Subscribe
    public void onGameTick(GameTick tick) {
        if (newDialogueOpened) {
            newDialogueOpened = false;
            this.newDialogueBoxIsShown();
        }
        if (client.getWidget(WidgetInfo.DIALOG_NPC_HEAD_MODEL) == null) {
            this.stopSound();
        }
    }

    private void newDialogueBoxIsShown() {
        String text = "You fell for it fool! Thunder Cross Split Attack!";
        int modelID = -1;

        if (client.getWidget(WidgetInfo.DIALOG_NPC_TEXT) != null) {
            text = TextUtil.crushDialogue(client.getWidget(WidgetInfo.DIALOG_NPC_TEXT).getText().trim());
        }
        if (client.getWidget(WidgetInfo.DIALOG_NPC_HEAD_MODEL) != null) {
            modelID = client.getWidget(WidgetInfo.DIALOG_NPC_HEAD_MODEL).getModelId();
        }

        ///if (modelID >= 0) {
        ///    debugChatMessage("[" + modelID + "] " + text);
        ///}

        if (modelID >= 0) {
            final Map<String, String> character = NpcList.actors.get(modelID);
            if (character != null) {
                final String voiceClip = character.get(text);
                if (voiceClip != null) {
                    this.playSound(modelID + "/" + voiceClip);
                } else {
                    this.debugChatMessage("Voicelip not found: " + text);
                    this.debugChatMessage("In List:");
                    this.printMap(character);
                }
            } else {
                this.debugChatMessage("NPC ID not found: " + modelID);
            }
        }
    }

    // Stop a playing sound
    private void stopSound() {
        if (this.sndThread != null && this.sndThread.isAlive()) {
            this.sndPlayer.stopSound();
        }
    }

    // Play a new sound
    private void playSound(String sndPath) {
        this.debugChatMessage("Playing:" + sndPath);
        this.stopSound();
        this.sndPlayer = new RunnableSndPlayer();
        this.sndPlayer.setVOLine(sndPath);
        this.sndThread = new Thread(this.sndPlayer);
        this.sndThread.start();
    }

    // For testing purposes
    private void debugChatMessage(String chatMessage) {
        final String message = new ChatMessageBuilder()
            .append(ChatColorType.HIGHLIGHT)
            .append(chatMessage)
            .build();

        chatMessageManager.queue(
            QueuedMessage.builder()
                .type(ChatMessageType.CONSOLE)
                .runeLiteFormattedMessage(message)
                .build());
    }

}
