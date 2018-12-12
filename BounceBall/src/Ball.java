import java.awt.*;

class Ball
{
    public static Ball I;
    private int xPosition, yPosition, radius;
    private int xVelocity = 0;
    private int yVelocity = 2;

    public Ball() {
        I = this;
    }
    public void setInformation(int xPosition, int yPosition, int radius) {
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        this.radius=radius;
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
        int saveXPosition = xPosition;
        int blockSize = Information.I.blockSize;
        int blockType;

        xPosition = xPosition + xVelocity * deltaTime;

        xIndex = xPosition / blockSize;
        yIndex = yPosition / blockSize;

        if(Information.I.isValidIndex(xIndex, yIndex)) {
            if (BlockController.I.isBlockEnable(xIndex, yIndex)) {
                blockType = BlockController.I.getBlockType(xIndex, yIndex);
                if(blockType == 9)
                    BlockController.I.setBlockEnable(xIndex, yIndex, false);
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
        int saveYPosition = yPosition;
        int blockSize = Information.I.blockSize;
        int blockType;

        yPosition = yPosition + yVelocity * deltaTime;

        xIndex = xPosition / blockSize;
        yIndex = yPosition / blockSize;

        if(Information.I.isValidIndex(xIndex, yIndex)) {
            if (BlockController.I.isBlockEnable(xIndex, yIndex)) {
                blockType = BlockController.I.getBlockType(xIndex, yIndex);
                if(blockType == 9)
                    BlockController.I.setBlockEnable(xIndex, yIndex, false);
                else if (yVelocity >= 0) {
                    yVelocity = -14;

                    if(blockType == 2)
                        BlockController.I.setBlockEnable(xIndex, yIndex, false);
                    else if(blockType == 3)
                        yVelocity = -18;
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

class BallRenderer implements ObjectRenderer
{
    private Ball ball;
    private Color color;

    public BallRenderer(Ball ball) {
        this.ball = ball;
        this.color = Color.red;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        int radius = ball.getRadius();
        g.fillOval(ball.getXPosition() - radius,
                ball.getYPosition() - radius, radius * 2, radius * 2);
    }
}