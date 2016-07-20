package core.september.morra.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import core.september.morra.Constants;


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

    public class AssetSounds {

        public final Sound clap;

        public AssetSounds(AssetManager am) {
            clap = am.get(Constants.CLAP_SOUND, Sound.class);

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

    public class Fonts {
        //public final BitmapFont defaultSmall;
        //public final BitmapFont defaultNormal;
        public final BitmapFont defaultBig;

        public Fonts() {


            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("dkWildBunch.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = Constants.VIEWPORT_HEIGHT / 3;
            parameter.borderColor = Color.BLACK;
            parameter.borderWidth = 3;
            parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";


            defaultBig = generator.generateFont(parameter);
            generator.dispose();


        }

        public void dispose() {
            defaultBig.dispose();
        }
    }


    public SimpleAsset background;
    public AssetSounds sounds;
    public TripleAsset zero;
    public TripleAsset uno;
    public TripleAsset due;
    public TripleAsset tre;
    public TripleAsset quattro;
    public TripleAsset cinque;
    public Fonts font;

    public void initresources(AssetManager manager) {

        TextureAtlas atlas = manager.get(Constants.TEXTURE_ATLAS_OBJECTS);


        background = new SimpleAsset(atlas,"background");
        zero = new TripleAsset(atlas,"zero");
        uno = new TripleAsset(atlas,"uno");
        due = new TripleAsset(atlas,"due");
        tre = new TripleAsset(atlas,"tre");
        quattro = new TripleAsset(atlas,"quattro");
        cinque = new TripleAsset(atlas,"cinque");
        font = new Fonts();
        sounds = new AssetSounds(manager);
    }


}
