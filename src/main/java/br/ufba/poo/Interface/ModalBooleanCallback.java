package br.ufba.poo.Interface;

@FunctionalInterface
public interface ModalBooleanCallback {
    void execute(boolean isConfirmed);
}