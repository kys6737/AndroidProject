package com.example.androidproject;




public class IlJeong_list_p {
    private int Date_day;
    private int Date_hour;
    private int Date_month;
    private int Date_year;
    private String student_name;
    private String student_number;
    private String classification;
    private String counseling_content;
    private String counseling_form;
    private String counseling_group;
    private String state;



    public IlJeong_list_p() {}

    public int getDate_day() {
        return Date_day;
    }

    public void setDate_day(int Date_day) {
        this.Date_day = Date_day;
    }

    public int getDate_hour() { return Date_hour;}

    public void setDate_hour(int Date_hour){ this.Date_hour = Date_hour;}

    public int getDate_month() { return Date_month;}

    public void setDate_month(int Date_month){ this.Date_month = Date_month;}

    public int getDate_year() { return Date_year; }

    public void setDate_year(int Date_year) { this.Date_year = Date_year; }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_number() {return student_number;}

    public void setStudent_number(String student_number) {this.student_number = student_number;}

    public String getClassification() { return classification;}

    public void setClassification(String classification) { this.classification = classification;}

    public String getCounseling_content() {
        return counseling_content;
    }

    public void setCounseling_content(String counseling_content) {this.counseling_content = counseling_content;}

    public String getCounseling_form() { return counseling_form; }

    public void setConseling_form(String counseling_form) { this.counseling_form = counseling_form;}

    public String getCounseling_group() { return counseling_group;}

    public void setCounseling_group(String counseling_group) { this.counseling_group = counseling_group;}

    public String getState() { return state;}

    public void setState(String state) { this.state = state;}



    public IlJeong_list_p(int Date_day, int Date_hour, int Date_month, int Date_year, String student_name, String student_number, String classification, String counseling_content, String counseling_form, String counseling_group, String state) {
        this.Date_day = Date_day;
        this.Date_hour = Date_hour;
        this.Date_month = Date_month;
        this.Date_year = Date_year;
        this.student_name = student_name;
        this.student_number = student_number;
        this.classification = classification;
        this.counseling_content = counseling_content;
        this.counseling_form = counseling_form;
        this.counseling_group = counseling_group;
        this.state = state;
    }
}