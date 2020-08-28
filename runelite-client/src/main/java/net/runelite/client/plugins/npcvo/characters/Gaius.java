package net.runelite.client.plugins.npcvo.characters;

import net.runelite.client.plugins.npcvo.CharacterBase;

public class Gaius extends CharacterBase {
    @Override
    protected void populateMap() {
        this.map.put("Welcome to my two-handed sword shop.", "greeting1");
    }

    @Override
    protected String getFolder() {
        return "Gaius";
    }
}
