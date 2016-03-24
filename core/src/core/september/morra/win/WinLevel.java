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
    private final int cpu;

    private TouchWrapper currentPlayer;
    private TouchWrapper currentCpu;


    public WinLevel(int player,int cpu, int playerBound, int cpuBound) {
		init();
        this.player = player;
        this.cpu = cpu;
        this.playerBound = playerBound;
        this.cpuBound = cpuBound;
        setColorizedValue();
	}


    private void setColorizedValue() {
        switch (this.player) {
            case 0:
                currentPlayer = lower0;
                break;
            case 1:
                currentPlayer = lower1;
                break;
            case 2:
                currentPlayer = lower2;
                break;
            case 3:
                currentPlayer = lower3;
                break;
            case 4:
                currentPlayer = lower4;
                break;
            case 5:
                currentPlayer = lower5;
                break;
        }



        switch (this.cpu) {
            case 0:
                currentCpu = upper0;
                break;
            case 1:
                currentCpu = upper1;
                break;
            case 2:
                currentCpu = upper2;
                break;
            case 3:
                currentCpu = upper3;
                break;
            case 4:
                currentCpu = upper4;
                break;
            case 5:
                currentCpu = upper5;
                break;
        }
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
