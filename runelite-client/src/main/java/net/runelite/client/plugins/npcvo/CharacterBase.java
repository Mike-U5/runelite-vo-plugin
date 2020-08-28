package net.runelite.client.plugins.npcvo;

import java.util.HashMap;
import java.util.Map;

public abstract class CharacterBase {
    protected final Map<String, String> map = new HashMap<>();

    public String getVo(String dialog) {
        final String fn = this.map.get(dialog);
        if (fn == null) {
            return null;
        }
        return this.getFolder() + "/" + fn;
    }

    protected abstract String getFolder();
}
