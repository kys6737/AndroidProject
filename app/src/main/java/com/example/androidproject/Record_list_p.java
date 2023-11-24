package com.example.androidproject;

import com.google.firebase.database.PropertyName;

public class Record_list_p {
    private int Date_year;
    private int Date_month;
    private int Date_day;
    private float Date_hour;
    private String Professor_name;
    private String Professor_number;
    private String Student_name;
    private String classification;
    private String counseling_form;
    private String counseling_group;
    private String counseling_content;

    private Record_list_p() {}


//    ----------------------------getter------------------------------

    public int getDate_year() {
        return Date_year;
    }

    public void setDate_year(int date_year) {
        Date_year = date_year;
    }

    public int getDate_month() {
        return Date_month;
    }

    public void setDate_month(int date_month) {
        Date_month = date_month;
    }

    public int getDate_day() {
        return Date_day;
    }

    public void setDate_day(int date_day) {
        Date_day = date_day;
    }

    public float getDate_hour() {
        return Date_hour;
    }

    public void setDate_hour(float date_hour) {
        Date_hour = date_hour;
    }

    public String getProfessor_name() {
        return Professor_name;
    }

    public void setProfessor_name(String professor_name) {
        Professor_name = professor_name;
    }

    public String getProfessor_number() {
        return Professor_number;
    }

    public void setProfessor_number(String professor_number) {
        Professor_number = professor_number;
    }

    public String getStudent_name() {
        return Student_name;
    }

    public void setStudent_name(String student_name) {
        Student_name = student_name;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getCounseling_form() {
        return counseling_form;
    }

    public void setCounseling_form(String counseling_form) {
        this.counseling_form = counseling_form;
    }

    public String getCounseling_group() {
        return counseling_group;
    }

    public void setCounseling_group(String counseling_group) {
        this.counseling_group = counseling_group;
    }

    public String getCounseling_content() {
        return counseling_content;
    }

    public void setCounseling_content(String counseling_content) {
        this.counseling_content = counseling_content;
    }


//    생성자---------------------------------------------------------------

    public Record_list_p(int Date_year, int Date_month, int Date_day, float Date_hour,String Professor_number, String Professor_name, String Student_name, String classification, String counseling_form, String counseling_group, String counseling_content) {
        this.Date_year = Date_year;
        this.Date_month = Date_month;
        this.Date_day = Date_day;
        this.Date_hour = Date_hour;
        this.Professor_number=Professor_number;
        this.Professor_name=Professor_name;
        this.Student_name = Student_name;
        this.classification = classification;
        this.counseling_form = counseling_form;
        this.counseling_group = counseling_group;
        this.counseling_content = counseling_content;
    }
}