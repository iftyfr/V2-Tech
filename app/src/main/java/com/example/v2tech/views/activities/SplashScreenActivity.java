package com.example.v2tech.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.v2tech.R;


public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView logo = findViewById(R.id.logo);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        logo.startAnimation(animation);

        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                super.run();
            }
        };
        timer.start();
    }
}
