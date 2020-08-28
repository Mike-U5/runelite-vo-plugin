package net.runelite.client.plugins.npcvo;

import net.runelite.api.NpcID;
import net.runelite.client.plugins.npcvo.characters.Achietties;
import net.runelite.client.plugins.npcvo.characters.LumbyCook;

import java.util.HashMap;

public class NpcList {
    public static HashMap<Integer, CharacterBase> chars = new HashMap<Integer, CharacterBase>();
    static {
        chars.put(NpcID.COOK_4626, new LumbyCook());
        chars.put(NpcID.ACHIETTIES, new Achietties());
    }
}
