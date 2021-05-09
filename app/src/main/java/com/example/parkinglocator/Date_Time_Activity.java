
package com.example.parkinglocator;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkinglocator.Model.Order;
import com.example.parkinglocator.Model.ParkingDetail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Date_Time_Activity extends AppCompatActivity {

    TextView starting_date, ending_date;
    DatePickerDialog datePickerDialog;
    Button btn_Time,btn_Time2;
    LinearLayout linearLayoutBtnFourWheeler,linearLayoutBtnTwoWheeler;
    TextView tvTime,tvTime2;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Calendar startTime=Calendar.getInstance();
    Calendar endTime=Calendar.getInstance();
    ParkingDetail parkingDetail;
    String type="twoWheeler";
    Boolean isStartTimeSelected=false;
    Boolean isEndTimeSelected=false;
    Button btnTwoWheeler,btnFourWheeler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date__time_);

        parkingDetail= (ParkingDetail) getIntent().getSerializableExtra("parkingDetail");

        linearLayoutBtnFourWheeler=findViewById(R.id.linearLayoutBtnFourWheeler);
        linearLayoutBtnTwoWheeler=findViewById(R.id.linearLayoutBtnTwoWheeler);
        starting_date = findViewById(R.id.starting_date);
        ending_date = findViewById(R.id.ending_date);
//        timepicker.setIs24HourView(true);
        btn_Time=findViewById(R.id.btn_time);
        btn_Time2=findViewById(R.id.btn_time1);
        btnTwoWheeler=findViewById(R.id.btnTwoWheeler);
        btnFourWheeler=findViewById(R.id.btnFourWheeler);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        linearLayoutBtnTwoWheeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Date_Time_Activity.this, "You Can Selected Two Wheeler", Toast.LENGTH_SHORT).show();
                type="twoWheeler";
            }
        });

        linearLayoutBtnFourWheeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Date_Time_Activity.this, "You Can Selected Four Wheeler", Toast.LENGTH_SHORT).show();
                type="fourWheeler";
            }
        });
        tvTime=findViewById(R.id.tvTime);

                            tvTime2=findViewById(R.id.tvTime2);

                    btn_Time2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            isEndTimeSelected=true;
                            if (v == btn_Time2) {

                                // Get Current Time
                                final Calendar c = Calendar.getInstance();
                                mHour = c.get(Calendar.HOUR_OF_DAY);
                                mMinute = c.get(Calendar.MINUTE);

                                endTime.set(Calendar.HOUR_OF_DAY,mHour);
                                endTime.set(Calendar.MINUTE,mMinute);


                                // Launch Time Picker Dialog
                                TimePickerDialog timePickerDialog = new TimePickerDialog(Date_Time_Activity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    tvTime2.setText(hourOfDay + ":" + minute);

                                }


                            }, mHour, mMinute, false);
                    timePickerDialog.show();
                }

            }
        });

        btn_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_Time) {

                    isStartTimeSelected=true;

                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);
                    startTime.set(Calendar.HOUR_OF_DAY,mHour);
                    startTime.set(Calendar.MINUTE,mMinute);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(Date_Time_Activity.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    tvTime.setText(hourOfDay + ":" + minute);

                                }


                            }, mHour, mMinute, false);
                    timePickerDialog.show();
                }
            }
        });


        starting_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                startTime.set(mYear,mMonth,mDay);

                // date picker dialog
                datePickerDialog = new DatePickerDialog(Date_Time_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                starting_date.setText(dayOfMonth + "/"
                                        + (+1) + "/" + year);

                            }


                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        ending_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                startTime.set(mYear,mMonth,mDay);

                // date picker dialog
                datePickerDialog = new DatePickerDialog(Date_Time_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                ending_date.setText(dayOfMonth + "/"
                                        + (+1) + "/" + year);

                            }


                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        Button btnNext=findViewById(R.id.next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(isStartTimeSelected && isEndTimeSelected){
    long duration=0;
    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmm", Locale.getDefault());
    try {
        duration=getDifference(fmt.parse(fmt.format(startTime.getTime())),fmt.parse(fmt.format(endTime.getTime())));
    } catch (ParseException e) {
        e.printStackTrace();
    }

    Order order= new Order(fmt.format(startTime.getTime()),fmt.format(startTime.getTime()),fmt.format(endTime.getTime()),duration,"",type);

    Intent intent=new Intent(Date_Time_Activity.this,OrderActivity.class);
    intent.putExtra("parkingDetail",parkingDetail);
    intent.putExtra("order",order);
    startActivity(intent);
}
else {
    Toast.makeText(Date_Time_Activity.this, "Please Insert Data", Toast.LENGTH_SHORT).show();
}





            }
        });


    }

    public long getDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return  elapsedMinutes;
    }


//    public String getCurrentTime(){
//        String currentTime="Current Time: "+timepicker.getCurrentHour()+":"+timepicker.getCurrentMinute();
//        return currentTime;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}