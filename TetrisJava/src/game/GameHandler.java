package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameHandler implements KeyListener {

    private GameEngine gameEngine;

    GameHandler(GameEngine engine) {
        this.gameEngine = engine;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if(gameEngine.isSelected()) {
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    gameEngine.moveActiveShape(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    gameEngine.moveActiveShape(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    gameEngine.rotateActiveShape();
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    gameEngine.speedUpActiveShape();
                    break;
                default: break;
            }
        }
        int gameIndex = gameEngine.getGameIndex();
        switch (keyCode) {
            case KeyEvent.VK_1:
                gameEngine.setSelected(gameIndex == 1);
                break;
            case KeyEvent.VK_2:
                gameEngine.setSelected(gameIndex == 2);
                break;
            case KeyEvent.VK_3:
                gameEngine.setSelected(gameIndex == 3);
                break;
            default: break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN || keyEvent.getKeyCode() == KeyEvent.VK_S) {
            gameEngine.speedDownActiveShape();
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }
}
