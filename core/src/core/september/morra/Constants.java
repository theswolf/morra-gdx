package core.september.morra;

/**
 * Created by christian on 21/03/16.
 */
public class Constants {
    public static final String TEXTURE_ATLAS_OBJECTS = "morrares.atlas";

    // Visible game world is 5 meters wide
    public static final int VIEWPORT_WIDTH = 800;

    // Visible game world is 5 meters tall
    public static final int VIEWPORT_HEIGHT = 480;

    // GUI Width
    public static final float VIEWPORT_GUI_WIDTH = 800.0f;

    // GUI Height
    public static final float VIEWPORT_GUI_HEIGHT = 480.0f;

    // Location of description file for texture atlas
    //public static final String TEXTURE_ATLAS_OBJECTS = "images/canyonbunny.pack";
    public static final String TEXTURE_ATLAS_UI = "images/canyonbunny-ui.pack";
    public static final String TEXTURE_ATLAS_LIBGDX_UI = "images/uiskin.atlas";

    // Location of description file for skins
    public static final String SKIN_LIBGDX_UI = "images/uiskin.json";
    public static final String SKIN_CANYONBUNNY_UI = "images/canyonbunny-ui.json";

    // Location of image file for level 01
    public static final String LEVEL_01 = "levels/level-01.png";

    // Amount of extra lives at level start
    public static final int LIVES_START = 3;

    // Duration of feather power-up in seconds
    public static final float ITEM_FEATHER_POWERUP_DURATION = 9;

    // Number of carrots to spawn
    public static final int CARROTS_SPAWN_MAX = 100;

    // Spawn radius for carrots
    public static final float CARROTS_SPAWN_RADIUS = 3.5f;

    // Delay after game over
    public static final float TIME_DELAY_GAME_OVER = 3;

    // Delay after game finished
    public static final float TIME_DELAY_GAME_FINISHED = 6;

    // Game preferences file
    public static final String PREFERENCES = "morragame.prefs";

    // Shader
    public static final String shaderMonochromeVertex = "shaders/monochrome.vs";
    public static final String shaderMonochromeFragment = "shaders/monochrome.fs";

    // Angle of rotation for dead zone (no movement)
    public static final float ACCEL_ANGLE_DEAD_ZONE = 5.0f;

    // Max angle of rotation needed to gain maximum movement velocity
    public static final float ACCEL_MAX_ANGLE_MAX_MOVEMENT = 20.0f;
    public static final String SCORE = "morragame.score";
}
