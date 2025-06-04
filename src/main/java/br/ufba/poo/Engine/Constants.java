package br.ufba.poo.Engine;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_HALF_WIDTH = SCREEN_WIDTH / 2;
    public static final int SCREEN_HALF_HEIGHT = SCREEN_HEIGHT / 2;

    public static final int UPS_TARGET = 10;
    public static final long NANOSEGUNDOS_PER_UPDATE = 1_000_000_000 / UPS_TARGET;
    public static final int FPS_TARGET = 120;
    public static final long NANOSECONDS_PER_FRAME = 1_000_000_000 / FPS_TARGET;

    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String ASSET_PATH = "/br/ufba/poo/Assets";
    public static final String DATA_PATH = "/src/main/java/br/ufba/poo/Config/Data";
    public static final String GUI_PATH = ASSET_PATH + "/GUI";
    public static final String CHARACTER_PATH = ASSET_PATH + "/Characters";

    public static final List<String> SELECT_CHAR_GROUP = Arrays.asList("/select_char_group1.png",
            "/select_char_group2.png", "/select_char_group3.png", "/select_char_group4.png", "/select_char_group5.png",
            "/select_char_group6.png", "/select_char_group7.png", "/select_char_group8.png", "/select_char_group9.png",
            "/select_char_group10.png", "/select_char_group11.png", "/select_char_group12.png",
            "/select_char_group13.png",
            "/select_char_group14.png", "/select_char_group15.png", "/select_char_group16.png",
            "/select_char_group17.png",
            "/select_char_group18.png", "/select_char_group19.png", "/select_char_group20.png");

    public static final int MAX_SAVED_GAMES = 3;
}