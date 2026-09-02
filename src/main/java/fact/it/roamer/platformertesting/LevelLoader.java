package fact.it.roamer.platformertesting;

import fact.it.roamer.platformertesting.Enums.GameState;
import fact.it.roamer.platformertesting.GameElements.*;
import fact.it.roamer.platformertesting.Listeners.GameEventListener;

import java.util.ArrayList;

import static java.lang.System.exit;

public class LevelLoader implements GameEventListener {

    private int currentLevel;
    private GameState gameState;
    private final GameBoard gameBoard;

    public LevelLoader(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void loadLevel(int level) {

        this.currentLevel = level;

        this.gameBoard.setObstacles(null);
        this.gameBoard.setEnemies(null);
        this.gameBoard.setWalls(null);
        this.gameBoard.setPlayers(null);
        this.gameBoard.setFlags(null);

        // Setup level
        switch (level) {

            case 1:
                this.gameBoard.setPortals(new ArrayList<>());
                this.gameBoard.setFlags(new ArrayList<>());
                this.gameBoard.setWalls(new ArrayList<>());
                this.gameBoard.setEnemies(new ArrayList<>());
                this.gameBoard.setObstacles(new ArrayList<>());
                this.gameBoard.setPlayers(new ArrayList<>());

                this.gameBoard.setDrawToolActive(false);

                this.gameBoard.addPlayer(GameObjectFactory.createPlayer(502, 814, 20, 50, 1536, 864, this));
                this.gameBoard.addFlag(GameObjectFactory.createFlag(1009, 814, this));
                break;

            case 2:
                this.gameBoard.setPortals(new ArrayList<>());
                this.gameBoard.setFlags(new ArrayList<>());
                this.gameBoard.setWalls(new ArrayList<>());
                this.gameBoard.setEnemies(new ArrayList<>());
                this.gameBoard.setObstacles(new ArrayList<>());
                this.gameBoard.setPlayers(new ArrayList<>());

                this.gameBoard.setDrawToolActive(false);

                this.gameBoard.addPlayer(GameObjectFactory.createPlayer(502, 814, 20, 50, 1536, 864,this));
                this.gameBoard.addFlag(GameObjectFactory.createFlag(1009, 814, this));
                this.gameBoard.addWall(GameObjectFactory.createWall(748, 564, 40, 300));
                break;

            case 3:
                this.gameBoard.setPortals(new ArrayList<>());
                this.gameBoard.setFlags(new ArrayList<>());
                this.gameBoard.setWalls(new ArrayList<>());
                this.gameBoard.setEnemies(new ArrayList<>());
                this.gameBoard.setObstacles(new ArrayList<>());
                this.gameBoard.setPlayers(new ArrayList<>());

                this.gameBoard.setDrawToolActive(false);

                this.gameBoard.addPlayer(GameObjectFactory.createPlayer(502, 814, 20, 50, 1536, 864, this));
                this.gameBoard.addFlag(GameObjectFactory.createFlag(1009, 814, this));
                this.gameBoard.addObstacle(GameObjectFactory.createObstacle(668, 854, 150, 10));
                this.gameBoard.addObstacle(GameObjectFactory.createObstacle(868, 854, 150, 10));
                break;

            case 4:
                this.gameBoard.setPortals(new ArrayList<>());
                this.gameBoard.setFlags(new ArrayList<>());
                this.gameBoard.setWalls(new ArrayList<>());
                this.gameBoard.setEnemies(new ArrayList<>());
                this.gameBoard.setObstacles(new ArrayList<>());
                this.gameBoard.setPlayers(new ArrayList<>());

                this.gameBoard.setDrawToolActive(false);

                this.gameBoard.addPlayer(GameObjectFactory.createPlayer(502, 814, 20, 50, 1536, 864, this));
                this.gameBoard.addFlag(GameObjectFactory.createFlag(502, 160, this));
                this.gameBoard.addObstacle(GameObjectFactory.createObstacle(868, 640, 668, 10));
                this.gameBoard.addObstacle(GameObjectFactory.createObstacle(0, 690, 700, 10));
                this.gameBoard.addWall(GameObjectFactory.createWall(0, 700, 768, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(868, 600, 668, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(0, 500, 718, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(818, 400, 718, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(0, 300, 768, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(768, 200, 768, 40));
                break;

            case 5:
                this.gameBoard.setPortals(new ArrayList<>());
                this.gameBoard.setFlags(new ArrayList<>());
                this.gameBoard.setWalls(new ArrayList<>());
                this.gameBoard.setEnemies(new ArrayList<>());
                this.gameBoard.setObstacles(new ArrayList<>());
                this.gameBoard.setPlayers(new ArrayList<>());

                this.gameBoard.setDrawToolActive(false);

                this.gameBoard.addPlayer(GameObjectFactory.createPlayer(758, 864, 20, 50, 1536, 864, this));
                this.gameBoard.addFlag(GameObjectFactory.createFlag(150, 160, this));
                this.gameBoard.addObstacle(GameObjectFactory.createObstacle(445, 580, 50, 20));
                this.gameBoard.addObstacle(GameObjectFactory.createObstacle(515, 580, 50, 20));
                this.gameBoard.addEnemy(GameObjectFactory.createEnemy(50, 50, 40, 40));
                this.gameBoard.addEnemy(GameObjectFactory.createEnemy(390, 30, 40, 40));
                this.gameBoard.addEnemy(GameObjectFactory.createEnemy(480, 120, 40, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(0, 200, 800, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(400, 350, 1136, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(400, 600, 1520, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(340, 700, 780, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(300, 330, 40, 410));
                break;
            case 6:
                this.gameBoard.setPortals(new ArrayList<>());
                this.gameBoard.setFlags(new ArrayList<>());
                this.gameBoard.setWalls(new ArrayList<>());
                this.gameBoard.setEnemies(new ArrayList<>());
                this.gameBoard.setObstacles(new ArrayList<>());
                this.gameBoard.setPlayers(new ArrayList<>());

                this.gameBoard.setDrawToolActive(false);

                this.gameBoard.addPlayer(GameObjectFactory.createPlayer(502, 814, 20, 50, 1536, 864, this));
                this.gameBoard.addFlag(GameObjectFactory.createFlag(502, 160, this));
                this.gameBoard.addObstacle(GameObjectFactory.createObstacle(868, 640, 668, 10));
                this.gameBoard.addObstacle(GameObjectFactory.createObstacle(0, 690, 700, 10));
                this.gameBoard.addWall(GameObjectFactory.createWall(0, 700, 768, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(868, 600, 668, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(0, 500, 718, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(818, 400, 718, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(0, 300, 768, 40));
                this.gameBoard.addWall(GameObjectFactory.createWall(768, 200, 768, 40));
                this.gameBoard.addPortal(GameObjectFactory.createPortal(30, 804, 20, 50, 900, 140, 20, 50));
                break;

            default:
                System.out.println("You won the game!");
                exit(0);
        }
    }

    public void loadNextLevel() {

        this.currentLevel += 1;
        loadLevel(currentLevel);

    }

    public void reloadLevel() {

        loadLevel(currentLevel);

    }

    public void checkLevelStatus() {
        if (gameState == GameState.VICTORY) {
            loadNextLevel();
            gameState = GameState.PLAYING;
        }
    }

    @Override
    public void onGameEvent(String eventType) {
        if (eventType.equals("VICTORY")) loadNextLevel();
    }
}
