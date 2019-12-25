package gui;

import gameobjects.GameField;
import game.Gamemode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {
    private transient Gamemode gamemode;
    private JPanel gamePanel;

    public MainFrame(Gamemode gm, GameField field, KeyListener listener) {
        super("MyTetrisGame");
        this.gamemode = gm;

        this.addKeyListener(listener);
        getContentPane().setLayout(null);


        gamePanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                gamemode.drawField(g);
                gamemode.drawShapes(g);
            }
        };

        gamePanel.setSize((int)(field.getFieldWidth() * 1.1), field.getFieldHeight());
        gamePanel.setLocation((int) (0.1 * field.getFieldWidth()), (int) (field.getFieldHeight() * 0.15));
        getContentPane().add(gamePanel);

        setSize((int) (field.getFieldWidth() * 1.2), (int) (field.getFieldHeight() * 1.2));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }
}
