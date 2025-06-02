package br.ufba.poo.Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.Sprite;
import br.ufba.poo.Engine.Transform2D;
import br.ufba.poo.Engine.Vector2;

public class Slider extends JPanel {
    private ArrowUp arrowUp;
    private ArrowDown arrowDown;
    private RoundedButton roundedButton;
    private Sprite slideBar;

    private Transform2D transform2D;

    private int value = 0;
    private Runnable callback;

    protected GridBagConstraints gbc;
    private BufferedImage sliderImage;
    private Vector2 padding;
    private int maxValue;

    public Slider() {
        setOpaque(false);
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        padding = new Vector2(15, 50);
        Vector2 position = new Vector2(100, 100);
        Vector2 scale = new Vector2(3, 3);
        transform2D = new Transform2D(position, scale);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        arrowUp = new ArrowUp();
        arrowDown = new ArrowDown();

        roundedButton = new RoundedButton();

        slideBar = new Sprite(Constants.GUI_PATH + "/slider_vertical.png");

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int dynamicValue() {
        return (value * 60) / maxValue;
    }

    public int getValue() {
        return value;
    }

    public void update() {
        int y = 40 + dynamicValue();
        int x = getWidth() / 2;

        roundedButton.setPosition(x, y);

        // System.out.println("value: " + value + " | y:" + y);
    }

    public void load() {
        slideBar.load();
        sliderImage = slideBar.getImage();

        arrowUp.setPosition(getWidth() / 2, 15);
        arrowUp.setScale((int) (transform2D.scale.x), (int) (transform2D.scale.y));
        arrowUp.setCallback(this::decrement);

        roundedButton.setPosition(getWidth() / 2, 40 + dynamicValue());
        roundedButton.setScale((int) (transform2D.scale.x), (int) (transform2D.scale.y));

        arrowDown.setPosition(getWidth() / 2, getHeight() - 15);

        arrowDown.setScale((int) (transform2D.scale.x), (int) (transform2D.scale.y));
        arrowDown.setCallback(this::increment);

        arrowUp.load();
        roundedButton.load();
        arrowDown.load();

        add(arrowUp, gbc);
        add(roundedButton, gbc);
        add(arrowDown, gbc);

        updateComponent();
    }

    public void setPosition(int x, int y) {
        Vector2 position = new Vector2(x, y);
        Vector2 scale = new Vector2(this.transform2D.scale);
        this.transform2D = new Transform2D(position, scale);

        updateComponent();
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    @Override
    public int getWidth() {
        return (int) ((sliderImage.getWidth() * this.transform2D.scale.x) + padding.x);
    }

    @Override
    public int getHeight() {
        return (int) ((sliderImage.getHeight() * this.transform2D.scale.y) + padding.y);
    }

    private void updateComponent() {
        if (sliderImage != null) {
            int x = (int) (this.transform2D.position.x - (getWidth() / 2));
            int y = (int) (this.transform2D.position.y - (getHeight() / 2));

            setPreferredSize(new Dimension(getWidth(), getHeight()));
            this.setBounds(x, y, getWidth(), getHeight());
        }

        revalidate();
        repaint();
    }

    protected void increment() {
        value++;
        if (value > maxValue)
            value = maxValue;
    }

    protected void decrement() {
        value--;
        if (value < 0)
            value = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());

        if (slideBar != null) {
            g.drawImage(sliderImage, (int) (padding.x / 2), (int) (padding.y / 2),
                    (int) (sliderImage.getWidth() * this.transform2D.scale.x),
                    (int) (sliderImage.getHeight() * this.transform2D.scale.y), null);
        }
    }
}