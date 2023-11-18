package com.example.app;

import android.app.Application;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomApplication extends Application {
    private Reservation currentReservation;
    private DatabaseReference databaseReference;

    @Override
    public void onCreate() {
        super.onCreate();

        // Firebase Database 초기화
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference("reservations");
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
            databaseReference.child(currentReservation.getStudentName()).setValue(currentReservation);
        }
    }
}