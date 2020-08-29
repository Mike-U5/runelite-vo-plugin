package net.runelite.client.plugins.npcvo.actors;

import net.runelite.client.plugins.npcvo.ActorBase;

public class Gaius extends ActorBase {
    @Override
    protected void populateMap() {
        this.map.put("Welcome to my two-handed sword shop.", "greeting1");
    }

    @Override
    protected String getFolder() {
        return "Gaius";
    }
}
