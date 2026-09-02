package fact.it.roamer.platformertesting.GameElements;

import fact.it.roamer.platformertesting.LevelLoader;
import fact.it.roamer.platformertesting.Interfaces.Collidable;
import fact.it.roamer.platformertesting.Interfaces.Drawable;

import java.awt.*;


public class Player extends GameEntity implements Collidable, Drawable {

    private boolean up;
    private boolean left;
    private boolean right;
    private int screenWidth;
    private int screenHeight;
    private int deathTimer = -1;

    private final LevelLoader levelLoader;

    Player(int x, int y, int width, int height, int screenWidth, int screenHeight, int speed, LevelLoader levelLoader) {
        super(x, y, width, height, speed);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.levelLoader = levelLoader;
    }

    public void draw(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

    }

    public void update() {

        // Check deathtimer
        if (deathTimer > 0) {
            deathTimer--;
            return;
        }
        if (deathTimer == 0) {
            levelLoader.reloadLevel();
            return;
        }

        // Gravity, Sliding, Jumping, Moving left and right, Updating the positions, Preventing moving off-screen
        gravitate();
        slide();
        if (this.up && super.isOnGround()) super.setVelocityY(-2280);
        if (this.left) super.setVelocityX(Math.max(super.getVelocityX() - super.getSpeed(), -super.getSpeed()));
        if (this.right) super.setVelocityX(Math.min(super.getVelocityX() + super.getSpeed(), super.getSpeed()));
        updatePositions();
        preventOffScreenX(screenWidth);

    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void checkCollisions() {

        // Check collisions with the ground
        if (getY() + getHeight() >= this.screenHeight) {
            setY(this.screenHeight - getHeight());
            super.setVelocityY(0);
            super.setOnGround(true);
        }

    }

    @Override
    public void collide(Collidable other) {

        if ((other instanceof Enemy || other instanceof Obstacle) && deathTimer == -1) {
            int originalHeight = getHeight();
            setY(getY() + originalHeight - 10);
            setHeight(10);
            setWidth(originalHeight);
            deathTimer = 120;
        }

    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
}