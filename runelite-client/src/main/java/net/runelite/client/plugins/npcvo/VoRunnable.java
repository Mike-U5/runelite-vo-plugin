package net.runelite.client.plugins.npcvo;

import javazoom.jl.player.Player;
import lombok.SneakyThrows;

import java.io.FileInputStream;

public class VoRunnable implements Runnable {
    private Player audioPlayer;
    private String voLine;

    public void setVOLine(String _voLine) {
        this.voLine = _voLine;
    }

    @SneakyThrows
    @Override
    public void run() {
        final FileInputStream fis = new FileInputStream("vo/" + this.voLine + ".mp3");
        audioPlayer = new Player(fis);
        audioPlayer.play();
    }

    public void stopSound() {
        audioPlayer.close();
    }
}
