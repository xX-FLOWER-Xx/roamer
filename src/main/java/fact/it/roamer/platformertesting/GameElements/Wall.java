package fact.it.roamer.platformertesting.GameElements;

import fact.it.roamer.platformertesting.EnemyStates.RoamState;
import fact.it.roamer.platformertesting.Interfaces.Collidable;
import fact.it.roamer.platformertesting.Interfaces.Drawable;

import java.awt.*;
import java.util.ArrayList;

public class Wall extends GameObject implements Collidable, Drawable {

    Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void checkCollisions(Wall wall, ArrayList<Player> players, ArrayList<Enemy> enemies) {

        // Check collisions with the player
        if (players != null) for (Player pl : players) if (wall.isCollidingWith(pl)) wall.collide(pl);

        // Check collisions with enemies
        if (enemies != null) for (Enemy en : enemies) if (wall.isCollidingWith(en)) wall.collide(en);

    }

    @Override
    public void collide(Collidable other) {

        // Player collision
        if (other instanceof Player player) {
            if (player.getY() + player.getHeight() - (player.getVelocityY() / 100) <= this.getY()) {
                player.setY(this.getY() - player.getHeight());
                player.setVelocityY(0);
                player.setOnGround(true);
            } else if (player.getY() - (player.getVelocityY() / 100) >= this.getY() + this.getHeight()) {
                player.setY(this.getY() + this.getHeight());
                player.setVelocityY(0);
            } else if (this.getX() + this.getWidth() / 2 < player.getX() + player.getWidth() / 2) {
                player.setX(this.getX() + this.getWidth());
                player.setVelocityX(0);
            } else if (this.getX() + this.getWidth() / 2 > player.getX() + player.getWidth() / 2) {
                player.setX(this.getX() - player.getWidth());
                player.setVelocityX(0);
            }
        }

        // Enemy collision
        if (other instanceof Enemy enemy) {
            if (enemy.getY() + enemy.getHeight() - (enemy.getVelocityY() / 100) <= this.getY()) {
                enemy.setY(this.getY() - enemy.getHeight());
                enemy.setVelocityY(0);
                enemy.setOnGround(true);
            } else if (enemy.getY() - (enemy.getVelocityY() / 100) >= this.getY() + this.getHeight()) {
                enemy.setY(this.getY() + this.getHeight());
                enemy.setVelocityY(0);
            } else if (this.getX() + this.getWidth() / 2 < enemy.getX() + enemy.getWidth() / 2) {
                enemy.setX(this.getX() + this.getWidth());
                enemy.setDirection(-1);
                enemy.setCurrentState(new RoamState());
            } else if (this.getX() + this.getWidth() / 2 > enemy.getX() + enemy.getWidth() / 2) {
                enemy.setX(this.getX() - enemy.getWidth());
                enemy.setDirection(1);
                enemy.setCurrentState(new RoamState());
            }
        }

    }

}