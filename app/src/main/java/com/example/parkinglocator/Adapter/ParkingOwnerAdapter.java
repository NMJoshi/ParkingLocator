package com.example.parkinglocator.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Model.ParkingOwner;
import com.example.parkinglocator.R;

import java.util.List;

public class ParkingOwnerAdapter  extends RecyclerView.Adapter<ParkingOwnerAdapter.ViewHolder> {

    Context context;
    List<ParkingOwner> parkingOwnerList;

    public ParkingOwnerAdapter(Context context, List<ParkingOwner> parkingOwnerList) {
        this.context = context;
        this.parkingOwnerList = parkingOwnerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.owner_profile_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText("  "+parkingOwnerList.get(position).getName());
        holder.tvEmail.setText("  "+parkingOwnerList.get(position).getEmail());
        holder.tvPhoneNumber.setText("  "+parkingOwnerList.get(position).getPhoneNumber());
        holder.tvAddress.setText(" "+parkingOwnerList.get(position).getAddress());
        holder.tvOwnerId.setText(" "+parkingOwnerList.get(position).getParkingOwnerId());

    }

    @Override
    public int getItemCount() {
        return parkingOwnerList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        TextView tvName,tvEmail,tvPhoneNumber,tvAddress,tvOwnerId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvEmail=itemView.findViewById(R.id.tvEmail);
            tvPhoneNumber=itemView.findViewById(R.id.tvPhoneNumber);
            tvAddress=itemView.findViewById(R.id.tvAddress);
            tvOwnerId=itemView.findViewById(R.id.tvOwnerId);

        }
    }
}
