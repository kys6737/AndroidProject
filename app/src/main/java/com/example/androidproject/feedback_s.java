package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feedback_s extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    private DatabaseReference DBReference=database.getReference();;

    String time;
    String professor;
    String kind;
    String msg;
    String pf_key;
    String mykey;
    String feedback_key;
    int year, month;
    boolean flag;

    TextView time_when;
    TextView professor_who;
    TextView kind_what;

    EditText feedback_record;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_s);


        Toolbar feedback_toolbar=findViewById(R.id.feedback_toolbar);
        feedback_toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(feedback_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("상세 정보");


        time_when=findViewById(R.id.time_when);
        professor_who=findViewById(R.id.professor_who);
        kind_what=findViewById(R.id.kind_what);

        feedback_record=findViewById(R.id.feedback_record);
        add_btn=findViewById(R.id.add);

        Bundle extras=getIntent().getExtras();

        time=extras.getString("time");
        professor=extras.getString("professor");
        kind=extras.getString("kind");
        msg=extras.getString("f");

        year=extras.getInt("year");
        month=extras.getInt("month");

//        mykey=extras.getString("private_key");
        mykey="187740328";
        databaseReference = database.getReference(mykey);


        time_when.setText(time);
        professor_who.setText(professor);
        kind_what.setText(kind);
        feedback_record.setText(msg);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("").setMessage("후기가 등록되었습니다.");

        databaseReference.child("professor_private_key").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int key=snapshot.getValue(Integer.class);
                pf_key=String.valueOf(key);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        add_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

                msg=feedback_record.getText().toString();

//                학생측 데이터베이스에 후기 저장
                databaseReference.child("history").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long num=snapshot.getChildrenCount();

                        for(int i=1; i<=num; i++){
                            String num2=String.valueOf(i);
                            databaseReference.child("history").child(num2).child("content").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    Record_list list=snapshot.getValue(Record_list.class);
                                    if(list.getDate_year()==year && list.getDate_month()==month){
                                        databaseReference.child("history").child(num2).child("content").child("review").setValue(msg);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//                databaseReference.child("history").child("1").child("content").child("review").setValue(msg);

                flag=false;
                DBReference.child(pf_key).child("review").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long count=snapshot.getChildrenCount();

                        for(int i=1; i<=count; i++){
                            String icount=String.valueOf(i);
                            DBReference.child(pf_key).child("review").child(icount).child("key").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        int keying=snapshot.getValue(Integer.class);
                                        feedback_key=String.valueOf(keying);
                                        if(feedback_key.equals(mykey)){
                                            flag=true;
                                            DBReference.child(pf_key).child("review").child(icount).child("review").setValue(msg);
                                        }
                                    } else {
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }

//                        if(flag==false){
//                            String path=String.valueOf(count+1);
//                            feedback_list new_f=new feedback_list(Integer.valueOf(mykey), msg);
//                            DBReference.child(pf_key).child("review").child(path).setValue(new_f);
//                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                add_btn.setText("수정");
                builder.setTitle("").setMessage("수정되었습니다.");
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}