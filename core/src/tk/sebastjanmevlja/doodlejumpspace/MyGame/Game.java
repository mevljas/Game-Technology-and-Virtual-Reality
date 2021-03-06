package tk.sebastjanmevlja.doodlejumpspace.MyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import de.golfgl.gdxgamesvcs.IGameServiceClient;
import de.golfgl.gdxgamesvcs.IGameServiceListener;
import de.golfgl.gdxgamesvcs.MockGameServiceClient;
import de.golfgl.gdxgamesvcs.achievement.IAchievement;
import de.golfgl.gdxgamesvcs.leaderboard.ILeaderBoardEntry;
import tk.sebastjanmevlja.doodlejumpspace.Gameplay.Player;
import tk.sebastjanmevlja.doodlejumpspace.Helpers.Assets;
import tk.sebastjanmevlja.doodlejumpspace.Helpers.LocalStorage;
import tk.sebastjanmevlja.doodlejumpspace.Screen.AboutScreen;
import tk.sebastjanmevlja.doodlejumpspace.Screen.EndScreen;
import tk.sebastjanmevlja.doodlejumpspace.Screen.Level1Screen;
import tk.sebastjanmevlja.doodlejumpspace.Screen.LoadingScreen;
import tk.sebastjanmevlja.doodlejumpspace.Screen.MenuScreen;
import tk.sebastjanmevlja.doodlejumpspace.Screen.PauseScreen;
import tk.sebastjanmevlja.doodlejumpspace.Screen.PreferencesScreen;
import tk.sebastjanmevlja.doodlejumpspace.Screen.Screens;


public class Game extends com.badlogic.gdx.Game implements IGameServiceListener {


    public static Game game;
    public static LocalStorage localStorage;
    public IGameServiceClient gsClient;
    public Level1Screen level1Screen;
    public Assets assets;
    private SpriteBatch batch;
    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private AboutScreen aboutScreen;
    private EndScreen endScreen;
    private PauseScreen pauseScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        game = this;
        assets = new Assets();
        assets.loadGame();

//        Gdx.input.setCatchBackKey(true); //back key doesnt the app close - deprecated
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        localStorage = new LocalStorage();

//        For desktop client
        if (gsClient == null)
            gsClient = new MockGameServiceClient(1) {
                @Override
                protected Array<ILeaderBoardEntry> getLeaderboardEntries() {
                    return null;
                }

                @Override
                protected Array<String> getGameStates() {
                    return null;
                }

                @Override
                protected byte[] getGameState() {
                    return new byte[0];
                }

                @Override
                protected Array<IAchievement> getAchievements() {
                    return null;
                }

                @Override
                protected String getPlayerName() {
                    return null;
                }
            };

        // for getting callbacks from the client
        gsClient.setListener(this);

        // establish a connection to the game service without error messages or login screens
        gsClient.resumeSession();


        changeScreen(Screens.LOADINGSCREEN);

    }


    public void changeScreen(Screens screen) {
        switch (screen) {

            case LOADINGSCREEN:
                if (loadingScreen == null)
                    loadingScreen = new LoadingScreen(this);
                setScreen(loadingScreen);
                break;
            case MENUSCREEN:
                if (menuScreen == null)
                    menuScreen = new MenuScreen(this);
                setScreen(menuScreen);
                break;
            case PREFERENCESSCREEN:
                if (preferencesScreen == null)
                    preferencesScreen = new PreferencesScreen(this);
                setScreen(preferencesScreen);
                break;
            case LEVEL1SCREEN:
                if (level1Screen != null && !Level1Screen.paused) {
                    level1Screen.dispose();
                    level1Screen = null;
                }
                if (level1Screen == null) {
                    level1Screen = new Level1Screen();
                }


                setScreen(level1Screen);
                break;
            case ABOUTSCREEN:
                if (aboutScreen == null)
                    aboutScreen = new AboutScreen(this);
                setScreen(aboutScreen);
                break;
            case ENDSCREEN:
                if (endScreen == null)
                    endScreen = new EndScreen(this);
                setScreen(endScreen);
                break;
            case PAUSESCREEN:
                if (pauseScreen == null)
                    pauseScreen = new PauseScreen(this);
                setScreen(pauseScreen);
                break;

        }
    }

    @Override
    public void render() {
        super.render();
    }


    @Override
    public void dispose() {

        batch.dispose();
        assets.dispose();
        level1Screen.dispose();
        aboutScreen.dispose();
        endScreen.dispose();
        loadingScreen.dispose();
        pauseScreen.dispose();
        preferencesScreen.dispose();
        aboutScreen.dispose();
    }


    public SpriteBatch getBatch() {
        return this.batch;
    }


    @Override
    public void pause() {
        super.pause();

        gsClient.pauseSession();
    }

    @Override
    public void resume() {
        super.resume();

        gsClient.resumeSession();
    }


    @Override
    public void gsOnSessionActive() {
        if (getScreen() == menuScreen) {
            menuScreen.show();
        }
    }

    @Override
    public void gsOnSessionInactive() {
        if (getScreen() == menuScreen) {
            menuScreen.show();
            if (Player.player != null)
                Player.player.clearScore();
        }
    }

    @Override
    public void gsShowErrorToUser(GsErrorType et, String msg, Throwable t) {
        System.out.println(msg);
    }
}
