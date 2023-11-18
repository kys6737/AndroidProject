package com.example.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.app.CustomApplication;
import com.example.app.Reservation;

public class MainActivity2 extends AppCompatActivity {
    EditText questionEditText;
    private String selectedType;
    private Reservation reservation;
    private Button faceConsultButton;
    private Button zoomConsultButton;
    private Button callConsultButton;
    private Button lastClickedButton;
    private Button reservationButton;
    private Button beforeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        reservation = getIntent().getParcelableExtra("reservation");
        Log.d("reservationDebug", "studentName: " + reservation.getStudentName() + ", Date: " + reservation.getDate() + ", Time: " + reservation.getTime());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        faceConsultButton = findViewById(R.id.faceConsultButton);
        zoomConsultButton = findViewById(R.id.zoomConsultButton);
        callConsultButton = findViewById(R.id.callConsultButton);
        reservationButton = findViewById(R.id.reservationButton);
        beforeButton = findViewById(R.id.beforeButton);
        questionEditText = findViewById(R.id.questionEditText);

        faceConsultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(faceConsultButton);
                updateNextButtonState();
            }
        });

        zoomConsultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(zoomConsultButton);
                updateNextButtonState();
            }
        });

        callConsultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(callConsultButton);
                updateNextButtonState();
            }
        });

        beforeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        reservationButton.setEnabled(false);
        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReservationDialog();
            }
        });
    }

    private void updateButtonState(Button clickedButton) {
        // 마지막에 클릭된 버튼이 있으면 원래대로 돌려놓음
        if (lastClickedButton != null) {
            lastClickedButton.setBackgroundResource(R.drawable.whitebutton);
        }

        // 현재 클릭한 버튼의 상태를 변경
        clickedButton.setBackgroundResource(R.drawable.green1button);

        // 마지막에 클릭된 버튼을 현재 클릭한 버튼으로 업데이트
        lastClickedButton = clickedButton;
    }
    private void updateNextButtonState() {
        reservationButton.setEnabled(lastClickedButton != null);
        reservationButton.setBackgroundResource(R.drawable.availablenextbutton);
    }

    private void showReservationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 사용자 정의 레이아웃을 설정
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog, null);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();
        // 사용자 정의 레이아웃 내부의 뷰를 참조
        TextView confirmTextView = dialogView.findViewById(R.id.confirmTextView);
        Button yesButton = dialogView.findViewById(R.id.yesButton);
        Button noButton = dialogView.findViewById(R.id.noButton);

        // 메시지 설정
        confirmTextView.setText("상담을 예약하시겠습니까?");

        // "예" 버튼 동작 설정
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastClickedButton==faceConsultButton) selectedType="대면상담";
                else if(lastClickedButton==callConsultButton) selectedType="통화상담";
                else if(lastClickedButton==zoomConsultButton) selectedType="화상상담";
                reservation.setType(selectedType);
                String question = questionEditText.getText().toString();
                reservation.setQuestion(question);
                Log.d("ReservationDebug", "studentName: " + reservation.getStudentName() + ", Date: " + reservation.getDate() + ", Time: " + reservation.getTime() +
                        ", Type: " + reservation.getType() + ", Question: " + reservation.getQuestion());
                CustomApplication customApplication = (CustomApplication) getApplication();
                customApplication.setCurrentReservation(reservation);
                showCompleteDialog();
                dialog.dismiss();
            }
        });

        // "아니오" 버튼 동작 설정
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // 다이얼로그 보이기 전에 모서리가 적용된 Window를 설정
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void showCompleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 사용자 정의 레이아웃을 설정
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog2, null);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();
        // 사용자 정의 레이아웃 내부의 뷰를 참조
        TextView confirmTextView = dialogView.findViewById(R.id.confirmTextView);
        Button yesButton = dialogView.findViewById(R.id.yesButton);

        // 메시지 설정
        confirmTextView.setText("상담 예약을 완료하였습니다.");

        // "예" 버튼 동작 설정
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}