package fact.it.roamer.platformertesting;

import fact.it.roamer.platformertesting.GameElements.*;
import fact.it.roamer.platformertesting.Listeners.GameEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class LevelLoader implements GameEventListener {

    private ArrayList<GameEventListener> listeners = new ArrayList<>();

    private int currentLevel;
    private final GameBoard gameBoard;
    private boolean editor;

    public LevelLoader(GameBoard gameBoard) {
        currentLevel = -1;
        this.gameBoard = gameBoard;

        LevelEditor levelEditor = new LevelEditor(this);
        levelEditor.addGameEventListener(gameBoard);
        addListener(levelEditor);
    }

    public void loadEditor() {

        editor = true;

        this.gameBoard.setPortals(new CopyOnWriteArrayList<>());
        this.gameBoard.setFlags(new CopyOnWriteArrayList<>());
        this.gameBoard.setWalls(new CopyOnWriteArrayList<>());
        this.gameBoard.setEnemies(new CopyOnWriteArrayList<>());
        this.gameBoard.setObstacles(new CopyOnWriteArrayList<>());
        this.gameBoard.setPlayers(new CopyOnWriteArrayList<>());

        this.gameBoard.setDrawToolActive(true);

    }

    public void loadFromSave(File file) {

        loadEditor();
        this.gameBoard.setDrawToolActive(false);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String[] parameters;

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("\\(");
            switch (line[0]) {
                case "Portal":
                    parameters = (line[1].substring(0, line[1].length()-1)).split(", ");
                    this.gameBoard.addPortal(GameObjectFactory.createPortal(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]), Integer.parseInt(parameters[5]), Integer.parseInt(parameters[6]), Integer.parseInt(parameters[7])));
                    break;
                case "Obstacle":
                    parameters = (line[1].substring(0, line[1].length()-1)).split(", ");
                    this.gameBoard.addObstacle(GameObjectFactory.createObstacle(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3])));
                    break;
                case "Enemy":
                    parameters = (line[1].substring(0, line[1].length()-1)).split(", ");
                    this.gameBoard.addEnemy(GameObjectFactory.createEnemy(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3])));
                    break;
                case "Wall":
                    parameters = (line[1].substring(0, line[1].length()-1)).split(", ");
                    this.gameBoard.addWall(GameObjectFactory.createWall(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3])));
                    break;
                case "Flag":
                    parameters = (line[1].substring(0, line[1].length()-1)).split(", ");
                    this.gameBoard.addFlag(GameObjectFactory.createFlag(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), this));
                    break;
                case "Player":
                    parameters = (line[1].substring(0, line[1].length()-1)).split(", ");
                    this.gameBoard.addPlayer(GameObjectFactory.createPlayer(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), this));
                    break;
            }
        }

    }

    public void loadLevel(int level_id) {

        currentLevel = level_id;
        // Setup level
        if (new File(System.getenv("LOCALAPPDATA") + "\\Roamer\\levels\\" + level_id).exists()) {
            loadFromSave(new File(System.getenv("LOCALAPPDATA") + "\\Roamer\\levels\\" + level_id));
        } else {
            loadEditor();
        }

    }

    public void loadNextLevel() {

        this.currentLevel += 1;
        loadLevel(currentLevel);

    }

    public void reloadLevel() {

        loadLevel(currentLevel);

    }

    public void trySave() {
        if (editor) {
            for (GameEventListener listener: listeners) {listener.onGameEvent("SAVE");}
        }
    }

    public ArrayList<Object> getLevelObjects() {
        ArrayList<Object> temp = new ArrayList<>();
        temp.addAll(this.gameBoard.getPortals());
        temp.addAll(this.gameBoard.getObstacles());
        temp.addAll(this.gameBoard.getEnemies());
        temp.addAll(this.gameBoard.getWalls());
        temp.addAll(this.gameBoard.getFlags());
        temp.addAll(this.gameBoard.getPlayers());
        return temp;
    }

    public int getLevelCount() {
        File file = new File(System.getenv("LOCALAPPDATA") + "\\Roamer\\levels");
        return file.listFiles().length;
    }

    public File getLevelByCount(int i) {
        File file = new File(System.getenv("LOCALAPPDATA") + "\\Roamer\\levels");
        return file.listFiles()[i];
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public boolean isEditor() {
        return editor;
    }

    private void addListener(GameEventListener gel) {listeners.add(gel);}

    @Override
    public void onGameEvent(String eventType) {
        if (eventType.equals("VICTORY")) loadNextLevel();
    }
}
