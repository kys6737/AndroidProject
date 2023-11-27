package com.example.androidproject;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.graphics.Color;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyPg_P extends AppCompatActivity {
    Button backBtn, editBtn, editCompleteBtn, logoutBtn;
    ImageButton changeImgBtn;
    ImageView myImg;

    EditText editPhone, editMail, editZoom;
    TextView name, laboratory,school,major, phone, mail, zoom;
    Switch onOff;
    Dialog editdialog, logoutdialog, logoutcheckdialog, imgselectdialog;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    String Storage_path = "user_image/";
    String Image_FileName="profile_image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypg_p);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("2021145818");
        storage=FirebaseStorage.getInstance();

        backBtn=findViewById(R.id.backBtn);
        changeImgBtn=findViewById(R.id.changeImgBtn);
        myImg=findViewById(R.id.myImg);
        editBtn=findViewById(R.id.editBtn);
        editCompleteBtn=findViewById(R.id.editCompleteBtn);
        logoutBtn=findViewById(R.id.logoutBtn);
        laboratory =findViewById(R.id.laboratory);
        phone=findViewById(R.id.phone);
        mail=findViewById(R.id.mail);
        zoom =findViewById(R.id.zoom);
        editPhone=findViewById(R.id.editPhone);
        editMail=findViewById(R.id.editMail);
        editZoom=findViewById(R.id.editZoom);
        onOff=findViewById(R.id.onOff);
        name = findViewById(R.id.name);
        school=findViewById(R.id.school);
        major=findViewById(R.id.major);


        editdialog=new Dialog(MyPg_P.this);
        editdialog.setContentView(R.layout.editdialog);
        logoutdialog=new Dialog(MyPg_P.this);
        logoutdialog.setContentView(R.layout.logoutdialog);
        logoutcheckdialog=new Dialog(MyPg_P.this);
        logoutcheckdialog.setContentView(R.layout.logoutcheckdialog);
        imgselectdialog=new Dialog(MyPg_P.this);
        imgselectdialog.setContentView(R.layout.imgselectdialog);

        // ValueEventListener 추가하여 Firebase에서 데이터 검색
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 데이터가 존재하는지 확인
                if (dataSnapshot.exists()) {
                    // 데이터 검색 및 TextViews 업데이트
                    String firebaseName = dataSnapshot.child("professor_information").child("name").getValue(String.class);
                    String firebaseSchool = String.valueOf(dataSnapshot.child("professor_information").child("belong").getValue());
                    String firebaseMajor = dataSnapshot.child("professor_information").child("major").getValue(String.class);
                    String firebaseLaboratory = dataSnapshot.child("professor_information").child("laboratory_location").getValue(String.class);
                    String firebasePhone = dataSnapshot.child("professor_information").child("phone_number").getValue(String.class);
                    String firebaseEmail = dataSnapshot.child("professor_information").child("email").getValue(String.class);
                    String firebaseZoom = dataSnapshot.child("professor_information").child("Zoom_link").getValue(String.class);

                    // 검색된 데이터로 TextViews 업데이트
                    name.setText(firebaseName);
                    school.setText(firebaseSchool);
                    major.setText(firebaseMajor);
                    laboratory.setText(firebaseLaboratory);
                    phone.setText(firebasePhone);
                    editPhone.setText(firebasePhone);
                    mail.setText(firebaseEmail);
                    editMail.setText(firebaseEmail);
                    zoom.setText(firebaseZoom);
                    editZoom.setText(firebaseZoom);

                    // 프로필 이미지 URL을 가져와서 이미지 설정
                    String imageUrl = dataSnapshot.child("professor_information").child("profileImageUrl").getValue(String.class);
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        // 이미지 URL이 존재하면 이미지를 다운로드하여 설정
                        downloadImageAndSetToImageView(imageUrl);
                    }
                    Boolean lastAlarmStatus = dataSnapshot.child("professor_information").child("alarm").getValue(Boolean.class);
                    // lastAlarmStatus 값에 따라 스위치 상태를 설정
                    if (lastAlarmStatus != null) {
                        onOff.setChecked(lastAlarmStatus);
                    }
                }
            }
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 오류 처리
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTextStatus(1);
            }
        });
        editCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextStatus(0);
                showEditDialog();
                String editedPhone = editPhone.getText().toString();
                String editedMail = editMail.getText().toString();
                String editedZoom = editZoom.getText().toString();

                databaseReference.child("professor_information").child("phone_number").setValue(editedPhone);
                databaseReference.child("professor_information").child("email").setValue(editedMail);
                databaseReference.child("professor_information").child("Zoom_link").setValue(editedZoom);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });

        onOff.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    Toast.makeText(MyPg_P.this,"알림 ON",Toast.LENGTH_SHORT).show();
                    databaseReference.child("professor_information").child("alarm").setValue(true);
                }
                else {
                    Toast.makeText(MyPg_P.this,"알림 OFF",Toast.LENGTH_SHORT).show();
                    databaseReference.child("professor_information").child("alarm").setValue(false);
                }
            }
        });
        changeImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImgSelectDialog();
            }
        });
    }
    private void downloadImageAndSetToImageView(String imageUrl) {
        // Firebase Storage 레퍼런스 설정
        StorageReference storageRef = storage.getReferenceFromUrl(imageUrl);

        // 이미지 다운로드
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(bytes -> {
                    // 이미지 다운로드 성공
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    myImg.setImageBitmap(bitmap);
                })
                .addOnFailureListener(e -> {
                    // 이미지 다운로드 실패
                    Toast.makeText(MyPg_P.this, "이미지 다운로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
                });
    }

    public void showEditDialog(){
        editdialog.show();
        Button okBtn = editdialog.findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                editdialog.dismiss();
            }
        });
    }
    public void setTextStatus(int i){
        if(i==1){
            editBtn.setVisibility(View.INVISIBLE);
            editCompleteBtn.setVisibility(View.VISIBLE);
            laboratory.setTextColor(Color.parseColor("#c2c2c2"));
            phone.setVisibility(View.INVISIBLE);
            mail.setVisibility(View.INVISIBLE);
            zoom.setVisibility(View.INVISIBLE);
            editPhone.setVisibility(View.VISIBLE);
            editMail.setVisibility(View.VISIBLE);
            editZoom.setVisibility(View.VISIBLE);
        }
        else{
            editBtn.setVisibility(View.VISIBLE);
            editCompleteBtn.setVisibility(View.INVISIBLE);
            laboratory.setTextColor(Color.BLACK);
            phone.setVisibility(View.VISIBLE);
            mail.setVisibility(View.VISIBLE);
            zoom.setVisibility(View.VISIBLE);
            editPhone.setVisibility(View.INVISIBLE);
            editMail.setVisibility(View.INVISIBLE);
            editZoom.setVisibility(View.INVISIBLE);
            phone.setText(editPhone.getText());
            mail.setText(editMail.getText());
            zoom.setText(editZoom.getText());
        }
    }
    public void showLogoutDialog(){
        logoutdialog.show();
        Button yesBtn = logoutdialog.findViewById(R.id.yesBtn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutdialog.dismiss();
                logoutcheckdialog.show();
                Button okBtn = logoutcheckdialog.findViewById(R.id.okBtn);
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logoutcheckdialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(),logIn.class);
                        startActivity(intent);

                    }
                });
            }
        });
        Button noBtn = logoutdialog.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutdialog.dismiss();
            }
        });
    }
    public void showImgSelectDialog(){
        imgselectdialog.getWindow().setGravity(Gravity.BOTTOM);
        imgselectdialog.show();
        Button backBtn = imgselectdialog.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgselectdialog.dismiss();
            }
        });
        Button gallery = imgselectdialog.findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityResult.launch(intent);

                imgselectdialog.dismiss();
            }
        });
        Button camera = imgselectdialog.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgselectdialog.dismiss();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultPicture.launch(intent);
            }
        });
        Button defaultImg = imgselectdialog.findViewById(R.id.defaultImg);
        defaultImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String defaultImageUrl = "gs://mypage-d913d.appspot.com/profile.jpg";
                databaseReference.child("professor_information").child("profileImageUrl").setValue(defaultImageUrl);
                myImg.setImageResource(R.drawable.profile);
                imgselectdialog.dismiss();
            }
        });
    }
    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                        Uri uri = result.getData().getData();

                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            myImg.setImageBitmap(bitmap);
                            uploadImageToFirebaseStorage(uri);
                        }
                        catch(FileNotFoundException e){
                            e.printStackTrace();
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    private void uploadImageToFirebaseStorage(Uri imageUri) {
        // Firebase Storage 레퍼런스 설정
        StorageReference storageRef = storage.getReference().child(Storage_path + Image_FileName);

        // 이미지 업로드
        storageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // 이미지 업로드 성공
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        // 다운로드 URL을 얻은 후, 이를 사용하여 이미지 표시 또는 데이터베이스에 저장
                        String imageUrl = uri.toString();
                        // 여기서 imageUrl을 사용하여 필요한 작업을 수행하세요.
                        // 데이터베이스에 저장
                        databaseReference.child("professor_information").child("profileImageUrl").setValue(imageUrl);
                    });
                })
                .addOnFailureListener(e -> {
                    // 이미지 업로드 실패
                    Toast.makeText(MyPg_P.this, "이미지 업로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
                });
    }

    ActivityResultLauncher<Intent> activityResultPicture = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                public void onActivityResult(ActivityResult result){
                    if(result.getResultCode()==RESULT_OK && result.getData()!=null) {
                        Bundle extras = result.getData().getExtras();

                        Bitmap bitmap = (Bitmap)extras.get("data");
                        myImg.setImageBitmap(bitmap);

                        uploadBitmapToFirebaseStorage(bitmap);
                    }
                }
            });
    private void uploadBitmapToFirebaseStorage(Bitmap bitmap) {
        // Firebase Storage 레퍼런스 설정
        StorageReference storageRef = storage.getReference().child(Storage_path + Image_FileName);

        // 비트맵을 JPEG 형식으로 변환
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        // 이미지 업로드
        storageRef.putBytes(data)
                .addOnSuccessListener(taskSnapshot -> {
                    // 이미지 업로드 성공
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        // 다운로드 URL을 얻은 후, 이를 사용하여 이미지 표시 또는 데이터베이스에 저장
                        String imageUrl = uri.toString();
                        // 여기서 imageUrl을 사용하여 필요한 작업을 수행하세요.
                        // 데이터베이스에 저장
                        databaseReference.child("professor_information").child("profileImageUrl").setValue(imageUrl);
                    });
                })
                .addOnFailureListener(e -> {
                    // 이미지 업로드 실패
                    Toast.makeText(MyPg_P.this, "이미지 업로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
                });
    }
}