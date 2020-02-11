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
    Paint buttonPixel;
    Paint background;
    int sideLength;
    int numberHorizontal;
    int numberVertical;
    float sideHorizontal;
    float sideVertical;
    int marginHorizontal;
    int marginVertical;
    int[][] gridAlpha;
    int[][] gridTask;
    int[][] gridColor;
    int[][] gridFlickerTime;

    public Canvas(Context context, int deviceWidth, int deviceHeight) {
        super(context);
        buttonPixel = new Paint();
        pixel = new Paint();
        background = new Paint();
        pixel.setColor(Color.parseColor("#000000"));
        pixel.setAlpha(180);
        buttonPixel.setColor(Color.parseColor("#ffca28"));
        buttonPixel.setAlpha(255);
        background.setColor(Color.parseColor("#353535"));
        Log.e("Con","Con");
        sideLength = 30;
        numberHorizontal = deviceWidth / sideLength;
        numberVertical = deviceHeight / sideLength;
        sideHorizontal = (float) deviceWidth / numberHorizontal;
        sideVertical = (float) deviceHeight / numberVertical;
        gridAlpha = new int[numberHorizontal][numberVertical];
        gridTask = new int[numberHorizontal][numberVertical];
        gridColor = new int[numberHorizontal][numberVertical];
        gridFlickerTime = new int[numberHorizontal][numberVertical];
        marginHorizontal = 1;
        marginVertical = 1;

        //temporary buttons at (getHeight/4)

        int midVertical = (numberVertical)/4;
        int midHorizontal = (numberHorizontal)/4;
        Log.e("V&H","V: "+midVertical+" H: "+midHorizontal);

        for(int i=-7;i<8;i++)
            for(int j=-2;j<2;j++)
                gridTask[i+midHorizontal][j+midVertical]=1;

        midHorizontal = (3*numberHorizontal)/4;
        Log.e("V&H","V: "+midVertical+" H: "+midHorizontal);

        for(int i=-7;i<8;i++)
            for(int j=-2;j<2;j++)
                gridTask[i+midHorizontal][j+midVertical]=1;


        midVertical = (numberVertical)/2;
        midHorizontal = (numberHorizontal)/2;
        Log.e("V&H","V: "+midVertical+" H: "+midHorizontal);

        for(int i=-2;i<2;i++)
            for(int j=-7;j<8;j++)
                gridTask[i+midHorizontal][j+midVertical]=1;

        midVertical = (3*numberVertical)/4;
        midHorizontal = (numberHorizontal)/2;
        Log.e("V&H","V: "+midVertical+" H: "+midHorizontal);

        for(int i=-15;i<16;i++)
            for(int j=-2;j<2;j++)
                gridTask[i+midHorizontal][j+midVertical]=1;

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
        Log.e("H", "" + sideHorizontal);
        Log.e("V", "" + sideVertical);
        Log.e("MH", "" + marginHorizontal);
        Log.e("MV", "" + marginVertical);
        for (int i = 0; i < numberHorizontal; i++) {
            for (int j = 0; j < numberVertical; j++) {
                if(gridTask[i][j]==0)
                    canvas.drawRect(i * sideHorizontal + marginHorizontal, j * sideVertical + marginVertical, (i + 1) * sideHorizontal - marginHorizontal, (j + 1) * sideVertical - marginVertical, pixel);
                if(gridTask[i][j]==1)
                    canvas.drawRect(i * sideHorizontal + marginHorizontal, j * sideVertical + marginVertical, (i + 1) * sideHorizontal - marginHorizontal, (j + 1) * sideVertical - marginVertical, buttonPixel);
            }
        }
    }
}
