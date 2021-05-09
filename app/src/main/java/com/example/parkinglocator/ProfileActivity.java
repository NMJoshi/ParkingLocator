package com.example.parkinglocator;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkinglocator.Adapter.UserAdapter;
import com.example.parkinglocator.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    String TAG="TAGER";
    RecyclerView recyclerView;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth=FirebaseAuth.getInstance();
        recyclerView=findViewById(R.id.recyclerView);
        userList=new ArrayList<>();
        firebaseFirestore=FirebaseFirestore.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firebaseFirestore.collection("User").document(auth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                User user= documentSnapshot.toObject(User.class);
                Log.d(TAG, "onSuccess: " + user.toString());

                userList.add(user);

                UserAdapter userAdapter = new UserAdapter(userList, ProfileActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(userAdapter);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.d(TAG, "onFailure: "+e.getMessage());
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}