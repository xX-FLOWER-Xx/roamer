package fact.it.roamer.platformertesting;

import fact.it.roamer.platformertesting.Enums.GameState;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer {

    private final Canvas canvas;
    private final GameBoard gameBoard;

    public Renderer(Canvas canvas, GameBoard gameBoard) {
        this.canvas = canvas;
        this.gameBoard = gameBoard;
    }

    public void render(BufferStrategy bs, GameState state) {
        Graphics2D g = null;
        do {
            do {
                try {
                    g = (Graphics2D) bs.getDrawGraphics();
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    gameBoard.draw(g);
                } finally {
                    if (g != null) g.dispose();
                }
            } while (bs.contentsRestored());
            bs.show();
        } while (bs.contentsLost());
    }
}