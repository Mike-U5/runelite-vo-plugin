package net.runelite.client.plugins.npcvo;

import net.runelite.api.NpcID;
import net.runelite.client.plugins.npcvo.actors.*;

import java.util.HashMap;

// TODO: Use JSON instead?

public class NpcList {
    public static HashMap<Integer, ActorBase> actors = new HashMap<>();
    static {
        actors.put(NpcID.COOK_4626, new LumbyCook());
        actors.put(NpcID.ACHIETTIES, new Achietties());
        actors.put(NpcID.GAIUS, new Gaius());
        actors.put(NpcID.TEGID, new Tegid());
        actors.put(NpcID.TRAIBORN, new Traiborn());
        actors.put(NpcID.PROFESSOR_ONGLEWIP, new ProfessorOnglewip());
    }
}
