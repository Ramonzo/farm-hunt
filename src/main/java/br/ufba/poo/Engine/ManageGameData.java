package br.ufba.poo.Engine;

import br.ufba.poo.Config.SavedGame;
import br.ufba.poo.Config.SavedGames;
import br.ufba.poo.Config.General.JsonDataManager;

public class ManageGameData {
    private static SavedGames saved_games;

    private static final String GAME_DATA_KEY = "game_data";
    private static final JsonDataManager manager = new JsonDataManager("/saved_games.json");

    public static void generateNewSavedGame(SavedGame saved_game) {
         saved_games = getSavedGames();
        if (!saved_games.getSavedGames().contains(saved_game)) {
            saved_games.setSavedGame(saved_game);

            manager.upsert(GAME_DATA_KEY, saved_games);
        }
    }

    public static SavedGames getSavedGames() {
        return manager.get(GAME_DATA_KEY, SavedGames.class);
    }
}
