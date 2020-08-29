package net.runelite.client.plugins.npcvo;

import javazoom.jl.player.Player;
import lombok.SneakyThrows;

import java.io.FileInputStream;

// The thread should die after the sound finishes playing
public class RunnableSndPlayer implements Runnable {
    private static final String BASE_PATH = "runelite-client/src/main/java/net/runelite/client/plugins/npcvo/vo/";
    private static final String FILE_EXT = ".mp3";
    private Player audioPlayer;
    private String sndPath;

    public void setVOLine(final String sndPath) {
        this.sndPath = sndPath;
    }

    @SneakyThrows
    @Override
    public void run() {
        final FileInputStream fis = new FileInputStream(BASE_PATH + this.sndPath + FILE_EXT);
        this.audioPlayer = new Player(fis);
        this.audioPlayer.play();
    }

    public void stopSound() {
        audioPlayer.close();
    }
}
