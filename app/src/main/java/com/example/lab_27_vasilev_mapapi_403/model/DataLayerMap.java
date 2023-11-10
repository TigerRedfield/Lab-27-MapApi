package com.example.lab_27_vasilev_mapapi_403.model;

import java.util.List;

public class DataLayerMap {
    private String name;
    private List<LineCoordinatesMap> lines;

    public DataLayerMap(String name, List<LineCoordinatesMap> lines) {
        this.name = name;
        this.lines = lines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LineCoordinatesMap> getLines() {
        return lines;
    }

    public void setLines(List<LineCoordinatesMap> lines) {
        this.lines = lines;
    }
}
