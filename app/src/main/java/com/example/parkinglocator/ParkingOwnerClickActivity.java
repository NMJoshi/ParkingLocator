package com.example.parkinglocator;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Adapter.SlotAdapter;
import com.example.parkinglocator.Model.Slot;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ParkingOwnerClickActivity extends AppCompatActivity {

    String TAG = "TAGER";
    TextView set;
    LinearLayout linearLayout;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    RecyclerView recyclerView, recyclerViewFourWheeler;
    List<Slot> slotList;
    List<Slot> slotListFourWheeler;
    String parkingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_owner_click);

        linearLayout = findViewById(R.id.linearLayout);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewFourWheeler = findViewById(R.id.recyclerViewFourWheeler);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        slotList = new ArrayList<>();
        slotListFourWheeler = new ArrayList<>();

        parkingId = getIntent().getStringExtra("parkingId");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firebaseFirestore.collection("ParkingOwner").document(auth.getCurrentUser().getUid()).collection("ParkingDetail").document(parkingId).
                collection("Slots").orderBy("number").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {


                        Slot slot = snapshot.toObject(Slot.class);
                        if (slot.getType().equals("twoWheeler")) {
                            slotList.add(slot);
                        } else {
                            slotListFourWheeler.add(slot);
                        }
                        Log.d(TAG, "onSuccess: " + slot.getNumber());

                    }

                    SlotAdapter slotAdapter = new SlotAdapter(ParkingOwnerClickActivity.this, slotList);
                    SlotAdapter slotAdapter1 = new SlotAdapter(ParkingOwnerClickActivity.this, slotListFourWheeler);

                    recyclerView.setLayoutManager(new GridLayoutManager(ParkingOwnerClickActivity.this, 5));
                    recyclerViewFourWheeler.setLayoutManager(new GridLayoutManager(ParkingOwnerClickActivity.this, 5));

                    recyclerView.setAdapter(slotAdapter);
                    recyclerViewFourWheeler.setAdapter(slotAdapter1);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.d(TAG, "onFailure: " + e);
            }
        });

//        firebaseFirestore.collection("ParkingOwner").document(auth.getCurrentUser().getUid()).collection("ParkingDetail").document(parkingId).
//                collection("Slots").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                if (!queryDocumentSnapshots.isEmpty()) {
//                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
//                        slotListFourWheeler.add(new Slot(Slot.four,"two wheeler added successfully",1));
//                        slotListFourWheeler.add(snapshot.toObject(Slot.class));
//
//                    }
//
//                    SlotFourAdapter slotFourAdapter = new SlotFourAdapter(slotListFourWheeler, ParkingOwnerClickActivity.this);
//                    recyclerViewFourWheeler.setLayoutManager(new GridLayoutManager(ParkingOwnerClickActivity.this, 5));
//                    recyclerView.setLayoutManager(new LinearLayoutManager(ParkingOwnerClickActivity.this,LinearLayoutManager.HORIZONTAL,false));
//                    recyclerViewFourWheeler.setAdapter(slotFourAdapter);
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//                Log.d(TAG, "onFailure: " + e);
//            }
//        });

//        SharedPreferences getsharedPref1=getSharedPreferences("Demo",0);
//        String value1=getsharedPref1.getString("twowheeler","");


//        set.setText(value1);
//
//
//        for(int i=1;i<=Integer.parseInt(value1);i++){
//
//            Button button=new Button(ParkingOwnerClickActivity.this);
//            button.setLayoutParams(new LinearLayout.LayoutParams(150,150));
//            button.setId(Integer.parseInt(value1));
//            button.setText("B"+value1);
//            linearLayout.addView(button);
//
//        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}