package core.september.morra;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Interpolation;

import core.september.morra.screens.DirectedGame;
import core.september.morra.screens.GameScreen;
import core.september.morra.screens.MenuScreen;
import core.september.morra.screens.transitions.ScreenTransition;
import core.september.morra.screens.transitions.ScreenTransitionSlice;
import core.september.morra.util.Assets;
import core.september.morra.util.AudioManager;
import core.september.morra.util.GamePreferences;

public class MorraGame extends DirectedGame {
	@Override

	public void create () {

		// Set Libgdx log level

		Gdx.app.setLogLevel(Application.LOG_DEBUG);



		// Load assets

		Assets.instance.init(new AssetManager());



		// Load preferences for audio settings and start playing music

		GamePreferences.instance.load();

		AudioManager.instance.play(Assets.instance.music.soundtrack);



		// Start game at menu screen

		ScreenTransition transition = ScreenTransitionSlice.init(2, ScreenTransitionSlice.UP_DOWN, 10, Interpolation.pow5Out);

		setScreen(new MenuScreen(this), transition);

	}
}
