import java.awt.*;
import java.awt.image.BufferedImage;

class Block {
    private final int size = 20;
    private int yPosition, xPosition;
    private int yIndex, xIndex;
    private int type;
    private boolean isEnable;

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
    public boolean isEnable() {
        return isEnable;
    }
}

class BlockController {
    private Block[][] blocks;

    public BlockController() {
        int yIndex, xIndex;
        int blockSize = Information.I.blockSize;

        blocks = new Block[Information.I.yMaxIndex][];
        for(yIndex = 0; yIndex < Information.I.yMaxIndex; yIndex++) {
            blocks[yIndex] = new Block[Information.I.xMaxIndex];
            for(xIndex = 0; xIndex < Information.I.xMaxIndex; xIndex++) {
                blocks[yIndex][xIndex] = new Block(
                        xIndex * blockSize, yIndex * blockSize,
                        xIndex, yIndex, false);
            }
        }
    }
    public void setMap(int [][] map) {
        int yIndex, xIndex;

        for(yIndex = 0; yIndex < Information.I.yMaxIndex; yIndex++) {
            for (xIndex = 0; xIndex < Information.I.xMaxIndex; xIndex++) {
                blocks[yIndex][xIndex].setType(map[yIndex][xIndex]);
                blocks[yIndex][xIndex].setEnable(map[yIndex][xIndex] != 0);
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

class BlockRenderer extends ObjectRenderer {
    private BufferedImage[] blockImage;

    public BlockRenderer() {
        blockImage = new BufferedImage[10];
        blockImage[1] = FileManager.I.getImage("src/image/normal.png");
        blockImage[2] = FileManager.I.getImage("src/image/cloud.png");
        blockImage[3] = FileManager.I.getImage("src/image/superjump.png");
        blockImage[4] = FileManager.I.getImage("src/image/dead.png");
        blockImage[5] = FileManager.I.getImage("src/image/boom.png");
        blockImage[6] = FileManager.I.getImage("src/image/blank.png");
        blockImage[9] = FileManager.I.getImage("src/image/star.png");
    }
    public boolean isEnable() {
        return false;
    }
    public void paint(Graphics g) {
        int yIndex, xIndex;
        int blockSize = Information.I.blockSize;

        for(yIndex = 0; yIndex < Information.I.yMaxIndex; yIndex++) {
            for (xIndex = 0; xIndex < Information.I.xMaxIndex; xIndex++) {
                Block block = GameManager.I.getBlock(xIndex, yIndex);
                if(block.isEnable()) {
                    g.drawImage(blockImage[block.getType()],
                            block.getXPosition(), block.getYPosition(),
                            blockSize, blockSize, GameRenderer.I);
                }
            }
        }
    }
}