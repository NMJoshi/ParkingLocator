package com.example.parkinglocator.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Model.ParkingDetail;
import com.example.parkinglocator.ParkingOwnerClickActivity;
import com.example.parkinglocator.R;

import java.util.List;

public class ParkingDetailAdapter extends RecyclerView.Adapter<ParkingDetailAdapter.ViewModel> {

    Context context;
    List<ParkingDetail> parkingDetailList;
    String TAG = "TAGER";


    public ParkingDetailAdapter(Context context, List<ParkingDetail> parkingDetailList) {
        this.context = context;
        this.parkingDetailList = parkingDetailList;
    }


    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_detail_item, null, false);
        return new ViewModel(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, final int position) {

        SharedPreferences getShared = context.getSharedPreferences("Demo", 0);
        final String value = getShared.getString("documentReferance", "noParkingId");
        SharedPreferences getShared1 = context.getSharedPreferences("Demo", 0);
        final String value1 = getShared1.getString("documentReferance1", "ParkingId");

        holder.tvName.setText("Name :- " + parkingDetailList.get(position).getName());
        holder.tvEmail.setText("Email :- " + parkingDetailList.get(position).getEmail());
        holder.tvPhoneNumber.setText("PhoneNumber :- " + parkingDetailList.get(position).getPhonenumber());
        holder.tvAddress.setText("Address :- " + parkingDetailList.get(position).getAddress());
        holder.tvTwoWheeler.setText("Two-Wheeler :- " + parkingDetailList.get(position).getTwoWheeler());
        holder.tvFourWheeler.setText("Four-Wheeler :- " + parkingDetailList.get(position).getFourWheeler());
        holder.tvlattitude.setText("Lattitude :- " + parkingDetailList.get(position).getLatitude());
        holder.tvlongitude.setText("Longitude :- " + parkingDetailList.get(position).getLongitude());

//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder dailog = new AlertDialog.Builder(context);
//                dailog.setTitle("Are You Sure??");
//                dailog.setMessage("Deleting this account will result in completely removing your" +
//                        "account from the system and you won't be able to acess the app:");
//
//                dailog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        FirebaseFirestore.getInstance().collection("ParkingOwner").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).
//                                collection("ParkingDetail").document(value).collection("Slots").document(value1)
//                                .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                });
//                dailog.setNegativeButton("dismiss", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//                    }
//                });
//
//                AlertDialog alertDialog = dailog.create();
//                alertDialog.show();
//
//            }
//        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ParkingOwnerClickActivity.class);
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                intent.putExtra("parkingId",parkingDetailList.get(position).getParkingDetailId());
                context.startActivity(intent);

            }
        });
    }


    public void setParkingList(List<ParkingDetail> offerList){
        this.parkingDetailList=offerList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parkingDetailList.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {

        TextView tvName, tvEmail, tvPhoneNumber, tvAddress, tvTwoWheeler, tvFourWheeler, tvlattitude, tvlongitude;
        LinearLayout linearLayout;


        public ViewModel(@NonNull View itemView) {
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

//        holder.tvName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, ParkingOwnerClickActivity.class);
//                intent.putExtra("Name",temp.getName());
//                intent.putExtra("Email",temp.getEmail());
//                intent.putExtra("Address",temp.getAddress());
//                intent.putExtra("TwoWheeler",temp.getTwoWheeler());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            }
//        });