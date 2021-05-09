package com.example.parkinglocator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddFourWheelerActivity extends AppCompatActivity {

     EditText edtCarName,edtCarPlateNumber,edtPhoneNumber;
     Button btnSubmit;
     TextView nearByMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_four_wheeler);

        edtCarName=findViewById(R.id.edtCarName);
        edtCarPlateNumber=findViewById(R.id.edtCarPlateNumber);
        edtPhoneNumber=findViewById(R.id.edtPhoneNumber);
        btnSubmit=findViewById(R.id.btnSubmit);
        nearByMe=findViewById(R.id.nearByMe);
        
    }
}