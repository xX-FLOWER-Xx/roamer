package fact.it.roamer.platformertesting.Interfaces;

import fact.it.roamer.platformertesting.GameElements.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public interface Collidable {

    Rectangle getHitbox();

    void collide(Collidable other);

    default boolean isCollidingWith(Collidable other) {
        return this.getHitbox().intersects(other.getHitbox());
    }

    static void checkAllCollisions(CopyOnWriteArrayList<Portal> portals, CopyOnWriteArrayList<Obstacle> obstacles, CopyOnWriteArrayList<Enemy> enemies, CopyOnWriteArrayList<Wall> walls, CopyOnWriteArrayList<Flag> flags, CopyOnWriteArrayList<Player> players) {

        // Resetting ground check
        if (players != null) for (Player pl : players) pl.setOnGround(false);
        if (enemies != null) for (Enemy en : enemies) en.setOnGround(false);

        // Checking actual collisions
        if (portals != null) for (Portal po : portals) po.checkCollisions(players, enemies);
        if (obstacles != null) for (Obstacle ob : obstacles) ob.checkCollisions(ob, players, enemies);
        if (enemies != null) for (Enemy en : enemies) en.checkCollisions(en, enemies, flags, players);
        if (walls != null) for (Wall wa : walls) wa.checkCollisions(wa, players, enemies);
        if (flags != null) for (Flag fl : flags) fl.checkCollisions(players);
        if (players != null) for (Player pl : players) pl.checkCollisions();
    }

}