package com.example.lab_27_vasilev_mapapi_403.model;

import com.google.gson.annotations.SerializedName;

public class MapTilesItem {
    @SerializedName("lat")
    public Float lat;
    @SerializedName("lon")
    public Float lon;
    @SerializedName("data")
    public String data;

    public MapTilesItem(Float lat, Float lon, String data){
        this.lat = lat;
        this.lon = lon;
        this.data = data;
    }

}
