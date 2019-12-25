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
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT || keyEvent.getKeyCode() == KeyEvent.VK_A){
            gameEngine.moveActiveShape(Direction.LEFT);
        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT || keyEvent.getKeyCode() == KeyEvent.VK_D){
            gameEngine.moveActiveShape(Direction.RIGHT);
        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_UP || keyEvent.getKeyCode() == KeyEvent.VK_W) {
            gameEngine.rotateActiveShape();
        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN || keyEvent.getKeyCode() == KeyEvent.VK_S) {
            gameEngine.speedUpActiveShape();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN || keyEvent.getKeyCode() == KeyEvent.VK_S) {
            gameEngine.speedDownActiveShape();
        }
    }
}
