import java.awt.*;

class Ball
{
    private int xPosition, yPosition, radius;
    private int xVelocity = 7;
    private int yVelocity = 2;

    public Ball(int xPosition, int yPosition, int radius) {
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

    //basic move
    public void move(int deltaTime) {
        int yIndex, xIndex;
        int saveXPosition = xPosition;

        xPosition = xPosition + xVelocity * deltaTime;

        xIndex = xPosition / 20;
        yIndex = yPosition / 20;

        if(Information.I.isValidIndex(xIndex, yIndex)) {
            if (BlockController.I.isBlockEnable(xIndex, yIndex)) {
                xVelocity = -xVelocity;
                xPosition = saveXPosition + xVelocity * deltaTime;
            }
        }
    }

    public void gravity(int deltaTime) {
        int yIndex, xIndex;
        int saveYPosition = yPosition;

        yPosition = yPosition + yVelocity * deltaTime;

        xIndex = xPosition / 20;
        yIndex = yPosition / 20;

        if(Information.I.isValidIndex(xIndex, yIndex)) {
            if (BlockController.I.isBlockEnable(xIndex, yIndex)) {
                if (yVelocity >= 0) {
                    yVelocity = -15;
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