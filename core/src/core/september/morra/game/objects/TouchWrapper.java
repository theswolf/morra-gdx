package core.september.morra.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by christian on 23/03/16.
 */
public class TouchWrapper {

    public Sprite untouched;
    public Sprite touched;
    public int id;


    public boolean isTouched;

   /* public TouchWrapper(Sprite untouched, Sprite touched, int id) {
        this.untouched = untouched;
        this.touched = touched;
        this.id = id;
        this.isTouched = false;
    }*/

    public TouchWrapper(
            TextureRegion untouched,
            TextureRegion touched,
            int id,
            float x, float y, float scale
            ) {
        this.untouched = fromRegion(untouched,x,y,scale);
        this.touched = fromRegion(touched,x,y,scale);
        this.id=id;
    }


    public void rotate(float rotation) {
        this.touched.rotate(rotation);
        this.untouched.rotate(rotation);
    }

    private Sprite fromRegion(TextureRegion region, float x, float y, float scale) {
        Sprite ret = new Sprite(region);
        ret.setPosition(x, y);
        ret.setScale(scale);
        return ret;
    }

    public Sprite getSprite() {
        return isTouched ? touched : untouched;
    }


}
