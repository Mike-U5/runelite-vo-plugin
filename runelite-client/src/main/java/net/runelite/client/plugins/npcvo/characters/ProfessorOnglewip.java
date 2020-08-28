package net.runelite.client.plugins.npcvo.characters;

import net.runelite.client.plugins.npcvo.CharacterBase;

public class ProfessorOnglewip extends CharacterBase {
    @Override
    protected void populateMap() {
        this.map.put("Oh no, I come from the Gnome Stronghold. I've been<lt>br<gt>sent here by King Narnode to learn about human<lt>br<gt>magics.", "talk1");
        this.map.put("It's in the North West of the continent - a long way<lt>br<gt>away. You should visit us there some time. The food's<lt>br<gt>great, and the company's delightful.", "talk2");
        this.map.put("Well, it's full of gnomes. How much nicer could it be?", "talk3");
    }

    @Override
    protected String getFolder() {
        return "ProfessorOnglewip";
    }
}
