package fact.it.roamer.platformertesting.Interfaces;

import fact.it.roamer.platformertesting.GameElements.*;

import java.util.ArrayList;

public interface Movable {

    void update();

    static void checkAllMovements(ArrayList<Player> players, ArrayList<Enemy> enemies) {

        if (players != null) for (Player pl : players) pl.update();
        if (enemies != null) for (Enemy en : enemies) en.update(players);

    }

}
