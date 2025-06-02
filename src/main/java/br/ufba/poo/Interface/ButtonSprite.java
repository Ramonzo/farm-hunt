package br.ufba.poo.Interface;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class ButtonSprite extends JComponent {
    private BufferedImage baseImage;
    private BufferedImage hoverImage;
    private BufferedImage clickImage;

    public static final String BASE_IMAGE_HASH = "base";
    public static final String HOVER_IMAGE_HASH = "hover";
    public static final String CLICK_IMAGE_HASH = "click";

    private HashMap<String, BufferedImage> actionSprites;

    private String basePath;
    private String hoverPath;
    private String clickPath;

    public ButtonSprite(String bgPath, String hoverPath, String clickPath) {
        this.basePath = bgPath;
        this.hoverPath = hoverPath;
        this.clickPath = clickPath;

        this.actionSprites = new HashMap<>();
    }

    public void load() {
        try {
            baseImage = ImageIO.read(getClass().getResource(basePath));
            hoverImage = ImageIO.read(getClass().getResource(hoverPath));
            clickImage = ImageIO.read(getClass().getResource(clickPath));

            actionSprites.put(BASE_IMAGE_HASH, baseImage);
            actionSprites.put(HOVER_IMAGE_HASH, hoverImage);
            actionSprites.put(CLICK_IMAGE_HASH, clickImage);

            // Adicione verificações para garantir que as imagens foram carregadas
            if (baseImage == null || hoverImage == null || clickImage == null) {
                throw new RuntimeException("Imagem não encontrada! Caminho: " + basePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar imagens: " + e.getMessage());
            System.exit(1); // Encerra se falhar
        }
    }

    public BufferedImage getSpriteAction(String action) {
        return actionSprites.get(action);
    }
}
