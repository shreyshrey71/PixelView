package com.android.pixelview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
import java.util.logging.Handler;

public class Canvas extends View {
    Paint pixel;
    Paint buttonPixel0;
    Paint buttonPixel1;
    Paint buttonPixel2;
    Paint buttonPixel3;
    Paint buttonPixel4;
    Paint buttonPixel5;
    Paint background;
    Context mContext;
    int sideLength;
    int deviceWidth;
    int deviceHeight;
    int numberHorizontal;
    int numberVertical;
    float sideHorizontal;
    float sideVertical;
    float marginHorizontal;
    float marginVertical;
    PixelClass[][] pixelVariable;
    int buttonFlickTimeLayer = 2;

    public Canvas(Context context, int deviceWidth, int deviceHeight) {
        super(context);
        mContext = context;
        buttonPixel0 = new Paint();
        buttonPixel1 = new Paint();
        buttonPixel2 = new Paint();
        buttonPixel3 = new Paint();
        buttonPixel4 = new Paint();
        buttonPixel5 = new Paint();
        pixel = new Paint();
        background = new Paint();
        pixel.setColor(Color.parseColor("#000000"));
        pixel.setAlpha(180);
        buttonPixel0.setColor(Color.parseColor("#dddddd"));
        buttonPixel0.setAlpha(255);
        buttonPixel1.setColor(Color.parseColor("#dddddd"));
        buttonPixel1.setAlpha(217);
        buttonPixel2.setColor(Color.parseColor("#dddddd"));
        buttonPixel2.setAlpha(178);
        buttonPixel3.setColor(Color.parseColor("#dddddd"));
        buttonPixel3.setAlpha(140);
        buttonPixel4.setColor(Color.parseColor("#dddddd"));
        buttonPixel4.setAlpha(102);
        buttonPixel5.setColor(Color.parseColor("#dddddd"));
        buttonPixel5.setAlpha(64);
        background.setColor(Color.parseColor("#353535"));
        Log.e("Con", "Con");
        this.deviceWidth = deviceWidth;
        this.deviceHeight = deviceHeight;
        sideLength = 15;
        numberHorizontal = deviceWidth / sideLength;
        numberVertical = deviceHeight / sideLength;

        while ((deviceWidth / sideLength) % 2 == 0) {
            sideLength--;
            numberHorizontal = deviceWidth / sideLength;
            numberVertical = deviceHeight / sideLength;
            Log.e("#", ""+sideLength);
        }
        sideHorizontal = (float) deviceWidth / numberHorizontal;
        sideVertical = (float) deviceHeight / numberVertical;
        /*gridAlpha = new int[numberHorizontal][numberVertical];
        gridTask = new int[numberHorizontal][numberVertical];
        gridColor = new int[numberHorizontal][numberVertical];
        gridFlickerTime = new int[numberHorizontal][numberVertical];*/
        pixelVariable = new PixelClass[numberHorizontal][numberVertical];
        for (int i = 0; i < numberHorizontal; i++)
            for (int j = 0; j < numberVertical; j++) {
                pixelVariable[i][j] = new PixelClass();
            }
        marginHorizontal = 0.5f;
        marginVertical = 0.5f;

        Log.e("LEN", "" + sideLength);
        Log.e("NUMBER", "V: " + numberVertical + " H: " + numberHorizontal);
        //temporary buttons at (getHeight/4)

        int midVertical = (numberVertical) / 16;
        int midHorizontal = (numberHorizontal) / 4;
        Log.e("V&H", "V: " + midVertical + " H: " + midHorizontal);

        for (int i = -16; i < 17; i++)
            for (int j = -5; j < 6; j++) {
                pixelVariable[i + midHorizontal][j + midVertical].pixelTask = 1;
                pixelVariable[i + midHorizontal][j + midVertical].pixelAlpha = Math.max(Math.abs(j), (Math.abs(i) + 1) / 3);
                pixelVariable[i + midHorizontal][j + midVertical].pixelInitialAlpha = Math.max(Math.abs(j), (Math.abs(i) + 1) / 3);
            }

        Log.e("V&H", "V: " + midVertical + " H: " + midHorizontal);
        midVertical = (numberVertical) / 16;
        midHorizontal = (3 * numberHorizontal) / 4;
        Log.e("V&H", "V: " + midVertical + " H: " + midHorizontal);

        for (int i = -16; i < 17; i++)
            for (int j = -5; j < 6; j++) {
                pixelVariable[i + midHorizontal][j + midVertical].pixelTask = 2;
                pixelVariable[i + midHorizontal][j + midVertical].pixelAlpha = Math.max(Math.abs(j), (Math.abs(i) + 1) / 3);
                pixelVariable[i + midHorizontal][j + midVertical].pixelInitialAlpha = Math.max(Math.abs(j), (Math.abs(i) + 1) / 3);
            }

        midVertical = (numberVertical) / 3;
        midHorizontal = (numberHorizontal) / 4;
        Log.e("V&H", "V: " + midVertical + " H: " + midHorizontal);

        for (int i = -16; i < 17; i++)
            for (int j = -5; j < 6; j++) {
                pixelVariable[i + midHorizontal][j + midVertical].pixelTask = 3;
                pixelVariable[i + midHorizontal][j + midVertical].pixelAlpha = Math.max(Math.abs(j), (Math.abs(i) + 1) / 3);
                pixelVariable[i + midHorizontal][j + midVertical].pixelInitialAlpha = Math.max(Math.abs(j), (Math.abs(i) + 1) / 3);
            }

        Log.e("V&H", "V: " + midVertical + " H: " + midHorizontal);
        midVertical = (numberVertical) / 3;
        midHorizontal = (3 * numberHorizontal) / 4;
        Log.e("V&H", "V: " + midVertical + " H: " + midHorizontal);

        for (int i = -16; i < 17; i++)
            for (int j = -5; j < 6; j++) {
                pixelVariable[i + midHorizontal][j + midVertical].pixelTask = 4;
                pixelVariable[i + midHorizontal][j + midVertical].pixelAlpha = Math.max(Math.abs(j), (Math.abs(i) + 1) / 3);
                pixelVariable[i + midHorizontal][j + midVertical].pixelInitialAlpha = Math.max(Math.abs(j), (Math.abs(i) + 1) / 3);
            }



        midVertical = (15 * numberVertical) / 16;
        midHorizontal = (numberHorizontal) / 2;
        Log.e("V&H", "V: " + midVertical + " H: " + midHorizontal);

        for (int i = -33; i < 34; i++)
            for (int j = -5; j < 6; j++) {
                pixelVariable[i + midHorizontal][j + midVertical].pixelTask = 6;
                pixelVariable[i + midHorizontal][j + midVertical].pixelAlpha = Math.max(Math.abs(j), (Math.abs(i) + 2) / 6);
                pixelVariable[i + midHorizontal][j + midVertical].pixelInitialAlpha = Math.max(Math.abs(j), (Math.abs(i) + 2) / 6);
            }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int cx = (int) (event.getX() * numberHorizontal) / deviceWidth, cy = (int) (event.getY() * numberVertical) / deviceHeight;
        switch (pixelVariable[cx][cy].pixelTask) {
            case 1: {
                int midVertical = (numberVertical) / 16;
                int midHorizontal = (numberHorizontal) / 4;
                for (int k = 1; k <= 3; k++) {
                    for (int i = -16; i < 17; i++)
                        for (int j = -5; j < 6; j++) {
                            pixelVariable[i + midHorizontal][j + midVertical].pixelFlickerTime = buttonFlickTimeLayer*6;
                        }
                }
                break;
            }
            case 2: {
                int midVertical = (numberVertical) / 16;
                int midHorizontal = (3 * numberHorizontal) / 4;
                for (int k = 1; k <= 3; k++) {
                    for (int i = -16; i < 17; i++)
                        for (int j = -5; j < 6; j++) {
                            pixelVariable[i + midHorizontal][j + midVertical].pixelFlickerTime = buttonFlickTimeLayer*6;
                        }
                }
                break;
            }
            case 3: {
                int midVertical = (numberVertical) / 3;
                int midHorizontal = (numberHorizontal) / 4;
                for (int k = 1; k <= 3; k++) {
                    for (int i = -16; i < 17; i++)
                        for (int j = -5; j < 6; j++) {
                            pixelVariable[i + midHorizontal][j + midVertical].pixelFlickerTime = buttonFlickTimeLayer*6;
                        }
                }
                break;
            }
            case 4: {
                int midVertical = (numberVertical) /3;
                int midHorizontal = (3 * numberHorizontal) / 4;
                for (int k = 1; k <= 3; k++) {
                    for (int i = -16; i < 17; i++)
                        for (int j = -5; j < 6; j++) {
                            pixelVariable[i + midHorizontal][j + midVertical].pixelFlickerTime = buttonFlickTimeLayer*6;
                        }
                }
                break;
            }
            case 5: {
                int midVertical = (numberVertical) / 2;
                int midHorizontal = (numberHorizontal) / 2;
                for (int k = 1; k <= 3; k++) {
                    for (int j = -16; j < 17; j++)
                        for (int i = -5; i < 6; i++) {
                            pixelVariable[i + midHorizontal][j + midVertical].pixelFlickerTime = buttonFlickTimeLayer*6;
                        }
                }
                break;
            }
            case 6: {
                int midVertical = (15 * numberVertical) / 16;
                int midHorizontal = (numberHorizontal) / 2;
                for (int k = 1; k <= 3; k++) {
                    for (int i = -33; i < 34; i++)
                        for (int j = -5; j < 6; j++) {
                            pixelVariable[i + midHorizontal][j + midVertical].pixelFlickerTime = buttonFlickTimeLayer*6;
                        }
                }
                break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
        for (int i = 0; i < numberHorizontal; i++) {
            for (int j = 0; j < numberVertical; j++) {
                if (pixelVariable[i][j].pixelTask == 0)
                    canvas.drawRect(i * sideHorizontal + marginHorizontal, j * sideVertical + marginVertical, (i + 1) * sideHorizontal - marginHorizontal, (j + 1) * sideVertical - marginVertical, pixel);
                else {

                    if (pixelVariable[i][j].pixelFlickerTime > 0) {
                        pixelVariable[i][j].pixelFlickerTime--;
                        pixelVariable[i][j].pixelAlpha = ((pixelVariable[i][j].pixelFlickerTime / buttonFlickTimeLayer) + pixelVariable[i][j].pixelInitialAlpha) % 6;
                        Log.e("$", "" + pixelVariable[i][j].pixelFlickerTime);
                    }
                    switch (pixelVariable[i][j].pixelAlpha) {
                        case 0: {
                            canvas.drawRect(i * sideHorizontal + marginHorizontal, j * sideVertical + marginVertical, (i + 1) * sideHorizontal - marginHorizontal - pixelVariable[i][j].pixelFlickerTime / 30, (j + 1) * sideVertical - marginVertical - pixelVariable[i][j].pixelFlickerTime / 30, buttonPixel0);
                            break;
                        }
                        case 1: {
                            canvas.drawRect(i * sideHorizontal + marginHorizontal, j * sideVertical + marginVertical, (i + 1) * sideHorizontal - marginHorizontal - pixelVariable[i][j].pixelFlickerTime / 30, (j + 1) * sideVertical - marginVertical - pixelVariable[i][j].pixelFlickerTime / 30, buttonPixel1);
                            break;
                        }
                        case 2: {
                            canvas.drawRect(i * sideHorizontal + marginHorizontal, j * sideVertical + marginVertical, (i + 1) * sideHorizontal - marginHorizontal - pixelVariable[i][j].pixelFlickerTime / 30, (j + 1) * sideVertical - marginVertical - pixelVariable[i][j].pixelFlickerTime / 30, buttonPixel2);
                            break;
                        }
                        case 3: {
                            canvas.drawRect(i * sideHorizontal + marginHorizontal, j * sideVertical + marginVertical, (i + 1) * sideHorizontal - marginHorizontal - pixelVariable[i][j].pixelFlickerTime / 30, (j + 1) * sideVertical - marginVertical - pixelVariable[i][j].pixelFlickerTime / 30, buttonPixel3);
                            break;
                        }
                        case 4: {
                            canvas.drawRect(i * sideHorizontal + marginHorizontal, j * sideVertical + marginVertical, (i + 1) * sideHorizontal - marginHorizontal - pixelVariable[i][j].pixelFlickerTime / 30, (j + 1) * sideVertical - marginVertical - pixelVariable[i][j].pixelFlickerTime / 30, buttonPixel4);
                            break;
                        }
                        case 5: {
                            canvas.drawRect(i * sideHorizontal + marginHorizontal, j * sideVertical + marginVertical, (i + 1) * sideHorizontal - marginHorizontal - pixelVariable[i][j].pixelFlickerTime / 30, (j + 1) * sideVertical - marginVertical - pixelVariable[i][j].pixelFlickerTime / 30, buttonPixel5);
                            break;
                        }
                    }
                }
            }
        }
        invalidate();
    }
}
