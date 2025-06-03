package br.ufba.poo.Interface;

import javax.swing.*;

import br.ufba.poo.Engine.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Button extends JComponent {
    private ButtonSprite buttonSprite;
    private Transform2D transform2D;

    private BufferedImage currentImage;

    private CustomText cardText;
    private String text;
    private boolean hovering = false;
    private boolean clicking = false;
    private Runnable callback;
    private Graphics2D g2d;

    public Button(String basePath, String hoverPath, String clickPath) {
        buttonSprite = new ButtonSprite(basePath, hoverPath, clickPath);

        Vector2 position = new Vector2(0, 0);
        Vector2 scale = new Vector2(1, 1);
        transform2D = new Transform2D(position, scale);

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public void load() {
        buttonSprite.load();
        currentImage = buttonSprite.getSpriteAction(ButtonSprite.BASE_IMAGE_HASH);

        cardText.load();
        cardText.setPosition(0, 0);

        add(cardText, g2d);

        setupListeners();
        updateComponent();
    }

    public void setPosition(int x, int y) {
        Vector2 position = new Vector2(x, y);
        Vector2 scale = new Vector2(this.transform2D.scale);
        this.transform2D = new Transform2D(position, scale);

        updateComponent();
    }

    public void setScale(float x, float y) {
        Vector2 position = new Vector2(this.transform2D.position);
        Vector2 scale = new Vector2(x, y);
        this.transform2D = new Transform2D(position, scale);

        updateComponent();
    }

    private void setupListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (callback != null)
                    callback.run();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                clicking = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                clicking = false;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
                clicking = false;
                repaint();
            }
        });
    }

    public void setText(String text) {
        this.text = text;
        cardText = new CustomText(text);

        load();

        repaint();
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    private void updateComponent() {
        if (currentImage != null) {
            setPreferredSize(new Dimension(getWidth(), getHeight()));

            this.setBounds((int) (this.transform2D.position.x - (getWidth() / 2)),
                    (int) (this.transform2D.position.y - (getHeight() / 2)), getWidth(), getHeight());
        }

        revalidate();
        repaint();
    }

    @Override
    public int getWidth() {
        int padding = 0;
        int width = (int) (currentImage.getWidth() * this.transform2D.scale.x);

        if (width < cardText.getWidth())
            padding = cardText.getWidth() - width;

        return width + padding;
    }

    @Override
    public int getHeight() {
        return (int) (currentImage.getHeight() * this.transform2D.scale.y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g.create();

        currentImage = buttonSprite.getSpriteAction(ButtonSprite.BASE_IMAGE_HASH);

        if (clicking) {
            currentImage = buttonSprite.getSpriteAction(ButtonSprite.CLICK_IMAGE_HASH);
        } else if (hovering) {
            currentImage = buttonSprite.getSpriteAction(ButtonSprite.HOVER_IMAGE_HASH);
        }

        if (currentImage != null) {

            g2d.drawImage(currentImage, 0, 0, getWidth(), getHeight(), null);
        }

        g2d.dispose();
    }
}
