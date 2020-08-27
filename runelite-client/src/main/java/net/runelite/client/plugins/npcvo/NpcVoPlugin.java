package net.runelite.client.plugins.npcvo;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.NpcID;
import net.runelite.api.events.GameTick;
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

    @Subscribe
    public void onGameTick(GameTick tick) {
        String npcName = "";
        String text = "";
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
            sendChatMessage("[" + modelID + "] " + npcName);
            System.out.println(NpcList.LumbyCook.get(text));
            System.out.println(NpcList.LumbyCook.get("ar02"));
        }

        if (NpcID.COOK_4626 == modelID) { // Lumby Castle Cook
            final String voiceClip = NpcList.LumbyCook.get(text);
            final VoiceThread voRunnable = new VoiceThread();
            voRunnable.setVOLine(voiceClip);
            final Thread voThread = new Thread(voRunnable);
            voThread.start();
            sendChatMessage("Voice Clip: " + voiceClip);
        }
    }

    private void sendChatMessage(String chatMessage)
    {
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
