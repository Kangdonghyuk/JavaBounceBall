import java.awt.*;

class Ball
{
    private int xPosition, yPosition, radius;
    private int xVelocity = 7;
    private int yVelocity = 2;
    private Container container;

    public Ball(int xPosition, int yPosition, int radius, Container container) {
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        this.radius=radius;
        this.container=container;
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
    public void move(int deltaTime) {
        xPosition = xPosition + xVelocity * deltaTime;
        yPosition = yPosition + yVelocity * deltaTime;

        if(container.inHorizontalContact(xPosition))
            xVelocity = -xVelocity;

        if(container.inVerticalContact(yPosition))
            yVelocity = -20;

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