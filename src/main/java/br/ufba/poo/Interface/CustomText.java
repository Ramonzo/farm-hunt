package br.ufba.poo.Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;

import br.ufba.poo.Engine.Transform2D;
import br.ufba.poo.Engine.Vector2;

public class CustomText extends JPanel {
    private Transform2D transform2D;
    private GridBagConstraints gbc;

    private String text;
    private String text_color = "normal";
    private CustomFont font;

    public CustomText(String text) {
        setOpaque(false);
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        Vector2 position = new Vector2(0, 0);
        Vector2 scale = new Vector2(3, 2);
        transform2D = new Transform2D(position, scale);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.text = text;

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public CustomText(String text, String color) {
        this(text);
        this.text_color = color;
    }

    public void load() {
        for (int i = 0; i < text.length(); i++) {
            font = new CustomFont(text_color);
            font.load();
            font.setPosition(i * 11, 0);
            font.text(text.charAt(i));
            add(font, gbc);
        }

        updateComponent();
    }

    public void setPosition(int x, int y) {
        Vector2 position = new Vector2(x, y);
        Vector2 scale = new Vector2(this.transform2D.scale);
        this.transform2D = new Transform2D(position, scale);

        updateComponent();
    }

    @Override
    public int getWidth() {
        return (int) ((6 * text.length()) * this.transform2D.scale.x);
    }

    @Override
    public int getHeight() {
        return (int) ((6 * text.length()) * this.transform2D.scale.y);
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
    }
}
