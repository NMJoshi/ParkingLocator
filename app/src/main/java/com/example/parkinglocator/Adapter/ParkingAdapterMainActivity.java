package com.example.parkinglocator.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Date_Time_Activity;
import com.example.parkinglocator.Model.ParkingDetail;
import com.example.parkinglocator.R;

import java.util.List;

public class ParkingAdapterMainActivity extends RecyclerView.Adapter<ParkingAdapterMainActivity.ViewHolder> {

    Context context;
    List<ParkingDetail> parkingDetailList;
    String TAG = "TAGER";


    public ParkingAdapterMainActivity(Context context, List<ParkingDetail> parkingDetailList) {
        this.context = context;
        this.parkingDetailList = parkingDetailList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_detail_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvName.setText("Name :- " + parkingDetailList.get(position).getName());
        holder.tvEmail.setText("Email :- " + parkingDetailList.get(position).getEmail());
        holder.tvPhoneNumber.setText("PhoneNumber :- " + parkingDetailList.get(position).getPhonenumber());
        holder.tvAddress.setText("Address :- " + parkingDetailList.get(position).getAddress());
        holder.tvTwoWheeler.setText("Two-Wheeler :- " + parkingDetailList.get(position).getTwoWheeler());
        holder.tvFourWheeler.setText("Four-Wheeler :- " + parkingDetailList.get(position).getFourWheeler());
        holder.tvlattitude.setText("Lattitude :- " + parkingDetailList.get(position).getLatitude());
        holder.tvlongitude.setText("Longitude :- " + parkingDetailList.get(position).getLongitude());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Date_Time_Activity.class);
                intent.putExtra("parkingDetail",parkingDetailList.get(position));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return parkingDetailList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvPhoneNumber, tvAddress, tvTwoWheeler, tvFourWheeler, tvlattitude, tvlongitude;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvTwoWheeler = itemView.findViewById(R.id.tvtwoWheeler);
            tvFourWheeler = itemView.findViewById(R.id.tvfourWheeler);
            tvlattitude = itemView.findViewById(R.id.tvlatitude);
            tvlongitude = itemView.findViewById(R.id.tvlongitude);
        }
    }
}
