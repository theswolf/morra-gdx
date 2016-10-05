package core.september.morra.win;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import core.september.morra.Constants;

/**
 * Created by christian on 21/03/16.
 */
public class WinRenderer implements Disposable {

    private OrthographicCamera camera;
    private Viewport viewport;
    private WinController controller;
    private SpriteBatch batch;
    private SpriteBatch colorbatch;
    private ShaderProgram shaderMonochrome;

    public WinRenderer(WinController controller) {
        this.controller=controller;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        colorbatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, camera);
        viewport.update(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT,true);
        shaderMonochrome = new ShaderProgram(Gdx.files.internal(Constants.shaderMonochromeVertex),
                Gdx.files.internal(Constants.shaderMonochromeFragment));
        if (!shaderMonochrome.isCompiled()) {
            String msg = "Could not compile shader program: " + shaderMonochrome.getLog();
            throw new GdxRuntimeException(msg);
        }
    }

    public void render() {
        renderWorld(batch);
        renderColorized(colorbatch);
    }

    private void renderWorld (SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
		/*if (GamePreferences.instance.useMonochromeShader) {
			batch.setShader(shaderMonochrome);
			shaderMonochrome.setUniformf("u_amount", 1.0f);
		}*/
        batch.setShader(shaderMonochrome);
        shaderMonochrome.setUniformf("u_amount", 1.0f);
        controller.level.render(batch);
        //batch.setShader(null);
        batch.end();



    }

    private void renderColorized(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
		/*if (GamePreferences.instance.useMonochromeShader) {
			batch.setShader(shaderMonochrome);
			shaderMonochrome.setUniformf("u_amount", 1.0f);
		}*/
        controller.level.renderColorized(batch);
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
