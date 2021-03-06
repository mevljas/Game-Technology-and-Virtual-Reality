package tk.sebastjanmevlja.doodlejumpspace.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {


    //    Assets
    public static Texture background;
    public static TextureAtlas atlas;
    public static Skin skin;
    public static Music backgroundMusic;
    public static Music fallingSound;
    public static Music jumpSound;
    public static Music monsterSound;
    public static Music platformBreakingSound;
    public static Music startSound;
    public static Music shieldSound;
    public static Music bulletSound;
    public static Music blackHoleSound;
    public static Music jetpackSound;
    public static Music magnetoSound;
    public static Music slimeSound;
    public static BitmapFont fontSmall;
    public static BitmapFont fontVerySmall;
    public static BitmapFont fontHud;
    public static BitmapFont fontMedium;
    public static BitmapFont fontBig;

    //    Assets descriptors
    private final AssetDescriptor<TextureAtlas> atlasDescriptor = new AssetDescriptor<>("images/Doodle_Jump_space.atlas", TextureAtlas.class);
    private final AssetDescriptor<Texture> backgroundImageDescriptor = new AssetDescriptor<>("images/loadingBackground.png", Texture.class);
    private final AssetDescriptor<Skin> skinDescriptor = new AssetDescriptor<>("skin/flat-earth-ui.json", Skin.class);
    private final AssetDescriptor<Music> backgroundMusicDescriptor = new AssetDescriptor<>("sounds/backgroundMusic.mp3", Music.class);
    private final AssetDescriptor<Music> fallingSoundDescriptor = new AssetDescriptor<>("sounds/fallingSound.mp3", Music.class);
    private final AssetDescriptor<Music> jumpSoundDescriptor = new AssetDescriptor<>("sounds/jumpSound.wav", Music.class);
    private final AssetDescriptor<Music> monsterSoundDescriptor = new AssetDescriptor<>("sounds/monsterSound.mp3", Music.class);
    private final AssetDescriptor<Music> platformBreakingSoundDescriptor = new AssetDescriptor<>("sounds/platformBreakingSound.wav", Music.class);
    private final AssetDescriptor<Music> startSoundDescriptor = new AssetDescriptor<>("sounds/startSound.wav", Music.class);
    private final AssetDescriptor<Music> shieldSoundDescriptor = new AssetDescriptor<>("sounds/shieldSound.mp3", Music.class);

    private final AssetDescriptor<Music> bulletSoundDescriptor = new AssetDescriptor<>("sounds/bulletSound.mp3", Music.class);
    private final AssetDescriptor<Music> blackHoleSoundDescriptor = new AssetDescriptor<>("sounds/bulletSound.mp3", Music.class);
    private final AssetDescriptor<Music> jetpackDescriptor = new AssetDescriptor<>("sounds/jetpackSound.mp3", Music.class);
    private final AssetDescriptor<Music> magnetoSoundDescriptor = new AssetDescriptor<>("sounds/magnetoSound.mp3", Music.class);
    private final AssetDescriptor<Music> slimeSoundDescriptor = new AssetDescriptor<>("sounds/slimeSound.mp3", Music.class);
    private final AssetManager assetManager = new AssetManager();

    public Assets() {
    }

    public void loadGame() {
        assetManager.load(skinDescriptor);
        assetManager.load(backgroundImageDescriptor);
        assetManager.load(backgroundMusicDescriptor);
        assetManager.load(fallingSoundDescriptor);
        assetManager.load(jumpSoundDescriptor);
        assetManager.load(monsterSoundDescriptor);
        assetManager.load(platformBreakingSoundDescriptor);
        assetManager.load(startSoundDescriptor);
        assetManager.load(shieldSoundDescriptor);
        assetManager.load(bulletSoundDescriptor);
        assetManager.load(blackHoleSoundDescriptor);
        assetManager.load(jetpackDescriptor);
        assetManager.load(magnetoSoundDescriptor);
        assetManager.load(slimeSoundDescriptor);

        assetManager.finishLoading();

        background = assetManager.get(backgroundImageDescriptor);
        skin = assetManager.get(skinDescriptor);
        backgroundMusic = assetManager.get(backgroundMusicDescriptor);
        fallingSound = assetManager.get(fallingSoundDescriptor);
        jumpSound = assetManager.get(jumpSoundDescriptor);
        monsterSound = assetManager.get(monsterSoundDescriptor);
        platformBreakingSound = assetManager.get(platformBreakingSoundDescriptor);
        startSound = assetManager.get(startSoundDescriptor);
        shieldSound = assetManager.get(shieldSoundDescriptor);
        bulletSound = assetManager.get(bulletSoundDescriptor);
        blackHoleSound = assetManager.get(blackHoleSoundDescriptor);
        jetpackSound = assetManager.get(jetpackDescriptor);
        magnetoSound = assetManager.get(magnetoSoundDescriptor);
        slimeSound = assetManager.get(slimeSoundDescriptor);


        initFonts();
    }

    private void initFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DoodleJump.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = (int) (Constants.HEIGHT * 0.04);
        fontSmall = generator.generateFont(parameter);

        parameter.size = (int) (Constants.HEIGHT * 0.024);
        fontVerySmall = generator.generateFont(parameter);

        parameter.size = (int) (Constants.HEIGHT * 0.055);
        fontMedium = generator.generateFont(parameter);

        parameter.size = (int) (Constants.HEIGHT * 0.07);
        fontBig = generator.generateFont(parameter);

        parameter.size = (int) (Constants.HEIGHT * 0.035);
        fontHud = generator.generateFont(parameter);
        generator.dispose();
    }

    public void loadAtlas() {
        assetManager.load(atlasDescriptor);
    }

    public void getAtlas() {
        atlas = assetManager.get(atlasDescriptor);
    }

    public void dispose() {
        assetManager.dispose();
        fontBig.dispose();
        fontHud.dispose();
        fontMedium.dispose();
        fontSmall.dispose();
        fontVerySmall.dispose();
    }

    public boolean update() {
        return assetManager.update();
    }

    public float getProgress() {
        return assetManager.getProgress();
    }


}
