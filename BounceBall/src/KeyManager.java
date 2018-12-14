import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyManager implements KeyListener{
    final int LEFT = 37;
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == UP) {
            GameManager.I.readStage(GameManager.I.getStage());
        }
        if(e.getKeyCode() == RIGHT) {
            GameManager.I.setBallXVelocity(5);
        }
        if(e.getKeyCode() == DOWN) {
            GameManager.I.pauseAndplay();
        }
        if(e.getKeyCode() == LEFT) {
            GameManager.I.setBallXVelocity(-5);
        }

    }
    public void keyReleased(KeyEvent e) {
        if(GameManager.I.getBallXVelocity() < 0 && e.getKeyCode() == LEFT)
            GameManager.I.setBallXVelocity(0);
        else if(GameManager.I.getBallXVelocity() > 0 && e.getKeyCode() == RIGHT)
            GameManager.I.setBallXVelocity(0);
    }
}