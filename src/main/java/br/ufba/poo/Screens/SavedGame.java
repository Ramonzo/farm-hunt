package br.ufba.poo.Screens;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SavedGame {
    @JsonProperty("name")
    private String name;

    @JsonProperty("generated_file")
    private String generatedFile;

    @JsonCreator
    public SavedGame(@JsonProperty("name") String name, @JsonProperty("generated_file") String generated_file) {
        this.name = name;
        this.generatedFile = generated_file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneratedFile() {
        return generatedFile;
    }

    public void setGeneratedFile(String generatedFile) {
        this.generatedFile = generatedFile;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj.getClass() != this.getClass())
            return false;

        final SavedGame other = (SavedGame) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        
        return true;
    }
}
