

class GameManager {
    private Ball ball;
    private GameRenderer gRenderer;
    private int deltaTime, delayTime;

    public GameManager(Ball ball, GameRenderer gRenderer) {
        this.ball = ball;
        this.gRenderer = gRenderer;

        delayTime = 20;
        deltaTime = 1;
    }

    public void gameLoop() {
        while (true)
        {
            Updating();
            Rendering();

            delay(delayTime);
        }
    }

    private void Updating() {
        ball.move(deltaTime);
        ball.gravity(deltaTime);
    }
    private void Rendering() {
        gRenderer.repaint();
    }
    private void delay(int delayTime) {
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) { }
    }
}