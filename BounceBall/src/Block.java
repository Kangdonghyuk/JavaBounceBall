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

class BlockController {
    public static BlockController I;
    private Block[][] blocks;

    public BlockController() {
        I = this;

        int yIndex, xIndex;

        blocks = new Block[Information.I.yMaxIndex][];
        for(yIndex = 0; yIndex < Information.I.yMaxIndex; yIndex++) {
            blocks[yIndex] = new Block[Information.I.xMaxIndex];
            for(xIndex = 0; xIndex < Information.I.xMaxIndex; xIndex++) {
                blocks[yIndex][xIndex] = new Block(
                        xIndex * 20, (yIndex+0) * 20,
                        xIndex, yIndex, false);
            }
        }
    }
    public void setMap(int [][] map) {
        int yIndex, xIndex;

        for(yIndex = 0; yIndex < Information.I.yMaxIndex; yIndex++) {
            for (xIndex = 0; xIndex < Information.I.xMaxIndex; xIndex++) {
                if(map[yIndex][xIndex] != 0) {
                    blocks[yIndex][xIndex].setType(map[yIndex][xIndex]);
                    blocks[yIndex][xIndex].setEnable(true);
                }
            }
        }
    }
    public void setBlockType(int xIndex, int yIndex, int type) {
        blocks[yIndex][xIndex].setType(type);
    }
    public void setBlockEnable(int xIndex, int yIndex, boolean enable) {
        blocks[yIndex][xIndex].setEnable(enable);
    }
    public int getBlockType(int xIndex, int yIndex) {
        return blocks[yIndex][xIndex].getType();
    }
    public boolean isBlockEnable(int xIndex, int yIndex) {
        return blocks[yIndex][xIndex].isEnable();
    }
    public Block getBlock(int xIndex, int yIndex) {
        return blocks[yIndex][xIndex];
    }
}

class BlockRenderer implements ObjectRenderer {
    public BlockRenderer() {
    }

    public void paint(Graphics g) {
        int yIndex, xIndex;

        for(yIndex = 0; yIndex < Information.I.yMaxIndex; yIndex++) {
            for (xIndex = 0; xIndex < Information.I.xMaxIndex; xIndex++) {
                if(BlockController.I.isBlockEnable(xIndex, yIndex)) {
                    Block block = BlockController.I.getBlock(xIndex, yIndex);
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