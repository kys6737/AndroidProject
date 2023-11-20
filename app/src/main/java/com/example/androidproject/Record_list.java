package com.example.androidproject;


public class Record_list {
    private String c_when;
    private String c_who;
    private String c_what;
    private int count;
    private String feedback;

    private String p_who;

    public Record_list(){}

    public String getC_when() {
        return c_when;
    }

    public void setC_when(String c_when) {
        this.c_when = c_when;
    }

    public String getC_who() {
        return c_who;
    }

    public void setC_who(String c_who) {
        this.c_who = c_who;
    }

    public String getC_what() {
        return c_what;
    }

    public void setC_what(String c_what) {
        this.c_what = c_what;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getP_who() {
        return p_who;
    }

    public void setP_who(String p_who) {
        this.p_who = p_who;
    }

    public Record_list(String c_when, String c_who, String c_what, int num, String feedback, String p_who) {
        this.c_when = c_when;
        this.c_who = c_who;
        this.c_what = c_what;
        this.count=num;
        this.feedback=feedback;
        this.p_who=p_who;
    }
}