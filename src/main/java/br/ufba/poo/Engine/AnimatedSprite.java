package br.ufba.poo.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSprite extends JComponent {
    private Sprite sprite;
    private Transform2D transform2D;

    private BufferedImage currentImage;
    private Vector2 bounds;

    private int spritePos = 0;
    private boolean loop = false;

    public AnimatedSprite(String path, Vector2 bounds) {
        sprite = new Sprite(path);
        this.bounds = bounds;

        Vector2 position = new Vector2(0, 0);
        Vector2 scale = new Vector2(1, 1);
        transform2D = new Transform2D(position, scale);

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public void load() {
        sprite.load();
        currentImage = sprite.getImage();

        updateComponent();
    }

    public void update() {
        if (spritePos < (currentImage.getWidth() / bounds.x))
            spritePos++;

        if (loop && spritePos > (currentImage.getWidth() / bounds.x))
            spritePos = 0;
    }

    public void setPosition(int x, int y) {
        Vector2 position = new Vector2(x, y);
        Vector2 scale = new Vector2(this.transform2D.scale);
        this.transform2D = new Transform2D(position, scale);

        updateComponent();
    }

    public void setScale(int x, int y) {
        Vector2 position = new Vector2(this.transform2D.position);
        Vector2 scale = new Vector2(x, y);
        this.transform2D = new Transform2D(position, scale);

        updateComponent();
    }

    private void updateComponent() {
        if (currentImage != null) {
            int width = (int) (bounds.x * this.transform2D.scale.x);
            int height = (int) (bounds.y * this.transform2D.scale.y);

            setPreferredSize(new Dimension(width, height));

            int boundPosX = (int) (this.transform2D.position.x - (width / 2));
            int boundPosY = (int) ((int) (this.transform2D.position.y - (height / 2)));

            this.setBounds(boundPosX, boundPosY, width, height);
        }

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        if (currentImage != null) {
            int width = (int) (currentImage.getWidth() * transform2D.scale.x);
            int height = (int) (currentImage.getHeight() * transform2D.scale.y);

            g2d.drawImage(currentImage, getFrameBound(), 0, width, height, null);
        }

        g2d.dispose();
    }

    private int getFrameBound() {
        if (spritePos < 1)
            return 0;

        int scaledBoundX = (int) -(bounds.x * transform2D.scale.x);

        return scaledBoundX * (spritePos - 1);
    }
}
