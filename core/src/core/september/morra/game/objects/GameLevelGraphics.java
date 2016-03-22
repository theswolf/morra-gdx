package core.september.morra.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import java.lang.ref.WeakReference;
import java.util.Random;

import core.september.morra.Constants;
import core.september.morra.util.Assets;

/**
 * Created by christian on 22/03/16.
 */
public class GameLevelGraphics {
    // player character

    public Array<Sprite> touchable;
    public ObjectMap<WeakReference<Sprite>,WeakReference<Sprite>> yellows;
    protected Random random;

    protected Sprite touched;
    protected float deltaTouch;
    protected float elapsedTotal;

    public Sprite backGround;

    public Sprite zero;
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
    public Sprite redcinque;

    public Sprite currentred;

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
        touched = null;
        currentred = null;
        deltaTouch = 0;
        elapsedTotal = 0;
        random = new Random();
        yellows = new ObjectMap<WeakReference<Sprite>, WeakReference<Sprite>>();
        initLowerHands();
        initYellowHand();
        initUpperHands();
        initRedUpperHands();




    }

    private void initLowerHands() {
        float yline = -50;
        float spacer = Constants.VIEWPORT_WIDTH / 6;

        zero = fromRegion(Assets.instance.zero.region,0,yline,scalesize);
        uno =  fromRegion(Assets.instance.uno.region,spacer,yline,scalesize);
        due =  fromRegion(Assets.instance.due.region,spacer*2,yline,scalesize);
        tre =  fromRegion(Assets.instance.tre.region,spacer*3,yline,scalesize);
        quattro =  fromRegion(Assets.instance.quattro.region,spacer*4,yline,scalesize);
        cinque =  fromRegion(Assets.instance.cinque.region,spacer*5,yline,scalesize);

        touchable = new Array<Sprite>();
        touchable.add(zero);
        touchable.add(uno);
        touchable.add(due);
        touchable.add(tre);
        touchable.add(quattro);
        touchable.add(cinque);
    }


    private void initUpperHands() {
        float yline = Constants.VIEWPORT_HEIGHT - 200;
        float rotation = 180;
        float spacer = Constants.VIEWPORT_WIDTH / 6;

        revertedzero = fromRegion(Assets.instance.zero.region,0,yline,scalesize);
        revertedzero.rotate(rotation);

        reverteduno =  fromRegion(Assets.instance.uno.region,spacer,yline,scalesize);
        reverteduno.rotate(rotation);

        reverteddue =  fromRegion(Assets.instance.due.region,spacer*2,yline,scalesize);
        reverteddue.rotate(rotation);

        revertedtre =  fromRegion(Assets.instance.tre.region,spacer*3,yline,scalesize);
        revertedtre.rotate(rotation);

        revertedquattro =  fromRegion(Assets.instance.quattro.region,spacer*4,yline,scalesize);
        revertedquattro.rotate(rotation);

        revertedcinque =  fromRegion(Assets.instance.cinque.region,spacer*5,yline,scalesize);
        revertedcinque.rotate(rotation);

    }

    private void initYellowHand() {
        float yline = -50;
        float spacer = Constants.VIEWPORT_WIDTH / 6;

        yellowzero = fromRegion(Assets.instance.zero.yellow_region,0,yline,scalesize);
        yellows.put(new WeakReference<Sprite>(zero), new WeakReference<Sprite>(yellowzero));

        yellowuno =  fromRegion(Assets.instance.uno.yellow_region,spacer,yline,scalesize);
        yellows.put(new WeakReference<Sprite>(uno), new WeakReference<Sprite>(yellowuno));

        yellowdue =  fromRegion(Assets.instance.due.yellow_region,spacer*2,yline,scalesize);
        yellows.put(new WeakReference<Sprite>(due), new WeakReference<Sprite>(yellowdue));

        yellowtre =  fromRegion(Assets.instance.tre.yellow_region,spacer*3,yline,scalesize);
        yellows.put(new WeakReference<Sprite>(tre), new WeakReference<Sprite>(yellowtre));

        yellowquattro =  fromRegion(Assets.instance.quattro.yellow_region,spacer*4,yline,scalesize);
        yellows.put(new WeakReference<Sprite>(quattro), new WeakReference<Sprite>(yellowquattro));

        yellowcinque =  fromRegion(Assets.instance.cinque.yellow_region,spacer*5,yline,scalesize);
        yellows.put(new WeakReference<Sprite>(cinque), new WeakReference<Sprite>(yellowcinque));

    }

    private void initRedUpperHands() {
        float yline = Constants.VIEWPORT_HEIGHT - 200;
        float rotation = 180;
        float spacer = Constants.VIEWPORT_WIDTH / 6;

        redzero = fromRegion(Assets.instance.zero.red_region,0,yline,scalesize);
        redzero.rotate(rotation);

        reduno =  fromRegion(Assets.instance.uno.red_region,spacer,yline,scalesize);
        reduno.rotate(rotation);

        reddue =  fromRegion(Assets.instance.due.red_region,spacer*2,yline,scalesize);
        reddue.rotate(rotation);

        redtre =  fromRegion(Assets.instance.tre.red_region,spacer*3,yline,scalesize);
        redtre.rotate(rotation);

        reduattro =  fromRegion(Assets.instance.quattro.red_region,spacer*4,yline,scalesize);
        reduattro.rotate(rotation);

        redcinque =  fromRegion(Assets.instance.cinque.red_region,spacer*5,yline,scalesize);
        redcinque.rotate(rotation);

    }

}
