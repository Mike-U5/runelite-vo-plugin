package net.runelite.client.plugins.npcvo.characters;

import net.runelite.client.plugins.npcvo.CharacterBase;

public class Achietties extends CharacterBase {

    @Override
    protected void populateMap() {
        this.map.put(
            "Greetings. Welcome to the Heroes' Guild.",
            "greetings1"
        );
    }

    @Override
    protected String getFolder() {
        return "Achietties";
    }
}
