package com.example.parkinglocator;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ConformOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform_order);
        final Thread t1=new Thread()
        {
            public void run()
            {
                try {
                    sleep(3000);
                    Intent intent=new Intent(ConformOrderActivity.this,MainActivity.class);
                    startActivity(intent);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        t1.start();
    }
}