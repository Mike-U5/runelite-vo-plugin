package net.runelite.client.plugins.npcvo;

public class TextUtil {
    public static String cleanDialogue(String str) {
        return str.replaceAll("<.*?>", " ").trim();
    }

}
