package gui;

import game.Gamemode;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MainFrame extends JFrame {
    private JLabel pointsLabel;
    private JLabel shapesLabel;
    public MainFrame(LinkedList<Gamemode> gamemodes) {
        super("MyTetrisGame");

        getContentPane().setLayout(null);

        pointsLabel = new JLabel("Punkte: " + 0) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int points = 0;
                for (Gamemode gm : gamemodes){
                    points += gm.getPoints();
                }
                setText("Punkte: " + points);
            }
        };
        shapesLabel = new JLabel("Formen: " + 0){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int shapes = 0;
                for (Gamemode gm : gamemodes){
                    shapes += gm.getShapesNumber();
                }
                setText("Formen: " + shapes);
            }
        };

        int frameWidth = 0;
        int frameHeight = (int)(gamemodes.get(0).getGameField().getFieldHeight() * 1.2);

        for(Gamemode gm : gamemodes){
            this.addKeyListener(gm.getGameHandler());
            JPanel gamePanel = new JPanel(){
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    gm.drawField(g);
                    gm.drawShapes(g);
                    gm.drawPreview(g, gm.getGameEngine().getNextShape());
                }
            };
            gamePanel.setLayout(null);
            JLabel nextLabel = new JLabel("Naechstes: ");
            nextLabel.setSize(nextLabel.getPreferredSize());
            nextLabel.setBounds(50, 0, 150, 30);
            nextLabel.setFont(new Font(nextLabel.getFont().getName(), Font.PLAIN, 20));
            gamePanel.add(nextLabel);

            gamePanel.setSize(gm.getGameField().getFieldWidth() + gm.getPreviewOffset(), gm.getGameField().getFieldHeight());
            gamePanel.setLocation(gamemodes.indexOf(gm) * gamePanel.getSize().width, (int) (gm.getGameField().getFieldHeight() * 0.15));
            getContentPane().add(gamePanel);
            gm.setGamePanel(gamePanel);
            frameWidth += gm.getGameField().getFieldWidth() + gm.getPreviewOffset();
        }
        frameWidth += 50;

        pointsLabel.setSize(pointsLabel.getPreferredSize());
        pointsLabel.setBounds(frameWidth / 2 - pointsLabel.getSize().width / 2, frameHeight / 12, 150, 20);
        pointsLabel.setFont(new Font(pointsLabel.getFont().getName(), Font.PLAIN, 20));
        getContentPane().add(pointsLabel);

        shapesLabel.setSize(shapesLabel.getPreferredSize());
        shapesLabel.setBounds(frameWidth / 2 - shapesLabel.getSize().width / 2, frameHeight / 25, 150, 20);
        shapesLabel.setFont(new Font(shapesLabel.getFont().getName(), Font.PLAIN, 20));
        getContentPane().add(shapesLabel);

        setSize(frameWidth, frameHeight);


        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
