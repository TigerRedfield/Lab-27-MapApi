package com.example.lab_27_vasilev_mapapi_403.model;

import com.google.gson.annotations.SerializedName;

public class MapTileLevelsItem {
    @SerializedName("level")
    public Integer level;
    @SerializedName("xtiles")
    public Integer xtiles;

    @SerializedName("ytiles")
    public Integer ytiles;
    @SerializedName("width")
    public Integer width;
    @SerializedName("height")
    public Integer height;
    @SerializedName("resolution")
    public Float resolution;

    public MapTileLevelsItem(Integer level, Integer xtiles, Integer ytiles, Integer width, Integer height, Float resolution)
    {
            this.level = level;
            this.xtiles = xtiles;
            this.ytiles = ytiles;
            this.width = width;
            this.height = height;
            this.resolution = resolution;
    }

}
