package br.ufba.poo.Engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.ufba.poo.Interface.Router;
import br.ufba.poo.Screens.MainMenu;
import br.ufba.poo.Screens.SelectChar;

public class GameWindow extends Canvas implements Runnable {
    private static JFrame frame;
    private static Router router;

    private static Runnable selectedUpdate;
    private volatile boolean running = false;

    private BufferStrategy strategy;

    public void start() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Farm Hunt - 0.0.1v");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setPreferredSize(new java.awt.Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

            frame.setLayout(new BorderLayout());
            frame.add(this, BorderLayout.CENTER);

            frame.pack();
            frame.setLocationRelativeTo(null);

            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    initGameResources();
                }
            });

            frame.setVisible(true);

            router = new Router();

            MainMenu menu = new MainMenu();
            SelectChar selectChar = new SelectChar();

            router.createRoute("/main_menu", menu, screen -> {
                changeRoute(screen, menu::update);
            });

            router.createRoute("/select_char", selectChar, screen -> {
                changeRoute(screen, selectChar::update);
            });

            router.goTo("/main_menu");
        });
    }

    public void initGameResources() {
        if (strategy != null)
            return;

        try {
            createBufferStrategy(3);
            strategy = getBufferStrategy();
            running = true;
            new Thread(this).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeRoute(JPanel panel, Runnable incommingUpdate) {
        selectedUpdate = incommingUpdate;

        JLayeredPane layeredPane = frame.getLayeredPane();

        for (Component comp : layeredPane.getComponentsInLayer(JLayeredPane.PALETTE_LAYER)) {
            layeredPane.remove(comp);
        }

        layeredPane.add(panel, JLayeredPane.PALETTE_LAYER, 0);
        panel.setBounds(0, 0, getWidth(), getHeight());

        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long acumulador = 0;
        long actualTime;
        long decorrido;

        while (running) {
            actualTime = System.nanoTime();
            decorrido = actualTime - lastTime;
            lastTime = actualTime;
            acumulador += decorrido;

            while (acumulador >= Constants.NANOSEGUNDOS_PER_UPDATE) {
                update();
                acumulador -= Constants.NANOSEGUNDOS_PER_UPDATE;
            }

            SwingUtilities.invokeLater(() -> {
                render();
                frame.repaint();
            });

            try {
                long remainingTime = (actualTime - System.nanoTime() + Constants.NANOSECONDS_PER_FRAME) / 1_000_000;
                if (remainingTime > 0) {
                    Thread.sleep(remainingTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        if (selectedUpdate != null) {
            selectedUpdate.run();
        }
    }

    private void render() {
        BufferStrategy strategy = getBufferStrategy();
        if (strategy == null) {
            createBufferStrategy(3);
            return;
        }

        do {
            do {
                Graphics g = strategy.getDrawGraphics();
                try {
                    g.setColor(Color.BLACK);
                } finally {
                    g.dispose();
                }
            } while (strategy.contentsRestored());

            strategy.show();
        } while (strategy.contentsLost());
    }
}
