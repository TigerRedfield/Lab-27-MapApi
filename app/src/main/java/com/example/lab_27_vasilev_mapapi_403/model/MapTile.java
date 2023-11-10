package com.example.lab_27_vasilev_mapapi_403.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


import com.example.lab_27_vasilev_mapapi_403.CallMapApi;
import com.example.lab_27_vasilev_mapapi_403.ApiHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapTile {
    public int scale;
    public int x;
    public int y;
    public Bitmap bmp;

    TilesLoader tilesLoader; // Добавляем слушатель загрузки тайлов изображения карты



    // Интерфейс для слушателя загрузки тайлов изображения карты
    public interface TilesLoader{
        void TileLoader(MapTile tile);
    }


    // Метод для установки слушателя загрузки тайлов изображения карты
    public void setLoadTiles(TilesLoader Loader) {this.tilesLoader = Loader;}

    public MapTile(int x, int y, int scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.bmp = null;

        CallMapApi API = ApiHelper.getMapApi();

        API.rasterData(scale, x, y).enqueue(new Callback<MapTilesItem>() {
            @Override
            public void onResponse(Call<MapTilesItem> call, Response<MapTilesItem> response) {
                byte[] tilesJPEG = Base64.decode(response.body().data, Base64.DEFAULT);
                bmp = BitmapFactory.decodeByteArray(tilesJPEG, 0, tilesJPEG.length);
            }

            @Override
            public void onFailure(Call<MapTilesItem> call, Throwable t) {

            }
        });
    }
    public int getScale() {
        return scale;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public TilesLoader getTilesLoader() {
        return tilesLoader;
    }
}

