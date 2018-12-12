class Information {
    public static Information I;

    public int screenHeight, screenWidth;
    public int containerHeight, containerWidth;
    public int yMaxIndex, xMaxIndex;

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
    }
    public void setMaxIndex(int xMaxIndex, int yMaxIndex) {
        this.xMaxIndex = xMaxIndex;
        this.yMaxIndex = yMaxIndex;
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
