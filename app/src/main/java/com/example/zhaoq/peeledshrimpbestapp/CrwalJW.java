package com.example.zhaoq.peeledshrimpbestapp;


import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.EditText;

import com.googlecode.tesseract.android.TessBaseAPI;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class CrwalJW {
    private static String captchaUrl = "http://jw.qdu.edu.cn/academic/getCaptcha.do";
    private static String jwUrl = "http://jw.qdu.edu.cn/academic/common/security/login.jsp";
    // to do change username and password
    private static String loginUrl = "http://jw.qdu.edu.cn/academic/j_acegi_security_check" +
            "?groupId=&j_username=WILLREPLACEUSERNAME&j_password=WILLREPLACEPASSWORD&j_captcha=";

    public static String SD_PATH= "/data/";
    public static String DICTIONARY="eng";
    public static String imageName ="/data/capture/capture.jpg";
    private static Map<String, String> cookies = null;


    public static byte[] getCaptureBytes() {
        Connection capConnection = Jsoup.connect(captchaUrl);
        Connection.Response capResponse =
                null;
        try {
            capResponse = capConnection.ignoreContentType(true).method(Connection.Method.GET).timeout(1000).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cookies = capResponse.cookies();
        byte[] bytes = capResponse.bodyAsBytes();
        return bytes;
    }


    public static void LogIn(String captureStr) throws IOException {
        String[] strs = MainActivity.studentInfo.getStuInfo();
        String userId =strs[0];
        String pswd = strs[1];
        loginUrl = loginUrl.replace("WILLREPLACEUSERNAME", userId).replace("WILLREPLACEPASSWORD", pswd);
        String tarUrl = loginUrl + captureStr;
//        System.out.println("tarUrl is " + tarUrl);
        Document document = Jsoup.connect(tarUrl).cookies(cookies).timeout(1000).get();
    }

    private static Elements weeklyCourseRange;
    public static Object[] getCourseArr(final int year, final int term, final int week) throws IOException {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String courseListUrl =
                        "http://jw.qdu.edu.cn/academic/student/currcourse/currcourse.jsdo";
                String courseRange =
                        "http://jw.qdu.edu.cn/academic/manager/coursearrange/studentWeeklyTimetable.do"
                                + "?yearid=" + (year - 1980)
                                + "&termid=" + term
                                + "&whichWeek=" + week;
                try {
                    // 必须先请求此页面
                    Document courselist =
                            Jsoup.connect(courseListUrl)
                                    .cookies(cookies).timeout(1000).get();

                    // 再请求此页面
                    weeklyCourseRange = Jsoup.connect(courseRange)
                            .cookies(cookies).timeout(1000).get().getElementsByClass("datalist").get(0)
                            .children().get(0)
                            .children();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Course> courseList = new ArrayList<>();
        if(weeklyCourseRange != null)
        for(int i  = 1; i < weeklyCourseRange.size(); i++) {
            Course temp = new Course();
            temp.courseDate =
                    weeklyCourseRange.get(i).getElementsByAttributeValue("name", "td0").html();
            temp.courseName =
                    weeklyCourseRange.get(i).getElementsByAttributeValue("name", "td1").html();
            temp.courseOrder =
                    weeklyCourseRange.get(i).getElementsByAttributeValue("name", "td6").html();
            temp.beginTime =
                    weeklyCourseRange.get(i).getElementsByAttributeValue("name", "td7").html();
            temp.endTIme =
                    weeklyCourseRange.get(i).getElementsByAttributeValue("name", "td8").html();
            temp.courseLocation =
                    weeklyCourseRange.get(i).getElementsByAttributeValue("name", "td10").html();
            courseList.add(temp);
        }
        return courseList.toArray();
    }
}
