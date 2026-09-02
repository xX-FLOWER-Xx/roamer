package fact.it.roamer.platformertesting.EnemyStates;

import fact.it.roamer.platformertesting.GameElements.Enemy;
import fact.it.roamer.platformertesting.GameElements.Player;

import java.util.ArrayList;

public interface EnemyState {

    void update(Enemy enemy, ArrayList<Player> player);

}
