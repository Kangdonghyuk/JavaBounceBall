import java.awt.*;
import java.awt.image.BufferedImage;

class Ball
{
    public static Ball I;
    private int xPosition, yPosition, radius;
    private int saveXPosition, saveYPosition;
    private int xVelocity = 0;
    private int yVelocity = 2;

    public Ball() {
        I = this;
    }
    public void setInformation(int xPosition, int yPosition, int radius) {
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        this.radius=radius;
        saveXPosition = xPosition;
        saveYPosition = yPosition;
        xVelocity = 0;
        yVelocity = 0;
    }
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }
    public int getRadius() {
        return radius;
    }
    public int getXVelocity() {
        return xVelocity;
    }
    public int getyVelocity() {
        return yVelocity;
    }

    //basic move
    public void move(int deltaTime) {
        int yIndex, xIndex;
        int blockSize = Information.I.blockSize;
        int blockType;

        saveXPosition = xPosition;
        xPosition = xPosition + xVelocity * deltaTime;

        xIndex = xPosition / blockSize;
        yIndex = yPosition / blockSize;

        if(Information.I.isValidIndex(xIndex, yIndex)) {
            if (BlockController.I.isBlockEnable(xIndex, yIndex)) {
                blockType = BlockController.I.getBlockType(xIndex, yIndex);
                if(blockType == 9) {
                    BlockController.I.setBlockEnable(xIndex, yIndex, false);
                    GameManager.I.discountStar();
                }
                else if(blockType == 5)
                    GameManager.I.readStage(GameManager.I.getStage());
                else {
                    xVelocity = -xVelocity;
                    xPosition = saveXPosition + xVelocity * deltaTime;
                    xVelocity = 0;
                }
            }
        }
    }
    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void gravity(int deltaTime) {
        int yIndex, xIndex;
        int blockSize = Information.I.blockSize;
        int blockType;

        saveYPosition = yPosition;
        yPosition = yPosition + yVelocity * deltaTime;

        xIndex = xPosition / blockSize;
        yIndex = yPosition / blockSize;

        if(Information.I.isValidIndex(xIndex, yIndex)) {
            if (BlockController.I.isBlockEnable(xIndex, yIndex)) {
                blockType = BlockController.I.getBlockType(xIndex, yIndex);
                if(blockType == 9) {
                    BlockController.I.setBlockEnable(xIndex, yIndex, false);
                    GameManager.I.discountStar();
                }
                else if(blockType == 5)
                    GameManager.I.readStage(GameManager.I.getStage());
                else if (yVelocity >= 0) {
                    yVelocity = -14;

                    if(blockType == 2) {
                        BlockController.I.setBlockEnable(xIndex, yIndex, false);
                        GameRenderer.I.addRenderer(new AnimationRenderer(
                                "cloudDestroy", 5, 50,
                                xIndex * blockSize, yIndex * blockSize,
                                blockSize, blockSize, false));
                    }
                    else if(blockType == 3)
                        yVelocity = -18;
                    else if(blockType == 4)
                        GameManager.I.readStage(GameManager.I.getStage());
                    //BlockController.I.setBlockEnable(xIndex, yIndex, false);
                    //SoundManager.I.PlaySound("src/sound/Jump.wav");
                } else
                    yVelocity = 3;
                yPosition = saveYPosition + yVelocity * deltaTime;
            }

            if (yVelocity <= 15)
                yVelocity += 1;
        }
    }
}

class BallRenderer extends ObjectRenderer
{
    private Ball ball;
    private Color color;
    private BufferedImage ballImage;

    public BallRenderer(Ball ball) {
        this.ball = ball;
        this.color = Color.red;
        ballImage = FileManager.I.getImage("src/image/ball.png");
    }
    public boolean isEnable() {
        return false;
    }
    public void paint(Graphics g) {
        int blockSize = Information.I.blockSize - 10;
        int radius = ball.getRadius();

        g.drawImage(
                ballImage,
                ball.getXPosition() - radius + 7, ball.getYPosition() - radius,
                blockSize, blockSize, GameRenderer.I);
        /*g.setColor(color);
        g.fillOval(ball.getXPosition() - radius,
                ball.getYPosition() - radius, radius * 2, radius * 2);*/
    }
}