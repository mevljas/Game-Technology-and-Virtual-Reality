package tk.sebastjanmevlja.doodlejumpspace.Screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import tk.sebastjanmevlja.doodlejumpspace.Helpers.Assets;
import tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants;
import tk.sebastjanmevlja.doodlejumpspace.MyGame.Game;


public class AboutScreen implements Screen {

    private final Game game;
    private final Stage stage;


    public AboutScreen(final Game game) {
        this.game = game;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Skin skin = Assets.skin;

        // Create a table that fills the screen. Everything else will go inside
        // this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.align(Align.center);


        // return to main screen button
        final TextButton backButton = new TextButton("Back", skin);
        final TextButton retryButton = new TextButton("Continue", skin);
        TextButton.TextButtonStyle textButtonStyle = retryButton.getStyle();
        textButtonStyle.font = Assets.fontMedium;
        retryButton.setStyle(textButtonStyle);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AboutScreen.this.game.changeScreen(Screens.MENUSCREEN);

            }
        });

        Label titleLabel = new Label("About", skin, "title");
        Label.LabelStyle LabelStyleBig = titleLabel.getStyle();
        LabelStyleBig.font = Assets.fontBig;
        titleLabel.setStyle(LabelStyleBig);
        titleLabel.setAlignment(Align.center);


        Label label = new Label("Gameteam presents " +
                "Doodle Jump Space " +
                "featuring Sebastjan Mevlja. " +
                "Done within TINR course. " +
                "Faculty of Computer and Information Science, " +
                "University of Ljubljana, " +
                "Slovenia, " +
                "2021.\n" +
                "\n" +
                "Sound effects from Zapsplat.com.\n" +
                "Skin from Raymond \"Raeleus\" Buckley.\n" +
                "Game textures by Pompam on itch.io.\n" +
                "Music by Benjamin Tissot.", skin);
        label.setWrap(true);
        Label.LabelStyle LabelStyleSmall = titleLabel.getStyle();
        LabelStyleSmall.font = Assets.fontVerySmall;
        label.setStyle(LabelStyleSmall);


        table.defaults().width(Value.percentWidth(.100F, table));
        table.defaults().height(Value.percentHeight(.10F, table));

        table.add(titleLabel).center().width(Value.percentWidth(.30F, table));
        table.row().padTop(Value.percentHeight(.05F, table));
        table.add(new Image(Assets.atlas.findRegion("logo"))).width(Value.percentWidth(.30F, table)).height(Value.percentWidth(.30F, table)).center();
        table.row().padTop(Value.percentHeight(.2F, table));
        table.add(label).width(Value.percentWidth(.90F, table));
        table.row().padTop(Value.percentHeight(.2F, table));
        table.add(backButton).center().width(Value.percentWidth(.50F, table));
    }

    @Override
    public void show() {
        //stage.clear();
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.changeScreen(Screens.MENUSCREEN);
        }

        Batch gameBatch = game.getBatch();

        gameBatch.begin(); //kdr zacenmo rendirat klicemo begin
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameBatch.draw(Assets.background, 0, 0, Constants.WIDTH, Constants.HEIGHT);
        gameBatch.end();

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();

    }

}
