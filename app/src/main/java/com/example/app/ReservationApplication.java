package com.example.app;

import android.app.Application;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReservationApplication extends Application {
    private Reservation currentReservation;
    private DatabaseReference databaseReference;

    @Override
    public void onCreate() {
        super.onCreate();

        // Firebase Database 초기화
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference("2021145818/Schedule_Management/187740328/content");

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
            databaseReference.child("Date_year").setValue(currentReservation.getDate_year());
            databaseReference.child("Date_month").setValue(currentReservation.getDate_month());
            databaseReference.child("Date_day").setValue(currentReservation.getDate_day());
            databaseReference.child("Date_hour").setValue(currentReservation.getTime());
            databaseReference.child("counseling_form").setValue(currentReservation.getType());


        }
    }
}