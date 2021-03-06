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
    public int totalwins;
    public int totalloose;
    public int round;
    //public Array<Match> matches ;//= new Array<Match>();

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
        totalwins = prefs.getInteger("totalwins",0);
        totalloose = prefs.getInteger("totalloose", 0);
        String _matches = prefs.getString("matches","[]");
        //matches  = getJson().fromJson(Array.class, _matches);
        //Gdx.app.log(TAG,String.format("Loaded %s matches",_matches));
        //if(matches == null) matches = new Array<Match>();
    }

    public void save () {
        prefs.putInteger("winInARow",winInARow);
        prefs.putInteger("wins",wins);
        prefs.putInteger("loose",loose);
        prefs.putInteger("totalwins",totalwins);
        prefs.putInteger("totalloose",totalloose);
        //String _matches = getJson().toJson(matches == null ? new Array<Match>() : matches);
        //prefs.putString("matches",_matches);
        ////Gdx.app.log(TAG,String.format("Stored %s matches",_matches));
        prefs.flush();

    }

    public void win() {
        load();
        winInARow = winInARow+1;
        wins = wins+1;
        totalwins +=1;
        //matches.add(new Match(true));
        save();
    }

    public void loose() {
        load();
        winInARow = 0;
        loose = loose+1;
        totalloose+=1;
        //matches.add(new Match(false));
        save();
    }

    public void resetMatches() {
        load();
        wins=0;
        loose=0;
        //matches = new Array<Match>();
        //matches.clear();
        save();
    }



    public boolean hasNextRound() {

        //Gdx.app.log(TAG,"Has next round "+prefs.getInteger("round",0));
        Gdx.app.log(TAG,String.format("Round %s of %s",wins+loose,GamePreferences.instance.getMatches()));
        return (wins + loose) < GamePreferences.instance.getMatches();
    }
}
