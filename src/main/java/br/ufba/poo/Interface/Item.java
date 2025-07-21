package br.ufba.poo.Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;

import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.Sprite;
import br.ufba.poo.Engine.Transform2D;
import br.ufba.poo.Engine.Vector2;

import java.awt.event.*;

public class Item extends JComponent {
    private Transform2D transform2D;
    private GridBagConstraints gbc;

    private Sprite itemBg;
    private Sprite itemIcon;
    private Sprite itemFrame;

    private String name = "Meu Lindo Item";
    private String description = "Descricao: </br> Este é um item de teste. </br> Atributos: </br> +2 forca. </br> +3 agilidade. </br> -1 defesa.";

    public Item() {
        setOpaque(false);
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        Vector2 position = new Vector2(0, 0);
        Vector2 scale = new Vector2(1, 1);
        transform2D = new Transform2D(position, scale);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        itemBg = new Sprite(Constants.GUI_PATH + "/item/item_icon_bg.png");
        itemIcon = new Sprite(Constants.GUI_PATH + "/item/item_icon_swd_tst.png");
        itemFrame = new Sprite(Constants.GUI_PATH + "/item/item_icon_frame.png");

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void load() {
        itemBg.load();
        itemIcon.load();
        itemFrame.load();

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
        return (int) (48 * this.transform2D.scale.x);
    }

    @Override
    public int getHeight() {
        return (int) (48 * this.transform2D.scale.y);
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

        if (itemIcon != null) {
            g.drawImage(itemIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    }
}
