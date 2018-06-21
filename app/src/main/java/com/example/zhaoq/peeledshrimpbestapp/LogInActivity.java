package com.example.zhaoq.peeledshrimpbestapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class LogInActivity extends Activity {
    private byte[] bytes = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        new Thread() {
            @Override
            public void run() {
                bytes = CrwalJW.getCaptureBytes();
                final Bitmap capture = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                ImageView imageView = (ImageView) findViewById(R.id.capture_img);
                imageView.setImageBitmap(capture);
            }
        }.start();

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Button loginBtn = (Button) findViewById(R.id.login_menu_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final String userid = ((EditText)findViewById(R.id.userid)).getText().toString();
                final String pswd = ((EditText)findViewById(R.id.password)).getText().toString();
                final String capture =  ((EditText)findViewById(R.id.capture_str)).getText().toString();
                if(userid.equals("")) {
                    Toast.makeText(LogInActivity.this, "Please input StudentID", Toast.LENGTH_SHORT).show();
                }
                else if(pswd.equals("")) {
                    Toast.makeText(LogInActivity.this, "Please input Password", Toast.LENGTH_SHORT).show();
                }
                else if(capture.equals("")) {
                    Toast.makeText(LogInActivity.this, "Please input Capture", Toast.LENGTH_SHORT).show();
                }
                else {
                    MainActivity.studentInfo.setStuInfo(userid, pswd);
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();

                            try {
                                CrwalJW.LogIn(capture);

//                                 to do 改为爬取一年内容， 并且存储到数据库
//                                Object[] carr = CrwalJW.getCourseArr(2018, 1,5);
//                                for(int i = 0; i < carr.length; i++) {
//                                    Course temp = (Course)carr[i];
//                                    System.out.println(temp);
//                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                }

                finish();
            }
        });
    }
}
