package com.android.pixelview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Canvas extends View {
    Paint pixel;
    Paint background;
    Paint grid;

    public Canvas(Context context) {
        super(context);
        pixel = new Paint();
        grid = new Paint();
        background = new Paint();
        pixel.setColor(Color.parseColor("#ffffff"));
        pixel.setAlpha(180);
        grid.setColor(Color.parseColor("#ff0000"));
        grid.setStyle(Paint.Style.STROKE);
        background.setColor(Color.parseColor("#ffca28"));
        Log.e("Con","Con");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Random random = new Random();
        int alpha =random.nextInt(255);
        pixel.setAlpha(alpha);
        Log.e("A",alpha+"");
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        Log.e("Draw","onDraw");
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
        int sideLength = 30;
        int numberHorizontal = getWidth() / sideLength;
        int numberVertical = getHeight() / sideLength;
        float sideHorizontal = (float) getWidth() / numberHorizontal;
        float sideVertical = (float) getHeight() / numberVertical;
        int marginHorizontal = 2;
        int marginVertical = 2;
        Log.e("H", "" + sideHorizontal);
        Log.e("V", "" + sideVertical);
        Log.e("MH", "" + marginHorizontal);
        Log.e("MV", "" + marginVertical);
        for (int i = 0; i < numberHorizontal; i++) {
            for (int j = 0; j < numberVertical; j++) {
                canvas.drawRect(i * sideHorizontal + marginHorizontal, j * sideVertical + marginVertical, (i + 1) * sideHorizontal - marginHorizontal, (j + 1) * sideVertical - marginVertical, pixel);
                canvas.drawRect(i * sideHorizontal, j * sideVertical, (i + 1) * sideHorizontal, (j + 1) * sideVertical, grid);
            }
        }
    }
}
