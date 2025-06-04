package br.ufba.poo.Config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SavedGames {
    private List<SavedGame> saved_games;

    @JsonCreator
    public SavedGames(@JsonProperty("saved_games") List<SavedGame> saved_games) {
        this.saved_games = saved_games;
    }

    public List<SavedGame> getSavedGames() {
        return saved_games;
    }

    public void setSavedGames(@JsonProperty("saved_games") List<SavedGame> saved_game) {
        this.saved_games = saved_game;
    }

    public void setSavedGame(@JsonProperty("saved_game") SavedGame saved_game) {
        this.saved_games.add(saved_game);
    }

    public void removeSavedGame(@JsonProperty("saved_game") SavedGame saved_game){
        this.saved_games.remove(saved_game);
    }
}
