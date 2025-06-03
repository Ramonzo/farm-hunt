package br.ufba.poo.Screens;

import br.ufba.poo.Engine.AnimatedSprite;
import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.Vector2;
import br.ufba.poo.Interface.*;

public class MainMenu extends Screen {
    private AnimatedSprite logo;

    private static final int buttonsX = Constants.SCREEN_HALF_WIDTH;

    public MainMenu() {
        setupUI();
    }

    protected void setupUI() {
        super.setupUI();

        logo = Screen.createAnimatedLogo(new Vector2(buttonsX, 130), new Vector2(3, 3), new Vector2(51, 64));

        int buttonDistance = 75;
        Button btnStart = Screen.createMenuButton("Start", new Vector2(buttonsX, 300), this::startGame);
        Button btnSettings = Screen.createMenuButton("Configuration", new Vector2(buttonsX, 300 + buttonDistance),
                this::openSettings);
        Button btnExit = Screen.createMenuButton("Exit", new Vector2(buttonsX, 300 + (buttonDistance * 2)), this::exitGame);

        add(logo, gbc);

        add(btnStart, gbc);
        add(btnSettings, gbc);
        add(btnExit, gbc);
    }

    public void update() {
        if (logo != null)
            logo.update();
    }

    private void startGame() {
        router.goTo("/select_char");
    }

    private void openSettings() {
        System.out.println("Abrindo configurações...");
    }

    private void exitGame() {
        System.exit(0);
    }
}