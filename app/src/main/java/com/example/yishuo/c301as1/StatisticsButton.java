package com.example.yishuo.c301as1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
import java.util.Arrays;

/**
 * Created by yishuo on 9/23/15.
 */
public class StatisticsButton extends Activity {
    private ArrayList<Float> singletime;
    private ArrayList<Integer> twoPlayersTime;
    private ArrayList<Integer> threePlayersTime;
    private ArrayList<Integer> fourPlayersTime;

    private static final String FSINGLE = "fsingle.sav";
    private static final String FTwoPlayers = "f2.sav";
    private static final String FThreePlayers = "f3.sav";
    private static final String FFourPlayers = "f4.sav";

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
    int n10 = 0, n100 = 0;
    float temp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button emailButton = (Button) findViewById(R.id.emailButton);
        text = (TextView) findViewById(R.id.statisticText);
        singletime = new ArrayList<Float>();
        twoPlayersTime = new ArrayList<Integer>();
        threePlayersTime = new ArrayList<Integer>();
        fourPlayersTime = new ArrayList<Integer>();
        loadFile();
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
        temp = singletime.get(0);
        if (s > n) {
            s = n;
        }
        for (i = 1; i < s; i++) {
            if (temp > singletime.get(i)) {
                temp = singletime.get(i);
            }
        }
        return temp;
    }

    private float getmax(int s) {
        temp = singletime.get(0);
        if (s > n) {
            s = n;
        }
        for (i = 1; i < s; i++) {
            if (temp < singletime.get(i)) {
                temp = singletime.get(i);
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
            sum = sum + singletime.get(i);
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
            ary[i] = singletime.get(i);
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
        n = singletime.size();
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
        textstr = textstr + "\n  - 2 player: Player 1>" + Integer.toString(twoPlayersTime.get(0)) + "    Player 2>" + Integer.toString(twoPlayersTime.get(1));
        textstr = textstr + "\n  ----------------------------------------------------------------";
        textstr = textstr + "\n  - 3 player: Player 1>" + Integer.toString(threePlayersTime.get(0)) + "    Player 2>" + Integer.toString(threePlayersTime.get(1));
        textstr = textstr + "\n                     Player 3>" + Integer.toString(threePlayersTime.get(2));
        textstr = textstr + "\n  ----------------------------------------------------------------";
        textstr = textstr + "\n  - 4 player: Player 1>" + Integer.toString(fourPlayersTime.get(0)) + "    Player 2>" + Integer.toString(fourPlayersTime.get(1));
        textstr = textstr + "\n                     Player 3>" + Integer.toString(fourPlayersTime.get(2)) + "    Player 4>" + Integer.toString(fourPlayersTime.get(3));
        text.setText(textstr);
        message = textstr;
    }

    // http://stackoverflow.com/questions/28546703/how-to-code-using-android-studio-to-send-an-email
    // ɥʇᴉɾuɐɹ

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
        /*
        float min = 0, min10 = 0, min100 = 0;
        float max = 0, max10 = 0, max100 = 0;
        float avg = 0, avg10 = 0, avg100 = 0;
        float median = 0, median10 = 0, median100 = 0;
        float sum = 0;
        int i;
        int n = singletime.size();
        int n10 = 0, n100 = 0;
        float temp;

        if (n != 0) {
            float [] singlearr = new float [n];
            min = singletime.get(0);
            min10 = min;
            min100 = min;

            for (i = 0; i < n; i++) {
                if (n10 < 10) {
                    n10++;
                }
                if (n100 < 100) {
                    n100++;
                }
                // update min, max, sum for all time, add element to the array
                temp = singletime.get(i);
                sum = sum + temp;
                singlearr[i] = temp;
                if (min > temp) {
                    min = temp;
                }
                if (max < temp) {
                    max = temp;
                }
                // update min10, max10, avg10
                if (i < 10) {
                    avg10 = sum / n10;
                    if (min10 > temp) {
                        min10 = temp;
                    }
                    if (max10 < temp) {
                        max10 = temp;
                    }
                }
                // update min100, max100, avg100
                if (i < 100) {
                    avg100 = sum / n100;
                    if (min100 > temp) {
                        min100 = temp;
                    }
                    if (max100 < temp) {
                        max100 = temp;
                    }
                }
            }
            avg = sum / n;
        }



        textstr = "Min>" + Float.toString(min) + "\nMin10>" + Float.toString(min10) + "\nMin100>" + Float.toString(min100);
        textstr = textstr + "\nMax>" + Float.toString(max) + "\nMax10>" + Float.toString(max10) + "\nMax100>" + Float.toString(max100);
        textstr = textstr + "\nAvg>" + Float.toString(avg) + "\nAvg10>" + Float.toString(avg10) + "\nAvg100>" + Float.toString(avg100);
        text.setText(textstr);
        */

    private void clearFile() {
        // clear the ArrayList and save it to the corresponding file
        int i;
        singletime = new ArrayList<Float>();
        twoPlayersTime = new ArrayList<Integer>();
        for (i = 0; i < 2; i++) {
            twoPlayersTime.add(0);
        }
        threePlayersTime = new ArrayList<Integer>();
        for (i = 0; i < 3; i++) {
            threePlayersTime.add(0);
        }
        fourPlayersTime = new ArrayList<Integer>();
        for(i = 0; i < 4; i++) {
            fourPlayersTime.add(0);
        }
        saveFile();
    }

    private void saveFile() {
        // single player
        try {
            FileOutputStream fos = openFileOutput(FSINGLE, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(singletime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // two players
        try {
            FileOutputStream fos = openFileOutput(FTwoPlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(twoPlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // three players
        try {
            FileOutputStream fos = openFileOutput(FThreePlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(threePlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // four players
        try {
            FileOutputStream fos = openFileOutput(FFourPlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(fourPlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void loadFile() {
        // single player
        try {
            FileInputStream fis = openFileInput(FSINGLE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Float>>() {}.getType();
            singletime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            singletime = new ArrayList<Float>();
        }

        // two players
        try {
            FileInputStream fis = openFileInput(FTwoPlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            twoPlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            twoPlayersTime = new ArrayList<Integer>();
            twoPlayersTime.add(0);
            twoPlayersTime.add(0);
        }

        // three palyers
        try {
            FileInputStream fis = openFileInput(FThreePlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            threePlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            threePlayersTime = new ArrayList<Integer>();
            threePlayersTime.add(0);
            threePlayersTime.add(0);
            threePlayersTime.add(0);
        }

        // fourplayers
        try {
            FileInputStream fis = openFileInput(FFourPlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            fourPlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            fourPlayersTime = new ArrayList<Integer>();
            fourPlayersTime.add(0);
            fourPlayersTime.add(0);
            fourPlayersTime.add(0);
            fourPlayersTime.add(0);
        }
    }
}

/*
public class StatisticsButton extends Activity {
    private ArrayList<Float> singletime;
    private ArrayList<Integer> twoPlayersTime;
    private ArrayList<Integer> threePlayersTime;
    private ArrayList<Integer> fourPlayersTime;

    private static final String FSINGLE = "fsingle.sav";
    private static final String FTwoPlayers = "f2.sav";
    private static final String FThreePlayers = "f3.sav";
    private static final String FFourPlayers = "f4.sav";

    private String textstr;
    public TextView text;

    int num;
    float min;
    float min10;
    float min100;
    float max;
    float max10;
    float max100;
    float avg;
    float avg10;
    float avg100;
    float median;
    float median10;
    float median100;
    float sum;
    float sum10;
    float sum100;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button emailButton = (Button) findViewById(R.id.emailButton);
        text = (TextView) findViewById(R.id.statisticText);
        loadFile();
        showRecord();

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //clearFile();
                showRecord();
            }
        });
    }

    public void showRecord() {
        textstr = "Reaction Time Statistics\nminium of all time: ";

        text.setText(textstr);

    }

    private void loadFile() {
        // single player
        try {
            FileInputStream fis = openFileInput(FSINGLE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            singletime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            singletime = new ArrayList<Float>();

            //////
            float temp = 0;
            singletime.add(temp);
            //////
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 2 players buzzer mode
        try {
            FileInputStream fis = openFileInput(FTwoPlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            twoPlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            twoPlayersTime = new ArrayList<Integer>();
            twoPlayersTime.add(0);
            twoPlayersTime.add(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 3 players buzzer mode
        try {
            FileInputStream fis = openFileInput(FThreePlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            threePlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            threePlayersTime = new ArrayList<Integer>();
            threePlayersTime.add(0);
            threePlayersTime.add(0);
            threePlayersTime.add(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 4 players buzzer mode
        try {
            FileInputStream fis = openFileInput(FFourPlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            fourPlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            fourPlayersTime = new ArrayList<Integer>();
            fourPlayersTime.add(0);
            fourPlayersTime.add(0);
            fourPlayersTime.add(0);
            fourPlayersTime.add(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void saveFile() {
        // Single Player
        try {
            FileOutputStream fos = openFileOutput(FSINGLE, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(singletime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Two Players
        try {
            FileOutputStream fos = openFileOutput(FTwoPlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(twoPlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Three Players
        try {
            FileOutputStream fos = openFileOutput(FThreePlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(threePlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Four Players
        try {
            FileOutputStream fos = openFileOutput(FFourPlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(fourPlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearFile() {
        singletime.clear();
        twoPlayersTime.clear();
        threePlayersTime.clear();
        fourPlayersTime.clear();
        saveFile();
        showRecord();
    }
}
*/
    /*
    public void clearFile() {
        // clear single player file
        try {
            FileOutputStream fos = openFileOutput(FSINGLE, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(singletime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // clear 2 players buzzer mode file
        try {
            FileOutputStream fos = openFileOutput(FTwoPlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(twoPlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // clear 3 players buzzer mode file
        try {
            FileOutputStream fos = openFileOutput(FThreePlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(threePlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // clear 4 players buzzer mode file
        try {
            FileOutputStream fos = openFileOutput(FFourPlayers, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(fourPlayersTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    */

