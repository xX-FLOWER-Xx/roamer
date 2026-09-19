package fact.it.roamer.platformertesting.GameElements;

import fact.it.roamer.platformertesting.LevelLoader;
import fact.it.roamer.platformertesting.Interfaces.Collidable;
import fact.it.roamer.platformertesting.Interfaces.Drawable;
import fact.it.roamer.platformertesting.Listeners.GameEventListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Flag extends GameObject implements Collidable, Drawable {

    private ArrayList<GameEventListener> gameEventListeners = new ArrayList<>();
    public void addListener(GameEventListener eventListener) {
        gameEventListeners.add(eventListener);
    }

    Flag(int startX, int startY, int width, int height, LevelLoader levelLoader) {

        super(startX, startY, width, height);
        addListener(levelLoader);

    }

    public void draw(Graphics g) {
        g.setColor(new Color(255, 215, 0));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void checkCollisions(CopyOnWriteArrayList<Player> players) {
        if (players != null) for (Player pl : players) if (this.isCollidingWith(pl)) {
            for (GameEventListener gel : gameEventListeners) gel.onGameEvent("VICTORY");
        }
    }

    @Override
    public void collide(Collidable other) {}

    @Override
    public String toString() {
        return "Flag(" + getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() + ")";
    }
}