package core.september.morra.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

import core.september.morra.scoreboard.ScoreController;
import core.september.morra.scoreboard.ScoreRenderer;
import core.september.morra.util.GamePreferences;


/**
 * Created by christian on 23/03/16.
 */
public class ScoreScreen extends AbstractGameScreen{
    private static final String TAG = ScoreScreen.class.getName();


    private ScoreController scoreController;
    private ScoreRenderer scoreRenderer;

    private boolean paused;

    public ScoreScreen(DirectedGame game) {
        super(game);

    }

    @Override
    public void render(float deltaTime) {
        // Do not update game world when paused.
        if (!paused) {
            // Update game world by the time that has passed
            // since last rendered frame.
            scoreController.update(deltaTime);
        }
        // Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Render game world to screen
        scoreRenderer.render();
    }


    @Override
    public void resize(int width, int height) {
        scoreRenderer.resize(width, height);
    }

    @Override
    public void show() {
        GamePreferences.instance.load();
        scoreController = new ScoreController(game);
        scoreRenderer = new ScoreRenderer(scoreController);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void hide() {
        GamePreferences.instance.save();
        scoreController.dispose();
        scoreRenderer.dispose();
        Gdx.input.setCatchBackKey(false);
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        super.resume();
        // Only called on Android!
        paused = false;
    }

    @Override
    public InputProcessor getInputProcessor() {
        return scoreController;
    }
}
