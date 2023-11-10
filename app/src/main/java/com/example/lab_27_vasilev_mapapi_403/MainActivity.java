package com.example.lab_27_vasilev_mapapi_403;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.lab_27_vasilev_mapapi_403.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding MainBinding;

    private MapDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(MainBinding.getRoot());
        db = new MapDatabaseHelper(this.getApplicationContext());

        MainBinding.mapView.refreshMap();

        MainBinding.mapView.setListener(new OnClickToMap() {
            @Override
            public void onClick(Float x, Float y) {
                    MainBinding.mapView.refreshMap();
            }

            @Override
            public void onMove()  {
                MainBinding.mapView.refreshMap();
            }
        });

        MainBinding.ButPlus.setOnClickListener(v -> {

            if (MainBinding.mapView.current_level_index == MainBinding.mapView.levels.length - 1) return;
            MainBinding.mapView.offset_x *= 2.0f;
            MainBinding.mapView.offset_y *= 2.0f;
            MainBinding.mapView.offset_x -= MainBinding.mapView.width / 2.0f;
            MainBinding.mapView.offset_y -= MainBinding.mapView.height / 2.0f;
            MainBinding.mapView.current_level_index++;
            MainBinding.mapView.invalidate();
            MainBinding.mapView.saveMapCurrent();
        });

        MainBinding.ButMinus.setOnClickListener(v -> {

            if (MainBinding.mapView.current_level_index == 0) return;
            MainBinding.mapView.offset_x += MainBinding.mapView.width / 2.0f;
            MainBinding.mapView.offset_y += MainBinding.mapView.height / 2.0f;
            MainBinding.mapView.offset_x /= 2.0f;
            MainBinding.mapView.offset_y /= 2.0f;
            MainBinding.mapView.current_level_index--;
            MainBinding.mapView.invalidate();
            MainBinding.mapView.saveMapCurrent();
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

        // Сохраняем данные в базу данных при приостановке активности
        MainBinding.mapView.saveMapCurrent();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Восстанавливаем данные из базы данных при восстановлении активности
        MainBinding.mapView.restoreMapCurrent();
    }
    }