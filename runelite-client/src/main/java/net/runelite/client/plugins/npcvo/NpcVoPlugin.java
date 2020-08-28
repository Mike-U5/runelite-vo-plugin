package net.runelite.client.plugins.npcvo;

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
    tags = {"highlight", "lines", "unaggro", "aggro", "aggressive", "npcs", "area", "slayer"},
    enabledByDefault = false
)

public class NpcVoPlugin extends Plugin {
    @Inject
    private Client client;
    @Inject
    private ChatMessageManager chatMessageManager;

    private boolean newDialogueOpened = false;

    // Tracks if a new NPC Dialogue box was opened
    @Subscribe
    public void onWidgetLoaded(final WidgetLoaded event) {
        if (event.getGroupId() == 231) { // Npc Dialogue
            this.newDialogueOpened = true;
        }
        sendChatMessage("Widget: " + event.getGroupId());
    }

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked menuOpt) {
        // Let's assume any option closes dialog for now
    }

    // If a new dialogue box was opened, call our own function
    @Subscribe
    public void onGameTick(GameTick tick) {
        if (newDialogueOpened) {
            newDialogueOpened = false;
            this.newDialogueBoxIsShown();
        }
    }

    private void newDialogueBoxIsShown() {
        String npcName = "DIO";
        String text = "KONO DIO DA!";
        int modelID = -1;

        if (client.getWidget(WidgetInfo.DIALOG_NPC) != null) {
            npcName = client.getWidget(WidgetInfo.DIALOG_NPC).getName();
        }
        if (client.getWidget(WidgetInfo.DIALOG_NPC_TEXT) != null) {
            text = client.getWidget(WidgetInfo.DIALOG_NPC_TEXT).getText();
        }
        if (client.getWidget(WidgetInfo.DIALOG_NPC_HEAD_MODEL) != null) {
            modelID = client.getWidget(WidgetInfo.DIALOG_NPC_HEAD_MODEL).getModelId();
        }

        if (modelID >= 0) {
            sendChatMessage("[" + modelID + "] " + npcName + ": " + text);
        }

        final CharacterBase character = NpcList.chars.get(modelID);
        if (character != null) {
            final String voiceClip = character.getVo(text);
            if (voiceClip != null) {
                final VoiceThread voRunnable = new VoiceThread();
                voRunnable.setVOLine(voiceClip);
                final Thread voThread = new Thread(voRunnable);
                voThread.start();
            }
            ///sendChatMessage("Voice Clip: " + voiceClip);
        }
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
