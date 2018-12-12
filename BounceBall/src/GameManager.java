

class GameManager {
    public static GameManager I;
    private Ball ball;
    private GameRenderer gRenderer;
    private BlockController blockController;

    private int deltaTime, delayTime;

    public GameManager() {
        I = this;

        delayTime = 20;
        deltaTime = 1;
    }

    public void enterGame() {
        FileManager.I.readStage(2);

        ball = new Ball();
        ball.setInformation(
                FileManager.I.getStartXPosition(),
                FileManager.I.getStartYPosition(),
                8);

        blockController = new BlockController();
        blockController.setMap(FileManager.I.getMap());

        gRenderer = new GameRenderer();
        gRenderer.addRenderer(new BlockRenderer());
        gRenderer.addRenderer(new BallRenderer(ball));
    }
    public void readStage(int stage) {
        FileManager.I.readStage(stage);
        ball.setInformation(
                FileManager.I.getStartXPosition(),
                FileManager.I.getStartYPosition(),
                8);
        blockController.setMap(FileManager.I.getMap());
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