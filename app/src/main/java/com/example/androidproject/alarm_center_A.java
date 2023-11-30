package com.example.androidproject;
//187740328
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;

public class alarm_center_A extends AppCompatActivity {

    public static class professor_information{
        public int private_key = 1;
        public String name = "name";
        public String laboratory_location = "laboratory_location";
        public String belong = "belong";
        public String phone_number = "phone_number";
        public String email = "email";
        public String Zoom_link = "Zoom_link";
        public Boolean alarm = true;
        public professor_information(int private_key, String name, String laboratory_location,String belong,
                                     String phone_number, String email, String Zoom_link, Boolean alarm){
            this.private_key = private_key;
            this.name = name;
            this.laboratory_location = laboratory_location;
            this.belong = belong;
            this.phone_number = phone_number;
            this.email = email;
            this.Zoom_link = Zoom_link;
            this.alarm = alarm;
        }
        private void dummy(){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            professor_information input = new professor_information(2021145818,
                    "강동기","공대7호관 429호", "전북대학교_컴퓨터인공지능학부"
                    ,"000-0000-0000","dongkikang@jbnu.ac.kr","Null",true);

            database.child("2021145818").child("professor_information").setValue(input);

        }
        public professor_information(){}
    }
    public static class student_information{
        public int private_key = 1;
        public String name = "name";
        public int student_number = 1;
        public String state = "state";
        public String belong = "belong";
        public String major = "major";
        public String phone_number = "phone_number";
        public String email = "email";
        public Boolean alarm = true;
        public student_information(int private_key, String name, int student_number
                , String belong, String major, String phone_number, String email,boolean alarm){
            this.private_key = private_key;
            this.name = name;
            this.student_number = student_number;
            this.belong = belong;
            this.major = major;
            this.phone_number = phone_number;
            this.email = email;
            this.alarm = alarm;
        }

        public void dummy(){

        }
        public student_information(){}
    }
    public static class alarm_notify {

        public String title = "title";
        public String msg = "msg";

        public alarm_notify(String title, String msg) {
            this.title = title;
            this.msg = msg;
        }

        public alarm_notify() {
        }
    }

    ListView lv_alarm;
    SimpleAdapter simpleAdapter;

    public class alarm {
        public String Classification;
        public String sender_id;
        public String recipient_id;
        public String msg1;
        public String msg2;
        public String date;
        public String title;

        public alarm(String Classification, String sender_id, String recipient_id
                , String msg1, String msg2, String date, String title) {
            this.Classification = Classification;
            this.sender_id = sender_id;
            this.recipient_id = recipient_id;
            this.msg1 = msg1;
            this.msg2 = msg2;
            this.date = date;
            this.title = title;
        }

        public alarm() {
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_center);
        //alarm(true,"후기작성","강동기","테스트","187740328",
        //        2023,11,27);
        click();

    }

    public void click() {
        lv_alarm = findViewById(R.id.lv_alarm);
        ArrayList<HashMap<String, String>> datas = new ArrayList<HashMap<String, String>>();
        final HashMap<String, String>[] map = new HashMap[]{new HashMap<String, String>()};

        String private_key = "187740328";//개인키
        String token = String.valueOf(FirebaseMessaging.getInstance().getToken());

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        int temp2 = 0;

        database.child(private_key).child("alarm").child("value").addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        GenericTypeIndicator<Long> t = new GenericTypeIndicator<Long>() {};
                        int temp = snapshot.getValue(t).intValue();
                        String iter;
                        for(int i = temp; i > 0; i--) {
                            iter = Integer.toString(i);
                            DatabaseReference database2 = FirebaseDatabase.getInstance().getReference();
                            database2.child(private_key).child("alarm").child(iter).child("notify")
                                    .addListenerForSingleValueEvent(
                                            new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    alarm_notify ala = snapshot.getValue(alarm_notify.class);

                                                    map[0].put("title", ala.title);
                                                    map[0].put("msg", ala.msg);
                                                    datas.add(map[0]);
                                                    map[0] = new HashMap<>();
                                                    simpleAdapter.notifyDataSetChanged();}
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                }
                                            });
                        }
                        simpleAdapter = new SimpleAdapter(alarm_center_A.this, datas, android.R.layout.simple_list_item_2,
                                new String[]{"title", "msg"},
                                new int[]{android.R.id.text1, android.R.id.text2});
                        lv_alarm.setAdapter(simpleAdapter);

                        lv_alarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        lv_alarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }

    public void alarm3 (Boolean student_professor, String Classification, String sender_name, String receiver_name,  String token,
                        String token2, String key, int date_year, int date_month, int date_hour){
        if(student_professor) {//to student
            DatabaseReference database11 = FirebaseDatabase.getInstance().getReference();
            database11.child(key).child("alarm").child("value")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    GenericTypeIndicator<Long> t = new GenericTypeIndicator<Long>() {};
                                    int temp = snapshot.getValue(t).intValue();
                                    if(Classification == "상담확정"){
                                        alarm_notify ala = new alarm_notify("상담확정",receiver_name + "님이 신청하신 상담("
                                                + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "."
                                                + Integer.toString(date_hour) +")이 상담이 확정되었습니다."
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".");
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                    }
                                    else if(Classification == "상담거절"){
                                        alarm_notify ala = new alarm_notify("상담거절",receiver_name + "님이 신청하신 상담("
                                                + Integer.toString(date_year) + "." +Integer.toString(date_month) + "."
                                                + Integer.toString(date_hour) +")이 상담이 거절되었습니다."  + "다시 신청해주시기 바랍니다."
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".");
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                    }
                                    else if(Classification == "경고"){
                                        alarm_notify ala = new alarm_notify("경고","상담을 신청하지 않아" + sender_name +
                                                "교수님이 경고를 보냈습니다"+ '\n' +" 빠른 시일 내에 상담을 신청해주시기 바랍니다."
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".");
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                    }
                                    else if(Classification == "상담취소"){
                                        alarm_notify ala = new alarm_notify("상담취소",receiver_name + "이 신청하신 상담("
                                                + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "."
                                                + Integer.toString(date_hour) +")이 취소되었습니다." + '\n' + "다시 신청해주시기 바랍니다."
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".");
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                    }
                                    else if(Classification == "후기작성"){
                                        alarm_notify ala = new alarm_notify("후기작성",sender_name + "교수님과의 상담이" +
                                                "완료되었습니다." + '\n' + "상담 후기를 작성해 주시기 바랍니다."
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".");
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });}
        else{//to professor
            DatabaseReference database11 = FirebaseDatabase.getInstance().getReference();
            database11.child(key).child("alarm").child("value")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    GenericTypeIndicator<Long> t = new GenericTypeIndicator<Long>() {};
                                    int temp = snapshot.getValue(t).intValue();
                                    if(Classification == "상담취소"){
                                        alarm_notify ala = new alarm_notify("상담취소",receiver_name + "이 신청하신 상담("
                                                + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "."
                                                + Integer.toString(date_hour) +")이 취소되었습니다." + '\n' + "다시 신청해주시기 바랍니다."
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".");
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                    }
                                    else if (Classification == "상담신청") {
                                        alarm_notify ala = new alarm_notify("상담신청",sender_name + "이 상담을 신청했습니다. 일시:("
                                                + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "." + Integer.toString(date_hour) +")"
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".");
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

        }
    }
    public void alarm2(Boolean student_professor, String Classification, String sender_name, String token, String token2, String key,
                       int date_year, int date_month, int date_hour){//학생이름 가져오기
        if(student_professor) {//professor to student
            DatabaseReference database10 = FirebaseDatabase.getInstance().getReference();
            database10.child(key).child("student_information")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {//추가
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    student_information temp = snapshot.getValue(student_information.class);
                                    String student_name = temp.name;
                                    alarm3 (student_professor, Classification, sender_name, student_name , token, token2
                                            , key, date_year, date_month,date_hour);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });

        }
        else{
            DatabaseReference database10 = FirebaseDatabase.getInstance().getReference();
            database10.child(key).child("professor_information")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {//추가
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    professor_information temp = snapshot.getValue(professor_information.class);
                                    String professor_name = temp.name;
                                    alarm3 (student_professor, Classification, sender_name, professor_name , token, token2
                                            , key, date_year, date_month,date_hour);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
        }

    }


    public void alarm(Boolean student_professor, String Classification, String sender_name, String token, String key,
                      int date_year, int date_month, int date_hour){

        DatabaseReference database10 = FirebaseDatabase.getInstance().getReference();
        database10.child(key).child("token")
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {};
                                String temp = snapshot.getValue(t);

                                alarm2 (student_professor, Classification, sender_name, token, temp
                                        , key, date_year, date_month, date_hour);
                            }

                            @Override public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
    }

}