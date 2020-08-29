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
            text = this.strip(client.getWidget(WidgetInfo.DIALOG_NPC_TEXT).getText());
        }
        if (client.getWidget(WidgetInfo.DIALOG_NPC_HEAD_MODEL) != null) {
            modelID = client.getWidget(WidgetInfo.DIALOG_NPC_HEAD_MODEL).getModelId();
        }

        ///if (modelID >= 0) {
        ///    debugChatMessage("[" + modelID + "] " + text);
        ///}

        if (modelID >= 0) {
            final ActorBase character = NpcList.chars.get(modelID);
            if (character != null) {
                final String voiceClip = character.getVo(text);
                if (voiceClip != null) {
                    this.playSound(voiceClip);
                }
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
        this.stopSound();
        this.sndPlayer = new RunnableSndPlayer();
        this.sndPlayer.setVOLine(sndPath);
        this.sndThread = new Thread(this.sndPlayer);
        this.sndThread.start();
    }

    // Strip out newlines from text
    private String strip(String str) {
        return str.replaceAll("<.*?>", " ");
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
