package fact.it.roamer.platformertesting.BackgroundObjects;

import fact.it.roamer.platformertesting.Interfaces.Drawable;

import java.awt.*;

public class GameButton implements Drawable {
    private Rectangle bounds;
    private String text;

    public GameButton(int x, int y, int width, int height, String text) {
        this.bounds = new Rectangle(x, y, width, height);
        this.text = text;
    }

    public boolean isClicked(int mouseX, int mouseY) {
        return bounds.contains(mouseX, mouseY);
    }

    public int getLevelId() {
        return Integer.parseInt(text);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(Color.WHITE);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.drawString(text, bounds.x + 10, bounds.y + bounds.height / 2);
    }
}