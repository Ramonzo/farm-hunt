package br.ufba.poo.Screens;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {
    private boolean soundEnabled;
    private int resolution;

    @JsonCreator
    public Config(@JsonProperty("soundEnabled") boolean soundEnabled,
                 @JsonProperty("resolution") int resolution) {
        this.soundEnabled = soundEnabled;
        this.resolution = resolution;
    }

    // Getters
    public boolean isSoundEnabled() { return soundEnabled; }
    public int getResolution() { return resolution; }
    
    // Setters
    public void setSoundEnabled(boolean soundEnabled) { this.soundEnabled = soundEnabled; }
    public void setResolution(int resolution) { this.resolution = resolution; }
}