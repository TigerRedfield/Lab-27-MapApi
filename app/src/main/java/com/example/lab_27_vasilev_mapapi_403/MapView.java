package com.example.lab_27_vasilev_mapapi_403;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.lab_27_vasilev_mapapi_403.model.MapTile;

import java.util.ArrayList;

public class MapView extends SurfaceView {
    ArrayList <MapTile> tiles = new ArrayList<MapTile>();
        /*
    Tile getTile(int x, int y, int scale)
    {

        for(int i=0; i<tiles.size(); i++)
        {
            Tile t = tiles.get(i);
            if(t.x == x && t.y == y && t.scale == scale) return t;
        }

        Tile nt = new Tile(x, y, scale, ctx);
        tiles.add(nt);

        return nt;
    } */

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

    Activity ctx;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /*
        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        setWillNotDraw(false); */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        /*
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
                return true;

            case MotionEvent.ACTION_UP:
                return true;
        } */
        return false;
    }

    /*
    boolean rect_intersects_rect(
            float ax0, float ay0, float ax1, float ay1,
            float bx0, float by0, float bx1, float by1) {

        if (ax1 < bx0) return false;
        if (ax0 > bx1) return false;
        if (ay1 < by0) return false;
        if (ay0 > by1) return false;

        return true;
    } */

    @Override
    protected void onDraw(Canvas canvas)
    {
        /*
       width = canvas.getWidth();
       height = canvas.getHeight();

       canvas.drawColor(Color.WHITE);

       int screen_x0 = 0;
       int screen_y0 = 0;
       int screen_x1 = canvas.getWidth() - 1;
       int screen_y1 = canvas.getHeight() - 1;

       int w = x_tiles[current_level_index];
       int h = y_tiles[current_level_index];

       for(int y = 0; y < h; y++)
            for(int x = 0; x < w; x++)
            {
                int x0 = x * tile_width + (int) offset_x;
                int y0 = y * tile_height + (int) offset_y;
                int x1 = x * tile_width;
                int y1 = y * tile_height;

                if(rect_intersects_rect(screen_x0, screen_y0, screen_x1, screen_y1, x0, y0, x1, y1) == false)
                    continue;


                Tile t = getTile(x, y, levels[current_level_index]);
                if(t.bmp != null) canvas.drawBitmap(t.bmp, x0, y0, p);

                canvas.drawRect(x0, y0, x1, y1, p);
                canvas.drawText(String.valueOf(levels[current_level_index]) + ", " + String.valueOf(x) +
                        ", " + String.valueOf(y), (x0 + x1)/2, (y0+y1)/2, p);
            }*/
    }
}
