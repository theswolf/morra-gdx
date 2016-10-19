package core.september.morra.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import core.september.morra.Constants;

/**
 * Created by christian on 21/03/16.
 */
public class GameRenderer implements Disposable {

    private OrthographicCamera camera;
    public  Viewport viewport;
    private GameController controller;
    private SpriteBatch batch;

    public GameRenderer(GameController controller) {
        this.controller=controller;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, camera);
        viewport.update(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT,true);
    }

    public void render() {
        renderWorld(batch);
    }

    private void renderWorld (SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
		/*if (GamePreferences.instance.useMonochromeShader) {
			batch.setShader(shaderMonochrome);
			shaderMonochrome.setUniformf("u_amount", 1.0f);
		}*/
        controller.level.render(batch);
        batch.setShader(null);
        batch.end();

    }

    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
