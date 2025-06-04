package br.ufba.poo.Screens;

import java.util.ArrayList;
import java.util.List;

import br.ufba.poo.Config.SavedGame;
import br.ufba.poo.Config.SavedGames;
import br.ufba.poo.Engine.Constants;
import br.ufba.poo.Engine.ManageGameData;
import br.ufba.poo.Engine.Vector2;
import br.ufba.poo.Interface.*;

public class SelectChar extends Screen {
    private List<CharCard> selectCharCards;
    private SavedGames loaded;

    public SelectChar() {

        loaded = ManageGameData.getSavedGames();

        selectCharCards = new ArrayList<>();

        setupUI();
    }

    public void update() {
        if (selectCharCards != null)
            for (CharCard selectCharCard : selectCharCards) {
                selectCharCard.update();
            }
    }

    protected void setupUI() {
        super.setupUI();

        Button btnBack = Screen.createMediumButton("back", new Vector2(70, Constants.SCREEN_HEIGHT - 100), this::back);

        if (loaded != null) {
            for (SavedGame savedGame : loaded.getSavedGames()) {
                CharCard selectCharCard = new CharCard(savedGame);
                selectCharCard.setPosition(270 + (200 * selectCharCards.size()), Constants.SCREEN_HEIGHT - 300);
                selectCharCard.load();
                selectCharCards.add(selectCharCard);

                add(selectCharCard, gbc);
            }

            if (selectCharCards.size() < Constants.MAX_SAVED_GAMES) {
                Button btnPlus = Screen.createPlusButton(
                        new Vector2(250 + (200 * selectCharCards.size()), Constants.SCREEN_HEIGHT - 300),
                        this::createNewGame);

                add(btnPlus, gbc);
            }
        }

        add(btnBack, gbc);
    }

    private void createNewGame() {
        SavedGame newSavedGame = new SavedGame("Save Game " + (selectCharCards.size() + 1),
                "/save" + (selectCharCards.size() + 1) + ".json");

        ManageGameData.generateNewSavedGame(newSavedGame);
        loaded = ManageGameData.getSavedGames();
        selectCharCards = new ArrayList<>();

        removeAll();
        setupUI();
    }

    private void back() {
        Screen.router.goTo("/main_menu");
    }

}
