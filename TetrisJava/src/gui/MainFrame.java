package gui;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class MainFrame extends JFrame {
    private JLabel pointsLabel;
    private JLabel shapesLabel;
    private GameOverScreen gameOverScreen;
    private List<Game> games;
    public MainFrame(List<Game> games) {
        super("MyTetrisGame");
        this.games = games;
        getContentPane().setLayout(null);

        pointsLabel = new JLabel("Punkte: " + 0) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int points = 0;
                for (Game gm : games){
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
                for (Game gm : games){
                    shapes += gm.getShapesNumber();
                }
                setText("Formen: " + shapes);
            }
        };

        int frameWidth = 0;
        int frameHeight = (int)(games.get(0).getGameField().getFieldHeight() * 1.2);

        for(Game gm : games){
            this.addKeyListener(gm.getGameHandler());
            MainFrame mf = this;
            JPanel gamePanel = new JPanel(){
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    gm.drawField(g);
                    gm.drawShapes(g);
                    gm.drawPreview(g, gm.getGameEngine().getNextShape());
                    if (gameOverScreen != null) {
                         gameOverScreen.repaint();
                    } else {
                        for (Game game : games) {
                            if (game.getGameEngine().isFinished() && gameOverScreen == null) {
                                gameOverScreen = new GameOverScreen(mf, mf.getWidth(), mf.getHeight() / 2);
                                mf.add(gameOverScreen);
                            }
                        }
                    }
                    if (!gm.getGameEngine().isSelected()) {
                        g.setColor(new Color(216, 213, 227, 100));
                        g.fill3DRect(0, 0, getWidth(), getHeight(), false);
                    }
                }
            };
            gamePanel.setLayout(null);
            JLabel nextLabel = new JLabel("Naechstes: ");
            nextLabel.setSize(nextLabel.getPreferredSize());
            nextLabel.setBounds(50, 0, 150, 30);
            nextLabel.setFont(new Font(nextLabel.getFont().getName(), Font.PLAIN, 20));
            gamePanel.add(nextLabel);

            gamePanel.setSize(gm.getGameField().getFieldWidth() + gm.getPreviewOffset(), gm.getGameField().getFieldHeight());
            gamePanel.setLocation(games.indexOf(gm) * gamePanel.getSize().width, (int) (gm.getGameField().getFieldHeight() * 0.15));
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

        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    List<Game> getGames() {
        return games;
    }
}
