package com.example.parkinglocator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parkinglocator.Model.Order;
import com.example.parkinglocator.Model.ParkingDetail;
import com.example.parkinglocator.Model.Slot;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class BookingActivity extends AppCompatActivity {

    ParkingDetail parkingDetail;
    Order order;
    Slot slot;
    TextView tvParkingName,tvEmail,tvPhoneNumber,tvStartTime,tvEndTime,tvType;
    Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvParkingName=findViewById(R.id.tvParkingName);
        tvEmail=findViewById(R.id.tvEmail);
        tvPhoneNumber=findViewById(R.id.tvPhoneNumber);
        tvParkingName=findViewById(R.id.tvParkingName);
        tvStartTime=findViewById(R.id.tvStartTime);
        tvEndTime=findViewById(R.id.tvEndTime);
        tvType=findViewById(R.id.tvType);


        btnBook=findViewById(R.id.btnBook);

        parkingDetail= (ParkingDetail) getIntent().getSerializableExtra("parkingDetail");
        order= (Order) getIntent().getSerializableExtra("order");
        slot= (Slot) getIntent().getSerializableExtra("slot");

        setDetails();


    }

    private void setDetails() {
        tvParkingName.setText(parkingDetail.getName());
        tvPhoneNumber.setText(parkingDetail.getPhonenumber());
        tvEmail.setText(parkingDetail.getEmail());
        tvStartTime.setText(order.getStartTime());
        tvEndTime.setText(order.getEndTime());
//        tvDuration.setText(order.getDuration());
        tvType.setText(slot.getType());



        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore.getInstance()
                        .collection("ParkingOwner")
                        .document(parkingDetail.getOwnerId())
                        .collection("ParkingDetail")
                        .document(parkingDetail.getParkingDetailId())
                        .collection("Slots")
                        .document(slot.getId())
                        .collection("Order")
                        .add(order)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(BookingActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                                Log.d("TAGGER", "onSuccess: Booking");

                                Intent intent=new Intent(BookingActivity.this,ConformOrderActivity.class);
                                intent.putExtra("parkingDetail",parkingDetail);
                                intent.putExtra("order",order);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}