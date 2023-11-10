package com.example.lab_27_vasilev_mapapi_403;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    MapView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mv = findViewById(R.id.mapView);
        mv.ctx = this;

    }

        public void zoomOut(View v)
        {
            if(mv.current_level_index == 0) return;
            mv.offset_x += mv.width / 2.0f;
            mv.offset_y += mv.height / 2.0f;
            mv.offset_x /= 2.0f;
            mv.offset_y /= 2.0f;
            mv.current_level_index--;
            mv.invalidate();
        }

        public void zoomIn(View v)
        {
            if(mv.current_level_index == mv.levels.length - 1) return;
            mv.offset_x *= 2.0f;
            mv.offset_y *= 2.0f;
            mv.offset_x -= mv.width / 2.0f;
            mv.offset_y -= mv.height / 2.0f;
            mv.current_level_index--;
            mv.invalidate();
        }
    }