package br.ufba.poo.Interface;

import javax.swing.JPanel;

import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.Sprite;
import br.ufba.poo.Engine.Transform2D;
import br.ufba.poo.Engine.Vector2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;

public class ModalError extends JPanel {
    private Transform2D transform2D;
    protected GridBagConstraints gbc;

    private String message;
    private Runnable callback;

    private Sprite background;
    private BufferedImage backgroundImage;
    private Button okButton;
    private Button cancelButton;
    private CustomText modalText;

    public ModalError(String message) {
        setOpaque(false);
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        Vector2 position = new Vector2(0, 0);
        Vector2 scale = new Vector2(3, 3);
        transform2D = new Transform2D(position, scale);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        modalText = new CustomText(message);
        okButton = Screen.createSmallButton("okay", new Vector2(0, 0), this::ok);

        background = new Sprite(Constants.GUI_PATH + "/modal_background.png");

        this.message = message;

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public ModalError(String message, Runnable callback) {
        setOpaque(false);
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        Vector2 position = new Vector2(0, 0);
        Vector2 scale = new Vector2(3, 3);
        transform2D = new Transform2D(position, scale);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        modalText = new CustomText(message);
        okButton = Screen.createSmallButton("okay", new Vector2(0, 0), this::approve);
        cancelButton = Screen.createSmallButton("cancel", new Vector2(0, 0), this::dennie);

        background = new Sprite(Constants.GUI_PATH + "/modal_background.png");

        this.message = message;
        this.callback = callback;

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public void load() {
        modalText.load();
        modalText.setPosition(20, 20);

        background.load();
        backgroundImage = background.getImage();

        if (callback != null) {
            okButton.setPosition(getWidth() / 2, getHeight() - 40);
            cancelButton.setPosition(getWidth() / 2, getHeight() - 40);
        } else {
            okButton.setPosition(getWidth() / 2, getHeight() - 40);
        }

        add(okButton, gbc);
        add(cancelButton, gbc);

        updateComponent();
    }

    private void updateComponent() {
        if (backgroundImage != null) {
            int x = (int) (this.transform2D.position.x - (getWidth() / 2));
            int y = (int) (this.transform2D.position.y - (getHeight() / 2));

            setPreferredSize(new Dimension(getWidth(), getHeight()));
            this.setBounds(x, y, getWidth(), getHeight());
        }

        revalidate();
        repaint();
    }

    @Override
    public int getWidth() {
        return (int) (backgroundImage.getWidth() * this.transform2D.scale.x);
    }

    @Override
    public int getHeight() {
        return (int) (backgroundImage.getHeight() * this.transform2D.scale.y);
    }

    public void approve() {

    }

    public void dennie() {

    }

    public void ok() {

    }

    protected void setupUI() {
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }
}
