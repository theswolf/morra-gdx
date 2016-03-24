package core.september.morra.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import core.september.morra.Constants;
import core.september.morra.util.Assets;

/**
 * Created by christian on 24/03/16.
 */
public class MenuScreen extends AbstractGameScreen {

    private static final String TAG = MenuScreen.class.getName();

    private Stage stage;
    private Skin skin;
    private ShaderProgram shaderMonochrome;
    private Sprite backGround;

    public MenuScreen(DirectedGame game) {
        super(game);


    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(deltaTime);
        Batch batch = stage.getBatch();
        Camera camera = stage.getCamera();
        renderBackground(batch,camera);
        stage.draw();

        //Table.drawDebug(stage);
    }

    private void renderBackground(Batch batch, Camera camera) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
		/*if (GamePreferences.instance.useMonochromeShader) {
			batch.setShader(shaderMonochrome);
			shaderMonochrome.setUniformf("u_amount", 1.0f);
		}*/
        batch.setShader(shaderMonochrome);
        shaderMonochrome.setUniformf("u_amount", 1.0f);
        backGround.draw(batch);
        batch.setShader(null);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constants.VIEWPORT_WIDTH,
                Constants.VIEWPORT_HEIGHT));
       //batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        stage = new Stage();

        backGround = new Sprite(Assets.instance.background.region,
                0,
                (Assets.instance.background.region.getRegionHeight() - Constants.VIEWPORT_HEIGHT)/2,
                Constants.VIEWPORT_WIDTH,
                Constants.VIEWPORT_HEIGHT
        );

        shaderMonochrome = new ShaderProgram(Gdx.files.internal(Constants.shaderMonochromeVertex),
                Gdx.files.internal(Constants.shaderMonochromeFragment));
        if (!shaderMonochrome.isCompiled()) {
            String msg = "Could not compile shader program: " + shaderMonochrome.getLog();
            throw new GdxRuntimeException(msg);
        }

        final TextButton button = new TextButton("Click me", skin, "default");

        button.setWidth(200f);
        button.setHeight(20f);
        button.setColor(Color.RED);
        button.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 10f);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                button.setText("You clicked the button");
            }
        });

        stage.addActor(button);

       // Gdx.input.setInputProcessor(stage); rebuildStage();
    }

    @Override
    public void hide() {
        stage.dispose();
        skin.dispose();
        //skinLibgdx.dispose();
    }

    @Override
    public void pause() {
    }





    @Override
    public InputProcessor getInputProcessor() {
        return stage;
    }

}