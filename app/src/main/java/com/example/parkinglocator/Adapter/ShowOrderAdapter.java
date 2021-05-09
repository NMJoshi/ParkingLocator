package com.example.parkinglocator.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Model.Order;
import com.example.parkinglocator.R;

import java.util.List;


public class ShowOrderAdapter extends RecyclerView.Adapter<ShowOrderAdapter.ViewHolder> {

    List<Order> orderList;
    Context context;

    public ShowOrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.showorder_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        TextView tvName,tvPhoneNumber,tvEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvPhoneNumber=itemView.findViewById(R.id.tvPhoneNumber);
            tvEmail=itemView.findViewById(R.id.tvEmail);


        }
    }
}
