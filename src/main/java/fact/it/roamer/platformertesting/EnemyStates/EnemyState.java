package fact.it.roamer.platformertesting.EnemyStates;

import fact.it.roamer.platformertesting.GameElements.Enemy;
import fact.it.roamer.platformertesting.GameElements.Player;

import java.util.concurrent.CopyOnWriteArrayList;

public interface EnemyState {

    void update(Enemy enemy, CopyOnWriteArrayList<Player> player);

}
