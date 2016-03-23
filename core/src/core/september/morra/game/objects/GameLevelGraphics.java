package core.september.morra.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import core.september.morra.Constants;
import core.september.morra.util.Assets;

/**
 * Created by christian on 22/03/16.
 */
public class GameLevelGraphics {
    // player character

   // public Array<Sprite> touchable;
    //public ObjectMap<WeakReference<Sprite>,WeakReference<Sprite>> yellows;
    protected Random random;

    //protected Sprite touched;
    protected float deltaTouch;
    protected float elapsedTotal;

    public Sprite backGround;

    /*public Sprite zero;
    public Sprite uno;
    public Sprite due;
    public Sprite tre;
    public Sprite quattro;
    public Sprite cinque;

    public Sprite revertedzero;
    public Sprite reverteduno;
    public Sprite reverteddue;
    public Sprite revertedtre;
    public Sprite revertedquattro;
    public Sprite revertedcinque;

    public Sprite yellowzero;
    public Sprite yellowuno;
    public Sprite yellowdue;
    public Sprite yellowtre;
    public Sprite yellowquattro;
    public Sprite yellowcinque;

    public Sprite redzero;
    public Sprite reduno;
    public Sprite reddue;
    public Sprite redtre;
    public Sprite reduattro;
    public Sprite redcinque;*/

    public TouchWrapper lower0;
    public TouchWrapper lower1;
    public TouchWrapper lower2;
    public TouchWrapper lower3;
    public TouchWrapper lower4;
    public TouchWrapper lower5;

    public TouchWrapper upper0;
    public TouchWrapper upper1;
    public TouchWrapper upper2;
    public TouchWrapper upper3;
    public TouchWrapper upper4;
    public TouchWrapper upper5;



    private final float scalesize = 0.8f;

    private Sprite fromRegion(TextureAtlas.AtlasRegion region, float x, float y, float scale) {
        Sprite ret = new Sprite(region);
        ret.setPosition(x, y);
        ret.setScale(scale);
        return ret;
    }

    protected void init () {
        backGround = new Sprite(Assets.instance.background.region,
                0,
                (Assets.instance.background.region.getRegionHeight() - Constants.VIEWPORT_HEIGHT)/2,
                Constants.VIEWPORT_WIDTH,
                Constants.VIEWPORT_HEIGHT
        );
        //touched = null;
        /*
        currentred = null;
        currentTouched = null;

        touchables = new Array<TouchWrapper>();

        deltaTouch = 0;
        elapsedTotal = 0;
        random = new Random();

        playerBound = random.nextInt(6)+5;
        cpuBound = different(playerBound);
        */

        initLowerHands();
        initUpperHands();

    }

    public int different(int input) {
        int different = random.nextInt(6)+5;
        return different == input ? different(input) : different;
    }

    private void initLowerHands() {
        float yline = -50;
        float spacer = Constants.VIEWPORT_WIDTH / 6;

        lower0 = new TouchWrapper(
                Assets.instance.zero.region,
                Assets.instance.zero.yellow_region,
                0,
                0,yline,scalesize
        );

        lower1 = new TouchWrapper(
                Assets.instance.uno.region,
                Assets.instance.uno.yellow_region,
                1,
                spacer,yline,scalesize
        );

        lower2 = new TouchWrapper(
                Assets.instance.due.region,
                Assets.instance.due.yellow_region,
                2,
                spacer*2,yline,scalesize
        );

        lower3 = new TouchWrapper(
                Assets.instance.tre.region,
                Assets.instance.tre.yellow_region,
                3,
                spacer*3,yline,scalesize
        );

        lower4 = new TouchWrapper(
                Assets.instance.quattro.region,
                Assets.instance.quattro.yellow_region,
                4,
                spacer*4,yline,scalesize
        );

        lower5 = new TouchWrapper(
                Assets.instance.cinque.region,
                Assets.instance.cinque.yellow_region,
                5,
                spacer*5,yline,scalesize
        );



    }


    private void initUpperHands() {
        float yline = Constants.VIEWPORT_HEIGHT - 200;
        float rotation = 180;
        float spacer = Constants.VIEWPORT_WIDTH / 6;

        upper0 = new TouchWrapper(
                Assets.instance.zero.region,
                Assets.instance.zero.red_region,
                0,
                0,yline,scalesize
        );
        upper0.rotate(rotation);

        upper1 = new TouchWrapper(
                Assets.instance.uno.region,
                Assets.instance.uno.red_region,
                1,
                spacer,yline,scalesize
        );
        upper1.rotate(rotation);

        upper2 = new TouchWrapper(
                Assets.instance.due.region,
                Assets.instance.due.red_region,
                2,
                spacer*2,yline,scalesize
        );
        upper2.rotate(rotation);

        upper3 = new TouchWrapper(
                Assets.instance.tre.region,
                Assets.instance.tre.red_region,
                3,
                spacer*3,yline,scalesize
        );
        upper3.rotate(rotation);

        upper4 = new TouchWrapper(
                Assets.instance.quattro.region,
                Assets.instance.quattro.red_region,
                4,
                spacer*4,yline,scalesize
        );
        upper4.rotate(rotation);

        upper5 = new TouchWrapper(
                Assets.instance.cinque.region,
                Assets.instance.cinque.red_region,
                5,
                spacer*5,yline,scalesize
        );
        upper5.rotate(rotation);

    }

}
