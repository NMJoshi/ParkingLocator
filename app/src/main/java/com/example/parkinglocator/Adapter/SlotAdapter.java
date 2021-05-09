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

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.ViewHolder> {

    Context context;
    List<Slot> slotList;

    public SlotAdapter(Context context, List<Slot> slotList) {
        this.context = context;
        this.slotList = slotList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_item_two_wheeler,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.btnTwoWheeler.setText(slotList.get(position).getNumber().toString());
    }

    @Override
    public int getItemCount() {
        return slotList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button btnTwoWheeler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnTwoWheeler=itemView.findViewById(R.id.btnTwoWheeler);
        }
    }
}
