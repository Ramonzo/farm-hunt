package br.ufba.poo.Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.ufba.poo.Config.SavedGame;
import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.Sprite;
import br.ufba.poo.Engine.Transform2D;
import br.ufba.poo.Engine.Vector2;

public class CharCard extends JPanel {
    private Transform2D transform2D;

    private Slider sliderVertical;
    private GridBagConstraints gbc;
    private Sprite background;
    private List<Sprite> selectCharGroupSprites;
    private BufferedImage backgroundImage;
    private Sprite charFrameSprite;
    private Button enterButton;

    private CustomText cardText;

    private int sliderValue;
    private SavedGame savedGame;

    public CharCard(SavedGame savedGame) {
        this.savedGame = savedGame;

        setOpaque(false);
        setLayout(null);
        setBackground(new Color(30, 30, 30));

        cardText = new CustomText(savedGame.getName());

        Vector2 position = new Vector2(0, 0);
        Vector2 scale = new Vector2(3, 3);
        transform2D = new Transform2D(position, scale);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        sliderVertical = new Slider();

        background = new Sprite(Constants.GUI_PATH + "/card_layout.png");
        charFrameSprite = new Sprite(Constants.GUI_PATH + "/square_layout.png");

        enterButton = Screen.createSmallButton("start", new Vector2(0, 0), this::enterCallback);

        selectCharGroupSprites = new ArrayList<>();
        for (String charImage : Constants.SELECT_CHAR_GROUP) {
            Sprite sprite = new Sprite(Constants.CHARACTER_PATH + charImage);
            selectCharGroupSprites.add(sprite);
        }

        setPreferredSize(new Dimension((int) (transform2D.scale.x * 5), (int) (transform2D.scale.y * 5)));
    }

    public void enterCallback() {
    }

    public void setPosition(int x, int y) {
        Vector2 position = new Vector2(x, y);
        Vector2 scale = new Vector2(this.transform2D.scale);
        this.transform2D = new Transform2D(position, scale);

        updateComponent();
    }

    public void load() {
        cardText.load();
        cardText.setPosition(20, 20);

        background.load();
        backgroundImage = background.getImage();

        charFrameSprite.load();

        sliderVertical.setPosition((getWidth() / 2) + 50, (getHeight() / 2) + 5);
        sliderVertical.setMaxValue(selectCharGroupSprites.size() - 1);

        sliderVertical.load();

        enterButton.setPosition(getWidth() / 2, getHeight() - 40);

        for (Sprite sprite : selectCharGroupSprites) {
            sprite.load();
        }

        add(cardText, gbc);
        add(sliderVertical, gbc);
        add(enterButton, gbc);

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

    public void update() {
        if (sliderVertical != null)
            sliderVertical.update();

        sliderValue = sliderVertical.getValue();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());

        if (backgroundImage != null)
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        if (charFrameSprite != null) {
            BufferedImage charFrameImage = charFrameSprite.getImage();
            g.drawImage(charFrameImage, (getWidth() / 2) - 60, (getHeight() / 2) - 35, charFrameImage.getWidth() * 3,
                    charFrameImage.getHeight() * 3, null);
        }
        
        if (selectCharGroupSprites.size() > 0) {
            Sprite sprite = selectCharGroupSprites.get(sliderValue);
            BufferedImage spriteImage = sprite.getImage();

            g.drawImage(spriteImage, (getWidth() / 2) - 50, (getHeight() / 2) - 35, spriteImage.getWidth() * 3,
                    spriteImage.getHeight() * 3, null);
        }
    }
}
