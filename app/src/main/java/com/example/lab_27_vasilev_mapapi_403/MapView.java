package com.example.lab_27_vasilev_mapapi_403;

import static java.lang.Math.abs;
import static java.lang.Math.cos;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

import com.example.lab_27_vasilev_mapapi_403.model.CoordinateMap;
import com.example.lab_27_vasilev_mapapi_403.model.LineCoordinatesMap;
import com.example.lab_27_vasilev_mapapi_403.model.MapTile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapView extends SurfaceView {
    private MapDatabaseHelper dbHelper;

    private SQLiteDatabase db;
    private OnClickToMap listener;

    ArrayList <MapTile> tiles = new ArrayList<MapTile>();

    MapTile getTile(int x, int y, int scale)
    {

        for(int i=0; i<tiles.size(); i++)
        {
            MapTile t = tiles.get(i);
            if(t.x == x && t.y == y && t.scale == scale) return t;
        }

        MapTile nt = new MapTile(x, y, scale);
        tiles.add(nt);

        return nt;
    }

    float last_x;
    float last_y;
    int current_level_index = 0;

    int[] levels = new int[] {16, 8, 4, 2, 1};
    int[] x_tiles = new int[] {54, 108, 216, 432, 864};
    int[] y_tiles = new int[] {27, 54, 108, 216, 432};

    int tile_width = 100;
    int tile_height = 100;

    float offset_x = 0.0f;
    float offset_y = 0.0f;

    Paint p;

    int width, height;

    private CallMapApi API = ApiHelper.getMapApi();
    private List<List<CoordinateMap>> coastlineCoordinates = new ArrayList<>();
    private Canvas mapCanvas = new Canvas();

    public void setListener(OnClickToMap newListener) {
        this.listener = newListener;
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLACK);

        setWillNotDraw(false);

        // Инициализация базы данных SQLite
        dbHelper = new MapDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Метод для сохранения состояния карты в базу данных
    public void saveMapCurrent() {
        if (db != null) {
            // Удалить старую запись
            db.delete("map_state", null, null);

            // Вставить новую запись с текущими данными карты
            db.insert("map_state", null, MapDatabaseHelper.getContentValues(this));
        }
    }


    //  Метод для восстановления состояния карты из базы данных

    public void restoreMapCurrent() {
        if (db != null) {
            Cursor cursor = db.query(
                    "map_state",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                MapDatabaseHelper.CursorFromLoad(this, cursor);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        int action = event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                last_x = event.getX();
                last_y = event.getY();
                return true;

            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();

                float dx = x - last_x;
                float dy = y - last_y;

                offset_x += dx;
                offset_y += dy;
                invalidate();

                last_x = x;
                last_y = y;
                listener.onMove();
                return true;

            case MotionEvent.ACTION_UP:
                listener.onClick(
                        (event.getX() + abs(offset_x)) / levels[4 - current_level_index],
                        (event.getY() + abs(offset_y)) / levels[4 - current_level_index]
                );
                return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {

        width = canvas.getWidth();
        height = canvas.getHeight();

        canvas.drawColor(Color.WHITE);

        int screen_x0 = 0;
        int screen_y0 = 0;
        int screen_x1 = canvas.getWidth() - 1;
        int screen_y1 = canvas.getHeight() - 1;

        // Добавленная обработка current_level_index какие квадраты строить
        if (current_level_index >= 0 && current_level_index < levels.length) {
            int w = 0;
            int h = 0;

            if (current_level_index < x_tiles.length && current_level_index < y_tiles.length) {
                w = x_tiles[current_level_index];
                h = y_tiles[current_level_index];
            }

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int x0 = x * tile_width + (int) offset_x;
                    int y0 = y * tile_height + (int) offset_y;
                    int x1 = x0 + tile_width;
                    int y1 = y0 + tile_height;

                    if (x0 >= screen_x1 || x1 <= screen_x0 || y0 >= screen_y1 || y1 <= screen_y0) {

                        // Пропускать рисование тайла, если он его нет на экране
                        continue;
                    }


                    MapTile t = getTile(x, y, levels[current_level_index]);
                    if (t.bmp != null) {
                        canvas.drawBitmap(t.bmp, x0, y0, p);

                    }

                    canvas.drawRect(x0, y0, x1, y1, p);
                    canvas.drawText(String.valueOf(levels[current_level_index]) + ", " + String.valueOf(x) + ", " + String.valueOf(y),
                            (x0 + x1) / 2,
                            (y0 + y1) / 2,
                            p);

                }
        }

            Paint coastlinePaint = new Paint();

            coastlinePaint.setColor(Color.DKGRAY);
            coastlinePaint.setStrokeWidth(2.0f);
            coastlinePaint.setStyle(Paint.Style.STROKE);


            for (List<CoordinateMap> coastLine : coastlineCoordinates) {
                for (int i = 0; i < coastLine.size() - 1; i++) {
                    canvas.drawLine(coastLine.get(i).x, coastLine.get(i).y, coastLine.get(i + 1).x, coastLine.get(i + 1).y, coastlinePaint);
                }
            }
        }
        else
        {
            // Обработка случая, когда current_level_index находится за пределами массива levels
            // Например, установка его в допустимое значение или выполнение других действий
            if (current_level_index < 0)
            {
                current_level_index = 0; // Установка его в минимальное допустимое значение
            }

            else
            {
                current_level_index = levels.length - 1; // Установка его в максимальное допустимое значение
            }
        }
        mapCanvas = canvas;
    }

    public void refreshMap() {

        coastlineCoordinates.clear();

        API.Coastline(
                levels[current_level_index],
                ((mapCanvas.getWidth() / 360) * (offset_x / 360)),
                ((mapCanvas.getHeight() / 360) * (offset_y / 360)),
                ((mapCanvas.getWidth() / 360) * ((offset_x + (mapCanvas.getWidth() - 1)) / 360)),
                ((mapCanvas.getHeight() / 360) * ((offset_y + (mapCanvas.getHeight() - 1)) / 360))
        ).enqueue(new Callback<List<List<CoordinateMap>>>() {
            @Override
            public void onResponse(Call<List<List<CoordinateMap>>> call, Response<List<List<CoordinateMap>>> response) {

                coastlineCoordinates.addAll(response.body());
                invalidate();
            }

            @Override
            public void onFailure(Call<List<List<CoordinateMap>>> call, Throwable t) {

            }
        });

    }

}
