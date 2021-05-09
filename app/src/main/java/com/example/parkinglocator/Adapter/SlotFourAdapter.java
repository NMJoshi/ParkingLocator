package com.example.parkinglocator.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Model.Slot;
import com.example.parkinglocator.R;

import java.util.List;

public class SlotFourAdapter extends RecyclerView.Adapter<SlotFourAdapter.ViewHolder> {

    List<Slot> slotListFourWheeler;
    Context context;

    public SlotFourAdapter(List<Slot> slotList, Context context) {
        this.slotListFourWheeler = slotList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_item_four_wheeler,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.btnFourWheeler.setText(slotListFourWheeler.get(position).getNumber().toString());
    }

    @Override
    public int getItemCount() {
        return slotListFourWheeler.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Button btnFourWheeler;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnFourWheeler=itemView.findViewById(R.id.btnFourWheeler);
        }
    }
}
