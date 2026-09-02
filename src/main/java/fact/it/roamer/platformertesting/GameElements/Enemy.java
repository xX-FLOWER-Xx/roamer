package fact.it.roamer.platformertesting.GameElements;

import fact.it.roamer.platformertesting.EnemyStates.EnemyState;
import fact.it.roamer.platformertesting.EnemyStates.RoamState;
import fact.it.roamer.platformertesting.Interfaces.Collidable;
import fact.it.roamer.platformertesting.Interfaces.Drawable;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends GameEntity implements Collidable, Drawable {

    private int screenWidth;
    private int screenHeight;
    private int direction;
    private int jumpCooldown;

    private EnemyState currentState;

    Enemy(int startX, int startY, int width, int height, int baseSpeed) {
        super(startX, startY, width, height, baseSpeed);
        jumpCooldown = (int) (180 * Math.random());
        direction = -1;
        currentState = new RoamState();
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void update(ArrayList<Player> players) {

        // Gravity, Sliding, Jumping, Moving left and right, Updating the positions, Preventing moving off-screen
        gravitate();
        slide();
        currentState.update(this, players);
        updatePositions();
        preventOffScreenX(this.screenWidth);

    }

    public void checkCollisions(Enemy enemy, ArrayList<Enemy> enemies, ArrayList<Flag> flags, ArrayList<Player> players) {

        // Check collisions with other, unchecked enemies
        int i = enemies.indexOf(enemy);
        for (int j = i + 1; j < enemies.size(); j++) {
            if (enemy.isCollidingWith(enemies.get(j))) {
                enemy.collide(enemies.get(j));
                enemies.get(j).collide(enemy);
            }
        }

        // Check collisions with the player
        if (players != null) for (Player pl : players) if (enemy.isCollidingWith(pl)) pl.collide(enemy);

        // Check collisions with flags
        if (flags != null) for (Flag fl : flags) if (enemy.isCollidingWith(fl)) enemy.collide(fl);

        // Check collisions with the ground
        if (getY() + getHeight() >= this.screenHeight) {
            setY(this.screenHeight - getHeight());
            super.setVelocityY(0);
            super.setOnGround(true);
        }

    }

    @Override
    public void collide(Collidable other) {
        if (other instanceof Enemy enemy) {
            if (getX() + getWidth()/2 < enemy.getX() + enemy.getWidth()/2) {
                setX(getX() - 5);
                direction = 1;
                enemy.setX(enemy.getX() + 5);
                enemy.direction = -1;
            } else {
                setX(getX() + 5);
                direction = -1;
                enemy.setX(enemy.getX() - 5);
                enemy.direction = 1;
            }
        }
        if (other instanceof Flag flag) {
            if (getY() + getHeight() - (super.getVelocityY() / 100) <= flag.getY()) {
                setY(flag.getY() - getHeight());
                super.setVelocityY(0);
                super.setOnGround(true);
            } else if (getY() - (getVelocityY() / 100) >= flag.getY() + flag.getHeight()) {
                setY(flag.getY() + flag.getHeight());
                super.setVelocityY(0);
            } else if (flag.getX() + flag.getWidth() / 2 < getX() + getWidth() / 2) {
                setX(getX() + getVelocityX()/100);
                direction = -1;
            } else if (flag.getX() + flag.getWidth() / 2 > getX() + getWidth() / 2) {
                setX(getX() - getVelocityX()/100);
                direction = 1;
            }
        }
    }

    @Override
    public void preventOffScreenX(int screenWidth) {
        if (getX() < 0) {
            setX(0);
            direction = -1;
        }
        if (getX() + getWidth() > screenWidth) {
            setX(screenWidth - getWidth());
            direction = 1;
        }
    }

    // Getters

    public int getDirection() {
        return direction;
    }

    public int getJumpCooldown() {
        return jumpCooldown;
    }

    // Setters

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setCurrentState(EnemyState currentState) {
        this.currentState = currentState;
    }

    public void setJumpCooldown(int jumpCooldown) {
        this.jumpCooldown = jumpCooldown;
    }
}