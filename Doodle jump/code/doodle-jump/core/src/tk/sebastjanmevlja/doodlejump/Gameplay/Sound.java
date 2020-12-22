package tk.sebastjanmevlja.doodlejump.Gameplay;

public class Sound {

    public static boolean musicEnabled = true;
    public static boolean soundEnabled = true;
    public static float musicVolume = 0.5f;
    public static float soundVolume = 1.0f;


    public static void changeMusicState() {
        if (musicEnabled) {
            if (!Asset.backgroundMusic.isPlaying()) {
                Asset.backgroundMusic.play();
                Asset.backgroundMusic.setLooping(true);
                Asset.backgroundMusic.setVolume(musicVolume);

            }
        } else {
            if (Asset.backgroundMusic.isPlaying()) {
                Asset.backgroundMusic.stop();
                Asset.backgroundMusic.setLooping(false);
            }
        }
    }

    public static void setMusicEnabled(boolean enabled) {
        musicEnabled = enabled;
        changeMusicState();
    }

    public static void changeMusicVolume(float volume) {
        musicVolume = volume;
        Asset.backgroundMusic.setVolume(musicVolume);
    }

    public static void setSoundEnabled(boolean enabled) {
        soundEnabled = enabled;
    }

    public static void changeSoundVolume(float volume) {
        soundVolume = volume;
        Asset.fallingSound.setVolume(volume);
        Asset.jumpSound.setVolume(volume);
        Asset.monsterSound.setVolume(volume);
        Asset.platformBreakingSound.setVolume(volume);
        Asset.startSound.setVolume(volume);
    }

    public static void playFallingSound() {
        if (soundEnabled) {
            Asset.fallingSound.stop();
            Asset.fallingSound.play();
        }

    }

    public static void playJumpSound() {
        if (soundEnabled) {
            Asset.jumpSound.stop();
            Asset.jumpSound.play();
        }
    }

    public static void playMonsterSound() {
        if (soundEnabled) {
            Asset.monsterSound.stop();
            Asset.monsterSound.play();
        }
    }

    public static void playPlatformBreakingSound() {
        if (soundEnabled) {
            Asset.platformBreakingSound.stop();
            Asset.platformBreakingSound.play();
        }
    }

    public static void playStartSound() {
        if (soundEnabled) {
            Asset.startSound.stop();
            Asset.startSound.play();
        }
    }


}