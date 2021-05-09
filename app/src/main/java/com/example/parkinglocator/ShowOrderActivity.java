package com.example.parkinglocator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Model.Order;
import com.example.parkinglocator.Model.ParkingDetail;

import java.util.ArrayList;
import java.util.List;

public class ShowOrderActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ParkingDetail parkingDetail;
    Order order;
    List<Order> orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);

        recyclerView=findViewById(R.id.recyclerView);
        orderList=new ArrayList<>();

    }
}