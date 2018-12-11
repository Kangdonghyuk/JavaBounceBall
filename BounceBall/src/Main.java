import javax.swing.*;
import java.awt.*;

public class Main{

    public static void main(String[] args) {
        Container container = new Container(1200, 700);

        ContainerWriter containerWriter = new ContainerWriter(container);

        FileManager fileManager = new FileManager();
        fileManager.readStage(1);

        Ball ball = new Ball(200, 50, 8, container);
        ball.setMap(fileManager.getMap());
        BallWriter ballWriter = new BallWriter(ball, Color.RED);

        BlockWriter blockWriter = new BlockWriter(fileManager.getMap());

        AnimationWriter animationWriter = new AnimationWriter(
                containerWriter, ballWriter, blockWriter,
                1200, 740);

        BounceController controller = new BounceController(ball, animationWriter);

        controller.runAnimation();
    }
}