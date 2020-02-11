package com.android.pixelview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Canvas canvas;
        canvas = new Canvas(this);
        setContentView(canvas);
    }
}
