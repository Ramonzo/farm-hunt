package br.ufba.poo.Interface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.Transform2D;
import br.ufba.poo.Engine.Vector2;

public class ArrowUp extends JComponent {
    private ButtonSprite buttonSprite;

    private Transform2D transform2D;

    private BufferedImage currentImage;

    private boolean hovering = false;
    private boolean clicking = false;
    private Runnable callback;

    public ArrowUp() {
        buttonSprite = new ButtonSprite(Constants.GUI_PATH + "/arrow_up_small_base.png",
                Constants.GUI_PATH + "/arrow_up_small_hover.png", Constants.GUI_PATH + "/arrow_up_small_click.png");

        Vector2 position = new Vector2(0, 0);
        Vector2 scale = new Vector2(1, 1);
        transform2D = new Transform2D(position, scale);

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public void load() {
        buttonSprite.load();
        currentImage = buttonSprite.getSpriteAction(ButtonSprite.BASE_IMAGE_HASH);

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

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    private void updateComponent() {
        if (currentImage != null) {
            int width = (int) (currentImage.getWidth() * this.transform2D.scale.x);
            int height = (int) (currentImage.getHeight() * this.transform2D.scale.y);

            setPreferredSize(new Dimension(width, height));

            this.setBounds((int) (this.transform2D.position.x - (width / 2)),
                    (int) (this.transform2D.position.y - (height / 2)), width, height);
        }

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

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