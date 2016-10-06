package core.september.morra.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import core.september.morra.Constants;
import core.september.morra.screens.transitions.ScreenTransition;
import core.september.morra.screens.transitions.ScreenTransitionSlice;
import core.september.morra.util.Assets;
import core.september.morra.util.AudioManager;
import core.september.morra.util.GamePreferences;
import core.september.morra.util.GameScore;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.touchable;

/**
 * Created by christian on 24/03/16.
 */
public class OldMenuScreen extends AbstractGameScreen {

    private static final String TAG = OldMenuScreen.class.getName();

    private Stage stage;
    private Skin uiSkin;
    private Skin gameSkin;
    private CheckBox chkSound;
    private Table layerBackground;
    private Table layerControls;
    private Table layerChoose;
    private Table layerOptionsWindow;
    private Table levelChoose;
    private Slider sldSound;
    private CheckBox chkMusic;
    private Slider sldMusic;
    private Viewport viewport;


    public OldMenuScreen(DirectedGame game) {
        super(game);
        GameScore.instance.resetMatches();
        init();
    }

    private void init() {
        SpriteBatch batch = new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, camera);
        viewport.update(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT,true);
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(deltaTime);
        stage.draw();
    }

    /*TextureAtlas atlas = ...
    Skin skin = new Skin();
    skin.addRegions(atlas);
    ...
    TextureRegion hero = skin.get("hero", TextureRegion.class);*/

    @Override
    public void show() {
        stage = new Stage(viewport);
        rebuildStage();
    }




    private void rebuildStage() {
        uiSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        gameSkin = new Skin();
        TextureAtlas atlas = Assets.instance.atlas;
        gameSkin.addRegions(atlas);
        // build all layers
         layerBackground = buildBackgroundLayer();
        //Table layerObjects = buildObjectsLayer();
        //Table layerLogos = buildLogosLayer();
         layerControls = buildControlsLayer();
         layerChoose = buildLevelChooseLayer();
         layerOptionsWindow = buildOptionsWindowLayer();
        // assemble stage for menu screen
        stage.clear();
        Stack stack = new Stack();
        stage.addActor(stack);
        stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
        stack.add(layerBackground);
        //stack.add(layerObjects);
        //stack.add(layerLogos);
        stack.add(layerControls);
        stack.add(layerChoose);
        //stage.addActor(layerOptionsWindow);
        stack.addActor(layerOptionsWindow);
        showFloatingActor(layerControls, true, false);
        showFloatingActor(layerChoose, false, false);
        showFloatingActor(layerOptionsWindow,false,false);
    }

    private Table buildBackgroundLayer() {
        Table layer = new Table();
        // + Background
        Image imgBackground = new Image(gameSkin, "background");
        layer.add(imgBackground);
        return layer;
    }

    private Table buildLevelChooseLayer() {
        levelChoose = new Table();
        levelChoose.center().center();
        levelChoose.addActor(levelButton(0));
        levelChoose.row();
        levelChoose.addActor(levelButton(1));
        levelChoose.row();
        levelChoose.addActor(levelButton(2));
        levelChoose.row();
        levelChoose.addActor(levelButton(3));
        levelChoose.row();

        return levelChoose;
    }

    private Table buildControlsLayer() {
        final Table layer = new Table();
        layer.center().center();
        // + Play Button
        Button btnMenuPlay = new TextButton("New Game", uiSkin, "default");
        layer.add(btnMenuPlay);
        btnMenuPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showFloatingActor(layerControls, false, true);
                showFloatingActor(levelChoose, true, true);
            }
        });
        layer.row();
        // + Options Button
        Button btnMenuOptions = new TextButton("Game options", uiSkin, "default");
        layer.add(btnMenuOptions);
        btnMenuOptions.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                loadSettings();
                showFloatingActor(layerControls,false,true);
                showFloatingActor(layerOptionsWindow,true,true);
            }
        });

        layer.row();

        Button btnMenuOptionsScore = new TextButton("Score", uiSkin, "default");
        layer.add(btnMenuOptionsScore);

        btnMenuOptionsScore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                OldMenuScreen.this.game.playServices.showScore();
            }
        });

        return layer;
    }





    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        //stage.setViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT, false);
    }






    private Button levelButton(final int level) {
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

        Button button = new TextButton(s_level.toUpperCase(), uiSkin, "default");

        //button.setWidth(uiWidth);
        //button.setHeight(20f);
        button.setColor(Color.RED);
        //button.setPosition(Gdx.graphics.getWidth() / 2 - 100f, Gdx.graphics.getHeight() / 2 - 10f);

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
        uiSkin.dispose();
        gameSkin.dispose();
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



    private Table buildOptionsWindowLayer() {
        //Table layer = new Table();
        Window winOptions = new Window("Options", uiSkin);
        // + Audio Settings: Sound/Music CheckBox and Volume Slider
        winOptions.add(buildOptWinAudioSettings()).row();
        // + Character Skin: Selection Box (White, Gray, Brown)
        //winOptions.add(buildOptWinSkinSelection()).row();
        // + Debug: Show FPS Counter
        //winOptions.add(buildOptWinDebug()).row();
        // + Separator and Buttons (Save, Cancel)
        winOptions.add(buildOptWinButtons()).pad(10, 0, 10, 0);
        // Make options window slightly transparent
        winOptions.setColor(1, 1, 1, 0.8f);
        // Hide options window by default
        winOptions.setVisible(false);

        // Let TableLayout recalculate widget sizes and positions
        winOptions.pack();
        // Move options window to bottom right corner
        winOptions.center().center();
        return winOptions;
    }

    private Table buildOptWinAudioSettings() {
        Table tbl = new Table();
        // + Title: "Audio"
        tbl.pad(10, 10, 0, 10);
        tbl.add(new Label("Audio", uiSkin, "default-font", Color.ORANGE))
                .colspan(3);
        tbl.row();
        tbl.columnDefaults(0).padRight(10);
        tbl.columnDefaults(1).padRight(10);
        // + Checkbox, "Sound" label, sound volume slider
        chkSound = new CheckBox("", uiSkin);
        tbl.add(chkSound);
        tbl.add(new Label("Sound", uiSkin));
        sldSound = new Slider(0.0f, 1.0f, 0.1f, false, uiSkin);
        tbl.add(sldSound);
        tbl.row();
        // + Checkbox, "Music" label, music volume slider
        chkMusic = new CheckBox("", uiSkin);
        tbl.add(chkMusic);
        tbl.add(new Label("Music", uiSkin));
        sldMusic = new Slider(0.0f, 1.0f, 0.1f, false, uiSkin);
        tbl.add(sldMusic);
        tbl.row();

        return tbl;
    }

    private Table buildOptWinButtons() {
        Table tbl = new Table();
        // + Separator
        Label lbl = null;
        lbl = new Label("", uiSkin);
        lbl.setColor(0.75f, 0.75f, 0.75f, 1);
        lbl.setStyle(new Label.LabelStyle(lbl.getStyle()));
        lbl.getStyle().background = uiSkin.newDrawable("white");
        tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 0, 0, 1);
        tbl.row();
        lbl = new Label("", uiSkin);
        lbl.setColor(0.5f, 0.5f, 0.5f, 1);
        lbl.setStyle(new Label.LabelStyle(lbl.getStyle()));
        lbl.getStyle().background = uiSkin.newDrawable("white");
        tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 1, 5, 0);
        tbl.row();
        // + Save Button with event handler
        TextButton btnWinOptSave = new TextButton("Save", uiSkin);
        tbl.add(btnWinOptSave).padRight(30);
        btnWinOptSave.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onSaveClicked();
            }
        });
        // + Cancel Button with event handler
        TextButton btnWinOptCancel = new TextButton("Cancel", uiSkin);
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
        AudioManager.instance.onSettingsUpdated();
    }

    private void onCancelClicked() {
        //showMenuButtons(true);
        //showOptionsWindow(false, true);
        showFloatingActor(layerControls, true, true);
        showFloatingActor(layerOptionsWindow,false,true);
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