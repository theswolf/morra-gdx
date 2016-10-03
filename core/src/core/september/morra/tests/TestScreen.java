package core.september.morra.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

import core.september.morra.scoreboard.ScoreController;
import core.september.morra.scoreboard.ScoreRenderer;
import core.september.morra.screens.AbstractGameScreen;
import core.september.morra.screens.DirectedGame;
import core.september.morra.screens.ScoreScreen;
import core.september.morra.util.GamePreferences;
import core.september.morra.util.GameScore;


/**
 * Created by christian on 23/03/16.
 */
public class TestScreen extends ScoreScreen {
    private static final String TAG = TestScreen.class.getName();


    public TestScreen(DirectedGame game) {
        super(game);
        GameScore.instance.resetMatches();
        GameScore.instance.win();
        GameScore.instance.loose();
        GameScore.instance.win();
        GameScore.instance.win();
        GameScore.instance.loose();
        GameScore.instance.win();
        GameScore.instance.win();
        GameScore.instance.loose();
        GameScore.instance.win();

    }
}
