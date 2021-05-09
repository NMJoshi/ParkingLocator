
package com.example.parkinglocator;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.parkinglocator.Model.ParkingDetail;
import com.example.parkinglocator.Model.Slot;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ParkingOwnerAddParkingDetails extends AppCompatActivity {

    TextView tvlatitude, tvlongitude, tvTwoWheeler, tvFourWheeler;
    EditText edtName, edtEmail, edtAddress, edtPhoneNumber, edtFourWheeler, edtTwoWheeler;
    Button btnSubmit;
    String parkingDetailId;
    FirebaseFirestore firebaseFirestore;
    FusedLocationProviderClient fusedLocationProviderClient;
    String TAG = "TAGER";
    FirebaseAuth auth;
    String slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_add_owner_parking_details);

        tvlatitude = findViewById(R.id.tvlatitude);
        tvlongitude = findViewById(R.id.tvlongitude);
        tvTwoWheeler = findViewById(R.id.tvtwoWheeler);
        tvFourWheeler = findViewById(R.id.tvfourWheeler);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtFourWheeler = findViewById(R.id.edtFourWheeler);
        edtTwoWheeler = findViewById(R.id.edtTwoWheeler);
        btnSubmit = findViewById(R.id.btnSubmit);
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ParkingOwnerAddParkingDetails.this);

        if (ActivityCompat.checkSelfPermission(ParkingOwnerAddParkingDetails.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(ParkingOwnerAddParkingDetails.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        btnSubmit.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Random random=new Random();
              final int randomNumber= random.nextInt(900000000);
                final String email = edtEmail.getText().toString();
                final String address = edtAddress.getText().toString();
                final String name = edtName.getText().toString();
                final String phonenumber = edtPhoneNumber.getText().toString();
                final String twoWheeler = edtTwoWheeler.getText().toString();
                final String fourWheeler = edtFourWheeler.getText().toString();
                final String latitude = tvlatitude.getText().toString();
                final String longitude = tvlongitude.getText().toString();


                SharedPreferences sharedPreferences = getSharedPreferences("Demo", 0);
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                SharedPreferences sharedPreferences1 = getSharedPreferences("Demo", 0);
                final SharedPreferences.Editor editor1 = sharedPreferences1.edit();

                  final String value=auth.getCurrentUser().getUid();
                parkingDetailId = auth.getCurrentUser().getUid();
                final String parkingId=String.valueOf(randomNumber);
                ParkingDetail parkingDetail = new ParkingDetail(name, email, phonenumber, address, twoWheeler, fourWheeler, latitude, longitude, parkingId,auth.getCurrentUser().getUid());
                final DocumentReference documentReference=firebaseFirestore.collection("ParkingOwner").document(value).collection("ParkingDetail").document(parkingId);

                documentReference.set(parkingDetail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        WriteBatch writeBatch=firebaseFirestore.batch();
                        int totalSlots=Integer.parseInt(twoWheeler)+Integer.parseInt(fourWheeler);

                        int twoWheelerSlot=Integer.parseInt(twoWheeler);
//                        int fourWheelerSlot=Integer.parseInt(fourWheeler);


//                        for (int i=0;i<twoWheelerSlot;i++){
//                            Random random=new Random();
//                            int randomNumber= random.nextInt(900000000);
//                            slotId=String.valueOf(randomNumber);
//
//                            writeBatch.set(documentReference.collection("Slots").document(slotId),new Slot(slotId,"available",Integer.toString(i+1),"twoWheeler"));
//                        }
//
//                        for (int j=0;j<fourWheelerSlot;j++){
//                            Random random=new Random();
//                            int randomNumber= random.nextInt(900000000);
//                            slotId=String.valueOf(randomNumber);
//                            writeBatch.set(documentReference.collection("Slots").document(slotId),new Slot(slotId,"available",Integer.toString(j+1),"fourWheeler"));
//                        }

                        for(int i=0;i<totalSlots;i++){
                            Random random=new Random();
                            int randomNumber= random.nextInt(900000000);
                            String slotId=String.valueOf(randomNumber);
                            if(i<twoWheelerSlot){
                                writeBatch.set(documentReference.collection("Slots").document(slotId),new Slot(slotId,"available", (long) (i+1),"twoWheeler"));
                            }
                            else {
                                writeBatch.set(documentReference.collection("Slots").document(slotId),new Slot(slotId,"available", (long) (i+1),"fourWheeler"));
                            }

                        }

                        writeBatch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent(ParkingOwnerAddParkingDetails.this, ParkingOwnerMainActivity.class);
                                startActivity(intent);
                                editor.putString("documentReferance", parkingId);
                                editor.apply();
                                editor1.putString("documentReferance1",slotId);
                                editor1.apply();

                                Toast.makeText(ParkingOwnerAddParkingDetails.this, "Data Added", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: "+e.getMessage());
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                });

            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(ParkingOwnerAddParkingDetails.this, Locale.getDefault());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        tvlatitude.setText(Html.fromHtml("" + addressList.get(0).getLatitude()));
                        tvlongitude.setText(Html.fromHtml("" + addressList.get(0).getLongitude()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
