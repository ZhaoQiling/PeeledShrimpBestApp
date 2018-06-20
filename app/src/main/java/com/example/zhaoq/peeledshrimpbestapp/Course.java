package com.example.zhaoq.peeledshrimpbestapp;


import java.util.HashMap;

public class Course{
    public String courseDate;
    public String courseName;
    public String courseOrder;
    public String beginTime;
    public String endTIme;
    public String courseLocation;

    @Override
    public String toString() {
        return "[" + courseName + "|" + courseDate + "|" + courseOrder + "|" + beginTime + "|" + endTIme + "|" + courseLocation + "]";
    }
}
