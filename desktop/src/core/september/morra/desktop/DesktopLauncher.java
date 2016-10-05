package core.september.morra.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import core.september.morra.Constants;
import core.september.morra.MorraGame;
import core.september.morra.util.PlayServices;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1600;
		new LwjglApplication(new MorraGame(new PlayServices() {
			@Override
			public void signIn() {

			}

			@Override
			public void signOut() {

			}

			@Override
			public void rateGame() {

			}

			@Override
			public void unlockAchievement() {

			}

			@Override
			public void submitScore(int highScore) {

			}

			@Override
			public void showAchievement() {

			}

			@Override
			public void showScore() {

			}

			@Override
			public boolean isSignedIn() {
				return false;
			}
		}), config);
	}
}
