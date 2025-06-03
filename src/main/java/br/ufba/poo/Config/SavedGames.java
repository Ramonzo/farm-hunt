package br.ufba.poo.Config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SavedGames {
    @JsonProperty("saved_games")
    private List<SavedGame> saveGames;

    @JsonCreator
    public SavedGames(@JsonProperty("saved_games") List<SavedGame> saved_games) {
        this.saveGames = saved_games;
    }

    public List<SavedGame> getSavedGames() {
        return saveGames;
    }

    public void setSavedGames(List<SavedGame> savedGame) {
        this.saveGames = savedGame;
    }

    public void setSavedGame(SavedGame savedGame) {
        this.saveGames.add(savedGame);
    }

    public void removeSavedGame(SavedGame savedGame){
        this.saveGames.remove(savedGame);
    }
}
