package br.ufba.poo.Interface;

import javax.swing.*;

public class Route {
    private String path;
    private JPanel screen;
    private RouteHandler callback;

    public Route(String p, JPanel s, RouteHandler c) {
        path = p;
        screen = s;
        callback = c;
    }

    public Route(Route p) {
        path = p.path;
        screen = p.screen;
        callback = p.callback;
    }

    public void setPath(String p) {
        path = p;
    }

    public String getPath() {
        return new String(path);
    }

    public void setScreen(JPanel s) {
        screen = s;
    }

    public JPanel getScreen() {
        return screen;
    }

    public void execute() {
        callback.execute(screen);
    }
}