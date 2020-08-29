package net.runelite.client.plugins.npcvo.actors;

import net.runelite.client.plugins.npcvo.ActorBase;

public class Tegid extends ActorBase {
    @Override
    protected void populateMap() {
        this.map.put("Yes. What is it to you?", "talk1");
        this.map.put("I suppose it is.", "talk2");
    }

    @Override
    protected String getFolder() {
        return "Tegid";
    }
}
