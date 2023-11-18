package com.example.androidproject;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app.Reservation;
import com.google.firebase.firestore.auth.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private boolean button0900Pressed = false;
    private String selectedDate;
    private String selectedTime;
    private CalendarView calendarView;
    private Button button0900;
    private Button button0930;
    private Button button1000;
    private Button button1030;
    private Button button1100;
    private Button button1130;
    private Button button1200;
    private Button button1230;
    private Button button1300;
    private Button button1330;
    private Button button1400;
    private Button button1430;
    private Button button1500;
    private Button button1530;
    private Button button1600;
    private Button button1630;
    private Button button1700;
    private Button button1730;
    private Button lastClickedButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        calendarView = findViewById(R.id.calendarView);
        nextButton = findViewById(R.id.nextButton);
        button0900 = findViewById(R.id.button0900);
        button0930 = findViewById(R.id.button0930);
        button1000 = findViewById(R.id.button1000);
        button1030 = findViewById(R.id.button1030);
        button1100 = findViewById(R.id.button1100);
        button1130 = findViewById(R.id.button1130);
        button1200 = findViewById(R.id.button1200);
        button1230 = findViewById(R.id.button1230);
        button1300 = findViewById(R.id.button1300);
        button1330 = findViewById(R.id.button1330);
        button1400 = findViewById(R.id.button1400);
        button1430 = findViewById(R.id.button1430);
        button1500 = findViewById(R.id.button1500);
        button1530 = findViewById(R.id.button1530);
        button1600 = findViewById(R.id.button1600);
        button1630 = findViewById(R.id.button1630);
        button1700 = findViewById(R.id.button1700);
        button1730 = findViewById(R.id.button1730);

        button0900.setVisibility(View.INVISIBLE);
        button0930.setVisibility(View.INVISIBLE);
        button1000.setVisibility(View.INVISIBLE);
        button1030.setVisibility(View.INVISIBLE);
        button1100.setVisibility(View.INVISIBLE);
        button1130.setVisibility(View.INVISIBLE);
        button1200.setVisibility(View.INVISIBLE);
        button1230.setVisibility(View.INVISIBLE);
        button1300.setVisibility(View.INVISIBLE);
        button1330.setVisibility(View.INVISIBLE);
        button1400.setVisibility(View.INVISIBLE);
        button1430.setVisibility(View.INVISIBLE);
        button1500.setVisibility(View.INVISIBLE);
        button1530.setVisibility(View.INVISIBLE);
        button1600.setVisibility(View.INVISIBLE);
        button1630.setVisibility(View.INVISIBLE);
        button1700.setVisibility(View.INVISIBLE);
        button1730.setVisibility(View.INVISIBLE);

        // 과거의 날짜 모두 비활성화
        Calendar currentDate = Calendar.getInstance(); // 현재 날짜 가져오는 메서드
        calendarView.setMinDate(currentDate.getTimeInMillis()); // 현재 날짜 이전 날짜 비활성화


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                showTimeButtons();
                // 선택한 날짜를 문자열로 변환
                selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
                lastClickedButtonState(lastClickedButton);
                nextButton.setEnabled(false);
                nextButton.setBackgroundResource(R.drawable.unavailablenextbutton);


            }
        });
        button0900.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button0900);
                updateNextButtonState();
            }
        });
        button0930.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button0930);
                updateNextButtonState();
            }
        });
        button1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1000);
                updateNextButtonState();
            }
        });
        button1030.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1030);
                updateNextButtonState();
            }
        });
        button1100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1100);
                updateNextButtonState();
            }
        });
        button1130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1130);
                updateNextButtonState();
            }
        });
        button1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1200);
                updateNextButtonState();
            }
        });
        button1230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1230);
                updateNextButtonState();
            }
        });
        button1300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1300);
                updateNextButtonState();
            }
        });
        button1330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1330);
                updateNextButtonState();
            }
        });
        button1400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1400);
                updateNextButtonState();
            }
        });
        button1430.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1430);
                updateNextButtonState();
            }
        });
        button1500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1500);
                updateNextButtonState();
            }
        });
        button1530.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1530);
                updateNextButtonState();
            }
        });
        button1600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1600);
                updateNextButtonState();
            }
        });
        button1630.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1630);
                updateNextButtonState();
            }
        });
        button1700.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1700);
                updateNextButtonState();
            }
        });
        button1730.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1730);
                updateNextButtonState();
            }
        });

        nextButton.setEnabled(false);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lastClickedButton==button0900) selectedTime="09:00";
                else if(lastClickedButton==button0930) selectedTime="09:30";
                else if(lastClickedButton==button1000) selectedTime="10:00";
                else if(lastClickedButton==button1030) selectedTime="10:30";
                else if(lastClickedButton==button1100) selectedTime="11:00";
                else if(lastClickedButton==button1130) selectedTime="11:30";
                else if(lastClickedButton==button1200) selectedTime="12:00";
                else if(lastClickedButton==button1230) selectedTime="12:30";
                else if(lastClickedButton==button1300) selectedTime="13:00";
                else if(lastClickedButton==button1330) selectedTime="13:30";
                else if(lastClickedButton==button1400) selectedTime="14:00";
                else if(lastClickedButton==button1430) selectedTime="14:30";
                else if(lastClickedButton==button1500) selectedTime="15:00";
                else if(lastClickedButton==button1530) selectedTime="15:30";
                else if(lastClickedButton==button1600) selectedTime="16:00";
                else if(lastClickedButton==button1630) selectedTime="16:30";
                else if(lastClickedButton==button1700) selectedTime="17:00";
                else if(lastClickedButton==button1730) selectedTime="17:30";
                Reservation reservation = new Reservation();
                reservation.setDate(selectedDate);
                reservation.setTime(selectedTime);
                reservation.setStudentName("Christiano Ronaldo");

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("reservation", reservation);
                startActivity(intent);
            }
        });


    }



    private void showTimeButtons() {
        button0900.setVisibility(View.VISIBLE);
        button0930.setVisibility(View.VISIBLE);
        button1000.setVisibility(View.VISIBLE);
        button1030.setVisibility(View.VISIBLE);
        button1100.setVisibility(View.VISIBLE);
        button1130.setVisibility(View.VISIBLE);
        button1200.setVisibility(View.VISIBLE);
        button1230.setVisibility(View.VISIBLE);
        button1300.setVisibility(View.VISIBLE);
        button1330.setVisibility(View.VISIBLE);
        button1400.setVisibility(View.VISIBLE);
        button1430.setVisibility(View.VISIBLE);
        button1500.setVisibility(View.VISIBLE);
        button1530.setVisibility(View.VISIBLE);
        button1600.setVisibility(View.VISIBLE);
        button1630.setVisibility(View.VISIBLE);
        button1700.setVisibility(View.VISIBLE);
        button1730.setVisibility(View.VISIBLE);
    }
    private void updateButtonState(Button clickedButton) {
        // 마지막에 클릭된 버튼이 있으면 원래대로 돌려놓음
        lastClickedButtonState(lastClickedButton);

        // 현재 클릭한 버튼의 상태를 변경
        clickedButton.setBackgroundResource(R.drawable.clickedtimebutton);

        // 마지막에 클릭된 버튼을 현재 클릭한 버튼으로 업데이트
        lastClickedButton = clickedButton;
    }

    private void lastClickedButtonState(Button button){
        if(lastClickedButton != null){
            lastClickedButton.setBackgroundResource(R.drawable.timebutton);
        }
    }
    private void updateNextButtonState() {
        nextButton.setEnabled(lastClickedButton != null);
        nextButton.setBackgroundResource(R.drawable.availablenextbutton);
    }




}
