package net.runelite.client.plugins.npcvo;

public class TextUtil {
    // Strip most non-alphanumeric chars from dialog string before comparing
    public static String crushDialogue(String str) {
        str = str.replaceAll("<.*?>", "");
        str = str.replaceAll("[^a-zA-Z0-9]", "");
        str = str.toLowerCase();
        return str;
    }
}
