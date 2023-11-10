package com.example.lab_27_vasilev_mapapi_403;

import com.example.lab_27_vasilev_mapapi_403.model.MapTileLevelsItem;
import com.example.lab_27_vasilev_mapapi_403.model.MapTilesItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallMapApi {
    @GET("/raster")
    Call<List<MapTileLevelsItem>> raster(
    );

    @GET("/raster/{level}/{x}-{y}")
    Call<MapTilesItem> rasterData(
            @Path("level") Integer LevelScale,
            @Path("x") Integer x,
            @Path("y") Integer y
    );

    @GET("/coastline/{level}")
    Call<Void> Coastline(
            @Path("level") Integer LevelScale,
            @Query("lat0") Query lat0,
            @Query("lon0") Query lan0,
            @Query("lat1") Query lat1,
            @Query("lon1") Query lan1
    );

}
