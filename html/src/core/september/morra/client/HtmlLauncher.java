package core.september.morra.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import core.september.morra.MorraGame;
import core.september.morra.util.PlayServices;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new MorraGame(new PlayServices() {
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
                });
        }
}