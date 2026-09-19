package fact.it.roamer.platformertesting;

import fact.it.roamer.platformertesting.Listeners.GameEventListener;

public class LevelEditor implements GameEventListener {

    private LevelLoader levelLoader;
    private final ReadWriteController rwc = new ReadWriteController();

    public LevelEditor(LevelLoader levelLoader) {
        this.levelLoader = levelLoader;
    }

    private void saveLevel() {
        rwc.write_objects(levelLoader.getLevelObjects());
    }

    @Override
    public void onGameEvent(String eventType) {
        if (eventType.equals("SAVE")) {
            saveLevel();
        }
    }

}
