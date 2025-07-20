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

public class ItemSlot extends JComponent {
    private Transform2D transform2D;
    private GridBagConstraints gbc;

    private Sprite actualSpriteAction;
    private Sprite itemSlot;
    private Sprite itemSlotHv;
    private Sprite itemSlotSl;
    protected boolean clicked = false;
    private Item item;
    private SlotHoverCallback hoverCallback;

    public ItemSlot() {
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

        itemSlot = new Sprite(Constants.GUI_PATH + "/inventory_modal/inventory_modal_slot.png");
        itemSlotHv = new Sprite(Constants.GUI_PATH + "/inventory_modal/inventory_modal_slot_hv.png");
        itemSlotSl = new Sprite(Constants.GUI_PATH + "/inventory_modal/inventory_modal_slot_sl.png");

        actualSpriteAction = null;
    }

    public void load() {
        itemSlot.load();
        itemSlotHv.load();
        itemSlotSl.load();

        if (item != null)
            item.load();

        setupListeners();
        updateComponent();
    }

    public void setHoverCallback(SlotHoverCallback hoverCallback) {
        this.hoverCallback = hoverCallback;
    }

    public void addItem(Item item) {
        if (item == null)
            return;

        this.item = item;

        add(item, gbc, 0);
    }

    public Item getItem() {
        return this.item;
    }

    private void setupListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked = !clicked;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                actualSpriteAction = itemSlotSl;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                actualSpriteAction = itemSlotSl;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                actualSpriteAction = itemSlotHv;
                if (hoverCallback != null)
                    hoverCallback.execute(item);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                actualSpriteAction = null;
                if (hoverCallback != null)
                    hoverCallback.execute(null);
                repaint();
            }
        });
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

        setPreferredSize(new Dimension(1000, 1000));
        setBounds(x, y, 1000, 1000);

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());

        if (itemSlot != null) {
            g.drawImage(itemSlot.getImage(), 0, 0, getWidth(), getHeight(), null);
        }

        if (actualSpriteAction != null) {
            g.drawImage(actualSpriteAction.getImage(), 0, 0, getWidth(), getHeight(), null);
        }

        if (clicked && itemSlotSl != null) {
            g.drawImage(itemSlotSl.getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    }
}
