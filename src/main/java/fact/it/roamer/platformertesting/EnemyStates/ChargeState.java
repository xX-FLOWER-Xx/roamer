package fact.it.roamer.platformertesting.EnemyStates;

import fact.it.roamer.platformertesting.GameElements.Enemy;
import fact.it.roamer.platformertesting.GameElements.Player;

import java.util.ArrayList;

public class ChargeState implements EnemyState {

    private int lastPlayerX;

    public ChargeState(int lastPlayerX) {
        this.lastPlayerX = lastPlayerX;
    }

    @Override
    public void update(Enemy enemy, ArrayList<Player> player) {

        // Checking other states logic

        if (Math.abs(lastPlayerX - enemy.getX()) < 10) {
            enemy.setCurrentState(new RoamState());
            return;
        }

        // Charging logic

        if (lastPlayerX > enemy.getX()) {
            enemy.setVelocityX(Math.min(enemy.getVelocityX() + enemy.getBaseSpeed()*3, enemy.getBaseSpeed()*3));
        } else {
            enemy.setVelocityX(Math.max(enemy.getVelocityX() - enemy.getBaseSpeed()*3, -enemy.getBaseSpeed()*3));
        }

    }
}
