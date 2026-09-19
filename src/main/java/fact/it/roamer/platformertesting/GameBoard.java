package fact.it.roamer.platformertesting;

import fact.it.roamer.platformertesting.Enums.DrawTool;
import fact.it.roamer.platformertesting.GameElements.*;
import fact.it.roamer.platformertesting.Interfaces.Collidable;
import fact.it.roamer.platformertesting.Interfaces.Movable;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameBoard implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean savePressed;

    private CopyOnWriteArrayList<Portal> portals;
    private CopyOnWriteArrayList<Obstacle> obstacles;
    private CopyOnWriteArrayList<Enemy> enemies;
    private CopyOnWriteArrayList<Wall> walls;
    private CopyOnWriteArrayList<Flag> flags;
    private CopyOnWriteArrayList<Player> players;

    private DrawTool drawTool;
    private boolean drawToolActive;

    private int screenWidth;
    private int screenHeight;

    private final LevelLoader loader = new LevelLoader(this);

    public GameBoard() { // This is the constructor

        loader.loadNextLevel();
        drawTool = DrawTool.WALL;

    }

    public void setScreenSize(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (drawToolActive) {
            if (drawTool == DrawTool.WALL) {
                Wall wall = GameObjectFactory.createWall(e.getX(), e.getY(), 10, 10);
                walls.add(wall);
            }
            if (drawTool == DrawTool.OBSTACLE) {
                Obstacle obstacle = GameObjectFactory.createObstacle(e.getX(), e.getY(), 10, 10);
                obstacles.add(obstacle);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = e.getWheelRotation();
        if (rotation < 0) {
            drawTool = DrawTool.values()[(drawTool.ordinal() + 1) % DrawTool.values().length];
        } else {
            drawTool = DrawTool.values()[(drawTool.ordinal() + DrawTool.values().length - 1) % DrawTool.values().length];
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (drawToolActive) {
            if (drawTool == DrawTool.PLAYER) {
                Player player = GameObjectFactory.createPlayer(e.getX(), e.getY(), 20, 50, loader);
                players.add(player);
            }
            if (drawTool == DrawTool.FLAG) {
                Flag flag = GameObjectFactory.createFlag(e.getX(), e.getY(), loader);
                flags.add(flag);
            }
            if (drawTool == DrawTool.ENEMY) {
                Enemy enemy = GameObjectFactory.createEnemy(e.getX(), e.getY());
                enemies.add(enemy);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_Z) upPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_S) downPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_Q) leftPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_D) rightPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_P) savePressed = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_Z) upPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_S) downPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_Q) leftPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_D) rightPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_P) savePressed = false;

    }

    // Called once per fixed physics tick by GameLoop
    public void update() {

        if (savePressed) {loader.trySave();}

        updateVariables();
        Movable.checkAllMovements(players, enemies);
        Collidable.checkAllCollisions(portals, obstacles, enemies, walls, flags, players);

    }

    // Called once per frame by Renderer
    public void draw(Graphics g) {

        if (portals != null) for (Portal po : portals) po.draw(g);
        if (players != null) for (Player pl : players) pl.draw(g);
        if (flags != null) for (Flag fl : flags) fl.draw(g);
        if (obstacles != null) for (Obstacle ob : obstacles) ob.draw(g);
        if (enemies != null) for (Enemy en : enemies) en.draw(g);
        if (walls != null) for (Wall wall : walls) wall.draw(g);

    }

    private void updateVariables() {

        if (players != null) {
            for (Player pl : players) {
                pl.setUp(this.upPressed);
                pl.setLeft(this.leftPressed);
                pl.setRight(this.rightPressed);
                pl.setScreenWidth(this.screenWidth);
                pl.setScreenHeight(this.screenHeight);
            }
        }

        if (enemies != null) {
            for (Enemy en : enemies) en.setScreenWidth(this.screenWidth);
            for (Enemy en : enemies) en.setScreenHeight(this.screenHeight);
        }

    }

    // Setters

    public void setPortals(CopyOnWriteArrayList<Portal> portals) {
        this.portals = portals;
    }

    public void setObstacles(CopyOnWriteArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void setEnemies(CopyOnWriteArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setWalls(CopyOnWriteArrayList<Wall> walls) {
        this.walls = walls;
    }

    public void setPlayers(CopyOnWriteArrayList<Player> players) {
        this.players = players;
    }

    public void setFlags(CopyOnWriteArrayList<Flag> flags) {
        this.flags = flags;
    }

    public void setDrawToolActive(boolean drawToolActive) {
        this.drawToolActive = drawToolActive;
    }

    // Adders

    public void addPortal(Portal portal) {
        portals.add(portal);
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addFlag(Flag flag) {
        flags.add(flag);
    }

    // Getters


    public List<Portal> getPortals() {
        return portals;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Flag> getFlags() {
        return flags;
    }

    public List<Player> getPlayers() {
        return players;
    }
}