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

public class DialogModal extends JPanel {
    private Transform2D transform2D;
    protected GridBagConstraints gbc;

    private String message;
    private ModalBooleanCallback callback;

    private Sprite modalSprite;
    private BufferedImage modalSpriteImage;

    private Sprite modalVL;
    private Sprite modalVR;
    private Sprite modalHT;
    private Sprite modalHB;

    private Sprite modalBL;
    private Sprite modalBR;
    private Sprite modalTL;
    private Sprite modalTR;

    private Sprite modalBG;
    private Sprite modalTopDetail;

    private CustomText modalText;

    private int width = 350;
    private int height = 250;

    public DialogModal(String message) {
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

        modalText = new CustomText(message, "black");

        modalSprite = new Sprite(Constants.GUI_PATH + "/modal/modal.png");

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

        this.message = message;

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public void load() {
        modalText.load();
        modalText.setPosition(20, 20);

        modalSprite.load();

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

        modalSpriteImage = modalSprite.getImage();
        add(modalText, gbc, 0);

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

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
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
