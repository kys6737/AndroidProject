package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class logIn extends AppCompatActivity {

    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference DBReference=database.getReference();

    String private_key;
    int key;

    EditText login_key;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        login_key=findViewById(R.id.login_key);
        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                private_key=login_key.getText().toString();

                DBReference.child("private_key").child(private_key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            int you = snapshot.getValue(Integer.class);

                            try{  //private_key 정수 변환
                                key=Integer.parseInt(private_key);
                            } catch(NumberFormatException e){
                                System.out.println("Please enter a valid integer");
                            }
                            if(key<1000000000){  //학생 로그인
                                Intent intent=new Intent(getApplicationContext(), MainScreen_S.class);

//                                내 로그인 코드 전달
//                                intent.putExtra("private_key", key);
//                                로그인 코드에 대한 상대 코드 값 전달
//                                intent.putExtra("your_key", you);

                                startActivity(intent);
                            }
                            else{  //교수 로그인
                                Intent intent=new Intent(getApplicationContext(), MainScreen_P.class);
                                startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "로그인 코드가 잘못되었습니다.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });
    }
}