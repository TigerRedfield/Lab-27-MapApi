package com.example.lab_27_vasilev_mapapi_403.model;

import com.google.gson.annotations.SerializedName;

public class CoordinateMap {

    @SerializedName("x")
    public Float x;
    @SerializedName("y")
    public Float y;

    public CoordinateMap(Float x, Float y){
        this.x = x;
        this.y = y;
    }
}
