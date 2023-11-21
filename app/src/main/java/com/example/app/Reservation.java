package com.example.app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Reservation implements Parcelable {
    private String date;
    private String time;
    private String studentName;
    private String type;
    private String question;
    private static final long serialVersionUID = 1L;

    public Reservation() {
        // 기본 생성자는 Firebase에서 데이터를 읽을 때 필요합니다.
    }


    protected Reservation(Parcel in) {
        date = in.readString();
        time = in.readString();
        studentName = in.readString();
        type = in.readString();
        question = in.readString();
    }

    public static final Creator<Reservation> CREATOR = new Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel in) {
            return new Reservation(in);
        }

        @Override
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };

    public String getDate () {
            return date;
        }

        public void setDate (String date){
            this.date = date;
        }

        public String getTime () {
            return time;
        }

        public void setTime (String time){
            this.time = time;
        }
        public String getType () {
            return type;
        }

        public void setType (String type) {
            this.type = type;
        }

        public String getQuestion () {
            return question;
        }

        public void setQuestion (String question) {
            this.question = question;
        }

        public String getStudentName () {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeString(studentName);
        parcel.writeString(type);
        parcel.writeString(question);
    }
}