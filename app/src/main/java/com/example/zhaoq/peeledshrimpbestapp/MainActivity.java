package com.example.zhaoq.peeledshrimpbestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    public static StudentInfo studentInfo = new StudentInfo();
    Button menuLogIn = null;
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
    }
}
