

class GameManager {
    public static GameManager I;

    private Ball ball;
    private GameRenderer gRenderer;
    private BlockController blockController;

    private boolean isPlay;

    private int deltaTime, delayTime;
    private int stage;
    private int starCount;

    public int nowTime;

    public GameManager() {
        I = this;

        delayTime = 20;
        deltaTime = 1;
        nowTime = 0;
        stage = 12;
        isPlay = true;
    }

    public void clearGame() {
        ball = null;
        blockController = null;
        gRenderer = null;
        isPlay = true;
    }
    public void enterGame() {
        starCount = FileManager.I.readStage(stage);

        clearGame();

        blockController = new BlockController();
        blockController.setMap(FileManager.I.getMap());

        ball = new Ball();
        ball.setInformation(
                FileManager.I.getStartXPosition(),
                FileManager.I.getStartYPosition(),
                20);

        gRenderer = new GameRenderer();
        gRenderer.addRenderer(new BlockRenderer());
        gRenderer.addRenderer(new BallRenderer(ball));

        isPlay = true;
    }
    public void readStage(int stage) {
        this.stage = stage;
        gRenderer.clearAnimation();

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
    public void CollideBlock(int xIndex, int yIndex) {

    }
    public void pauseAndplay() {
        isPlay = !isPlay;
    }
    public void gameLoop() {
        while (true)
        {
            if(isPlay)
                Updating();
            Rendering();

            delay(delayTime);
            nowTime += delayTime;
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