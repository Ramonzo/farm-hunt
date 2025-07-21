package br.ufba.poo.Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.Sprite;
import br.ufba.poo.Engine.Transform2D;
import br.ufba.poo.Engine.Vector2;

public class CustomFont extends JPanel {
    private Transform2D transform2D;
    private GridBagConstraints gbc;

    private Sprite sprite;
    private BufferedImage spriteImage;

    private Character text;

    private static HashMap<Character, Vector2> letterPositions;
    public static int letterSize = 16;

    public CustomFont() {
        setOpaque(false);
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        Vector2 position = new Vector2(0, 0);
        Vector2 scale = new Vector2(2.5f, 2.5f);
        transform2D = new Transform2D(position, scale);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        sprite = new Sprite(Constants.GUI_PATH + "/font_normal.png");

        loadFontPositions();

        setPreferredSize(new Dimension(32, 32));
    }

    public CustomFont(String color) {
        this();

        sprite = new Sprite(Constants.GUI_PATH + "/font_" + color + ".png");
    }

    public void text(Character text) {
        this.text = text;
    }

    public void load() {
        if (spriteImage == null) {
            sprite.load();
            spriteImage = sprite.getImage();
        }

        updateComponent();
    }

    private void updateComponent() {
        int x = (int) (this.transform2D.position.x);
        int y = (int) (this.transform2D.position.y);

        setPreferredSize(new Dimension(getWidth(), getHeight()));
        setBounds(x, y, getWidth(), getHeight());

        revalidate();
        repaint();
    }

    @Override
    public int getWidth() {
        return (int) (letterSize * this.transform2D.scale.x);
    }

    @Override
    public int getHeight() {
        return (int) (letterSize * this.transform2D.scale.y);
    }

    public void setPosition(int x, int y) {
        Vector2 position = new Vector2(x, y);
        Vector2 scale = new Vector2(this.transform2D.scale);
        this.transform2D = new Transform2D(position, scale);

        updateComponent();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());

        if (spriteImage != null) {
            Vector2 letterPosition = letterPositions.get(text);

            if (letterPosition == null)
                letterPosition = letterPositions.get('?');

            g.drawImage(spriteImage, (int) -(letterPosition.x * getWidth()), (int) -(letterPosition.y * getHeight()),
                    (int) (spriteImage.getWidth() * this.transform2D.scale.x),
                    (int) (spriteImage.getHeight() * this.transform2D.scale.y), null);
        }
    }

    public void loadFontPositions() {
        if (letterPositions == null) {
            letterPositions = new HashMap<>();
            letterPositions.put('a', new Vector2(0, 0));
            letterPositions.put('A', new Vector2(1, 0));
            letterPositions.put('b', new Vector2(2, 0));
            letterPositions.put('B', new Vector2(3, 0));
            letterPositions.put('c', new Vector2(4, 0));
            letterPositions.put('C', new Vector2(5, 0));
            letterPositions.put('d', new Vector2(6, 0));
            letterPositions.put('D', new Vector2(7, 0));
            letterPositions.put('e', new Vector2(8, 0));
            letterPositions.put('E', new Vector2(9, 0));
            letterPositions.put('f', new Vector2(10, 0));
            letterPositions.put('F', new Vector2(11, 0));
            letterPositions.put('g', new Vector2(12, 0));
            letterPositions.put('G', new Vector2(13, 0));

            letterPositions.put('h', new Vector2(0, 1));
            letterPositions.put('H', new Vector2(1, 1));
            letterPositions.put('i', new Vector2(2, 1));
            letterPositions.put('I', new Vector2(3, 1));
            letterPositions.put('j', new Vector2(4, 1));
            letterPositions.put('J', new Vector2(5, 1));
            letterPositions.put('k', new Vector2(6, 1));
            letterPositions.put('K', new Vector2(7, 1));
            letterPositions.put('l', new Vector2(8, 1));
            letterPositions.put('L', new Vector2(9, 1));
            letterPositions.put('m', new Vector2(10, 1));
            letterPositions.put('M', new Vector2(11, 1));
            letterPositions.put('n', new Vector2(12, 1));
            letterPositions.put('N', new Vector2(13, 1));

            letterPositions.put('o', new Vector2(0, 2));
            letterPositions.put('O', new Vector2(1, 2));
            letterPositions.put('p', new Vector2(2, 2));
            letterPositions.put('P', new Vector2(3, 2));
            letterPositions.put('q', new Vector2(4, 2));
            letterPositions.put('Q', new Vector2(5, 2));
            letterPositions.put('r', new Vector2(6, 2));
            letterPositions.put('R', new Vector2(7, 2));
            letterPositions.put('s', new Vector2(8, 2));
            letterPositions.put('S', new Vector2(9, 2));
            letterPositions.put('t', new Vector2(10, 2));
            letterPositions.put('T', new Vector2(11, 2));
            letterPositions.put('u', new Vector2(12, 2));
            letterPositions.put('U', new Vector2(13, 2));

            letterPositions.put('v', new Vector2(0, 3));
            letterPositions.put('V', new Vector2(1, 3));
            letterPositions.put('w', new Vector2(2, 3));
            letterPositions.put('W', new Vector2(3, 3));
            letterPositions.put('x', new Vector2(4, 3));
            letterPositions.put('X', new Vector2(5, 3));
            letterPositions.put('y', new Vector2(6, 3));
            letterPositions.put('Y', new Vector2(7, 3));
            letterPositions.put('z', new Vector2(8, 3));
            letterPositions.put('Z', new Vector2(9, 3));
            letterPositions.put(' ', new Vector2(10, 3));

            letterPositions.put('?', new Vector2(0, 4));
            letterPositions.put('+', new Vector2(1, 4));
            letterPositions.put('-', new Vector2(2, 4));
            letterPositions.put('=', new Vector2(3, 4));
            letterPositions.put('%', new Vector2(4, 4));

            letterPositions.put('1', new Vector2(0, 5));
            letterPositions.put('2', new Vector2(1, 5));
            letterPositions.put('3', new Vector2(2, 5));
            letterPositions.put('4', new Vector2(3, 5));
            letterPositions.put('5', new Vector2(4, 5));
            letterPositions.put('6', new Vector2(5, 5));
            letterPositions.put('7', new Vector2(6, 5));
            letterPositions.put('8', new Vector2(7, 5));
            letterPositions.put('9', new Vector2(8, 5));
            letterPositions.put('0', new Vector2(9, 5));
        }
    }
}
