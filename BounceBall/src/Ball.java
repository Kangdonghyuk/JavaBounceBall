import java.awt.*;

class Ball
{
    private int xPosition, yPosition, radius;
    private int xVelocity = 7;
    private int yVelocity = 2;
    private int[][] map;
    private Container container;

    public Ball(int xPosition, int yPosition, int radius, Container container) {
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        this.radius=radius;
        this.container=container;
    }
    public void setMap(int [][]map) {
        this.map = map;
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
        int nowYPosition, nowXPosition;
        int saveXPosition = xPosition;

        xPosition = xPosition + xVelocity * deltaTime;

        nowXPosition = xPosition / 20;
        nowYPosition = yPosition / 20;

        if(map[nowYPosition][nowXPosition] != 0) {
            xVelocity = -xVelocity;
            xPosition = saveXPosition + xVelocity * deltaTime;
        }
    }

    //key event move
    public void move(int xVelocity, int deltaTime) {
        xPosition = xPosition + xVelocity * deltaTime;

        if(container.inHorizontalContact(xPosition))
            xPosition = xPosition - xVelocity * deltaTime;
    }
    public void gravity(int deltaTime) {
        int nowYPosition, nowXPosition;
        int saveYPosition = yPosition;

        yPosition = yPosition + yVelocity * deltaTime;

        nowXPosition = xPosition / 20;
        nowYPosition = yPosition / 20;

        if(map[nowYPosition][nowXPosition] != 0) {
            if(yVelocity >= 0)
                yVelocity = -15;
            else
                yVelocity = 3;
            yPosition = saveYPosition + yVelocity * deltaTime;
        }

        if(yVelocity <= 15)
            yVelocity += 1;
    }
}

class BallWriter
{
    private Ball ball; // the (address of the) ball object displayed
    private Color color; // the ball's color

    public BallWriter(Ball ball, Color color) {
        this.ball = ball;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        int radius = ball.getRadius();
        g.fillOval(ball.getXPosition() - radius,
                ball.getYPosition() - radius, radius * 2, radius * 2);
    }
}