package com.example.yishuo.c301as1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by yishuo on 9/23/15.
 */
public class StatisticsButton extends Activity{
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
        loadFile();
        showRecord();

        clearButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                clearFile();
                showRecord();
            }
        });
    }

    public void showRecord() {
        textstr = "Reaction Time Statistics\nminium of all time: ";
        int i;




        text.setText(textstr);

    }

    private void loadFile() {
        // single player
        try {
            FileInputStream fis = openFileInput(FSINGLE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            singletime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            singletime = new ArrayList<Float>();
            float temp = 0;
            singletime.add(temp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 2 players buzzer mode
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 3 players buzzer mode
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 4 players buzzer mode
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


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
}
