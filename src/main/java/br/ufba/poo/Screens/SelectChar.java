package br.ufba.poo.Screens;

import java.util.List;

import br.ufba.poo.Config.General.JsonDataManager;
import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.Vector2;
import br.ufba.poo.Interface.*;

public class SelectChar extends Screen {
    private CharCard selectCharCard;
    private JsonDataManager manager;
    private List<SavedGame> saveGames;

    public SelectChar() {

        manager = new JsonDataManager("/saved_games.json");

        SavedGames loaded = manager.get("game_data", SavedGames.class);

        saveGames = loaded.getSavedGames();

        setupUI();
    }

    public void update() {
        if (selectCharCard != null)
            selectCharCard.update();
    }

    protected void setupUI() {
        super.setupUI();

        Button btnBack = Screen.createSmallBackButton("", new Vector2(100, Constants.SCREEN_HEIGHT - 100), this::back);

        if (saveGames != null) {
            int i = 0;
            for (SavedGame savedGame : saveGames) {
                CharCard selectCharCard = new CharCard(savedGame);
                selectCharCard.setPosition(300 + (200 * i), Constants.SCREEN_HEIGHT - 300);
                selectCharCard.load();
                add(selectCharCard, gbc);
                i++;
            }

            if (saveGames.size() > 1) {

            }
        }

        add(btnBack, gbc);
    }

    private void back() {
        Screen.router.goTo("/main_menu");
    }

}
