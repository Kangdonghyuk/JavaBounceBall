import javax.swing.*;
import java.awt.*;

class AnimationWriter extends JPanel
{
    private ContainerWriter containerWriter; // the output-view of the box
    private BallWriter ballWriter; // the output-view of the ball in the box
    private BlockWriter blockWriter;
    private int width, height;

    public AnimationWriter(
            ContainerWriter containerWriter, BallWriter ballWriter,
            BlockWriter blockWriter, int width, int height) {
        this.containerWriter = containerWriter;
        this.ballWriter = ballWriter;
        this.blockWriter = blockWriter;
        this.width = width;
        this.height = height;

        JFrame my_frame = new JFrame();
        my_frame.getContentPane().add(this);
        my_frame.setTitle("Bounce");
        my_frame.setSize(width, height);
        my_frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        containerWriter.paint(g);
        ballWriter.paint(g);
        blockWriter.paint(g);
    }
}