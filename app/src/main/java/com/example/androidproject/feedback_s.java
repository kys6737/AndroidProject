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
    private DatabaseReference DBReference=database.getReference();

    String time;
    String professor;
    String kind;
    String msg;
    String pf_key;
    String mykey;

    TextView time_when;
    TextView professor_who;
    TextView kind_what;

    EditText feedback_record;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_s);

        mykey="187740328";
        databaseReference = database.getReference(mykey);

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


        time_when.setText(time);
        professor_who.setText(professor);
        kind_what.setText(kind);
        feedback_record.setText(msg);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("").setMessage("후기가 등록되었습니다.");

        databaseReference.child("professor_pravate_key").addListenerForSingleValueEvent(new ValueEventListener() {
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
                databaseReference.child("history").child("2023_2").child("review").child("review").setValue(msg);

                DBReference.child(pf_key).child("review").child(mykey).child("review").setValue(msg);

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