package com.example.parkinglocator.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.BookingActivity;
import com.example.parkinglocator.Model.Order;
import com.example.parkinglocator.Model.ParkingDetail;
import com.example.parkinglocator.Model.Slot;
import com.example.parkinglocator.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    List<Slot> slotList;
    ParkingDetail parkingDetail;
    Order order;

    public OrderAdapter(Context context, List<Slot> slotList, ParkingDetail parkingDetail, Order order) {
        this.context = context;
        this.slotList = slotList;
        this.parkingDetail = parkingDetail;
        this.order = order;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sloat,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        getAvailableOrder(position, holder);
    }

    private void getAvailableOrder(final int position, final ViewHolder holder) {

                Task start=FirebaseFirestore
                        .getInstance()
                        .collection("ParkingOwner")
                        .document(parkingDetail.getOwnerId())
                        .collection("ParkingDetail")
                        .document(parkingDetail.getParkingDetailId())
                        .collection("Slots")
                        .document(slotList.get(position).getId())
                        .collection("Order")
                        .whereGreaterThan("startTime",order.getEndTime())
                        .get();

                Task end=FirebaseFirestore
                        .getInstance()
                        .collection("ParkingOwner")
                        .document(parkingDetail.getOwnerId())
                        .collection("ParkingDetail")
                        .document(parkingDetail.getParkingDetailId())
                        .collection("Slots")
                        .document(slotList.get(position).getId())
                        .collection("Order")
                        .whereLessThan("endTime",order.getStartTime())
                        .get();

                Task<List<QuerySnapshot>> tasks= Tasks.whenAllSuccess(start,end);
                tasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
                    @Override
                    public void onSuccess(List<QuerySnapshot> querySnapshot) {
                        Log.d(TAG, "onSuccess: size "+querySnapshot.size());
//                        for(Order order : orders){
//                            Log.d(TAG, "loop: "+order.getId());
//                        }
                        if(querySnapshot.get(0).getDocuments().size()>0 || querySnapshot.get(0).getDocuments().size()>0){
                            holder.tvStatus.setText("Slot Unavailable");
                            Log.d(TAG, "onSuccess: SLOT UNAVAIALBLE "+slotList.get(position).getId());
                        }
                        else {
                            Log.d(TAG, "onSuccess: SLOT AVAILABLE "+slotList.get(position).getId());
                            holder.tvStatus.setText("Slot Available");

                            holder.tvStatus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=new Intent(holder.itemView.getContext(), BookingActivity.class);
                                    intent.putExtra("parkingDetail",parkingDetail);
                                    intent.putExtra("order",order);
                                    intent.putExtra("slot",slotList.get(position));
                                    holder.itemView.getContext().startActivity(intent);
                                }
                            });
                        }
                    }
                });


    }

    String TAG="TAGGER";
    @Override
    public int getItemCount() {
        return slotList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layoutParent;
        TextView tvStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutParent=itemView.findViewById(R.id.layoutParent);
            tvStatus=itemView.findViewById(R.id.tvStatus);
        }
    }
}
