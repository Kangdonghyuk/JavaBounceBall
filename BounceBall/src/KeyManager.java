import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyManager implements KeyListener{
    final int LEFT = 37;
    final int RIGHT = 39;

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 38) {
            GameManager.I.readStage(3);
        }
        if(e.getKeyCode() == RIGHT) {
            //System.out.println("Right pressed");
//			ball.xPosition += 10;
            Ball.I.setXVelocity(5);
        }
        if(e.getKeyCode() == 40) {
            //System.out.println("Down pressed");
        }
        if(e.getKeyCode() == LEFT) {
            //System.out.println("Left pressed");
            Ball.I.setXVelocity(-5);
        }

    }
    public void keyReleased(KeyEvent e) {
        if(Ball.I.getXVelocity() < 0 && e.getKeyCode() == LEFT)
            Ball.I.setXVelocity(0);
        else if(Ball.I.getXVelocity() > 0 && e.getKeyCode() == RIGHT)
            Ball.I.setXVelocity(0);
    }
}