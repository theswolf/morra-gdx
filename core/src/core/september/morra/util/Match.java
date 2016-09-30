package core.september.morra.util;

/**
 * Created by christian on 30/09/16.
 */

public class Match {
    public boolean win;
    public boolean loose;

    public Match(boolean won) {
        this.winned(won);
    }

    public void winned(boolean isWin) {
        win = isWin;
        loose = !isWin;
    }
}
