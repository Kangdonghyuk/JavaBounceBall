import javax.swing.*;
import java.awt.*;

public class Main{

    public static void main(String[] args) {
        Container container = new Container(1200, 700);

        ContainerWriter containerWriter = new ContainerWriter(container);

        Ball ball = new Ball(20, 600, 8, container);
        BallWriter ballWriter = new BallWriter(ball, Color.RED);

        FileManager fileManager = new FileManager();
        fileManager.readStage(1);

        BlockWriter blockWriter = new BlockWriter(fileManager.getMap());

        AnimationWriter animationWriter = new AnimationWriter(
                containerWriter, ballWriter, blockWriter,
                1200, 800);

        BounceController controller = new BounceController(ball, animationWriter);

        controller.runAnimation();
    }
}