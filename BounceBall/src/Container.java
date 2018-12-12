import java.awt.*;

class Container {
    private int height, width;
    public Container(int width, int height) {
        this.height = height;
        this.width = width;
    }
    public boolean inHorizontalContact(int xPosition) {
        return xPosition <= 0 || xPosition >= width;
    }
    public boolean inVerticalContact(int yPosition) {
        return yPosition <= 0 || yPosition >= height;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
}

class ContainerRenderer
{
    private Container container;

    public ContainerRenderer(Container container) {
        this.container = container;
    }

    public void paint(Graphics g) {
        int height = container.getHeight(), width = container.getWidth();
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.black);
        g.drawRect(0, 0, width, height);
    }
}