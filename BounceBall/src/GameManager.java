

class GameManager {
    public static GameManager I;

    private Ball ball;
    private GameRenderer gRenderer;
    private BlockController blockController;

    private int deltaTime, delayTime;
    private int stage;
    private int starCount;

    public GameManager() {
        I = this;

        delayTime = 20;
        deltaTime = 1;
        stage = 1;
    }

    public void clearGame() {
        ball = null;
        blockController = null;
        gRenderer = null;
    }
    public void enterGame() {
        starCount = FileManager.I.readStage(stage);

        clearGame();

        ball = new Ball();
        ball.setInformation(
                FileManager.I.getStartXPosition(),
                FileManager.I.getStartYPosition(),
                20);

        blockController = new BlockController();
        blockController.setMap(FileManager.I.getMap());

        gRenderer = new GameRenderer();
        gRenderer.addRenderer(new BlockRenderer());
        gRenderer.addRenderer(new BallRenderer(ball));
    }
    public void readStage(int stage) {
        this.stage = stage;
        starCount = FileManager.I.readStage(stage);
        ball.setInformation(
                FileManager.I.getStartXPosition(),
                FileManager.I.getStartYPosition(),
                20);
        blockController.setMap(FileManager.I.getMap());
    }
    public int getStage() {
        return stage;
    }
    public void discountStar() {
        starCount -= 1;
        if(starCount == 0)
            readStage(stage + 1);
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