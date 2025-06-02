package br.ufba.poo.Engine;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Sprite {
    private BufferedImage image;

    private String path;

    public Sprite(String path) {
        this.path = path;
    }

    public void load() {
        try {
            image = ImageIO.read(getClass().getResource(path));

            if (image == null) {
                throw new RuntimeException("Sprite não encontrado! Caminho: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar sprite: " + e.getMessage());
            System.exit(1); // Encerra se falhar
        }
    }

    public BufferedImage getImage() {
        if (image == null) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar sprite: Sprite não carregado.");
            System.exit(1); // Encerra se falhar
            return null;
        }

        return image;
    }
}
