package core.september.morra.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import core.september.morra.Constants;
import core.september.morra.game.GameLevel;

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
    public Array<Match> matches = new Array<Match>();

    private Json json;


    // singleton: prevent instantiation from other classes
    private GameScore () {
        prefs = Gdx.app.getPreferences(Constants.SCORE);

    }

    private Json getJson() {
        if(json == null) {
            json = new Json();
        }
        return json;
    }

    public void load () {
        winInARow = prefs.getInteger("winInARow",0);
        wins = prefs.getInteger("wins",0);
        loose = prefs.getInteger("loose", 0);
        //String matches = json.toJson(person);
        matches  = getJson().fromJson(Array.class, prefs.getString("matches"));
        if(matches == null) matches = new Array<Match>();
    }

    public void save () {
        prefs.putInteger("winInARow",winInARow);
        prefs.putInteger("wins",wins);
        prefs.putInteger("loose",loose);
        prefs.putString("matches",getJson().toJson(matches));
        prefs.flush();

    }

    public void win() {
        load();
        winInARow = winInARow+1;
        wins = wins+1;
        matches.add(new Match(true));
        save();
    }

    public void loose() {
        load();
        winInARow = 0;
        loose = loose+1;
        matches.add(new Match(false));
        save();
    }

    public void resetMatches() {
        load();
        matches = new Array<Match>();
        save();
    }

    public void nextRound() {

        int put = (prefs.getInteger("round")+1)% GameLevel.matches;
        prefs.putInteger("round",put);
        Gdx.app.log(TAG,"Putting "+put);
    }

    public boolean hasNextRound() {

        Gdx.app.log(TAG,"Has next round "+prefs.getInteger("round",0));
        return prefs.getInteger("round",0) < GameLevel.matches - 1;
    }
}
