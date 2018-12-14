import java.awt.*;
import java.awt.image.BufferedImage;

class Ball
{
    private int xPosition, yPosition, radius;
    private int saveXPosition, saveYPosition;
    private int xVelocity = 0;
    private int yVelocity = 2;

    public Ball() {
        //I = this;
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
    public int getYVelocity() {
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
            if((blockType = GameManager.I.CollideBlock(xIndex, yIndex)) != 0) {
                if (blockType != 9 && blockType != 5) {
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
            if((blockType = GameManager.I.CollideBlock(xIndex, yIndex)) != 0)
                if (blockType != 9 && blockType != 5 && yVelocity >= 0) {
                    yVelocity = -14;
                    if(blockType == 3)
                        yVelocity = -18;
                    SoundManager.I.PlaySound("src/sound/Jump.wav");
                } else if(blockType != 9)
                    yVelocity = 3;
                yPosition = saveYPosition + yVelocity * deltaTime;

            if (yVelocity <= 15)
                yVelocity += 1;
        }
    }
}

class BallRenderer extends ObjectRenderer
{
    private Ball ball;
    private BufferedImage ballImage;

    public BallRenderer(Ball ball) {
        this.ball = ball;
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
    }
}