package com.example.app;

import android.app.Application;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReservationApplication extends Application {
    private Reservation currentReservation;
    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference2;

    @Override
    public void onCreate() {
        super.onCreate();

        // Firebase Database 초기화
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference1 = FirebaseDatabase.getInstance().getReference("2021145818/Schedule_Management/187740328/content");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("187740328/Schedule_Management/2021145818/content");


        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference("2021145818/Timetable/fixation");
    }

    public Reservation getCurrentReservation() {
        if (currentReservation == null) {
            currentReservation = new Reservation();
        }
        return currentReservation;
    }

    public void setCurrentReservation(Reservation currentReservation) {
        this.currentReservation = currentReservation;

        // 파이어베이스에 데이터 업로드
        if (currentReservation != null) {
            databaseReference1.child("Date_year").setValue(currentReservation.getDate_year());
            databaseReference1.child("Date_month").setValue(currentReservation.getDate_month());
            databaseReference1.child("Date_day").setValue(currentReservation.getDate_day());
            databaseReference1.child("Date_hour").setValue(currentReservation.getTime());
            databaseReference1.child("Date_week").setValue(currentReservation.getDate_week());
            databaseReference1.child("counseling_form").setValue(currentReservation.getType());
            databaseReference1.child("question").setValue(currentReservation.getQuestion());

            databaseReference2.child("Date_year").setValue(currentReservation.getDate_year());
            databaseReference2.child("Date_month").setValue(currentReservation.getDate_month());
            databaseReference2.child("Date_day").setValue(currentReservation.getDate_day());
            databaseReference2.child("Date_hour").setValue(currentReservation.getTime());
            databaseReference2.child("Date_week").setValue(currentReservation.getDate_week());
            databaseReference2.child("counseling_form").setValue(currentReservation.getType());
            databaseReference2.child("question").setValue(currentReservation.getQuestion());




        }
    }
}