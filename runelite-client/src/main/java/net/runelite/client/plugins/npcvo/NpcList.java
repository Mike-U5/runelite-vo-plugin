package net.runelite.client.plugins.npcvo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NpcList {
    private static final String BASE_PATH = "runelite-client/src/main/java/net/runelite/client/plugins/npcvo/vo/";
    private static final String ACT_FILE = "/act.txt";
    public static final Map<Integer, Map<String, String>> actors = new HashMap<>();

    static {
        final File folder = new File(BASE_PATH);
        for (final File entry : folder.listFiles()) {
            if (entry.isDirectory() && entry.getName().matches("[0-9]+")) {
                final File file = new File(BASE_PATH + entry.getName() + ACT_FILE);
                if (file.exists()) {
                    try {
                        parseJson(file, Integer.parseInt(entry.getName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static String stripQuotes(final String _str) {
        final String str = _str.trim();
        if (str.charAt(0) == '"' && str.charAt(str.length() - 1) == '"') {
            return str.substring(1, str.length() - 1);
        }
        return null;
    }

    private static void parseJson(final File file, int id) throws IOException {
        String line = null;

        // Init Buffered Reader
        final BufferedReader br = new BufferedReader(new FileReader(file));
        final Map<String, String> character = new HashMap();

        while ((line = br.readLine()) != null) {
            // Split the line by :
            final String[] parts = line.split(":(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Thanks Rohit Jain
            // Should produce two parts, if not line is not correct and should be skipped
            if (parts.length == 2) {
                final String key = TextUtil.cleanDialogue(NpcList.stripQuotes(parts[1]));
                final String name = NpcList.stripQuotes(parts[1]);

                // Check if provided name is valid
                if (key != null && name != null && name.length() < 250 && name.matches("^[a-zA-Z0-9_]+$")) {
                    character.put(key, name);
                }
            }
        }

        NpcList.actors.put(id, character);
    }

}
