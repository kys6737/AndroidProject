package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Record_p extends AppCompatActivity {

    private ArrayList<Record_list_p> mArrayList;
    private CustomAdapter_p mAdapter;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    String mykey;
    List<Integer> yourkeyList=new ArrayList<>();
    int element;
    int count;
    String element_s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_p);

        Toolbar record_toolbar=findViewById(R.id.feedback_p_toolbar);
        record_toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(record_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("상담 내역");

        mykey="2021145818";
        databaseReference=database.getReference(mykey);


//        ---------------------------recyclerView---------------------------
        RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList=new ArrayList<>();

        mAdapter=new CustomAdapter_p(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        //recyclerview의 아이템 사이에 구분선 추가
        //linewarlayoutmanager.getorientation() : recyclerview와 관련된 linearlayoutmanager의 방향 결정(구분선 방향 설정)
        DividerItemDecoration dividerItemDecoration=new
                DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);  //구분선 그리기


//        -----------------------------------------------------------------------------------------------

        count=0;
        databaseReference.child("student_pravate_key").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                yourkeyList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    int temp=dataSnapshot.getValue(Integer.class);
                    yourkeyList.add(temp);
                    count++;
                }

                if (!yourkeyList.isEmpty()) {
                    for(int i=0; i<count; i++) {
                        element = yourkeyList.get(i);
                        element_s = String.valueOf(element);

                        databaseReference.child("history").child("2023_2").child(element_s).child("content").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists()) {
                                    Record_list_p read_list = snapshot.getValue(Record_list_p.class);
                                    Record_list_p call_list = new Record_list_p(read_list.getDate_year(), read_list.getDate_month(), read_list.getDate_day(), read_list.getDate_hour(), read_list.getProfessor_number(), read_list.getProfessor_name(), read_list.getStudent_name(), read_list.getClassification(), read_list.getCounseling_form(), read_list.getCounseling_group(), read_list.getCounseling_content());
                                    mArrayList.add(call_list);

                                    mAdapter.notifyDataSetChanged();
                                } else{}
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Handle the error if needed
                            }
                        });
                    }
                } else {
                    // Handle the case where yourkeyList is empty
                    Toast.makeText(getApplicationContext(), "No keys found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        databaseReference.child("history").child("2023_2").child(yourkey).child("content").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                mArrayList.clear();
//                if(snapshot.exists()){
////                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Record_list read_list = snapshot.getValue(Record_list.class);
//                    Record_list call_list = new Record_list(read_list.getDate_year(), read_list.getDate_month(), read_list.getDate_day(), read_list.getDate_hour(), read_list.getProfessor_number(), read_list.getProfessor_name(), read_list.getClassification(), read_list.getCounseling_form(), read_list.getCounseling_group(), read_list.getCounseling_content());
//                    mArrayList.add(call_list);
//
//                    mAdapter.notifyDataSetChanged();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

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