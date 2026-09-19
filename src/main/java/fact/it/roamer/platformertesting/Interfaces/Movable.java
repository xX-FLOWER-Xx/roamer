package fact.it.roamer.platformertesting.Interfaces;

import fact.it.roamer.platformertesting.GameElements.*;

import java.util.concurrent.CopyOnWriteArrayList;

public interface Movable {

    void update();

    static void checkAllMovements(CopyOnWriteArrayList<Player> players, CopyOnWriteArrayList<Enemy> enemies) {

        if (players != null) for (Player pl : players) pl.update();
        if (enemies != null) for (Enemy en : enemies) en.update(players);

    }

}
