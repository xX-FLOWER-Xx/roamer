package fact.it.roamer.platformertesting;

import fact.it.roamer.platformertesting.Enums.GameState;

import java.awt.image.BufferStrategy;

// Runnable is standard java interface
public class GameLoop implements Runnable {

    private Thread thread;

    private final double FIXED_DELTA = 1.0 / 120.0;
    private double accumulator = 0.0;

    private long lastTime = System.nanoTime();
    private long fpsTimer = System.currentTimeMillis();
    private int framesRendered = 0;
    private int currentDisplayFPS = 0;

    private boolean gameRunning;

    private final GameBoard gameBoard;
    private final Renderer renderer;
    private final BufferStrategy bs;

    public GameLoop(GameBoard gameBoard, Renderer renderer, BufferStrategy bs) {
        this.gameBoard = gameBoard;
        this.renderer = renderer;
        this.bs = bs;
    }

    @Override
    public void run() {

        while (gameRunning) {

            long now = System.nanoTime();
            double elapsedSeconds = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            if (elapsedSeconds > 0.25) {
                elapsedSeconds = 0.25;
            }

            accumulator += elapsedSeconds;

            while (accumulator > FIXED_DELTA) {
                updateGamePhysics(FIXED_DELTA);
                accumulator -= FIXED_DELTA;
            }

            renderFrame();
            framesRendered++;

            if (System.currentTimeMillis() - fpsTimer >= 1000) {
                currentDisplayFPS = framesRendered;
                framesRendered = 0;
                fpsTimer += 1000;
            }

        }

    }

    private void updateGamePhysics(double deltaTime) {
        gameBoard.update();
    }

    private void renderFrame() {
        renderer.render(bs, GameState.PLAYING); // swap in real current state if you track one
    }

    public void start_gameloop() {
        gameRunning = true;
        thread = new Thread(this, "gameloop");
        thread.setDaemon(true);
        thread.start();
    }

    public void stop_gameloop() {
        gameRunning = false;
    }

}