package br.ufba.poo.Interface;

import javax.swing.*;

@FunctionalInterface
public interface RouteHandler {
    void execute(JPanel screen);
}