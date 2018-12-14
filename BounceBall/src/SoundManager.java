

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

class SoundManager {
    public static SoundManager I;

    public SoundManager() {
        I = this;
    }

    public void PlaySound(String soundName) {
        try {
            InputStream in = new FileInputStream(new File(soundName));
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {

        }
    }
}

