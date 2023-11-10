package com.example.lab_27_vasilev_mapapi_403;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MapDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MapDatabase";

    public static final String COLUMN_OFFSET_X = "offset_x";
    public static final String COLUMN_OFFSET_Y = "offset_y";
    public static final String COLUMN_CURRENT_LEVEL_INDEX = "current_level_index";


    public MapDatabaseHelper(Context context) {
        super(context, "map_database.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }

    // Метод для получения данных состояния карты из курсора
    @SuppressLint("Range")
    public static void CursorFromLoad(MapView mapView, Cursor cursor) {
        if (cursor != null) {
            mapView.offset_x = cursor.getFloat(cursor.getColumnIndex(COLUMN_OFFSET_X));
            mapView.offset_y = cursor.getFloat(cursor.getColumnIndex(COLUMN_OFFSET_Y));
            mapView.current_level_index = cursor.getInt(cursor.getColumnIndex(COLUMN_CURRENT_LEVEL_INDEX));
        }
    }

    // Метод для создания объекта ContentValues из текущего состояния карты
    public static ContentValues getContentValues(MapView mapView) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_OFFSET_X, mapView.offset_x);
        values.put(COLUMN_OFFSET_Y, mapView.offset_y);
        values.put(COLUMN_CURRENT_LEVEL_INDEX, mapView.current_level_index);
        return values;
    }




}

