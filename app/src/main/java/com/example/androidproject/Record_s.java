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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Record_s extends AppCompatActivity {

    private ArrayList<Record_list_new> mArrayList;
    private CustomAdapter mAdapter;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_s);

        Toolbar record_toolbar=findViewById(R.id.feedback_p_toolbar);
        record_toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(record_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("상담 내역");


//        Button btnInsert=(Button)findViewById(R.id.btn);

//        ---------------------------recyclerView---------------------------
        RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList=new ArrayList<>();

        mAdapter=new CustomAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration=new
                DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);



        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener(){
            public void onClick(View view, int position){
                Record_list_new list_click=mArrayList.get(position);

                Intent intent=new Intent(getBaseContext(), feedback_s.class);

//                intent.putExtra("t", list_click.getC_when());
//                intent.putExtra("p", list_click.getC_who());
//                intent.putExtra("k", list_click.getC_what());
//                intent.putExtra("n", list_click.getCount());
//                intent.putExtra("f", list_click.getFeedback());

                startActivity(intent);
            }

            public void onLongClick(View view, int position){}
        }));
//        ---------------------------recyclerView---------------------------

        databaseReference.child("187740328").child("history").child("2023_2").child("content").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Record_list_new read_list = snapshot.getValue(Record_list_new.class);
                    Record_list_new call_list = new Record_list_new(read_list.getDate_day(), read_list.getDate_hour(), read_list.getDate_month(), read_list.getDate_year(), read_list.getProfessor_name(), read_list.getProfessor_number(), read_list.getClassification(), read_list.getCounseling_content(), read_list.getCounseling_form(), read_list.getCounseling_group());
                    mArrayList.add(call_list);
                    mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Query myTopPOstsQuery=databaseReference.orderByChild("counsel").equalTo("complete");
//        myTopPOstsQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                mArrayList.clear();
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Record_list read_list = dataSnapshot.getValue(Record_list.class);
//                    Record_list call_list = new Record_list(read_list.getC_when(), read_list.getC_who(), read_list.getC_what(), read_list.getCount(), read_list.getFeedback(), read_list.getP_who());
//                    mArrayList.add(call_list);
//                    mAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        btnInsert.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//                databaseReference.child("counsel_record").child("1").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Record_list read_list=snapshot.getValue(Record_list.class);
//
//                        Record_list call_list=new Record_list(read_list.getC_when(), read_list.getC_who(), read_list.getC_what());
//                        mArrayList.add(call_list);
//                        mAdapter.notifyDataSetChanged();
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
////                Record_list data=new Record_list("23.06.12", "강동기", "대면상담");
////                databaseReference.child("counsel_record").child("2").setValue(data);
////                mArrayList.add(data);
////                mAdapter.notifyDataSetChanged();
//            }
//        });
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Record_s.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Record_s.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
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