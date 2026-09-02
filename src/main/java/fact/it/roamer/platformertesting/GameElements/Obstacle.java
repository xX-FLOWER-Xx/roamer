package fact.it.roamer.platformertesting.GameElements;

import fact.it.roamer.platformertesting.Interfaces.Collidable;
import fact.it.roamer.platformertesting.Interfaces.Drawable;

import java.awt.*;
import java.util.ArrayList;

public class Obstacle extends GameObject implements Collidable, Drawable {

    Obstacle(int startX, int startY, int width, int height) {
        super(startX, startY, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(128, 0, 128));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(getX(), getY(), getWidth(),getHeight());
    }

    public void checkCollisions(Obstacle obstacle, ArrayList<Player> players, ArrayList<Enemy> enemies) {

        // Check collisions with the player
        if (players != null) for (Player pl : players) if (obstacle.isCollidingWith(pl)) pl.collide(obstacle);

        // Check collisions with enemies
        if (enemies != null) for (Enemy en : enemies) if (obstacle.isCollidingWith(en)) obstacle.collide(en);
    }

    @Override
    public void collide(Collidable other) {

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
                enemy.setX(enemy.getX() + 5);
                enemy.setDirection(-1);
            } else if (this.getX() + this.getWidth() / 2 > enemy.getX() + enemy.getWidth() / 2) {
                enemy.setX(enemy.getX() - 5);
                enemy.setDirection(1);
            }
        }

    }

}