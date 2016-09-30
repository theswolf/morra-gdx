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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import core.september.morra.Constants;
import core.september.morra.game.objects.GameLevelGraphics;
import core.september.morra.game.objects.TouchWrapper;
import core.september.morra.util.Assets;


public class ScoreLevel extends GameLevelGraphics{

	public static final String TAG = ScoreLevel.class.getName();


    private TouchWrapper currentPlayer;
    private TouchWrapper currentCpu;


    public ScoreLevel() {
		init();

	}




    public void renderColorizedInfos(SpriteBatch batch) {
        BitmapFont font = Assets.instance.font.defaultBig;

        if(cpu+player == playerBound) {
            font.setColor(Color.YELLOW);
            font.draw(batch, String.valueOf(playerBound), Constants.VIEWPORT_WIDTH / 8, Constants.VIEWPORT_HEIGHT * 0.65f);

        }

        else {
            font.setColor(Color.RED);
            font.draw(batch, String.valueOf(cpuBound), (Constants.VIEWPORT_WIDTH / 8) * 7, Constants.VIEWPORT_HEIGHT * 0.65f);
        }

    }

	public void update (float deltaTime) {


	}



	public void render (SpriteBatch batch) {
		// Draw Mountains
		backGround.draw(batch);
        renderLowerHands(batch);
        renderUpperHands(batch);
        renderInfos(batch);
	}

    public void renderColorized(SpriteBatch batch) {
        currentPlayer.touched.draw(batch);
        currentCpu.touched.draw(batch);
        renderColorizedInfos(batch);
    }







}
