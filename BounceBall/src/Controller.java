class BounceController {
    private Ball ball; // model object
    private AnimationWriter animationWriter; // output-view object
    public BounceController(Ball ball, AnimationWriter animationWriter) {
        this.ball = ball;
        this.animationWriter = animationWriter;
    }

    public void runAnimation() {
        int deltaTime = 1;
        int delayTime = 20;

        while (true)
        {
            delay(delayTime);
            ball.move(deltaTime);
            ball.gravity(deltaTime);
            animationWriter.repaint();
        }
    }

    private void delay(int delayTime) {
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) { }
    }
}