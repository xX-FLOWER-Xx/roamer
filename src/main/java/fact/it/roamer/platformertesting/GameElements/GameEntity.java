package fact.it.roamer.platformertesting.GameElements;

public abstract class GameEntity extends GameObject {

    private int velocityX;
    private int velocityY;
    private int speed;
    private boolean onGround;
    private final int baseSpeed;

    GameEntity(int x, int y, int width, int height, int baseSpeed) {
        super(x, y, width, height);
        this.baseSpeed = baseSpeed;
        this.speed = baseSpeed;
        this.velocityX = 0;
        this.velocityY = 0;
        this.onGround = false;
    }

    GameEntity(int x, int y, int width, int height, int baseSpeed, int velocityX, int velocityY, boolean onGround) {
        super(x, y, width, height);
        this.baseSpeed = baseSpeed;
        this.speed = baseSpeed;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.onGround = onGround;
    }

    public void gravitate() {
        if (!onGround) {
            velocityY += 76;
        }
    }

    public void slide() {
        if (velocityX > 0) {
            velocityX -= 20;
        } else if (velocityX < 0) {
            velocityX += 20;
        }
    }

    public void slide(double modifier) {
        if (velocityX > 0) {
            velocityX -= speed / 25 * modifier;
        } else if (velocityX < 0) {
            velocityX += speed / 25 * modifier;
        }
    }

    public void updatePositions() {
        setY(getY() + getVelocityY() / 100);
        setX(getX() + getVelocityX() / 100);
    }

    public void preventOffScreenX(int screenWidth) {
        if (getX() < 0) setX(0);
        if (getX() + getWidth() > screenWidth) setX(screenWidth - getWidth());
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBaseSpeed() { return baseSpeed; }
}
