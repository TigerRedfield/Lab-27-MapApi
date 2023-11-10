package com.example.lab_27_vasilev_mapapi_403.model;

public class MapZoomLevel {
    private int level;
    private int tileWidth;
    private int tileHeight;

    public MapZoomLevel(int level, int tileWidth, int tileHeight) {
        this.level = level;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }
}
