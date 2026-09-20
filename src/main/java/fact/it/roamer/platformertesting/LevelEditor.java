package fact.it.roamer.platformertesting;

import fact.it.roamer.platformertesting.Listeners.GameEventListener;

import java.util.ArrayList;

public class LevelEditor implements GameEventListener {

    private LevelLoader levelLoader;
    private final ReadWriteController rwc = new ReadWriteController();

    ArrayList<GameEventListener> gameEventListeners = new ArrayList<>();

    public LevelEditor(LevelLoader levelLoader) {
        this.levelLoader = levelLoader;
    }

    private void saveLevel() {
        rwc.write_objects(levelLoader.getLevelObjects(), String.valueOf(levelLoader.getLevelCount() + 1));
        for (GameEventListener gel : gameEventListeners) gel.onGameEvent("LEVEL_SAVE");
    }

    @Override
    public void onGameEvent(String eventType) {
        if (eventType.equals("SAVE")) saveLevel();
    }

    public void addGameEventListener(GameEventListener gel) {
        gameEventListeners.add(gel);
    }

}
