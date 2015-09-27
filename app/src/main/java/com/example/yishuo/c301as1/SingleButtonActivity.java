package com.example.yishuo.c301as1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by yishuo on 9/23/15.
 */
public class SingleButtonActivity extends Activity {
    private int status; // 0 = press button to start
                        // 1 = started, press button to stop
    private Random rand;
    private int n;
    private int nclick;
    private int nloop;
    private String textstr;
    private float [] tentime;
    private float [] hundredtime;

    private long startTime;
    private long endTime;
    private int timeSet;
    private float diff;
    public TextView text;
    private static final String fsingle = "fsingle.sav";
    Timer timer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlebuttonactivity);
        status = 0;
        nclick = 0;
        timeSet = 0;
        text = (TextView) findViewById(R.id.textView);
        //Button reactButton = (Button) findViewById(R.id.reactButton);
        text.setText("Press \"React\" to start");
        //reactButton.setOnClickListener((View.OnClickListener) this);
    }

    /*
    public void record() {
        try {
            FileInputStream fis = openFileInput(fsingle);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));


        } catch (FileNotFoundException e) {

        }
        //
        try {
            FileOutputStream fos = openFileOutput(fsingle, 0);
            Gson gson = new Gson();
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            for (int i = 0; i < 100; i = i + 1) {
                hundredtime[i] = 0;
            }
            gson.toJson(hundredtime, writer);
            writer.flush();
            fos.close();
        }
    }
    */
        /*
        try {
            FileInputStream fis = openFileInput(fsingle);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            byte[] bytes = new byte[100];
            try {
                fis.read(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            File f = new File(fsingle);
            try {
                f.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        */
    // http://stackoverflow.com/questions/28243847/how-to-make-a-timer-in-android-studio-1-0-2
    // By: vinitius
    public void click(View view) {
        if (status == 1) {
            timer.cancel();
            endTime = System.currentTimeMillis();
            diff = (float)(endTime - startTime);
            //textstr = "Stop " + Float.toString(diff) + " n " + Integer.toString(n);
            if (diff < (float)n) {
                //textstr = textstr + " You pressed too fast";
                textstr = "You pressed too fast";
            } else {
                diff = diff - n;
                diff = (float) (diff / 1000.0);
                textstr = "Latency: " + Float.toString(diff) + "s";
                //record(); // TO DO
            }
            textstr = textstr + "\nStart " + Integer.toString((int)startTime) + " end " + Integer.toString((int)endTime) + " diff " + Float.toString(diff);
            //textstr = textstr + " Press \"React\" to start a new game status" + Integer.toString(status);
            textstr = textstr + "\nPress \"React\" to start a new game\nn " + Integer.toString(n);
            text.setText(textstr);
            status = 0;
        } else {
            textstr = "Game start";
            text.setText(textstr);
            status = 1;
            timer = new Timer();
            rand = new Random();
            n = rand.nextInt(1991) + 10;
            startTime = System.currentTimeMillis();
            textstr = textstr + Integer.toString((int) startTime);
            text.setText(textstr);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            endTime = System.currentTimeMillis();
                            diff = (float)(endTime - startTime);
                            //textstr = "status" + Integer.toString(status);
                            if (diff >= (float) n) {
                                status = 1;
                                textstr = "click";
                            }
                            text.setText(textstr);
                        }
                    });
                }
            }, 0, 10);
        }

    }

    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);
        //textstr = "timer";
        //text.setText(textstr);
    }

    private Runnable Timer_Tick = new Runnable() {
        @Override
        public void run() {

        }
    };
}

    /*
    //public void click(View view) {
    public void click(View view) {
        if (status == -1) {
            status = 0;
            timer();
        } else if (status == 0) {
            nclick = 1;
        }
    }
    public void timer(){
        if (status == 0) {
            startTime = System.currentTimeMillis();
            textstr = "Wait ";
            rand = new Random();
            n = rand.nextInt(1991) + 10;
            n = 2000;
            textstr = textstr + Integer.toString(n) + " starttime " + Integer.toString((int)startTime);
            text.setText(textstr);
            endTime = System.currentTimeMillis();
            diff = (int) (endTime - startTime);
            while (diff < n) {
                endTime = System.currentTimeMillis();
                diff = (int) (endTime - startTime);
                if (nclick == 1) {
                    break;
                }
            }
            textstr = textstr + "   Click";
            status = 1;
        } else if (status == 1) {
            endTime = System.currentTimeMillis();
            diff = (int) (endTime - startTime);
            textstr = "Start " + Integer.toString((int)startTime) + " End " + Integer.toString((int)endTime) + " Diff " + Integer.toString(diff);
            textstr = textstr + " Press \"React\" to restart";
            text.setText(textstr);
            status = 0;
        }
    }
    */
