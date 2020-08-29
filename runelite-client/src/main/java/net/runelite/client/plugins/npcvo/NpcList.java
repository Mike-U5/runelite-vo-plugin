package net.runelite.client.plugins.npcvo;

import net.runelite.api.NpcID;
import net.runelite.client.plugins.npcvo.actors.*;

import java.util.HashMap;

// TODO: Populate with JSON?

public class NpcList {
    public static HashMap<Integer, ActorBase> chars = new HashMap<>();
    static {
        chars.put(NpcID.COOK_4626, new LumbyCook());
        chars.put(NpcID.ACHIETTIES, new Achietties());
        chars.put(NpcID.GAIUS, new Gaius());
        chars.put(NpcID.TEGID, new Tegid());
        chars.put(NpcID.TRAIBORN, new Traiborn());
        chars.put(NpcID.PROFESSOR_ONGLEWIP, new ProfessorOnglewip());
    }
}
