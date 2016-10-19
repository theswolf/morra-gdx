package core.september.morra.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

import core.september.morra.game.GameController;
import core.september.morra.game.GameRenderer;
import core.september.morra.util.GamePreferences;
import core.september.morra.win.WinController;
import core.september.morra.win.WinRenderer;

/**
 * Created by christian on 23/03/16.
 */
public class WinScreen extends AbstractGameScreen{
    private static final String TAG = WinScreen.class.getName();
    private final int player;
    private final int playerBound;
    private final int cpu;
    private final int cpuBound;

    private WinController winController;
    private WinRenderer winRenderer;

    private boolean paused;

    public WinScreen(DirectedGame game,int player,int cpu, int playerBound, int cpuBound) {
        super(game);
        this.player = player;
        this.cpu = cpu;
        this.playerBound = playerBound;
        this.cpuBound = cpuBound;
    }

    @Override
    public void render(float deltaTime) {
        // Do not update game world when paused.
        if (!paused) {
            // Update game world by the time that has passed
            // since last rendered frame.
            winController.update(deltaTime);
        }
        // Sets the clear screen color to: Cornflower Blue
        //Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Render game world to screen
        winRenderer.render();
    }


    @Override
    public void resize(int width, int height) {
        winRenderer.resize(width, height);
    }

    @Override
    public void show() {
        GamePreferences.instance.load();
        winController = new WinController(game, player, cpu,  playerBound,  cpuBound);
        winRenderer = new WinRenderer(winController);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void hide() {
        GamePreferences.instance.save();
        winController.dispose();
        winRenderer.dispose();
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
        return winController;
    }
}
