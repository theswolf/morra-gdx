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


package core.september.morra.win;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import core.september.morra.Constants;
import core.september.morra.game.objects.GameLevelGraphics;
import core.september.morra.game.objects.TouchWrapper;
import core.september.morra.util.Assets;


public class WinLevel extends GameLevelGraphics{

	public static final String TAG = WinLevel.class.getName();
    private final int player;
    private final int playerBound;
    private final int cpu;
    private final int cpuBound;


    public WinLevel(int player,int cpu, int playerBound, int cpuBound) {
		init();
        this.player = player;
        this.cpu = cpu;
        this.playerBound = playerBound;
        this.cpuBound = cpuBound;
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
        // Draw Mountains
        backGround.draw(batch);
        renderLowerHands(batch);
        renderUpperHands(batch);
        renderInfos(batch);
    }

    private void renderInfos(SpriteBatch batch) {
        BitmapFont font = Assets.instance.font.defaultBig;

        font.setColor(Color.YELLOW);
        font.draw(batch,String.valueOf(playerBound), Constants.VIEWPORT_WIDTH / 8,Constants.VIEWPORT_HEIGHT * 0.65f);

        font.setColor(Color.RED);
        font.draw(batch,String.valueOf(cpuBound), (Constants.VIEWPORT_WIDTH / 8)*7,Constants.VIEWPORT_HEIGHT * 0.65f);
    }

    public void renderLowerHands(SpriteBatch batch) {
        lower0.getSprite().draw(batch);
        lower1.getSprite().draw(batch);
        lower2.getSprite().draw(batch);
        lower3.getSprite().draw(batch);
        lower4.getSprite().draw(batch);
        lower5.getSprite().draw(batch);

    }

    public void renderUpperHands(SpriteBatch batch) {
        upper0.getSprite().draw(batch);
        upper1.getSprite().draw(batch);
        upper2.getSprite().draw(batch);
        upper3.getSprite().draw(batch);
        upper4.getSprite().draw(batch);
        upper5.getSprite().draw(batch);
    }




}
