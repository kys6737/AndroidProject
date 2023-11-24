package com.example.realtime_alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;




public class MainActivity extends AppCompatActivity {
    public static final String TAG = "YOUR-TAG-NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //pushdata();
    }

    public void Buttonclick(View v){
        pushdata();
    }
    public class professor_information{
        public int private_key;
        public String name;
        public String laboratory_location;
        public String belong;
        public String phone_number;
        public String email;
        public String Zoom_link;
        public Boolean alarm;
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
    }

    public class Timetable_fixation{

        public String monday;
        public String tuseday;
        public String wednseday;
        public String thursday;
        public String friday;

        public Timetable_fixation(String monday, String tuseday, String wednseday, String thursday, String friday ){
            this.monday = monday;
            this.tuseday = tuseday;
            this.wednseday = wednseday;
            this.thursday = thursday;
            this.friday = friday;
        }
        private void dummy(){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            Timetable_fixation data = new Timetable_fixation("000000000000000000", "000000000000000000",
                    "000000000000000000","000000000000000000","000000000000000000");
            database.child("2021145818").child("Timetable").child("fixation").setValue(data);
        }
    }

    public class Timetable_fluctuations{

        public String d1;public String d2;public String d3;public String d4;public String d5;
        public String d6;public String d7;public String d8;public String d9;public String d10;
        public String d11;public String d12;public String d13;public String d14;public String d15;
        public String d16;public String d17;public String d18;public String d19;public String d20;
        public String d21;public String d22;public String d23;public String d24;public String d25;
        public String d26;public String d27;public String d28;public String d29;public String d30;
        public String d31;


        public Timetable_fluctuations(String d1, String d2, String d3, String d4, String d5, String d6,
                                      String d7, String d8, String d9, String d10, String d11, String d12,
                                      String d13, String d14, String d15, String d16, String d17, String d18,
                                      String d19, String d20, String d21, String d22, String d23,
                                      String d24, String d25, String d26, String d27, String d28,
                                      String d29, String d30, String d31){
            this.d1 = d1;this.d2 = d2;this.d3 = d3;this.d4 = d4;this.d5 = d5;this.d6 = d6;
            this.d7 = d7;this.d8 = d8;this.d9 = d9;this.d10 =d10;this.d11 =d11;this.d12 =d12;
            this.d13 =d13; this.d14 =d14;this.d15 =d15;this.d16 =d16;this.d17 =d17;this.d18 =d18;
            this.d19 =d19;this.d20 =d20;this.d21 =d21;this.d22 =d22;this.d23 =d23;this.d24 =d24;
            this.d25 =d25;this.d26 =d26;this.d27 =d27;this.d28 =d28;this.d29 =d29;this.d30 =d30;
            this.d31 =d31;
        }


    }
    public class Timetable_fluctuations2{
        public String bitstring;

        public Timetable_fluctuations2(String bitstring){
            this.bitstring = bitstring;
        }

    }

    public class history{
        public int Date_year;
        public int Date_month;
        public int Date_day;
        public float Date_hour;
        public String Professor_number;
        public String Professor_name;
        public String classification;
        public String counseling_form;
        public String counseling_group;
        public String counseling_content;


        public history(int Date_year,int Date_month,int Date_day,float Date_hour,
                       String Professor_number, String Professor_name, String classification
                , String counseling_form, String counseling_group, String counseling_content){
            this.Date_year = Date_year;
            this.Date_month = Date_month;
            this.Date_day = Date_day;
            this.Date_hour = Date_hour;
            this.Professor_number =Professor_number;
            this.Professor_name = Professor_name;
            this.classification = classification;
            this.counseling_form = counseling_form;
            this.counseling_group = counseling_group;
            this.counseling_content = counseling_content;


        }
        public void pushdata(){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            professor_information input = new professor_information(2021145818,
                    "강동기","공대7호관 429호", "전북대학교_컴퓨터인공지능학부"
                    ,"000-0000-0000","dongkikang@jbnu.ac.kr","Null",true);

            database.child("2021145818").child("professor_information").setValue(input);
        }
    }

    public class review{
        public String review;
        public int key;

        public review(String review,int key){
            this.review = review;
            this.key = key;
        }
        public void dummy(){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            review data = new review("상담후기",187740328);
            database.child("2021145818").child("review").child("1").setValue(data);
        }
    }




    public class student_information{
        public int private_key;
        public String name;
        public int student_number;
        public int School_year;
        public String Date_admission;
        public String belong;
        public String major;
        public String double_minor_linkedmajor;
        public String phone_number;
        public String email;
        public Boolean alarm;
        public student_information(int private_key, String name, int student_number, int School_year
                , String Date_admission, String belong, String major, String double_minor_linkedmajor
                , String phone_number, String email,boolean alarm){
            this.private_key = private_key;
            this.name = name;
            this.student_number = student_number;
            this.School_year = School_year;
            this.Date_admission = Date_admission;
            this.belong = belong;
            this.major = major;
            this.double_minor_linkedmajor = double_minor_linkedmajor;
            this.phone_number = phone_number;
            this.email = email;
            this.alarm = alarm;
        }

        public void dummy(){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            question_group data = new question_group("question1","answer1","question2","answer2"
                    ,"question3","answer3","question4","answer4","question5","answer5"
                    ,"null","null","null","null","null","null");
            database.child("2021145818").child("history").child("2023_2")
                    .child("187740328").child("question").setValue(data);
        }
    }

    //public class question{
    //public String question;
    //public String answer;

    //public question(String question, String answer){
    //    this.question = question;
    //    this.answer = answer;
    //}

    //}

    public class question_group{
        public String question1; public String question2; public String question3; public String question4;
        public String question5; public String question6; public String question7; public String question8;

        public String answer1 ; public String answer2; public String answer3; public String answer4;
        public String answer5; public String answer6; public String answer7; public String answer8;
        public question_group(String question1, String answer1, String question2, String answer2
                , String question3, String answer3, String question4, String answer4
                , String question5, String answer5, String question6, String answer6
                , String question7, String answer7, String question8, String answer8){
            this.question1 = question1;         this.question2 = question2;
            this.answer1 = answer1;            this.answer2 = answer2;
            this.question3 = question3;         this.question4 = question4;
            this.answer3 = answer3;            this.answer4 = answer4;
            this.question5 = question5;         this.question6 = question6;
            this.answer5 = answer5;            this.answer6 = answer6;
            this.question7 = question7;         this.question8 = question8;
            this.answer7 = answer7;            this.answer8 = answer8;
        }

        public void error(){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            question_group data = new question_group("question1","answer1","question2","answer2"
                    ,"question3","answer3","question4","answer4","question5","answer5"
                    ,"null","null","null","null","null","null");
            database.child("187740328").child("history").child("2023_2")
                    .child("question").setValue(data);
        }

    }
    public class Schedule_Management{
        public String state;// 미정, 진행중, 완료
        public int Date_year;
        public int Date_month;
        public int Date_day;
        public float Date_hour;
        public String student_name;
        public String student_number;
        public String Professor_number;
        public String Professor_name;
        public String classification;
        public String counseling_form;
        public String counseling_group;

        public Schedule_Management(String state, int Date_year, int Date_month, int Date_day, float Date_hour
                , String student_name, String student_number, String Professor_number, String Professor_name
                , String classification, String counseling_form, String counseling_group){
            this.state = state;
            this.Date_year = Date_year;
            this.Date_month = Date_month;
            this.Date_day = Date_day;
            this.Date_hour = Date_hour;
            this.student_name = student_name;
            this.student_number = student_number;
            this.Professor_number = Professor_number;
            this.Professor_name = Professor_name;
            this.classification = classification;
            this.counseling_form = counseling_form;
            this.counseling_group = counseling_group;

        }

        public void dummy(){
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            Schedule_Management data = new Schedule_Management("완료",2023,11,20,20
                    ,"000000000","A","00000","강동기"
                    ,"학업 및 학교생활", "직접상담", " 개인상담");
            database.child("187740328").child("Schedule_Management").child("content").setValue(data);
        }
    }

    public class image{
        public byte[] byteArray;

        public image(byte[] byteArray){
            this.byteArray = byteArray;
        }


    }

    public static String byteArrayToBinaryString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; ++i) {
            sb.append(byteToBinaryString(b[i]));
        }
        return sb.toString();
    }
    public static String byteToBinaryString(byte n) {
        StringBuilder sb = new StringBuilder("00000000");
        for (int bit = 0; bit < 8; bit++) {
            if (((n >> bit) & 1) > 0) {
                sb.setCharAt(7 - bit, '1');
            }
        }
        return sb.toString();
    }
    //출처: https://billcorea.tistory.com/75 [생각저장소 (배움의길에서 만나는 이야기들):티스토리]

    public class FCM_message{
        public Long target_id;
        public String title;
        public String head;
        public String msg;

        public FCM_message(Long target_id, String title, String head, String msg){
            this.target_id = target_id;
            this.title = title;
            this.head = head;
            this.msg = msg;
        }
    }

    public void send_alarm(String Classification, String sender_id, String recipient_id
            , String msg1, String msg2, String date){

        if(Classification == "일정확정"){
            FirebaseMessaging fm = FirebaseMessaging.getInstance();
            fm.send(new RemoteMessage.Builder(sender_id + "@fcm.googleapis.com")
                    .setMessageId(recipient_id)
                    .addData("Head", "일정이 확정안내드립니다.")
                    .addData("msg", "신청하신 일정이 수락되어 일정이 " + msg1 + "로 확정되었습니다.")
                    .build());
        }
        else if (Classification == "경고") {
            FirebaseMessaging fm = FirebaseMessaging.getInstance();
            fm.send(new RemoteMessage.Builder(sender_id + "@fcm.googleapis.com")
                    .setMessageId(recipient_id)
                    .addData("Head", "경고")
                    .addData("msg", "교수님이 상담신청을 하지 않아 경고를 보냈습니다. 빠른 시일 내에 상담신청을 해주시길 바랍니다.")
                    .build());
        }
        else if (Classification == "피드백안내") {
            FirebaseMessaging fm = FirebaseMessaging.getInstance();
            fm.send(new RemoteMessage.Builder(sender_id + "@fcm.googleapis.com")
                    .setMessageId(recipient_id)
                    .addData("Head", "피드백안내드립니다.")
                    .addData("msg", "상담이 완료되어 피드백작성을 안내드립니다. 피드백을 작성하여주시기 바랍니다.")
                    .build());
        }
        else if (Classification == "일정미수리") {
            FirebaseMessaging fm = FirebaseMessaging.getInstance();
            fm.send(new RemoteMessage.Builder(sender_id + "@fcm.googleapis.com")
                    .setMessageId(recipient_id)
                    .addData("Head", "상담신청이 거부되었습니다.")
                    .addData("msg", "상담신청이 거부되었습니다. 사유: " + msg1 )
                    .build());
        }
        else if (Classification == "일정신청") {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            FirebaseMessaging fm = FirebaseMessaging.getInstance();
            fm.send(new RemoteMessage.Builder(sender_id + "@fcm.googleapis.com")
                    .setMessageId(recipient_id)
                    .addData("Head", "상담일정신청이 왔습니다.")
                    //.addData("msg", database.child(sender_id).child("student_information").get(String name) + "이 상담을 신청했습니다.")
                    .build());
        }
        else if (Classification == "상담취소") {
            FirebaseMessaging fm = FirebaseMessaging.getInstance();
            fm.send(new RemoteMessage.Builder(sender_id + "@fcm.googleapis.com")
                    .setMessageId(recipient_id)
                    .addData("Head", "상담이 취소되었습니다.")
                    .addData("msg","상담이 취소되었습니다.")
                    .build());
        }
        else if (Classification == "상담임박") {
            FirebaseMessaging fm = FirebaseMessaging.getInstance();
            fm.send(new RemoteMessage.Builder(sender_id + "@fcm.googleapis.com")
                    .setMessageId(recipient_id)
                    .addData("Head", msg1)
                    .addData("msg", msg2)
                    .build());
        }

    }

    public class alarm{
        public String Classification;
        public String sender_id;
        public String recipient_id;
        public String msg1;
        public String msg2;
        public String date;

        public alarm(String Classification, String sender_id, String recipient_id
                , String msg1, String msg2, String date){
            this.Classification = Classification;
            this.sender_id = sender_id;
            this.recipient_id = recipient_id;
            this.msg1 = msg1;
            this.msg2 = msg2;
            this.date = date;
        }
    }



    public void pushdata(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        String token = String.valueOf(FirebaseMessaging.getInstance().getToken());

        database.child("test").child("message").setValue(token);


        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        fm.send(new RemoteMessage.Builder(token + "@fcm.googleapis.com")
                .setMessageId(token)
                .addData("my_message", "Hello World")
                .addData("my_action","SAY_HELLO")
                .build());

        //FCM_message a = new FCM_message(token, "title", "head","msg")

        fm.send(new RemoteMessage.Builder(6 + "@fcm.googleapis.com")
                .setMessageId(token)//Integer.toString(messageId)
                .addData("my_message", "Hello World")
                .addData("my_action","SAY_HELLO")
                .build());

    }




}


//Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image01);
//ByteArrayOutputStream stream = new ByteArrayOutputStream();
//bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//byte[] byteArray = stream.toByteArray();
//String data = byteArrayToBinaryString(byteArray);
//database.child("test").child("image").setValue(data);