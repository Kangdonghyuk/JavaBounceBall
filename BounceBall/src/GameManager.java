

class GameManager {
    public static GameManager I;

    private Ball ball;
    private GameRenderer gRenderer;
    private BlockController blockController;

    private boolean isPlay;

    private int deltaTime, delayTime;
    private int stage;
    private int starCount;
    private int blockSize;

    public int nowTime;

    public GameManager() {
        I = this;

        delayTime = 20;
        deltaTime = 1;
        nowTime = 0;
        stage = 1;
        isPlay = true;
        blockSize = Information.I.blockSize;
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
                blockSize/2);

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
                blockSize/2);
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
    public void setBallXVelocity(int xVelocity) {
        ball.setXVelocity(xVelocity);
    }
    public int getBallXVelocity() {
        return ball.getXVelocity();
    }
    public int CollideBlock(int xIndex, int yIndex) {
        int type = blockController.getBlockType(xIndex, yIndex);

        if(blockController.isBlockEnable(xIndex, yIndex) == false)
            type = 0;

        switch(type) {
            case 2:
                if(ball.getYVelocity() >= 0)
                    blockController.setBlockEnable(xIndex, yIndex, false);
                    gRenderer.addRenderer(new AnimationRenderer(
                            "cloudDestroy", 5, 50,
                            xIndex * blockSize, yIndex * blockSize,
                            blockSize, blockSize, false));
                break;
            case 3:
                break;
            case 4:
                if(ball.getYVelocity() >= 0) {
                    GameManager.I.readStage(GameManager.I.getStage());
                    SoundManager.I.PlaySound("src/sound/GameOver.wav");
                }
                break;
            case 5:
                GameManager.I.readStage(GameManager.I.getStage());
                SoundManager.I.PlaySound("src/sound/GameOver.wav");
                break;
            case 9:
                blockController.setBlockEnable(xIndex, yIndex, false);
                GameManager.I.discountStar();
                break;
        }

        return type;
    }
    public Block getBlock(int xIndex, int yIndex) {
        return blockController.getBlock(xIndex, yIndex);
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