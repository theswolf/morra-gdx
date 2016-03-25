package core.september.morra.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

import core.september.morra.Constants;

/**
 * Created by christian on 25/03/16.
 */
public class GameScore {

    public static final String TAG = GameScore.class.getName();
    public static final GameScore instance = new GameScore();

    private Preferences prefs;

    public int winInARow;
    public int wins;
    public int loose;
    public int round;

    // singleton: prevent instantiation from other classes
    private GameScore () {
        prefs = Gdx.app.getPreferences(Constants.SCORE);

    }

    public void load () {
        winInARow = prefs.getInteger("winInARow",0);
        wins = prefs.getInteger("wins",0);
        loose = prefs.getInteger("loose", 0);
    }

    public void save () {
        prefs.putInteger("winInARow",winInARow);
        prefs.putInteger("wins",wins);
        prefs.putInteger("loose",loose);
        prefs.flush();
    }

    public void win() {
        winInARow = winInARow+1;
        wins = wins+1;
        save();
    }

    public void loose() {
        winInARow = 0;
        loose = loose+1;
        save();
    }

    public void nextRound() {
        prefs.putInteger("round",prefs.getInteger("round")+1);
    }

    public boolean hasNextRound() {
        return prefs.getInteger("round",0) < 3;
    }
}