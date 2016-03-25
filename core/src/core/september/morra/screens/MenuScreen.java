package core.september.morra.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import javax.swing.WindowConstants;

import core.september.morra.Constants;
import core.september.morra.screens.transitions.ScreenTransition;
import core.september.morra.screens.transitions.ScreenTransitionSlice;
import core.september.morra.util.Assets;
import core.september.morra.util.GamePreferences;
import core.september.morra.util.GameScore;

/**
 * Created by christian on 24/03/16.
 */
public class MenuScreen extends AbstractGameScreen {

    private static final String TAG = MenuScreen.class.getName();

    private Stage stage;
    private Skin skin;
    private ShaderProgram shaderMonochrome;
    private Sprite backGround;
    private final float uiWidth=200f;

    private Table mainMenuTable;
    private Table levelChooseTable;
    private Window winOptions;
    private CheckBox chkSound;
    private Slider sldSound;
    private CheckBox chkMusic;
    private Slider sldMusic;


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
        renderBackground(batch, camera);
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


        //stage.addActor(button);

        winOptions = buildOptionsWindowLayer(skin);
        stage.addActor(winOptions);

        mainMenuTable  = new Table();
        mainMenuTable.setFillParent(true);
        stage.addActor(mainMenuTable);
        addToTable(mainMenuTable, playButton());
        addToTable(mainMenuTable, optionButton());

        levelChooseTable = new Table();
        levelChooseTable.setFillParent(true);
        stage.addActor(levelChooseTable);
        addToTable(levelChooseTable, levelButton(0));
        addToTable(levelChooseTable, levelButton(1));
        addToTable(levelChooseTable, levelButton(2));
        addToTable(levelChooseTable, levelButton(3));


        showFloatingActor(mainMenuTable, true, false);
        showFloatingActor(levelChooseTable, false, false);
        showFloatingActor(winOptions,false,false);

       // Gdx.input.setInputProcessor(stage); rebuildStage();
    }

    private void addToTable(Table table, Actor actor) {
        table.add(actor).width(uiWidth).padBottom(5);
        table.row();
    }

    private TextButton playButton() {
        final TextButton button = new TextButton("New Game", skin, "default");

        button.setWidth(uiWidth);
        button.setHeight(20f);
        button.setColor(Color.RED);
        button.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 10f);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //button.setText("You clicked the button");
                showFloatingActor(mainMenuTable, false, true);
                showFloatingActor(levelChooseTable, true, true);
            }
        });

        return button;

    }

    private TextButton optionButton() {
        final TextButton button = new TextButton("Game options", skin, "default");

        button.setWidth(uiWidth);
        button.setHeight(20f);
        button.setColor(Color.RED);
        button.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 10f);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loadSettings();
                showFloatingActor(mainMenuTable,false,true);
                showFloatingActor(winOptions,true,true);
            }
        });

        return button;

    }

    private TextButton levelButton(final int level) {
        String s_level=null;
        switch (level) {
            case 0:
                s_level = "easy";
                break;
            case 1:
                s_level = "medium";
                break;
            case 2:
                s_level = "hard";
                break;
            case 3:
                s_level = "maniac";
                break;
        }

        final TextButton button = new TextButton(s_level.toUpperCase(), skin, "default");

        button.setWidth(uiWidth);
        button.setHeight(20f);
        button.setColor(Color.RED);
        button.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 10f);

        //button.getStyle().font = Assets.instance.font.defaultBig;

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //button.setText("You clicked the button");
                GamePreferences.instance.gameLevel = level;
                GamePreferences.instance.save();
                ScreenTransition transition = ScreenTransitionSlice.init(2, ScreenTransitionSlice.UP_DOWN, 10, Interpolation.pow5Out);

                game.setScreen(new GameScreen(game), transition);
                //showFloatingActor(mainMenuTable, true, true);
                //showFloatingActor(levelChooseTable, false, true);
            }
        });

        return button;

    }

    private void showFloatingActor(Actor input, boolean visible, boolean animated) {
        float alphaTo = visible ? 0.8f : 0.0f;
        float duration = animated ? 1.0f : 0.0f;
        Touchable touchEnabled = visible ? Touchable.enabled
                : Touchable.disabled;
        input.addAction(sequence(touchable(touchEnabled),
                alpha(alphaTo, duration)));
    }

    @Override
    public void hide() {
        stage.dispose();
        skin.dispose();
        //skinLibgdx.dispose();
    }

    /*private void showMenuButtons(boolean visible) {
        float moveDuration = 1.0f;
        Interpolation moveEasing = Interpolation.swing;
        float delayOptionsButton = 0.25f;

        float moveX = visible ? 0 : Constants.VIEWPORT_WIDTH*2;
        float moveY = 0 * (visible ? -1 : 1);
        final Touchable touchEnabled = visible ? Touchable.enabled
                : Touchable.disabled;
        //btnMenuPlay.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
        //btnMenuOptions.addAction(sequence(Actions.delay(delayOptionsButton),
        //        moveBy(moveX, moveY, moveDuration, moveEasing)));

        mainMenuTable.addAction(sequence(Actions.delay(delayOptionsButton),
                moveBy(moveX, moveY, moveDuration, moveEasing)));

        SequenceAction seq = Actions.sequence();
        if (visible)
            seq.addAction(delay(delayOptionsButton + moveDuration));
        seq.addAction(run(new Runnable() {
            public void run() {
                //btnMenuPlay.setTouchable(touchEnabled);
                //btnMenuOptions.setTouchable(touchEnabled);
                mainMenuTable.setTouchable(touchEnabled);
            }
        }));
        stage.addAction(seq);
    }*/



    @Override
    public void pause() {
    }





    @Override
    public InputProcessor getInputProcessor() {
        return stage;
    }

    private Window buildOptionsWindowLayer(Skin skin) {
        winOptions = new Window("Options", skin);
        // + Audio Settings: Sound/Music CheckBox and Volume Slider
        winOptions.add(buildOptWinAudioSettings(skin)).row();
        // + Character Skin: Selection Box (White, Gray, Brown)
        //winOptions.add(buildOptWinSkinSelection()).row();
        // + Debug: Show FPS Counter
        //winOptions.add(buildOptWinDebug()).row();
        // + Separator and Buttons (Save, Cancel)
        winOptions.add(buildOptWinButtons(skin)).pad(10, 0, 10, 0);

        // Make options window slightly transparent
        winOptions.setColor(1, 1, 1, 0.8f);
        // Hide options window by default
        showFloatingActor(winOptions, false, false);

        // Let TableLayout recalculate widget sizes and positions
        winOptions.pack();
        // Move options window to bottom right corner
        //winOptions.setPosition(
        //        Constants.VIEWPORT_WIDTH - winOptions.getWidth() - 50, 50);
        winOptions.setPosition(
                Constants.VIEWPORT_WIDTH/2 - winOptions.getWidth()/2,
                Constants.VIEWPORT_HEIGHT/2 - winOptions.getHeight()/2
        );
        return winOptions;
    }

    private Table buildOptWinAudioSettings(Skin skin) {
        Table tbl = new Table();
        // + Title: "Audio"
        tbl.pad(10, 10, 0, 10);
        tbl.add(new Label("Audio", skin, "default-font", Color.ORANGE))
                .colspan(3);
        tbl.row();
        tbl.columnDefaults(0).padRight(10);
        tbl.columnDefaults(1).padRight(10);
        // + Checkbox, "Sound" label, sound volume slider
        chkSound = new CheckBox("", skin);
        tbl.add(chkSound);
        tbl.add(new Label("Sound", skin));
        sldSound = new Slider(0.0f, 1.0f, 0.1f, false, skin);
        tbl.add(sldSound);
        tbl.row();
        // + Checkbox, "Music" label, music volume slider
        chkMusic = new CheckBox("", skin);
        tbl.add(chkMusic);
        tbl.add(new Label("Music", skin));
        sldMusic = new Slider(0.0f, 1.0f, 0.1f, false, skin);
        tbl.add(sldMusic);
        tbl.row();

        return tbl;
    }

    private Table buildOptWinButtons(Skin skin) {
        Table tbl = new Table();
        // + Separator
        Label lbl = null;
        lbl = new Label("", skin);
        lbl.setColor(0.75f, 0.75f, 0.75f, 1);
        lbl.setStyle(new Label.LabelStyle(lbl.getStyle()));
        lbl.getStyle().background = skin.newDrawable("white");
        tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 0, 0, 1);
        tbl.row();
        lbl = new Label("", skin);
        lbl.setColor(0.5f, 0.5f, 0.5f, 1);
        lbl.setStyle(new Label.LabelStyle(lbl.getStyle()));
        lbl.getStyle().background = skin.newDrawable("white");
        tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 1, 5, 0);
        tbl.row();
        // + Save Button with event handler
        TextButton btnWinOptSave = new TextButton("Save", skin);
        tbl.add(btnWinOptSave).padRight(30);
        btnWinOptSave.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onSaveClicked();
            }
        });
        // + Cancel Button with event handler
        TextButton btnWinOptCancel = new TextButton("Cancel", skin);
        tbl.add(btnWinOptCancel);
        btnWinOptCancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onCancelClicked();
            }
        });
        return tbl;
    }

    private void onSaveClicked() {
        saveSettings();
        onCancelClicked();
        //AudioManager.instance.onSettingsUpdated();
    }

    private void onCancelClicked() {
        //showMenuButtons(true);
        //showOptionsWindow(false, true);
        showFloatingActor(mainMenuTable, true, true);
        showFloatingActor(winOptions,false,true);
        //AudioManager.instance.onSettingsUpdated();
    }

    private void loadSettings() {
        GamePreferences prefs = GamePreferences.instance;
        prefs.load();
        chkSound.setChecked(prefs.sound);
        sldSound.setValue(prefs.volSound);
        chkMusic.setChecked(prefs.music);
        sldMusic.setValue(prefs.volMusic);
        //selCharSkin.setSelectedIndex(prefs.charSkin);
        //onCharSkinSelected(prefs.charSkin);
        //chkShowFpsCounter.setChecked(prefs.showFpsCounter);
        //chkUseMonochromeShader.setChecked(prefs.useMonochromeShader);
    }

    private void saveSettings() {
        GamePreferences prefs = GamePreferences.instance;
        prefs.sound = chkSound.isChecked();
        prefs.volSound = sldSound.getValue();
        prefs.music = chkMusic.isChecked();
        prefs.volMusic = sldMusic.getValue();
        //prefs.charSkin = selCharSkin.getSelectedIndex();
        //prefs.showFpsCounter = chkShowFpsCounter.isChecked();
        //prefs.useMonochromeShader = chkUseMonochromeShader.isChecked();
        prefs.save();
    }

}