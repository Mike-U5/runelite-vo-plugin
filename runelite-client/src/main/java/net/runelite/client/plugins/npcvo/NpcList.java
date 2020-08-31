package net.runelite.client.plugins.npcvo;

import net.runelite.api.NpcID;
import net.runelite.client.plugins.npcvo.actors.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

// TODO: Use JSON instead?

public class NpcList {
    private static final String BASE_PATH = "runelite-client/src/main/java/net/runelite/client/plugins/npcvo/vo/";
    private static final String ACT_FILE = "/act.txt";
    public static final HashMap<String, String> strings = new HashMap();

    public static HashMap<Integer, ActorBase> actors = new HashMap<>();
    static {
        final File folder = new File(BASE_PATH);
        for (final File entry : folder.listFiles()) {
            if (entry.isDirectory()) {
                final File file = new File(BASE_PATH + entry.getName() + ACT_FILE);
                if (file.exists()) {
                    try {
                        parseJson(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println(entry.getName());
            }
        }
    }
    static {
        actors.put(NpcID.COOK_4626, new LumbyCook());
        actors.put(NpcID.ACHIETTIES, new Achietties());
        actors.put(NpcID.GAIUS, new Gaius());
        actors.put(NpcID.TEGID, new Tegid());
        actors.put(NpcID.TRAIBORN, new Traiborn());
        actors.put(NpcID.PROFESSOR_ONGLEWIP, new ProfessorOnglewip());
    }

    private static String stripQuotes(final String _str) {
        final String str = _str.trim();
        if (str.charAt(0) == '"' && str.charAt(str.length() - 1) == '"') {
            return str.substring(1, str.length() - 1);
        }
        return null;
    }

    private static void parseJson(final File file) throws IOException {
        String line = null;

        // Init Buffered Reader
        final BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null) {
            // Split the line by :
            final String[] parts = line.split(":(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Thanks Rohit Jain
            // Should produce two parts, if not line is not correct and should be skipped
            if (parts.length == 2) {
                final String key = NpcList.stripQuotes(parts[0]);
                final String name = NpcList.stripQuotes(parts[1]);

                //NpcList.strings.put("_"+key, name + "{" + name.length() + "}");

                // Check if provided name is valid
                if (key != null && name != null && name.length() < 250 && name.matches("^[a-zA-Z0-9_]+$")) {
                    NpcList.strings.put(key, name);
                }
            }
        }
    }

}
