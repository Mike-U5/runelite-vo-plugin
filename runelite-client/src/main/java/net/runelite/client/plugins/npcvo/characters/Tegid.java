package net.runelite.client.plugins.npcvo.characters;

import net.runelite.client.plugins.npcvo.CharacterBase;

public class Tegid extends CharacterBase {
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
