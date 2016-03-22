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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.lang.ref.WeakReference;

import core.september.morra.game.objects.GameLevelGraphics;


public class GameLevel extends GameLevelGraphics{

	public static final String TAG = GameLevel.class.getName();


    public GameLevel() {
		init();
	}


	public void update (float deltaTime) {
        //Gdx.app.log(TAG, String.format("DeltaTime %f DeltaTouch %f", deltaTime, deltaTouch));

        if(touched == null) {
            deltaTouch = 0;
        }
        else {
            deltaTouch += deltaTime;
        }


        if(deltaTouch > 0.1f) {
            deltaTouch = 0;
            touched = null;
        }

        if(elapsedTotal > 0.2f) {
            elapsedTotal = 0;
            if(currentred == null) {
                int red = random.nextInt(6);
                switch (red) {
                    case 0:
                        currentred = redzero;
                        break;
                    case 1:
                        currentred = reduno;
                        break;
                    case 2:
                        currentred = reddue;
                        break;
                    case 3:
                        currentred = redtre;
                        break;
                    case 4:
                        currentred = reduattro;
                        break;
                    case 5:
                        currentred = redcinque;
                        break;
                }
            }
            else {
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
        if(touched != null) {
            renderLowerHandTouched(batch);
        }
        if(currentred != null) {
            renderUpperHandTouched(batch);
        }


	}

    public void renderLowerHands(SpriteBatch batch) {
        zero.draw(batch);
        uno.draw(batch);
        due.draw(batch);
        tre.draw(batch);
        quattro.draw(batch);
        cinque.draw(batch);
    }

    public void renderUpperHands(SpriteBatch batch) {
        revertedzero.draw(batch);
        reverteduno.draw(batch);
        reverteddue.draw(batch);
        revertedtre.draw(batch);
        revertedquattro.draw(batch);
        revertedcinque.draw(batch);
    }

    public void renderLowerHandTouched(SpriteBatch batch) {
        touched.draw(batch);
    }

    public void renderUpperHandTouched(SpriteBatch batch) {
        currentred.draw(batch);
    }

    public void touched(Vector2 point) {
        for(Sprite s:touchable) {
            if (s.getBoundingRectangle().contains(point)) {
                for(WeakReference<Sprite> ref: yellows.keys()) {
                    if(ref.get().equals(s)) {
                        touched = yellows.get(ref).get();
                        break;
                    }
                }
                break;
            }
        }
        //return null;
    }


}
