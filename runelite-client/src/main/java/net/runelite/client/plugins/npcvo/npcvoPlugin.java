package net.runelite.client.plugins.npcvo;

import javazoom.jl.decoder.JavaLayerException;
import lombok.SneakyThrows;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.Notifier;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import javax.inject.Inject;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@PluginDescriptor(
    name = "Test Plugin",
    description = "Kono Dio Da",
    tags = {"highlight", "lines", "unaggro", "aggro", "aggressive", "npcs", "area", "slayer"},
    enabledByDefault = false
)

public class npcvoPlugin extends Plugin {
    @Inject
    private Client client;
    @Inject
    private ChatMessageManager chatMessageManager;

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked menuOptionClicked) throws JavaLayerException, FileNotFoundException {
        if (menuOptionClicked.getMenuAction() == MenuAction.WIDGET_TYPE_6 && menuOptionClicked.getMenuOption().equals("Continue")) {
            final int actionID = menuOptionClicked.getActionParam();
            final voiceThread voRunnable = new voiceThread();
            final Thread thread = new Thread(voRunnable);
            thread.start();
            sendChatMessage("Action ID: " + actionID);
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
