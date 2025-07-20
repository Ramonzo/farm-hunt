package br.ufba.poo.Interface;

import javax.swing.JPanel;

import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.Sprite;
import br.ufba.poo.Engine.Transform2D;
import br.ufba.poo.Engine.Vector2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

public class InventoryModal extends JPanel {
    private Transform2D transform2D;
    protected GridBagConstraints gbc;

    private ModalBooleanCallback callback;

    private Sprite modalBG;

    private CustomText title;

    private int width = 62;
    private int height = 74;

    private List<ItemSlot> slots;

    private int INVENTORY_MIN_SLOT_LENGTH = 16;
    private int INVENTORY_MAX_SLOT_LENGTH = 100;
    private int INVENTORY_MAX_SLOT_LINE_LENGTH = 4;

    private DialogModal modalItem;

    public InventoryModal() {
        setOpaque(false);
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        Vector2 position = new Vector2(0, 0);
        Vector2 scale = new Vector2(4, 4);
        transform2D = new Transform2D(position, scale);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        title = new CustomText("Inventory", "black");

        modalBG = new Sprite(Constants.GUI_PATH + "/inventory_modal/inventory_modal_bg.png");

        slots = new ArrayList<>();

        for (int i = 0; i < INVENTORY_MIN_SLOT_LENGTH / INVENTORY_MAX_SLOT_LINE_LENGTH; i++) {
            createSlotLine();
        }

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public void showItemModal(Item item) {
        if (item != null) {
            modalItem = new DialogModal(item.getName(), item.getDescription());

            modalItem.setPosition(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);

            modalItem.load();
            add(modalItem, gbc, 0);
        } else {
            remove(modalItem);
        }
    }

    public void createSlotLine() {
        if (slots.size() >= INVENTORY_MAX_SLOT_LENGTH)
            return;

        for (int i = 0; i < INVENTORY_MAX_SLOT_LINE_LENGTH; i++) {
            int x = 20 + Constants.SCREEN_WIDTH / 2;
            int y = 85 + Constants.SCREEN_HEIGHT / 2;
            ItemSlot newItemSlot = new ItemSlot();
            newItemSlot.setPosition(((newItemSlot.getWidth() - 2) * i) + x,
                    (newItemSlot.getHeight() * (slots.size() / INVENTORY_MAX_SLOT_LINE_LENGTH)) + y);

            if (i == 0) {
                Item item = new Item();
                newItemSlot.addItem(item);
                newItemSlot.setHoverCallback(this::showItemModal);
            }

            slots.add(newItemSlot);
        }
    }

    public void removeSlotLine() {
        if (slots.size() <= INVENTORY_MIN_SLOT_LENGTH)
            return;
    }

    public void load() {
        title.load();
        title.setPosition((Constants.SCREEN_WIDTH / 2) + 20, (Constants.SCREEN_HEIGHT / 2) + 20);

        modalBG.load();

        slots.forEach(slot -> {
            slot.load();
            add(slot, gbc, 0);
        });

        add(title, gbc, 0);

        updateComponent();
    }

    private void updateComponent() {
        int x = (int) (this.transform2D.position.x - ((int) (width * this.transform2D.scale.x) / 2));
        int y = (int) (this.transform2D.position.y - ((int) (height * this.transform2D.scale.y) / 2));

        setPreferredSize(
                new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBounds(x, y, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        revalidate();
        repaint();
    }

    @Override
    public int getWidth() {
        return Constants.SCREEN_WIDTH;
    }

    @Override
    public int getHeight() {
        return Constants.SCREEN_HEIGHT;
    }

    public void cancel() {
        this.callback.execute(false);
    }

    public void ok() {
        this.callback.execute(true);
    }

    protected void setupUI() {
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());

        if (modalBG != null) {
            g.drawImage(modalBG.getImage(), Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2,
                    (int) (width * this.transform2D.scale.x),
                    (int) (height * this.transform2D.scale.y), null);
        }

        // int borderWidth = (getWidth() - 65) / 5;
        // int borderHeight = (getHeight() - 45) / 5;

        // for (int i = 0; i < borderWidth; i++) {
        // if (modalHT != null) {
        // g.drawImage(modalHT.getImage(), -15 + (i * 5), -25, 80, 80, null);
        // }
        // }
    }
}
