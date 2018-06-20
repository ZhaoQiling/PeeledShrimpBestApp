package com.example.zhaoq.peeledshrimpbestapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class StudentInfo extends AppCompatActivity{

    private HashMap<String, String> info = new HashMap<>();

    public void setStuInfo(String stuId, String stuPswd) {

        info.put("stuid", stuId);
        info.put("stupswd", stuPswd);
    }


    public String[] getStuInfo() {
        return new String[] {
                info.get("stuid"),
                info.get("stupswd")};
    }

}
