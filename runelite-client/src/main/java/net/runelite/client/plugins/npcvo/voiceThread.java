package net.runelite.client.plugins.npcvo;

import javazoom.jl.player.Player;
import lombok.SneakyThrows;

import java.io.FileInputStream;

public class voiceThread implements Runnable {
    private Player audioPlayer;

    @SneakyThrows
    @Override
    public void run() {
        FileInputStream fis = new FileInputStream("vo/KONO DIO DA.mp3");
        audioPlayer = new Player(fis);
        audioPlayer.play();
    }
}
