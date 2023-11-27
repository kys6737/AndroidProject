package com.example.androidproject;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailIlJeong_s extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView day_db, pro_db, kind_db;
    TextView questionBox;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iljeong_detail_s);

        Toolbar record_toolbar=findViewById(R.id.toolbar2);
        record_toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(record_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("상담 일정");

        day_db = findViewById(R.id.day_db);
        pro_db = findViewById(R.id.pro_db);
        kind_db = findViewById(R.id.kind_db);

        questionBox = findViewById(R.id.questionbox);
        questionBox.setText("");

        Intent intent = getIntent();
        if(intent != null){
            int day = intent.getIntExtra("dd", 0);
            float hour = intent.getFloatExtra("dh", 0);
            int month = intent.getIntExtra("dm", 0);
            String week = intent.getStringExtra("dw");
            int year = intent.getIntExtra("dy", 0);
            String professorName = intent.getStringExtra("pm");
            String form = intent.getStringExtra("cf");
            String question = intent.getStringExtra("qu");

            if(hour%1 != 0)
            {
                day_db.setText(String.format("%d-%02d-%02d", year, month, day) + "("+week+")" + " "+ (int)hour + ":30");
            }else{
                day_db.setText(String.format("%d-%02d-%02d", year, month, day) + "("+week+")" + " "+ (int)hour + ":00");
            }
            pro_db.setText(professorName);
            kind_db.setText(form);
            questionBox.setText(question);
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("187740328/Schedule_Management/content");

        cancel = findViewById(R.id.cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // LayoutInflater를 사용하여 XML 레이아웃 파일을 뷰로 변환
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.cancel_popup, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(DetailIlJeong_s.this);
                builder.setView(dialogView);

                // 다이얼로그 생성
                final AlertDialog alertDialog = builder.create();

                // "아니요" 버튼 처리
                Button noButton = dialogView.findViewById(R.id.noButton);
                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // "아니요" 버튼을 눌렀을 때 다이얼로그 닫기
                        alertDialog.dismiss();
                    }
                });

                Button yesButton = dialogView.findViewById(R.id.yesButton);

                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater inflater = getLayoutInflater();
                        View yesdialogView = inflater.inflate(R.layout.cancel_yes, null);

                        AlertDialog.Builder yesbuilder = new AlertDialog.Builder(DetailIlJeong_s.this);
                        yesbuilder.setView(yesdialogView);

                        // 다이얼로그 생성
                        final AlertDialog yesalertDialog = yesbuilder.create();

                        Button ok = yesdialogView.findViewById(R.id.OK);

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                databaseReference.removeValue();
                                yesalertDialog.dismiss(); // CancelYes 다이얼로그만 종료
                                alertDialog.dismiss();
                                finish();
                            }
                        });
                        yesalertDialog.show();
                    }
                });

                // 다이얼로그 표시
                alertDialog.show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // 뒤로가기 버튼 눌렀을 때 현재 액티비티 종료
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}