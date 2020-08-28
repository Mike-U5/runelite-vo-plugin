package net.runelite.client.plugins.npcvo.characters;

import net.runelite.client.plugins.npcvo.CharacterBase;

public class LumbyCook extends CharacterBase {
    public LumbyCook() {
        this.map.put(
            "Seriously, you were great!<br>I can't believe I nearly caused all of those people to be<br>killed!",
            "KONO DIO DA"
        );
        this.map.put(
            "ar02",
            "Some article"
        );
    }

    @Override
    protected String getFolder() {
        return "LumbyCook";
    }
}
