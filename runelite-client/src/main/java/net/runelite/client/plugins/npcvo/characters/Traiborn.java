package net.runelite.client.plugins.npcvo.characters;

import net.runelite.client.plugins.npcvo.CharacterBase;

public class Traiborn extends CharacterBase {
    @Override
    protected void populateMap() {
        this.map.put("Ello young thingummywut.", "talk1");
        this.map.put("A thingummywut? Where? Where?", "talk2");
        this.map.put("Those pesky thingummywuts. They get everywhere. They leave a terrible mess too.", "talk3");
        this.map.put("You're a thingummywut? I've never seen one up close before. They said I was mad!", "talk4");
        this.map.put("Now you are my proof! There ARE thingummywuts in this tower. Now where can I find a cage big enough to keep you?", "proof5");
        this.map.put("Oh ok, have a good time, and watch out for sheep! They're more cunning than they look.", "talk6");
        this.map.put("That's a pity. I thought maybe they were winding me up.", "talk7");
        this.map.put("Wizard eh? You don't want any truck with that sort. They're not to be trusted. That's what I've heard anyways.", "racist1");
        this.map.put("How dare you? Of course I'm a wizard. Now don't be so cheeky or I'll turn you into a frog.", "racist2");
        this.map.put("Cheerio then. It was nice chatting to you.", "");
        this.map.put("Don't be ridiculous. No-one has ever seen one.", "ramble1");
        this.map.put("They're invisible, or a myth, or a figment of my imagination. Can't remember which right now.", "ramble2");

    }

    @Override
    protected String getFolder() {
        return "WizardTraiborn";
    }
}
