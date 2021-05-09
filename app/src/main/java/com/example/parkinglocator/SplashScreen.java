package com.example.parkinglocator;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Thread t1=new Thread()
        {
            public void run()
            {
                try {
                    sleep(0);
                    Intent intent=new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
//pela 3000 htoo

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        t1.start();
    }
}