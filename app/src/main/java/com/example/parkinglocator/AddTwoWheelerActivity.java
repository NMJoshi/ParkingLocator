package com.example.parkinglocator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddTwoWheelerActivity extends AppCompatActivity {

    EditText edtBikeName,edtBikePlateNumber,edtPhoneNumber;
    Button btnSubmit;
    TextView nearByMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_two_wheeler);

        edtBikeName=findViewById(R.id.edtCarName);
        edtBikePlateNumber=findViewById(R.id.edtCarPlateNumber);
        edtPhoneNumber=findViewById(R.id.edtPhoneNumber);
        btnSubmit=findViewById(R.id.btnSubmit);
        nearByMe=findViewById(R.id.nearByMe);

    }
}