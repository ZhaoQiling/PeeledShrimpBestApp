package com.example.zhaoq.peeledshrimpbestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity {
    public static StudentInfo studentInfo = new StudentInfo();
    Button menuLogIn = null;
    Button menuSwitch = null;
    private Handler handler = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        menuLogIn = (Button) findViewById(R.id.main_menu_login);
        menuLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
            }
        });

        menuSwitch = (Button) findViewById(R.id.main_menu_switch);
        menuSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SwtichWeekActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SwtichWeekActivity.year == -1)
            return;

        handler = new Handler();
        handler.post(runnableUI);
    }
    Runnable runnableUI = new Runnable() {
        @Override
        public void run() {
            try {
                Object[] carr = CrwalJW.getCourseArr(SwtichWeekActivity.year, SwtichWeekActivity.term, SwtichWeekActivity.week);
                String[] courseInfoArr = new String[carr.length];
                for(int i = 0; i < carr.length; i++)
                    courseInfoArr[i] = ((Course) carr[i]).toString();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        MainActivity.this, android.R.layout.simple_list_item_1, courseInfoArr);
                ListView listView = (ListView) findViewById(R.id.course_list);
                listView.setAdapter(adapter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
