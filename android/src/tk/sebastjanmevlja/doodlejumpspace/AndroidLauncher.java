package tk.sebastjanmevlja.doodlejumpspace;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import de.golfgl.gdxgamesvcs.GpgsClient;
import tk.sebastjanmevlja.doodlejumpspace.MyGame.Game;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true; //screen stays on
		config.useAccelerometer = true;

		Game game = new Game();
		game.gsClient = new GpgsClient().initialize(this, false);
		initialize(game, config);


	}
}
