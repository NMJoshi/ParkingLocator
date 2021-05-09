package com.example.parkinglocator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Adapter.OrderAdapter;
import com.example.parkinglocator.Model.Order;
import com.example.parkinglocator.Model.ParkingDetail;
import com.example.parkinglocator.Model.Slot;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    Order order;
    ParkingDetail parkingDetail;
    List<Slot> slotList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        recyclerView=findViewById(R.id.recyclerView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        order= (Order) getIntent().getSerializableExtra("order");
        parkingDetail=(ParkingDetail)getIntent().getSerializableExtra("parkingDetail");

//        getAvailableOrder();
        slotList=new ArrayList<>();

        FirebaseFirestore
                .getInstance()
                .collection("ParkingOwner")
                .document(parkingDetail.getOwnerId())
                .collection("ParkingDetail")
                .document(parkingDetail.getParkingDetailId())
                .collection("Slots")
                .whereEqualTo("type",order.getType())
                .get().addOnSuccessListener( new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        slotList.add(snapshot.toObject(Slot.class));

                    }

                    OrderAdapter orderAdapter = new OrderAdapter(OrderActivity.this, slotList,parkingDetail,order);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(orderAdapter);

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


            }
        });



    }

//    private void getAvailableOrder() {
//        FirebaseFirestore
//                .getInstance()
//                .collection("ParkingOwner")
//                .document(parkingDetail.getOwnerId())
//                .collection("ParkingDetail")
//                .document(parkingDetail.getParkingDetailId())
//                .collection("Slots")
//                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                List<Slot> slotList=new ArrayList<Slot>();
//
//                Task start=FirebaseFirestore
//                        .getInstance()
//                        .collection("ParkingOwner")
//                        .document(parkingDetail.getOwnerId())
//                        .collection("ParkingDetail")
//                        .document(parkingDetail.getParkingDetailId())
//                        .collection("Slots")
//                        .document(slotList.get(position))
//                        .collection("Order")
//                        .whereGreaterThan("startTime",order.getEndTime())
//                        .get();
//
//                Task end=FirebaseFirestore
//                        .getInstance()
//                        .collection("ParkingOwner")
//                        .document(parkingDetail.getOwnerId())
//                        .collection("ParkingDetail")
//                        .document(parkingDetail.getParkingDetailId())
//                        .collection("Slots")
//                        .document(slotList.get(position))
//                        .collection("Order")
//                        .whereLessThan("endTime",order.getStartTime())
//                        .get();
//
//                Task<List<Order>> tasks=Tasks.whenAllSuccess(start,end);
//                tasks.addOnSuccessListener(new OnSuccessListener<List<Order>>() {
//                    @Override
//                    public void onSuccess(List<Order> orders) {
//                        if(orders.size()>0){
//                            //slot not available
//                        }
//                        else {
//                            //slot available
//                        }
//                    }
//                });
//            }
//        });
//
//    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}