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

    TextView message, name, date, time, date_FB, time_FB;
    Button alert, myPage, consul, iljeongCheck, history, ilJeong, myProfessor;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, MdatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen_s);

        name = findViewById(R.id.Name);
        date = findViewById(R.id.Date);
        date_FB = findViewById(R.id.Date_FB);
        time = findViewById(R.id.Time);
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
        databaseReference = firebaseDatabase.getReference("187740328/student_information");

        MdatabaseReference = firebaseDatabase.getReference("187740328/Schedule_Management");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String firebaseName = snapshot.child("name").getValue(String.class);
                    //String firebaseDate = snapshot.child("date").getValue(String.class);
                    //long firebaseTime = snapshot.child("Date_hour").getValue(long.class);

                    name.setText(firebaseName);
                    //date_FB.setText(firebaseDate);
                    //time_FB.setText(firebaseTime);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MdatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = snapshot.getChildrenCount();
                if(count == 1){
                    message.setText("예약한 상담이 있습니다.");
                    date.setVisibility(View.VISIBLE);
                    date_FB.setVisibility(View.VISIBLE);
                    time.setVisibility(View.VISIBLE);
                    time_FB.setVisibility(View.VISIBLE);
                    iljeongCheck.setVisibility(View.VISIBLE);
                    consul.setVisibility(View.INVISIBLE);

                    long year = snapshot.child("content/Date_year").getValue(long.class);
                    long month = snapshot.child("content/Date_month").getValue(long.class);
                    long day = snapshot.child("content/Date_day").getValue(long.class);
                    float hour = snapshot.child("content/Date_hour").getValue(float.class);
                    String week = snapshot.child("content/Date_week").getValue(String.class);

                    date_FB.setText((String)(year+"."+month+"."+day+"("+week+")"));
                    if(hour%1 != 0){
                        time_FB.setText((String)((int)hour+":30"));
                    } else{
                        time_FB.setText((String)((int)hour+":00"));
                    }



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_S.this, alarm_center_A.class);
                startActivity(intent);


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
                Intent intent = new Intent(MainScreen_S.this, IlJeong_S.class);
                startActivity(intent);
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
                Intent intent = new Intent(MainScreen_S.this, MyProfessorActivity.class);
                startActivity(intent);


            }
        });

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_S.this, MyPg_S.class);
                startActivity(intent);


            }
        });
    }
}