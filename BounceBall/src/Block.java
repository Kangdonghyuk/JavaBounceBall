import java.awt.*;

class Block {
    private final int size = 20;
    private int yPosition, xPosition;
    private int yIndex, xIndex;
    private int type;
    private boolean isEnable;

    private Color color;

    public Block(int xPosition, int yPosition,
                 int xIndex, int yIndex, boolean isEnable) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.yIndex = yIndex;
        this.xIndex = xIndex;
        this.isEnable = isEnable;
    }

    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }
    public void setType(int type) {
        this.type = type;
        if(type == 0)
            color = Color.black;
        else if(type == 1)
            color = Color.yellow;
        else if(type == 2)
            color = Color.blue;
        else if(type == 3)
            color = Color.green;
        else if(type == 4)
            color = Color.gray;
        else if(type == 5)
            color = Color.pink;
    }
    public int getSize() {
        return size;
    }
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }
    public int getYIndex() {
        return yIndex;
    }
    public int getXIndex() {
        return xIndex;
    }
    public int getType() {
        return type;
    }
    public Color getColor() {
        return color;
    }
    public boolean isEnable() {
        return isEnable;
    }
}

class BlockWriter {
    private Block[][] blocks;

    public BlockWriter(int [][]map) {
        int yIndex, xIndex;

        blocks = new Block[36][];
        for(yIndex = 0; yIndex < 36; yIndex++) {
            blocks[yIndex] = new Block[60];
            for(xIndex = 0; xIndex < 60; xIndex++) {
                blocks[yIndex][xIndex] = new Block(
                        xIndex * 20, (yIndex+0) * 20,
                        xIndex, yIndex, false);
                if(map[yIndex][xIndex] != 0) {
                    blocks[yIndex][xIndex].setType(map[yIndex][xIndex]);
                    blocks[yIndex][xIndex].setEnable(true);
                }
            }
        }
    }

    public void paint(Graphics g) {
        int yIndex, xIndex;

        for(yIndex = 0; yIndex < 36; yIndex++) {
            for (xIndex = 0; xIndex < 60; xIndex++) {
                if(blocks[yIndex][xIndex].isEnable()) {
                    Block block = blocks[yIndex][xIndex];
                    g.setColor(block.getColor());
                    g.fillRect(
                            block.getXPosition(), block.getYPosition(),
                            20, 20);
                    g.setColor(Color.black);
                    g.drawRect(
                            block.getXPosition(), block.getYPosition(),
                            20, 20);
                }
            }
        }
    }
}