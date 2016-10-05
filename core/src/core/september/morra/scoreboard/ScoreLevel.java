/*******************************************************************************
 * Copyright 2013 Andreas Oehlke
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package core.september.morra.scoreboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import core.september.morra.Constants;
import core.september.morra.game.objects.GameLevelGraphics;
import core.september.morra.game.objects.TouchWrapper;
import core.september.morra.util.Assets;
import core.september.morra.util.GameScore;
import core.september.morra.util.Match;


public class ScoreLevel extends GameLevelGraphics{

	public static final String TAG = ScoreLevel.class.getName();


    private TouchWrapper currentPlayer;
    private TouchWrapper currentCpu;


    public ScoreLevel() {
		init();

	}


    protected void init () {
        backGround = new Sprite(Assets.instance.background.region,
                0,
                (Assets.instance.background.region.getRegionHeight() - Constants.VIEWPORT_HEIGHT) / 2,
                Constants.VIEWPORT_WIDTH,
                Constants.VIEWPORT_HEIGHT
        );
    }





	public void update (float deltaTime) {


	}



	public void render (SpriteBatch batch) {
        // Draw Mountains
        backGround.draw(batch);
    }



    public void renderColorized(SpriteBatch batch) {
        BitmapFont font = Assets.instance.font.defaultNormal;
        BitmapFont big = Assets.instance.font.defaultBig;

        Array<Match> matches = GameScore.instance.matches;
        Gdx.app.log(TAG,String.format("There are %s matches",matches.size));

        if(matches.size == 0) return;

        int level = 0;
        int won = 0;
        int lost = 0;
        for(Match match:matches) {
            level += (match.win? 1: -1);
            if(match.win)
                won+=1;
            else
                lost+=1;
        }
        font.setColor(level>0 ? Color.YELLOW : Color.RED);
        font.draw(batch, String.format("YOU %s %s matches of %s!!!", level>0 ? "WIN" : "LOOSE",level>0 ? won : lost,matches.size),
                Constants.VIEWPORT_WIDTH / 16, Constants.VIEWPORT_HEIGHT / 2);



    }
}
