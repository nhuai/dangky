package com.example.aipham.dangky;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#FDEFE6"))
                .withBeforeLogoText("Remember Me")
                .withLogo(R.mipmap.rememberme);

        config.getBeforeLogoTextView().setTextSize(30);
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
