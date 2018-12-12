import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

interface ObjectRenderer {
    public void paint(Graphics g);
}

class GameRenderer extends JPanel
{
    private ArrayList<ObjectRenderer> rendererList;

    public GameRenderer() {
        rendererList = new ArrayList<>();

        JFrame my_frame = new JFrame();
        my_frame.getContentPane().add(this);
        my_frame.setTitle("Bounce");
        my_frame.setSize(Information.I.screenWidth, Information.I.screenHeight);
        my_frame.setVisible(true);
    }

    public void addRenderer(ObjectRenderer renderer) {
        rendererList.add(renderer);
    }

    public void paintComponent(Graphics g) {
        int index;

        for(index = 0; index < rendererList.size(); index++) {
            rendererList.get(index).paint(g);
        }
    }
}