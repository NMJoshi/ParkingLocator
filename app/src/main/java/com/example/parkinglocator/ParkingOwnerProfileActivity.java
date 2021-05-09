package com.example.parkinglocator;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Adapter.ParkingOwnerAdapter;
import com.example.parkinglocator.Model.ParkingOwner;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ParkingOwnerProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    String TAG="TAGER";
    RecyclerView recyclerView;
    List<ParkingOwner> parkingOwnerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_owner_profile);

        auth=FirebaseAuth.getInstance();
        recyclerView=findViewById(R.id.recyclerView);
        parkingOwnerList=new ArrayList<>();
        firebaseFirestore=FirebaseFirestore.getInstance();
        parkingOwnerList=new ArrayList<>();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firebaseFirestore.collection("ParkingOwner").document(auth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                ParkingOwner parkingOwner= documentSnapshot.toObject(ParkingOwner.class);

                parkingOwnerList.add(parkingOwner);

                ParkingOwnerAdapter parkingOwnerAdapter = new ParkingOwnerAdapter(ParkingOwnerProfileActivity.this, parkingOwnerList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ParkingOwnerProfileActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(parkingOwnerAdapter);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.d(TAG, "onFailure: "+e.getMessage());
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}