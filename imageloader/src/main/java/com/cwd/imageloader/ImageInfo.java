package com.cwd.imageloader;

public class ImageInfo {

    private int width;
    private int height;
    private boolean dontCompress;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isDontCompress() {
        return dontCompress;
    }

    public void setDontCompress(boolean dontCompress) {
        this.dontCompress = dontCompress;
    }
}
