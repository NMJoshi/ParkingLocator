package com.example.parkinglocator.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Model.User;
import com.example.parkinglocator.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<User> userList;
    Context context;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText("  "+userList.get(position).getName());
        holder.tvEmail.setText("  "+userList.get(position).getEmail());
        holder.tvPhoneNumber.setText("  "+userList.get(position).getPhoneNumber());
        holder.tvAddress.setText(" "+userList.get(position).getAddress());
        holder.tvOwnerId.setText(" "+userList.get(position).getUserId());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
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
