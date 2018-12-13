
import javafx.animation.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

abstract class ObjectRenderer {
    public boolean isAnimation = false;
    public abstract void paint(Graphics g);
    public boolean isEnable() {
        return false;
    }
}

class GameRenderer extends JPanel
{
    public static GameRenderer I;
    private ArrayList<ObjectRenderer> rendererList;
    BufferedImage backgroundImage;
    BufferedImage exImage;

    public GameRenderer() {
        I = this;

        rendererList = new ArrayList<>();

        JFrame my_frame = new JFrame();
        my_frame.getContentPane().add(this);
        my_frame.setTitle("Bounce");
        my_frame.setSize(Information.I.screenWidth, Information.I.screenHeight);

        my_frame.addKeyListener(new KeyManager());
        my_frame.setVisible(true);

        backgroundImage = FileManager.I.getImage("src/image/background.jpg");
        exImage = FileManager.I.getImage("src/image/ex.png");
    }

    public void addRenderer(ObjectRenderer renderer) {
        rendererList.add(renderer);
    }
    public void clearAnimation() {
        int index;
        for(index = 0; index < rendererList.size(); ) {
            if(rendererList.get(index).isAnimation == true)
                rendererList.remove(index);
            else
                index++;
        }
    }
    public void paintComponent(Graphics g) {
        int index;

        g.drawImage(backgroundImage,
                0, 0,
                Information.I.screenWidth, Information.I.screenHeight,
                this);
        g.drawImage(exImage,
                Information.I.screenWidth - 300, -50,
                200, 200, this);
        for(index = 0; index < rendererList.size(); index++) {
            rendererList.get(index).paint(g);
            if(rendererList.get(index).isEnable() == true) {
                rendererList.remove(index);
            }
        }
    }
}