package net.runelite.client.plugins.npcvo.actors;

import net.runelite.client.plugins.npcvo.ActorBase;

public class ProfessorOnglewip extends ActorBase {
    @Override
    protected void populateMap() {
        this.map.put("Oh no, I come from the Gnome Stronghold. I've been sent here by King Narnode to learn about human magics.", "talk1");
        this.map.put("It's in the North West of the continent - a long way away. You should visit us there some time. The food's great, and the company's delightful.", "talk2");
        this.map.put("Well, it's full of gnomes. How much nicer could it be?", "talk3");
    }

    @Override
    protected String getFolder() {
        return "ProfessorOnglewip";
    }
}
