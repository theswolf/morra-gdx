package core.september.morra.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import core.september.morra.Constants;

/**
 * Created by christian on 21/03/16.
 */
public class InputTransform
{
    private static int appWidth = Double.valueOf(Constants.VIEWPORT_WIDTH).intValue();
    private static int appHeight = Double.valueOf(Constants.VIEWPORT_HEIGHT).intValue();

    public static float getCursorToModelX(int cursorX)
    {

        return ((((float)cursorX) * appWidth) / ((float)Gdx.graphics.getWidth()));
    }

    public static float getCursorToModelY(int cursorY)
    {
        return (((float)(Gdx.graphics.getHeight() - cursorY)) * appHeight / ((float)Gdx.graphics.getHeight()) );
    }

    public static Vector2 point(int screenX, int screenY) {
        return new Vector2(
                getCursorToModelX(screenX),
                getCursorToModelY(screenY)
        );
    }
}
