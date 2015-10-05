package com.example.yishuo.c301as1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yishuo on 9/23/15.
 */

/*
    Resource:
    How to setup a timer
    http://stackoverflow.com/questions/28243847/how-to-make-a-timer-in-android-studio-1-0-2,
    By: vinitius
    2015-09-23
 */
public class SingleButtonActivity extends Activity {
    private int status; // 0 = press button to start; 1 = started, press button to stop
    private Random rand;
    private int n;
    private String textstr;

    private long startTime;
    private long endTime;
    private int timeSet;
    private float diff;
    public TextView text;
    Timer timer;

    private FileReadWrite sfile;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlebuttonactivity);
        status = 0;
        timeSet = 0;
        text = (TextView) findViewById(R.id.textView);
        textstr = "Press \"React\" to start";
        sfile = new FileReadWrite();
        sfile.loadFile(this);
        sfile.modeNum = 1;
        text.setText(textstr);
    }

    public void record() {
        if (sfile.sizesingle == 0) {
            sfile.singletime.add((Float)diff);
        } else {
            sfile.singletime.add(0, (Float)diff); // appends diff to the begining of the array list
        }
        sfile.saveFile(this);
    }

    // How to setup a timer
    // http://stackoverflow.com/questions/28243847/how-to-make-a-timer-in-android-studio-1-0-2
    // By: vinitius
    // 2015-09-23
    public void click(View view) {
        if (status == 1) {
            timer.cancel();
            status = 0;
            endTime = System.currentTimeMillis();
            diff = (float)(endTime - startTime);
            if (diff < (float)n) {
                textstr = "You pressed too early";
            } else {
                diff = diff - n;
                diff = (float) (diff / 1000.0);
                textstr = "Latency: " + Float.toString(diff) + "s";
                record();
            }
            textstr = textstr + "\nPress \"React\" to start a new game";
            text.setText(textstr);

        } else {
            textstr = "Game start";
            text.setText(textstr);
            status = 1;
            timer = new Timer();
            rand = new Random();
            n = rand.nextInt(1991) + 10;
            startTime = System.currentTimeMillis();
            text.setText(textstr);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            endTime = System.currentTimeMillis();
                            diff = (float)(endTime - startTime);
                            if (diff >= (float) n) {
                                cancel();
                                status = 1;
                                textstr = "click";
                                text.setText(textstr);
                            }
                        }
                    });
                }
            }, 0, 5);
        }

    }

    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        @Override
        public void run() {
        }
    };
}

