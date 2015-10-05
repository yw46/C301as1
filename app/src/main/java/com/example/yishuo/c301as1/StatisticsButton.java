package com.example.yishuo.c301as1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

/**
 * Created by yishuo on 9/23/15.
 */

/*
    Resource:
    How to send an email
    http://stackoverflow.com/questions/28546703/how-to-code-using-android-studio-to-send-an-email,
    ɥʇᴉɾuɐɹ
    2015-09-28
 */
public class StatisticsButton extends Activity {
    private String textstr;
    public TextView text;
    private String message;

    // for statistics use
    float min = 0, min10 = 0, min100 = 0;
    float max = 0, max10 = 0, max100 = 0;
    float avg = 0, avg10 = 0, avg100 = 0;
    float median = 0, median10 = 0, median100 = 0;
    float sum = 0;
    int i;
    int n;
    float temp;

    private FileReadWrite sfile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button emailButton = (Button) findViewById(R.id.emailButton);
        text = (TextView) findViewById(R.id.statisticText);
        sfile = new FileReadWrite();
        sfile.loadFile(this);
        showRecord();
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearFile();
                showRecord();
            }
        });
        emailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private float getmin(int s) {
        temp = sfile.singletime.get(0);
        if (s > n) {
            s = n;
        }
        for (i = 1; i < s; i++) {
            if (temp > sfile.singletime.get(i)) {
                temp = sfile.singletime.get(i);
            }
        }
        return temp;
    }

    private float getmax(int s) {
        temp = sfile.singletime.get(0);
        if (s > n) {
            s = n;
        }
        for (i = 1; i < s; i++) {
            if (temp < sfile.singletime.get(i)) {
                temp = sfile.singletime.get(i);
            }
        }
        return temp;
    }

    private float getavg(int s) {
        sum = 0; // initialize to zero
        if (s > n) {
            s = n;
        }
        for (i = 0; i < s; i++) {
            sum = sum + sfile.singletime.get(i);
        }
        temp = sum / s;
        return temp;
    }

    private float getmedian(int s) {
        if (s > n) {
            s = n;
        }
        float[] ary = new float[s];
        // put all elements into the array
        for (i = 0; i < s; i++) {
            ary[i] = sfile.singletime.get(i);
        }
        // now sort the array
        Arrays.sort(ary);
        int mid = s / 2;
        if (ary.length % 2 == 1) {
            temp = ary[mid];
        } else {
            temp = (ary[mid - 1] + ary[mid])/2;
        }
        return temp;
    }

    private void showRecord() {
        n = sfile.sizesingle;
        if (n == 0) {
            // all zeros
            min = 0;
            min10 = 0;
            min100 = 0;
            max = 0;
            max10 = 0;
            max100 = 0;
            avg = 0;
            avg10 = 0;
            avg100 = 0;
            median = 0;
            median10 = 0;
            median100 = 0;
        } else {
            min = getmin(n);
            min10 = getmin(10);
            min100 = getmin(100);
            max = getmax(n);
            max10 = getmax(10);
            max100 = getmax(100);
            avg = getavg(n);
            avg10 = getavg(10);
            avg100 = getavg(100);
            median = getmedian(n);
            median10 = getmedian(10);
            median100 = getmedian(100);
        }
        textstr = "- Reaction Time Statistics" + "\n  - Minimum time of all times>" + Float.toString(min) + "\n  - Minimum time of last 10 times>" + Float.toString(min10) + "\n  - Minimum time of last 100 times>" + Float.toString(min100);
        textstr = textstr + "\n  ----------------------------------------------------------------";
        textstr = textstr + "\n  - Maximum time of all times>" + Float.toString(max) + "\n  - Maximum time of last 10 times>" + Float.toString(max10) + "\n  - Maximum time of last 100 times>" + Float.toString(max100);
        textstr = textstr + "\n  ----------------------------------------------------------------";
        textstr = textstr + "\n  - Average time of all times>" + Float.toString(avg) + "\n  - Average time of last 10 times>" + Float.toString(avg10) + "\n  - Average time of last 100 times>" + Float.toString(avg100);
        textstr = textstr + "\n  ----------------------------------------------------------------";
        textstr = textstr + "\n  - Median time of all times>" + Float.toString(median) + "\n  - Median time of last 10 times>" + Float.toString(median10) + "\n  - Median time of last 100 times>" + Float.toString(median100);
        textstr = textstr + "\n\n- Buzzer Counts";
        textstr = textstr + "\n  ----------------------------------------------------------------";
        textstr = textstr + "\n  - 2 player: Player 1>" + Integer.toString(sfile.twoPlayersTime.get(0)) + "    Player 2>" + Integer.toString(sfile.twoPlayersTime.get(1));
        textstr = textstr + "\n  ----------------------------------------------------------------";
        textstr = textstr + "\n  - 3 player: Player 1>" + Integer.toString(sfile.threePlayersTime.get(0)) + "    Player 2>" + Integer.toString(sfile.threePlayersTime.get(1));
        textstr = textstr + "\n                     Player 3>" + Integer.toString(sfile.threePlayersTime.get(2));
        textstr = textstr + "\n  ----------------------------------------------------------------";
        textstr = textstr + "\n  - 4 player: Player 1>" + Integer.toString(sfile.fourPlayersTime.get(0)) + "    Player 2>" + Integer.toString(sfile.fourPlayersTime.get(1));
        textstr = textstr + "\n                     Player 3>" + Integer.toString(sfile.fourPlayersTime.get(2)) + "    Player 4>" + Integer.toString(sfile.fourPlayersTime.get(3));
        text.setText(textstr);
        message = textstr;
    }


    public void sendEmail(){
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        }catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(StatisticsButton.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFile() {
        sfile.clearFile(this);
    }
}


