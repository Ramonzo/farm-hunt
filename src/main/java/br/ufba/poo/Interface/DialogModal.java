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
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DialogModal extends JPanel {
    private Transform2D transform2D;
    protected GridBagConstraints gbc;

    private ModalBooleanCallback callback;

    private static Sprite modalVL;
    private static Sprite modalVR;
    private static Sprite modalHT;
    private static Sprite modalHB;

    private static Sprite modalBL;
    private static Sprite modalBR;
    private static Sprite modalTL;
    private static Sprite modalTR;

    private static Sprite modalBG;
    private static Sprite modalTopDetail;

    private CustomText title;
    private String[] texts;

    private static int width = 80;
    private static int height = 80;
    private static int textDistance = 20;
    private int maxTextLength = 0;

    public DialogModal(String title, String description) {
        setOpaque(false);
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        Vector2 position = new Vector2(400, 400);
        Vector2 scale = new Vector2(3, 3);
        transform2D = new Transform2D(position, scale);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.title = new CustomText(title, "black");
        texts = description.split("</br>");

        modalVL = new Sprite(Constants.GUI_PATH + "/dialog_modal/dialog_modal_vl.png");
        modalVR = new Sprite(Constants.GUI_PATH + "/dialog_modal/dialog_modal_vr.png");
        modalHT = new Sprite(Constants.GUI_PATH + "/dialog_modal/dialog_modal_ht.png");
        modalHB = new Sprite(Constants.GUI_PATH + "/dialog_modal/dialog_modal_hb.png");

        modalBL = new Sprite(Constants.GUI_PATH + "/dialog_modal/dialog_modal_bl.png");
        modalBR = new Sprite(Constants.GUI_PATH + "/dialog_modal/dialog_modal_br.png");
        modalTL = new Sprite(Constants.GUI_PATH + "/dialog_modal/dialog_modal_tl.png");
        modalTR = new Sprite(Constants.GUI_PATH + "/dialog_modal/dialog_modal_tr.png");

        modalBG = new Sprite(Constants.GUI_PATH + "/dialog_modal/dialog_modal_bg.png");
        modalTopDetail = new Sprite(Constants.GUI_PATH + "/dialog_modal/dialog_modal_t_detail.png");

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public void load() {
        title.load();
        title.setPosition(20, 20);

        for (int i = 0; i < texts.length; i++) {
            if (texts[i].length() > maxTextLength)
                maxTextLength = texts[i].length();

            CustomText newText = new CustomText(texts[i], "black");
            newText.load();
            newText.setPosition(20, (45) + (textDistance * i));
            add(newText, gbc, 0);
        }

        modalVL.load();
        modalVR.load();
        modalHT.load();
        modalHB.load();

        modalBL.load();
        modalBR.load();
        modalTL.load();
        modalTR.load();

        modalBG.load();
        modalTopDetail.load();

        add(title, gbc, 0);

        updateComponent();
    }

    private void updateComponent() {
        int x = (int) (this.transform2D.position.x - (getWidth() / 2));
        int y = (int) (this.transform2D.position.y - (getHeight() / 2));

        setPreferredSize(new Dimension(getWidth() + 100, getHeight() + 100));
        this.setBounds(x, y, getWidth() + 100, getHeight() + 100);

        revalidate();
        repaint();
    }

    public void setPosition(int x, int y) {
        Vector2 position = new Vector2(x, y);
        Vector2 scale = new Vector2(this.transform2D.scale);
        this.transform2D = new Transform2D(position, scale);

        updateComponent();
    }

    @Override
    public int getWidth() {
        return width + CustomFont.letterSize * textDistance;
    }

    @Override
    public int getHeight() {
        return height + (textDistance * texts.length);
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
            g.drawImage(modalBG.getImage(), 15, 15, getWidth() - 30, getHeight() - 30, null);
        }

        int borderWidth = (getWidth() - 65) / 5;
        int borderHeight = (getHeight() - 45) / 5;

        for (int i = 0; i < borderWidth; i++) {
            if (modalHT != null) {
                g.drawImage(modalHT.getImage(), -15 + (i * 5), -25, 80, 80, null);
            }
            if (modalHB != null) {
                g.drawImage(modalHB.getImage(), -15 + (i * 5), getHeight() - 45, 80, 80, null);
            }
        }

        for (int i = 0; i < borderHeight; i++) {
            if (modalVL != null) {
                g.drawImage(modalVL.getImage(), -30, (i * 5), 80, 80, null);
            }
            if (modalVR != null) {
                g.drawImage(modalVR.getImage(), getWidth() - 45, (i * 5), 80, 80, null);
            }
        }

        if (modalTL != null) {
            g.drawImage(modalTL.getImage(), 0, 0, 80, 80, null);
        }

        if (modalTR != null) {
            g.drawImage(modalTR.getImage(), getWidth() - 75, 0, 80, 80, null);
        }

        if (modalBL != null) {
            g.drawImage(modalBL.getImage(), 0, getHeight() - 75, 80, 80, null);
        }

        if (modalBR != null) {
            g.drawImage(modalBR.getImage(), getWidth() - 75, getHeight() - 75, 80, 80, null);
        }

        if (modalTopDetail != null) {
            g.drawImage(modalTopDetail.getImage(), (getWidth() / 3) + 20, -25, 80, 80, null);
        }
    }
}
