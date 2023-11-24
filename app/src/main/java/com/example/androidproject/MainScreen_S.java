package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainScreen_S extends AppCompatActivity {

    TextView message, name, date_FB, time_FB;
    Button alert, myPage, consul, iljeongCheck, history, ilJeong, myProfessor;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen_s);

        name = findViewById(R.id.Name);
        date_FB = findViewById(R.id.Date_FB);
        time_FB = findViewById(R.id.Time_FB);
        message = findViewById(R.id.Message);
        alert = findViewById(R.id.alert);
        consul = findViewById(R.id.Consultation);
        iljeongCheck = findViewById(R.id.IljeongButton);
        history = findViewById(R.id.History);
        ilJeong = findViewById(R.id.Iljeong);
        myProfessor = findViewById(R.id.MyProfessor);
        myPage = findViewById(R.id.MyPage);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("187740328/Schedule_Management/content");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String firebaseName = snapshot.child("student_name").getValue(String.class);
                    //String firebaseDate = snapshot.child("date").getValue(String.class);
                    long firebaseTime = snapshot.child("Date_hour").getValue(long.class);

                    name.setText(firebaseName);
                    //date_FB.setText(firebaseDate);
                    //time_FB.setText(firebaseTime);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        consul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_S.this, ConsultRequest_S_1.class);
                startActivity(intent);

            }
        });

        iljeongCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_S.this, Record_S.class);
                startActivity(intent);


            }
        });

        ilJeong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_S.this, IlJeong_S.class);
                startActivity(intent);
            }
        });

        myProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}