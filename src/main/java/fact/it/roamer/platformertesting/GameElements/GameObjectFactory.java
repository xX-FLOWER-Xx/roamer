package fact.it.roamer.platformertesting.GameElements;

import fact.it.roamer.platformertesting.LevelLoader;

public class GameObjectFactory {

    public static Player createPlayer(int x, int y, LevelLoader levelLoader) {
        return createPlayer(x, y, 40, 50, 1536, 864, 420, levelLoader);
    }

    public static Player createPlayer(int x, int y, int width, int height, LevelLoader levelLoader) {
        return createPlayer(x, y, width, height, 1536, 864, 420, levelLoader);
    }

    public static Player createPlayer(int x, int y, int width, int height, int speed, LevelLoader levelLoader) {
        return createPlayer(x, y, width, height, 1536, 864, speed, levelLoader);
    }

    public static Player createPlayer(int x, int y, int width, int height, int screenWidth, int screenHeight, LevelLoader levelLoader) {
        return createPlayer(x, y, width, height, screenWidth, screenHeight, 420, levelLoader);
    }

    public static Player createPlayer(int x, int y, int width, int height, int screenWidth, int screenHeight, int speed, LevelLoader levelLoader) {
        return new Player(x, y, width, height, screenWidth, screenHeight, speed, levelLoader);
    }

    public static Enemy createEnemy(int startX, int startY) {
        return createEnemy(startX, startY, 40, 40, 252);
    }

    public static Enemy createEnemy(int startX, int startY, int width, int height) {
        return createEnemy(startX, startY, width, height, 252);
    }

    public static Enemy createEnemy(int startX, int startY, int width, int height, int baseSpeed) {
        return new Enemy(startX, startY, width, height, baseSpeed);
    }

    public static Flag createFlag(int startX, int startY, LevelLoader levelLoader) {
        return createFlag(startX, startY, 30, 30, levelLoader);
    }

    public static Flag createFlag(int startX, int startY, int width, int height, LevelLoader levelLoader) {
        return new Flag(startX, startY, width, height, levelLoader);
    }

    public static Obstacle createObstacle(int startX, int startY) {
        return createObstacle(startX, startY, 40, 10);
    }

    public static Obstacle createObstacle(int startX, int startY, int width, int height) {
        return new Obstacle(startX, startY, width, height);
    }

    public static Portal createPortal(int portalInX, int portalInY, int portalInWidth, int portalInHeight, int portalOutX, int portalOutY, int portalOutWidth, int portalOutHeight) {
        return new Portal(portalInX, portalInY, portalInWidth, portalInHeight, portalOutX, portalOutY, portalOutWidth, portalOutHeight);
    }

    public static Wall createWall(int x, int y, int width, int height) {
        return new Wall(x, y, width, height);
    }

}
