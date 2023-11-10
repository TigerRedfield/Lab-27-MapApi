package com.example.lab_27_vasilev_mapapi_403.model;

import java.util.List;

public class DataMap {

    private String name;
    private List<MapZoomLevel> zoomLevels;
    private List<MapTile> tiles;

    public DataMap(String name, List<MapZoomLevel> zoomLevels, List<MapTile> tiles) {
        this.name = name;
        this.zoomLevels = zoomLevels;
        this.tiles = tiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MapZoomLevel> getZoomLevels() {
        return zoomLevels;
    }

    public void setZoomLevels(List<MapZoomLevel> zoomLevels) {
        this.zoomLevels = zoomLevels;
    }

    public List<MapTile> getTiles() {
        return tiles;
    }

    public void setTiles(List<MapTile> tiles) {
        this.tiles = tiles;
    }

}
