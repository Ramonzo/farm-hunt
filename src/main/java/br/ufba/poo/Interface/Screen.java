package br.ufba.poo.Interface;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;

import br.ufba.poo.Engine.AnimatedSprite;
import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.Vector2;

public class Screen extends JPanel {
    protected static Router router = new Router();
    protected GridBagConstraints gbc;

    public Screen() {
        setLayout(null);
        setBackground(new Color(30, 30, 30));
    }

    public static AnimatedSprite createAnimatedLogo(Vector2 position, Vector2 scale, Vector2 bounds) {
        AnimatedSprite animatedSprite = new AnimatedSprite(Constants.GUI_PATH + "/farm_hunt_logo.png", bounds);

        animatedSprite.setPosition((int) (position.x), (int) (position.y));
        animatedSprite.setScale((int) (scale.x), (int) (scale.y));
        animatedSprite.load();
        return animatedSprite;
    }

    public static Button createMenuButton(String text, Vector2 position, Runnable callback) {
        Button btn = createButton(text, new Vector2(position), new Vector2(3f, 2.3f), callback,
                "/button_base.png",
                "/button_hover.png",
                "/button_click.png");

        return btn;
    }

    public static Button createMediumButton(String text, Vector2 position, Runnable callback) {
        Button btn = createButton(text, new Vector2(position), new Vector2(3f, 3f), callback,
                "/button_small_base.png",
                "/button_small_hover.png",
                "/button_small_click.png");

        return btn;
    }

    public static Button createSmallButton(String text, Vector2 position, Runnable callback) {
        Button btn = createButton(text, new Vector2(position), new Vector2(3f, 3f), callback,
                "/button_small_base.png",
                "/button_small_hover.png",
                "/button_small_click.png");

        return btn;
    }

    public static Button createButton(String text, Vector2 position, Vector2 scale, Runnable callback, String basePath,
            String hoverPath, String clickPath) {
        Button btn = new Button(
                Constants.GUI_PATH + basePath,
                Constants.GUI_PATH + hoverPath,
                Constants.GUI_PATH + clickPath);

        btn.setText(text);
        btn.setPosition((int) (position.x), (int) (position.y));
        btn.setScale(scale.x, scale.y);
        btn.setCallback(callback);
        btn.load();

        return btn;
    }

    protected void setupUI() {
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }
}
