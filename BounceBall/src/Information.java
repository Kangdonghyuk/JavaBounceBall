class Information {
    public static Information I;

    public int screenHeight, screenWidth;
    public int containerHeight, containerWidth;
    public int yMaxIndex, xMaxIndex;
    public int blockSize;

    public Information() {
        I = this;
    }

    public void setScreenSize(int width, int height) {
        screenHeight = height;
        screenWidth = width;
    }
    public void setContainerSize(int width, int height) {
        containerHeight = height;
        containerWidth = width;
        xMaxIndex = containerWidth / blockSize;
        yMaxIndex = (containerHeight / blockSize) + 1;
    }
    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }
    public boolean isValidXIndex(int xIndex) {
        return xIndex >= 0 && xIndex < xMaxIndex;
    }
    public boolean isValidYIndex(int yIndex) {
        return yIndex >= 0 && yIndex < yMaxIndex;
    }
    public boolean isValidIndex(int xIndex, int yIndex) {
        return xIndex >= 0 && xIndex < xMaxIndex &&
                yIndex >= 0 && yIndex < yMaxIndex;
    }
}
