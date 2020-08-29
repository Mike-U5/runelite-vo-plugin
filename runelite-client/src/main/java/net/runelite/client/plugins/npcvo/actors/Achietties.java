package net.runelite.client.plugins.npcvo.actors;

import net.runelite.client.plugins.npcvo.ActorBase;

public class Achietties extends ActorBase {

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
