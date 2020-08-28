package net.runelite.client.plugins.npcvo.characters;

import net.runelite.client.plugins.npcvo.CharacterBase;

public class Achietties extends CharacterBase {

    public Achietties() {
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
