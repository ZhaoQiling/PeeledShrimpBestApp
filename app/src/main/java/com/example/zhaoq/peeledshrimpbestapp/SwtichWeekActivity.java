package com.example.zhaoq.peeledshrimpbestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class SwtichWeekActivity extends Activity {
    public static int year = -1, term, week;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_layout);

        Button confirm = (Button) findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = Integer.parseInt(((Spinner)findViewById(R.id.year_spinner)).getSelectedItem().toString());
                term = Integer.parseInt(((Spinner)findViewById(R.id.order_spinner)).getSelectedItem().toString());
                week = Integer.parseInt(((Spinner)findViewById(R.id.week_spinner)).getSelectedItem().toString());
                finish();
            }
        });
    }
}
