package net.runelite.client.plugins.npcvo;

import javazoom.jl.player.Player;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.*;
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
    name = "Test Plugin",
    description = "Kono Dio Da",
    tags = {"voice", "lines", "npcs", "sound"},
    enabledByDefault = false
)

public class NpcVoPlugin extends Plugin {
    @Inject
    private Client client;
    @Inject
    private ChatMessageManager chatMessageManager;
    private Thread voThread;
    private VoRunnable voRunnable;
    private boolean newDialogueOpened = false;

    // Tracks if a new NPC Dialogue box was opened
    @Subscribe
    public void onWidgetLoaded(final WidgetLoaded event) {
        if (event.getGroupId() == 231) { // Npc Dialogue
            this.newDialogueOpened = true;
        }
    }

    // If a new dialogue box was opened, call our own function
    @Subscribe
    public void onGameTick(GameTick tick) {
        if (newDialogueOpened) {
            newDialogueOpened = false;
            this.newDialogueBoxIsShown();
        }
        if (client.getWidget(WidgetInfo.DIALOG_NPC_HEAD_MODEL) == null) {
            this.stopActiveVo();
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

        if (modelID >= 0) {
            sendChatMessage("[" + modelID + "] " + text);
        }

        final CharacterBase character = NpcList.chars.get(modelID);
        if (character != null) {
            final String voiceClip = character.getVo(text);
            if (voiceClip != null) {
                this.setActiveVo(voiceClip);
            }
        }
    }

    private void stopActiveVo() {
        if (this.voThread != null && this.voThread.isAlive()) {
            this.voRunnable.stopSound();
        }
    }

    private void setActiveVo(String voiceClip) {
        this.stopActiveVo();
        this.voRunnable = new VoRunnable();
        this.voRunnable.setVOLine(voiceClip);
        this.voThread = new Thread(this.voRunnable);
        this.voThread.start();
    }

    // Strip out comma's and HTML Tags
    private String strip(final String str) {
        return str.replace("lt>br<gt", " ");
    }

    private void sendChatMessage(String chatMessage) {
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
