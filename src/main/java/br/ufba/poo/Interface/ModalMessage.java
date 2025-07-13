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

public class ModalMessage extends JPanel {
    private Transform2D transform2D;
    protected GridBagConstraints gbc;

    private String message;
    private Runnable callback;

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

    private Button okButton;
    private Button cancelButton;
    private CustomText modalText;

    public ModalMessage(String message) {
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

        modalText = new CustomText(message);
        okButton = Screen.createSmallButton("okay", new Vector2(0, 0), this::ok);

        modalSprite = new Sprite(Constants.GUI_PATH + "/modal/modal.png");

        modalVL = new Sprite(Constants.GUI_PATH + "/modal/modal_vl.png");
        modalVR = new Sprite(Constants.GUI_PATH + "/modal/modal_vr.png");
        modalHT = new Sprite(Constants.GUI_PATH + "/modal/modal_ht.png");
        modalHB = new Sprite(Constants.GUI_PATH + "/modal/modal_hb.png");

        modalBL = new Sprite(Constants.GUI_PATH + "/modal/modal_bl.png");
        modalBR = new Sprite(Constants.GUI_PATH + "/modal/modal_br.png");
        modalTL = new Sprite(Constants.GUI_PATH + "/modal/modal_tl.png");
        modalTR = new Sprite(Constants.GUI_PATH + "/modal/modal_tr.png");

        modalBG = new Sprite(Constants.GUI_PATH + "/modal/modal_bg.png");

        this.message = message;

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public ModalMessage(String message, Runnable callback) {
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

        modalText = new CustomText(message);
        okButton = Screen.createSmallButton("okay", new Vector2(0, 0), this::ok);
        cancelButton = Screen.createSmallButton("cancel", new Vector2(0, 0), this::cancel);

        modalSprite = new Sprite(Constants.GUI_PATH + "/modal.png");

        this.message = message;
        this.callback = callback;

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

        modalSpriteImage = modalSprite.getImage();

        if (callback != null) {
            okButton.setPosition(getWidth() / 2, getHeight() - 40);
            cancelButton.setPosition(getWidth() / 2, getHeight() - 40);

            add(cancelButton, gbc);
        } else {
            okButton.setPosition(getWidth() / 2, getHeight() - 40);
        }

        add(okButton, gbc);

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
        return (int) (350);
    }

    @Override
    public int getHeight() {
        return (int) (200);
    }

    public void cancel() {

    }

    public void ok() {

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
            g.drawImage(modalBG.getImage(), 15, 15, getWidth() - 50, getHeight() - 30, null);
        }

        if (modalTL != null) {
            g.drawImage(modalTL.getImage(), -30, -30, 80, 80, null);
        }

        if (modalTR != null) {
            g.drawImage(modalTR.getImage(), getWidth() - 70, -30, 80, 80, null);
        }

        if (modalBL != null) {
            g.drawImage(modalBL.getImage(), -30, getHeight() - 50, 80, 80, null);
        }

        if (modalBR != null) {
            g.drawImage(modalBR.getImage(), getWidth() - 70, getHeight() - 50, 80, 80, null);
        }

        int borderWidth = (getWidth() - 65) / 5;
        int borderHeight = (getHeight() - 65) / 5;

        for (int i = 0; i < borderWidth; i++) {
            if (modalHT != null) {
                g.drawImage(modalHT.getImage(), -15 + (i * 5), -30, 80, 80, null);
            }
            if (modalHB != null) {
                g.drawImage(modalHB.getImage(), -15 + (i * 5), getHeight() - 45, 80, 80, null);
            }
        }

        for (int i = 0; i < borderHeight; i++) {
            if (modalVL != null) {
                g.drawImage(modalVL.getImage(), -30, -15 + (i * 5), 80, 80, null);
            }
            if (modalVR != null) {
                g.drawImage(modalVR.getImage(), getWidth() - 70, -15 + (i * 5), 80, 80, null);
            }
        }
    }
}
