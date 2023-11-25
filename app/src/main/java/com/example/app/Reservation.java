package com.example.app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Reservation implements Parcelable {
    private int Date_year;
    private int Date_day;
    private int Date_month;
    private float Date_hour;
    private String counseling_form;

    private static final long serialVersionUID = 1L;

    public Reservation() {
        // 기본 생성자는 Firebase에서 데이터를 읽을 때 필요합니다.
    }


    protected Reservation(Parcel in) {
        Date_year = in.readInt();
        Date_month = in.readInt();
        Date_day = in.readInt();
        Date_hour = in.readFloat();
        counseling_form = in.readString();

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

        public int getDate_year () {
        return Date_year;
    }

        public void setDate_year (int Date_year){
        this.Date_year = Date_year;
    }

        public int getDate_month () {
        return Date_month;
    }

        public void setDate_month (int Date_month){
        this.Date_month = Date_month;
    }

        public int getDate_day () {
            return Date_day;
        }

        public void setDate_day (int Date_day){
            this.Date_day = Date_day;
        }

        public float getTime () {
            return Date_hour;
        }

        public void setTime (float Date_hour){
            this.Date_hour = Date_hour;
        }

        public String getType () {
            return counseling_form;
        }

        public void setType (String conseling_form) {
            this.counseling_form = conseling_form;
        }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(Date_year);
        parcel.writeInt(Date_month);
        parcel.writeInt(Date_day);
        parcel.writeFloat(Date_hour);
        parcel.writeString(counseling_form);
        }
}