package fact.it.roamer.platformertesting.EnemyStates;

import fact.it.roamer.platformertesting.GameElements.Enemy;
import fact.it.roamer.platformertesting.GameElements.Player;

import java.util.ArrayList;

public class RoamState implements EnemyState {

    @Override
    public void update(Enemy enemy, ArrayList<Player> players) {

        // Roaming logic

        if (enemy.getDirection() == -1) {
            enemy.setVelocityX(Math.min(enemy.getVelocityX() + enemy.getSpeed(), enemy.getSpeed()));
        } else {
            enemy.setVelocityX(Math.max(enemy.getVelocityX() - enemy.getSpeed(), -enemy.getSpeed()));
        }

        // Jump logic

        if (enemy.getJumpCooldown() == 0 && enemy.isOnGround()) {
            enemy.setJumpCooldown(180);
            enemy.setVelocityY(-1000);
        } else if (enemy.getJumpCooldown() > 0) {
            enemy.setJumpCooldown(enemy.getJumpCooldown() - 1);
        }

        // Checking other states logic

        Player target = getClosestChargeablePlayer(enemy, players);
        if (target != null) enemy.setCurrentState(new ChargeState(target.getX()));

    }

    private Player getClosestChargeablePlayer(Enemy enemy, ArrayList<Player> players) {
        Player closest = null;
        int closestDistance = Integer.MAX_VALUE;
        for (Player pl : players) {
            if (pl.getY() + pl.getHeight() == enemy.getY() + enemy.getHeight() && ((enemy.getDirection() == -1 && pl.getX() > enemy.getX()) || (enemy.getDirection() == 1 && pl.getX() <= enemy.getX()))) {
                int distance = Math.abs(pl.getX() - enemy.getX());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closest = pl;
                }
            }
        }
        return closest;
    }

}