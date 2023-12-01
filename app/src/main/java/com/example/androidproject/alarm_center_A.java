package com.example.androidproject;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

        public alarm() {}
    }
    public static final String TAG = "YOUR-TAG-NAME";
    public static final String CHANNEL_ID = "CHANNEL_ID";
    public static final CharSequence CHANNEL_NAME = "CHANNEL_NAME";
    // Declare the launcher at the top of your Activity/Fragment:
    public static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String SERVER_KEY = "AAAADl46TdM:APA91bHHAFkqqd__9NiRq6UmX2occYSS2DvRTOsW5hrakumLgP3bWBjYSzAkoGqLBCYyqyrPMtivligsemZVhf2JoryIPRPO94889X0J_B3LdxSu64idd2bNO3VlPMS6pfEb2tQuDRMP";
    private void sendPostToFCM(final String token, final String title, final String message) {
        //출처:https://m.blog.naver.com/eo930827/221632468403
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //주의
        database.child("test").child("token").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String userData = dataSnapshot.getValue(String.class);
                //Toast.makeText(MainActivity.this, userData, Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject root = new JSONObject();
                            JSONObject data = new JSONObject();
                            //data.put("body", message);
                            //data.put("click_action", "MyFirebaseMessagingService");
                            //data.put("title", title);
                            //root.put("notification", data);
                            JSONObject data2 = new JSONObject();
                            //data2.put("notifid", 2025090001);
                            data2.put("msg", message);
                            data2.put("title2", title);
                            root.put("to", token);
                            root.put("data", data2);
                            //root.put("to", token);
                            root.put("priority", "high");
                            //root.put("time_to_live", 8640);
                            URL url = new URL(FCM_MESSAGE_URL);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);
                            conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                            conn.setRequestProperty("Accept", "application/json");
                            conn.setRequestProperty("Content-type", "application/json");
                            OutputStream os = conn.getOutputStream();
                            os.write(root.toString().getBytes("utf-8"));
                            os.flush();
                            conn.getResponseCode();
                            //Log.d("tag1",userData);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(alarm_center_A.this, "취소되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_center);
        askNotificationPermission();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        database.child("2021145818").child("alarm").child("token").setValue(token);
                        // Log and toast

                        Toast.makeText(alarm_center_A.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        alarm(false,"상담취소","김영수","2021145818",
                2023,11,27);
        click();

    }

    public void click() {
        lv_alarm = findViewById(R.id.lv_alarm);
        ArrayList<HashMap<String, String>> datas = new ArrayList<HashMap<String, String>>();
        final HashMap<String, String>[] map = new HashMap[]{new HashMap<String, String>()};

        String private_key = "2021145818";//개인키
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

                        String KEY_SIMPLE_MSG = "A";
                        final int REQUEST_CODE_FRIEND = 0;
                        lv_alarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if(datas.get(position).get("title") == "경고"){
                                    //학생이 상담신청하는 클래스
                                    Intent intent = new Intent(getApplicationContext(),student_information.class);
                                    String key = private_key;
                                    intent.putExtra(KEY_SIMPLE_MSG,key);
                                    startActivityForResult(intent, REQUEST_CODE_FRIEND);
                                }
                                else if (datas.get(position).get("title") == "상담취소" && private_key.length() == 9) {
                                    //학생이 상담신청하는 클래스
                                    Intent intent = new Intent(getApplicationContext(),student_information.class);
                                    String key = private_key;
                                    intent.putExtra(KEY_SIMPLE_MSG,key);
                                    startActivityForResult(intent, REQUEST_CODE_FRIEND);
                                }
                                else if (datas.get(position).get("title") == "상담취소"&& private_key.length() == 10) {
                                    //교수 상담일정 확인창으로
                                    Intent intent = new Intent(getApplicationContext(),student_information.class);
                                    String key = private_key;
                                    intent.putExtra(KEY_SIMPLE_MSG,key);
                                    startActivityForResult(intent, REQUEST_CODE_FRIEND);
                                }
                                else if (datas.get(position).get("title") == "상담신청") {
                                    //교수 상담일정 확인창으로
                                    Intent intent = new Intent(getApplicationContext(),student_information.class);
                                    String key = private_key;
                                    intent.putExtra(KEY_SIMPLE_MSG,key);
                                    startActivityForResult(intent, REQUEST_CODE_FRIEND);
                                }
                                else if (datas.get(position).get("title") == "후기작성") {
                                    // 후기작성 클래스로
                                    Intent intent = new Intent(getApplicationContext(),student_information.class);
                                    String key = private_key;
                                    intent.putExtra(KEY_SIMPLE_MSG,key);
                                    startActivityForResult(intent, REQUEST_CODE_FRIEND);
                                }
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
                        String key, int date_year, int date_month, int date_hour){
        if(student_professor) {//to student
            DatabaseReference database11 = FirebaseDatabase.getInstance().getReference();
            database11.child(key).child("alarm").child("value")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    GenericTypeIndicator<Long> t = new GenericTypeIndicator<Long>() {};
                                    int temp = snapshot.getValue(t).intValue();
                                    //if(Classification == "상담확정"){
                                    //    alarm_notify ala = new alarm_notify("상담확정",receiver_name + "님이 신청하신 상담("
                                    //            + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "."
                                    //            + Integer.toString(date_hour) +")이 상담이 확정되었습니다."
                                    //            + '\n' + date_year + "." + date_month + "." + date_hour + ".");
                                    //    DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                    //    database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                    //    database12.child(key).child("alarm").child("value").setValue(temp+1);
                                    //}
                                    //else if(Classification == "상담거절"){
                                    //    alarm_notify ala = new alarm_notify("상담거절",receiver_name + "님이 신청하신 상담("
                                    //            + Integer.toString(date_year) + "." +Integer.toString(date_month) + "."
                                    //            + Integer.toString(date_hour) +")이 상담이 거절되었습니다."  + "다시 신청해주시기 바랍니다."
                                    //            + '\n' + date_year + "." + date_month + "." + date_hour + ".");
                                    //    DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                    //    database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                    //    database12.child(key).child("alarm").child("value").setValue(temp+1);
                                    //}
                                    if(Classification == "경고"){
                                        String msg = "상담을 신청하지 않아" + sender_name +
                                                "교수님이 경고를 보냈습니다"+ '\n' +" 빠른 시일 내에 상담을 신청해주시기 바랍니다."
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".";
                                        alarm_notify ala = new alarm_notify("경고",msg);
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                        sendPostToFCM(token, "경고" , msg);
                                    }
                                    else if(Classification == "상담취소"){
                                        String msg = receiver_name + "이 신청하신 상담("
                                                + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "."
                                                + Integer.toString(date_hour) +")이 취소되었습니다." + '\n' + "다시 신청해주시기 바랍니다."
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".";
                                        alarm_notify ala = new alarm_notify("상담취소",msg);
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                        sendPostToFCM(token, "상담취소" , msg);
                                    }
                                    else if(Classification == "후기작성"){
                                        String msg = sender_name + "교수님과의 상담이" +
                                                "완료되었습니다." + '\n' + "상담 후기를 작성해 주시기 바랍니다."
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".";
                                        alarm_notify ala = new alarm_notify("후기작성",msg);
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                        sendPostToFCM(token, "경고" , msg);
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
                                        String msg = sender_name + "이(가) 신청한 상담("
                                                + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "."
                                                + Integer.toString(date_hour) +")이 취소되었습니다."
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".";
                                        alarm_notify ala = new alarm_notify("상담취소",msg);
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                        sendPostToFCM(token, "상담취소" , msg);
                                    }
                                    else if (Classification == "상담신청") {
                                        String msg = sender_name + "이(가) 상담을 신청했습니다. 일시:("
                                                + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "." + Integer.toString(date_hour) +")"
                                                + '\n' + date_year + "." + date_month + "." + date_hour + ".";
                                        alarm_notify ala = new alarm_notify("상담신청",msg);
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                        sendPostToFCM(token, "상담신청" , msg);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(alarm_center_A.this, "메시지를 받는 사람의 알람 초기설정이 되지 않았습니다."
                                            , Toast.LENGTH_SHORT).show();
                                }
                            });

        }
    }
    public void alarm2(Boolean student_professor, String Classification, String sender_name, String token, String key,
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
                                    alarm3 (student_professor, Classification, sender_name, student_name , token
                                            , key, date_year, date_month,date_hour);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(alarm_center_A.this, "메시지를 받는 사람의 이름 정보를 찾을 수 없습니다."
                                            , Toast.LENGTH_SHORT).show();
                                }
                            });

        }
        else{
            DatabaseReference database10 = FirebaseDatabase.getInstance().getReference();
            database10.child(key).child("professor_information")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {//이름 가져오기
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    professor_information temp = snapshot.getValue(professor_information.class);
                                    String professor_name = temp.name;
                                    alarm3 (student_professor, Classification, sender_name, professor_name , token
                                            , key, date_year, date_month,date_hour);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(alarm_center_A.this, "메시지를 받는 사람의 이름 정보를 찾을 수 없습니다."
                                            , Toast.LENGTH_SHORT).show();
                                }
                            });
        }

    }


    public void alarm(Boolean student_professor, String Classification, String sender_name, String key,
                      int date_year, int date_month, int date_hour){

        DatabaseReference database10 = FirebaseDatabase.getInstance().getReference();
        database10.child(key).child("alarm").child("token")
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {};
                                String temp = snapshot.getValue(t);

                                alarm2 (student_professor, Classification, sender_name, temp
                                        , key, date_year, date_month, date_hour);
                            }

                            @Override public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(alarm_center_A.this, "메시지를 받는 사람의 토큰 정보를 찾을 수 없습니다."
                                        , Toast.LENGTH_SHORT).show();
                            }
                        });
    }

}