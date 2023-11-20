package ui;

import model.Item;
import model.User;

import javax.swing.*;
import java.awt.*;

public class ShoppingUI extends JPanel implements User {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 30;
    private static final int TEXT_INDENT = 30;
    private static final String[] data = {"S i l e n t...", "R i n g i n g..."};
    private int displayString;
    private Color fillColor;

    public ShoppingUI() {
        displayString = 0;
        fillColor = Color.pink;
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(fillColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawChars(data[displayString].toCharArray(),
                0,
                data[displayString].length(),
                TEXT_INDENT,
                2 * getHeight() / 3);
    }

    @Override
    public void update(boolean isShopping) {
        displayString = (isShopping ? 1 : 0);
        fillColor = (isShopping ? Color.RED : Color.GREEN);
        repaint();
    }
}
