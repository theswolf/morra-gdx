package core.september.morra.win;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import core.september.morra.screens.DirectedGame;
import core.september.morra.screens.GameScreen;
import core.september.morra.screens.transitions.ScreenTransition;
import core.september.morra.screens.transitions.ScreenTransitionFlash;
import core.september.morra.util.InputTransform;

/**
 * Created by christian on 21/03/16.
 */
public class WinController extends InputAdapter implements Disposable {

    private static final String TAG = WinController.class.getName();

    private final int player;
    private final int playerBound;
    private final int cpu;
    private final int cpuBound;

    private DirectedGame game;
    public WinLevel level;

    private float duration;

    public WinController(DirectedGame game,int player,int cpu, int playerBound, int cpuBound) {
        this.game = game;
        this.player = player;
        this.cpu = cpu;
        this.playerBound = playerBound;
        this.cpuBound = cpuBound;
        this.duration = 0f;
        init();
    }




    private void init() {
        level = new WinLevel( player, cpu,  playerBound,  cpuBound);
    }




    public void update (float deltaTime) {
        duration += deltaTime;
        if(duration > 1f) {
            game.setScreen(new GameScreen(game), ScreenTransitionFlash.init(0.5f));
        }
        //Gdx.app.log(TAG,"WinController updating");
    }


    @Override
    public void dispose() {
        //game.dispose();
    }
}
