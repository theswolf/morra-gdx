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


package core.september.morra.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import core.september.morra.Constants;
import core.september.morra.game.objects.GameLevelGraphics;
import core.september.morra.game.objects.TouchWrapper;
import core.september.morra.util.Assets;


public class GameLevel extends GameLevelGraphics{

	public static final String TAG = GameLevel.class.getName();

    public TouchWrapper currentred;
    public TouchWrapper currentTouched;

    public Array<TouchWrapper> touchables;

    public int playerBound;
    public int cpuBound;


    public GameLevel() {
		init();
	}

    public void init() {
        super.init();
        touchables.addAll(lower0, lower1, lower2, lower3, lower4, lower5);
        currentred = null;
        currentTouched = null;

        touchables = new Array<TouchWrapper>();

        deltaTouch = 0;
        elapsedTotal = 0;
        random = new Random();
        //initLowerHands();
        //initUpperHands();
        playerBound = random.nextInt(6)+5;
        cpuBound = different(playerBound);
    }

	public void update (float deltaTime) {
        //Gdx.app.log(TAG, String.format("DeltaTime %f DeltaTouch %f", deltaTime, deltaTouch));

        if(currentTouched == null) {
            deltaTouch = 0;
        }
        else {
            deltaTouch += deltaTime;
        }


        if(deltaTouch > 0.1f) {
            deltaTouch = 0;
            currentTouched.isTouched=false;
            currentTouched = null;
        }

        if(elapsedTotal > 0.2f) {
            elapsedTotal = 0;
            if(currentred == null) {
                int red = random.nextInt(6);
                switch (red) {
                    case 0:
                        currentred = upper0;
                        break;
                    case 1:
                        currentred = upper1;
                        break;
                    case 2:
                        currentred = upper2;
                        break;
                    case 3:
                        currentred = upper3;
                        break;
                    case 4:
                        currentred = upper4;
                        break;
                    case 5:
                        currentred = upper5;
                        break;
                }

                currentred.isTouched = true;
            }
            else {
                currentred.isTouched = false;
                currentred = null;
            }
        }
        else {
            elapsedTotal += deltaTime;
        }


	}



	public void render (SpriteBatch batch) {
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



    public void touched(Vector2 point) {
        for(TouchWrapper wrapper: touchables) {
            if(wrapper.getSprite().getBoundingRectangle().contains(point)) {
                if(currentTouched != null ) {
                    currentTouched.isTouched = false;
                }
                wrapper.isTouched = true;
                currentTouched = wrapper;
                break;
            }
        }
        //return null;
    }


}
