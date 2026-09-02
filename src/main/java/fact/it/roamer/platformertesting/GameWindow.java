package fact.it.roamer.platformertesting;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameWindow {
    public static void main(String[] args) {

        Canvas canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(1536, 864));
        canvas.setIgnoreRepaint(true);
        canvas.setFocusable(true);

        JFrame frame = new JFrame("Roamer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);                 // removes title bar/borders
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // maximizes to screen size

        frame.add(canvas);
        frame.setVisible(true);   // no frame.pack() — let MAXIMIZED_BOTH size it

        canvas.createBufferStrategy(2);
        BufferStrategy bs = canvas.getBufferStrategy();

        GameBoard gameBoard = new GameBoard();
        gameBoard.setScreenSize(canvas.getWidth(), canvas.getHeight());

        canvas.addKeyListener(gameBoard);
        canvas.addMouseListener(gameBoard);
        canvas.addMouseMotionListener(gameBoard);
        canvas.addMouseWheelListener(gameBoard);
        canvas.requestFocusInWindow();

        Renderer renderer = new Renderer(canvas, gameBoard);
        GameLoop loop = new GameLoop(gameBoard, renderer, bs);
        loop.start_gameloop();
    }
}