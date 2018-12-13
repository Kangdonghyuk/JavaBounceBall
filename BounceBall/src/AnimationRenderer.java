import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class AnimationRenderer extends ObjectRenderer {
    private BufferedImage[] animationImage;
    private int frame, frameSize, frameTime, nowTime;
    private int xPosition, yPosition;
    private int width, height;
    private String fileName;
    private boolean isLoop, isLooped;

    public AnimationRenderer(String fileName, int frameSize, int frameTime,
                             int xPosition, int yPosition,
                             int width, int height, boolean isLoop) {
        int index;

        this.fileName = fileName;
        this.frameSize = frameSize;
        this.frameTime = frameTime;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.isLoop = isLoop;
        isLooped = false;

        frame = 0;

        animationImage = new BufferedImage[frameSize];
        for(index = 0; index < frameSize; index++) {
            animationImage[index] = FileManager.I.getImage(
                    "src/image/" + fileName + index + ".png");
        }
        isAnimation = true;
    }
    public void paint(Graphics g) {
        if(isLooped == false) {
            g.drawImage(
                    animationImage[frame],
                    xPosition, yPosition,
                    width, height, GameRenderer.I);
            if(GameManager.I.nowTime >= nowTime) {
                nowTime = GameManager.I.nowTime + frameTime;
                frame = (frame + 1) % frameSize;
                if (frame == 0 && isLoop == false)
                    isLooped = true;
            }
        }
    }
    public boolean isEnable() {
        return isLooped;
    }
}
