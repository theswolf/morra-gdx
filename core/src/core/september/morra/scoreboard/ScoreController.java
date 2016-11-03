package core.september.morra.scoreboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Disposable;

import core.september.morra.screens.DirectedGame;
import core.september.morra.screens.GameScreen;
import core.september.morra.screens.MenuScreen;
import core.september.morra.screens.transitions.ScreenTransitionFlash;
import core.september.morra.screens.transitions.ScreenTransitionSlide;
import core.september.morra.util.GameScore;

/**
 * Created by christian on 21/03/16.
 */
public class ScoreController extends InputAdapter implements Disposable {

    private static final String TAG = ScoreController.class.getName();



    private DirectedGame game;
    public ScoreLevel level;

    private float duration;

    public ScoreController(DirectedGame game) {
        this.game = game;

        this.duration = 0f;
        init();
    }




    private void init() {
        level = new ScoreLevel();
    }




    public void update (float deltaTime) {
        duration += deltaTime;
        if(duration > 1f) {
           int score = GameScore.instance.totalwins * GameScore.instance.winInARow + GameScore.instance.wins * 10;
            game.playServices.submitScore(score);
            Gdx.app.debug(this.getClass().getSimpleName(),String.format(
                    "GameScore.instance.totalwins %s * GameScore.instance.winInARow %s + GameScore.instance.wins %s* 10",
                    GameScore.instance.totalwins,GameScore.instance.winInARow, GameScore.instance.wins));
            game.setScreen(new MenuScreen(game), null);//ScreenTransitionSlide.init(0.75f, ScreenTransitionSlide.DOWN, false, Interpolation.bounceOut));

        }
        ////Gdx.app.log(TAG,"WinController updating");
    }


    @Override
    public void dispose() {
        //game.dispose();
    }
}
