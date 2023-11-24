package com.example.androidproject;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class feedback_p extends AppCompatActivity {

    String mykey;
    List<Integer> yourkeyList=new ArrayList<>();
    int element;
    int count;
    String element_s;

    private ArrayList<feedback_list> mArrayList;
    private CustomAdapter_feedback_p mAdapter;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_p);

        Toolbar record_toolbar = findViewById(R.id.feedback_p_toolbar);
        record_toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(record_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("상담 후기");

        mykey="2021145818";  //로그인부터해서 intent로 값 넘겨받기
        databaseReference = database.getReference(mykey);


//        ---------------------------recyclerView---------------------------
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();

        mAdapter = new CustomAdapter_feedback_p(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);


//        ---------------------------recyclerView---------------------------

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

                        databaseReference.child("review").child(element_s).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists()) {
                                    feedback_list read_list = snapshot.getValue(feedback_list.class);
                                    feedback_list call_list = new feedback_list(read_list.getReview());
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