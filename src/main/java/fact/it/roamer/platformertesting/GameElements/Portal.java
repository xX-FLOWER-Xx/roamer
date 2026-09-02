package fact.it.roamer.platformertesting.GameElements;

import fact.it.roamer.platformertesting.Interfaces.Collidable;
import fact.it.roamer.platformertesting.Interfaces.Drawable;

import java.awt.*;
import java.util.ArrayList;

public class Portal {

    private PortalIn portalIn;
    private PortalOut portalOut;
    private int portalCooldown;

    Portal(int portalInX, int portalInY, int portalInWidth, int portalInHeight, int portalOutX, int portalOutY, int portalOutWidth, int portalOutHeight) {
        this.portalIn = new PortalIn(portalInX, portalInY, portalInWidth, portalInHeight);
        this.portalOut = new PortalOut(portalOutX, portalOutY, portalOutWidth, portalOutHeight);
        this.portalCooldown = 0;
    }

    public void draw(Graphics g) {
        portalIn.draw(g);
        portalOut.draw(g);
    }

    public void checkCollisions(ArrayList<Player> players, ArrayList<Enemy> enemies) {

        if (portalCooldown > 0) {
            portalCooldown--;
            return;
        }

        // Check portalIn collisions with the player
        if (players != null) for (Player pl : players) if (portalIn.isCollidingWith(pl)) {
            portalIn.collide(pl);
            portalCooldown = 60;
            return;
        }
        if (enemies != null) for (Enemy en : enemies) if (portalIn.isCollidingWith(en)) {
            portalIn.collide(en);
            portalCooldown = 60;
            return;
        }

        // Check portalIn collisions with the player
        if (players != null) for (Player pl : players) if (portalOut.isCollidingWith(pl)) {
            portalOut.collide(pl);
            portalCooldown = 60;
            return;
        }
        if (enemies != null) for (Enemy en : enemies) if (portalOut.isCollidingWith(en)) {
            portalOut.collide(en);
            portalCooldown = 60;
            return;
        }

    }

    // SubClasses

    private class PortalOut implements Drawable {

        private final int portalOutX;
        private final int portalOutY;
        private final int portalOutWidth;
        private final int portalOutHeight;

        private PortalOut(int portalInX, int portalInY, int portalInWidth, int portalInHeight) {
            this.portalOutX = portalInX;
            this.portalOutY = portalInY;
            this.portalOutWidth = portalInWidth;
            this.portalOutHeight = portalInHeight;
        }

        private boolean isCollidingWith(Collidable other) {
            return this.getHitbox().intersects(other.getHitbox());
        }

        public void draw(Graphics g) {
            g.setColor(new Color(255, 140, 0));
            g.fillRect(portalOutX, portalOutY, portalOutWidth, portalOutHeight);
        }

        public Rectangle getHitbox() {
            return new Rectangle(portalOutX, portalOutY, portalOutWidth, portalOutHeight);
        }

        public void collide(Collidable other) {

            // Player collision
            if (other instanceof Player player) {
                player.setY(portalIn.portalInY);
                player.setX(portalIn.portalInX + portalIn.portalInWidth/2);
            }

            // Enemy collision
            if (other instanceof Enemy enemy) {
                enemy.setY(portalIn.portalInY);
                enemy.setX(portalIn.portalInX + portalIn.portalInWidth/2);
            }

        }

    }


    private class PortalIn implements Drawable {

        private final int portalInX;
        private final int portalInY;
        private final int portalInWidth;
        private final int portalInHeight;

        private PortalIn(int portalInX, int portalInY, int portalInWidth, int portalInHeight) {
            this.portalInX = portalInX;
            this.portalInY = portalInY;
            this.portalInWidth = portalInWidth;
            this.portalInHeight = portalInHeight;
        }

        private boolean isCollidingWith(Collidable other) {
            return this.getHitbox().intersects(other.getHitbox());
        }

        public void draw(Graphics g) {
            g.setColor(new Color(0, 51, 0));
            g.fillRect(portalInX, portalInY, portalInWidth, portalInHeight);
        }

        public Rectangle getHitbox() {
            return new Rectangle(portalInX, portalInY, portalInWidth, portalInHeight);
        }

        public void collide(Collidable other) {

            // Player collision
            if (other instanceof Player player) {
                player.setY(portalOut.portalOutY);
                player.setX(portalOut.portalOutX + portalOut.portalOutWidth/2);
            }

            // Enemy collision
            if (other instanceof Enemy enemy) {
                enemy.setY(portalOut.portalOutY);
                enemy.setX(portalOut.portalOutX + portalOut.portalOutWidth/2);
            }

        }

    }

}