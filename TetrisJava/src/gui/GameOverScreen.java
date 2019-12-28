package gui;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

class GameOverScreen extends JPanel {
    private JLabel gameOverLabel;
    private JButton replayButton;
    private JButton exitButton;

    GameOverScreen(final MainFrame mf, int width, int height) {
        super();
        setLayout(new GridBagLayout());
        setSize(width, height);
        setPreferredSize(getSize());
        setLocation(0, height / 2);
        setBackground(new Color(214, 62, 98, 100));

        gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font(gameOverLabel.getFont().getName(), Font.BOLD, 50));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBackground(new Color(0, 0, 0, 0));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(gameOverLabel, c);

        ActionListener l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton bt = (JButton) actionEvent.getSource();
                if (bt.equals(exitButton)) {
                    System.exit(0);
                } else if (bt.equals(replayButton)) {
                    int size = mf.getGames().size();
                    var synchronizer = new AtomicBoolean(false);
                    var gList = new LinkedList<Game>();
                    for (int i = 0; i < size; i++) {
                        Game g = new Game(synchronizer, i + 1, mf.getGames().get(0).getLevel());
                        gList.add(g);
                    }
                    mf.dispose();
                    new MainFrame(gList);
                    for (Game g : gList) {
                        new Thread(g.getGameEngine()).start();
                    }
                    replayButton.removeActionListener(this);
                }
            }
        };

        replayButton = new JButton();
        replayButton.setIcon(new ImageIcon("src/resources/replayIcon.png"));
        replayButton.setBackground(new Color(0, 0, 0, 0));
        replayButton.setBorderPainted(false);
        replayButton.setText("Nochmal");
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.gridx = 0;
        c.gridy = 1;
        replayButton.addActionListener(l);
        add(replayButton, c);

        exitButton = new JButton();
        exitButton.setIcon(new ImageIcon("src/resources/exitIcon.png"));
        exitButton.setBackground(new Color(0, 0, 0, 0));
        exitButton.setBorderPainted(false);
        exitButton.setText("Verlassen");
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.gridx = 1;
        c.gridy = 1;
        exitButton.addActionListener(l);
        add(exitButton, c);
    }
}
