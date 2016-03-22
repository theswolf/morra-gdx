package core.september.morra.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;


/**
 * Created by christian on 21/03/16.
 */
public abstract class AssetsResources {

    public class SimpleAsset {
        public final TextureAtlas.AtlasRegion region;

        public SimpleAsset(TextureAtlas atlas,String regionName) {
            region = atlas.findRegion(regionName);
        }
    }

    public class TripleAsset {
        public final TextureAtlas.AtlasRegion region;
        public final TextureAtlas.AtlasRegion yellow_region;
        public final TextureAtlas.AtlasRegion red_region;

        public TripleAsset(TextureAtlas atlas,String regionName) {
            region = atlas.findRegion(regionName);
            yellow_region= atlas.findRegion("yellow-"+regionName);
            red_region= atlas.findRegion("red-"+regionName);

        }
    }


    public SimpleAsset background;
    public TripleAsset zero;
    public TripleAsset uno;
    public TripleAsset due;
    public TripleAsset tre;
    public TripleAsset quattro;
    public TripleAsset cinque;

    public void initresources(TextureAtlas atlas) {
        background = new SimpleAsset(atlas,"background");
        zero = new TripleAsset(atlas,"zero");
        uno = new TripleAsset(atlas,"uno");
        due = new TripleAsset(atlas,"due");
        tre = new TripleAsset(atlas,"tre");
        quattro = new TripleAsset(atlas,"quattro");
        cinque = new TripleAsset(atlas,"cinque");
    }


}