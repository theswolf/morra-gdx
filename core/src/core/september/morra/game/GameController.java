package core.september.morra.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import core.september.morra.screens.DirectedGame;
import core.september.morra.util.InputTransform;

/**
 * Created by christian on 21/03/16.
 */
public class GameController extends InputAdapter implements Disposable {

    private static final String TAG = GameController.class.getName();

    private DirectedGame game;
    public GameLevel level;
    private Vector2 touch;

    public GameController (DirectedGame game) {
        this.game = game;
        init();
    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        //Gdx.app.log(TAG,String.format("int screenX %d, int screenY %d, int pointer %d, int button %d",screenX, screenY, pointer, button));
        touch = InputTransform.point(screenX,screenY);
       // Gdx.app.log(TAG, "Touched " + touch);

        return true;
    }

    public void handleTouch() {
        if(touch != null) {
            level.touched(touch);
            //Gdx.app.log(TAG, null != touched ? touched.toString() : "Untuoched" );
            touch = null;
        }

    }


    private void init() {
        touch = null;
        level = new GameLevel();
    }




    public void update (float deltaTime) {
        handleTouch();
        level.update(deltaTime);
    }


    @Override
    public void dispose() {
        
    }
}
