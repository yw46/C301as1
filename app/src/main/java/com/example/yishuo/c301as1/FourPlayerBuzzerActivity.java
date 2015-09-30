package com.example.yishuo.c301as1;

import android.app.Activity;
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
 * Created by yishuo on 9/26/15.
 */
public class FourPlayerBuzzerActivity extends Activity{
    private String textstr;
    public TextView text;
    private int status; // 0 if not clicked
    // 1 if clicked
    private int plr; // who pressed first
    private int p1num;
    private int p2num;
    private int p3num;
    private int p4num;
    private static final String FFourPlayers = "f4.sav";
    private ArrayList<Integer> fourPlayersTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourplayerbuzzer);
        text = (TextView) findViewById(R.id.fourPlayerText);
        text.setText("Press any button to start a new game");
        Button fourPlayer1 = (Button) findViewById(R.id.fourPlayer1);
        Button fourPlayer2 = (Button) findViewById(R.id.fourPlayer2);
        Button fourPlayer3 = (Button) findViewById(R.id.fourPlayer3);
        Button fourPlayer4 = (Button) findViewById(R.id.fourPlayer4);
        p1num = 0; // load from file
        p2num = 0;
        p3num = 0;
        p4num = 0;
        status = 0;
        plr = 0;
        loadFile();

        fourPlayer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                if (status == 1){
                    p1num = p1num + 1;
                    p2num = p2num;
                    p3num = p3num;
                    p4num = p4num; // then save into file
                    textstr = "Player 1 Pressed First\nPress \"Player 1\" to start a new game" + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num) + " p4 " + Integer.toString(p4num);
                    text.setText(textstr);
                    status = 0;
                    plr = 1;
                    record();
                } else if ((status == 0) && (plr == 1)){
                    status = 1;
                    textstr = "Click";
                    plr = 1;
                    text.setText(textstr);
                } else if ((status == 0) && (plr == 0)) {
                    status = 1;
                    textstr = "Click";
                    plr = 1;
                    text.setText(textstr);
                }
            }
        });

        fourPlayer2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                if (status == 1){
                    p1num = p1num;
                    p2num = p2num + 1;
                    p3num = p3num;
                    p4num = p4num; // then save into file
                    textstr = "Player 2 Pressed First\nPress \"Player 2\" to start a new game" + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num) + " p4 " + Integer.toString(p4num);
                    text.setText(textstr);
                    status = 0;
                    plr = 2;
                    record();
                } else if ((status == 0) && (plr == 2)){
                    status = 1;
                    textstr = "Click";
                    plr = 2;
                    text.setText(textstr);
                } else if ((status == 0) && (plr == 0)) {
                    status = 1;
                    textstr = "Click";
                    plr = 2;
                    text.setText(textstr);
                }
            }
        });

        fourPlayer3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                if (status == 1){
                    p1num = p1num;
                    p2num = p2num;
                    p3num = p3num + 1;
                    p4num = p4num; // then save into file
                    textstr = "Player 3 Pressed First\nPress \"Player 3\" to start a new game" + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num) + " p4 " + Integer.toString(p4num);
                    text.setText(textstr);
                    status = 0;
                    plr = 3;
                    record();
                } else if ((status == 0) && (plr == 3)){
                    status = 1;
                    textstr = "Click";
                    plr = 3;
                    text.setText(textstr);
                } else if ((status == 0) && (plr == 0)) {
                    status = 1;
                    textstr = "Click";
                    plr = 3;
                    text.setText(textstr);
                }
            }
        });

        fourPlayer4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                if (status == 1){
                    p1num = p1num;
                    p2num = p2num;
                    p3num = p3num;
                    p4num = p4num + 1; // then save into file
                    textstr = "Player 4 Pressed First\nPress \"Player 4\" to start a new game" + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num) + " p4 " + Integer.toString(p4num);
                    text.setText(textstr);
                    status = 0;
                    plr = 4;
                    record();
                } else if ((status == 0) && (plr == 4)){
                    status = 1;
                    textstr = "Click";
                    plr = 4;
                    text.setText(textstr);
                } else if ((status == 0) && (plr == 0)) {
                    status = 1;
                    textstr = "Click";
                    plr = 4;
                    text.setText(textstr);
                }
            }
        });
    }

    private void record() {
        textstr = textstr + "\nARRAYLIST SIZE " + Integer.toString(fourPlayersTime.size());
        text.setText(textstr);

        int temp0 = fourPlayersTime.get(0);
        int temp1 = fourPlayersTime.get(1);
        int temp2 = fourPlayersTime.get(2);
        int temp3 = fourPlayersTime.get(3);
        p1num = p1num + temp0;
        p2num = p2num + temp1;
        p3num = p3num + temp2;
        p4num = p4num + temp3;

        fourPlayersTime.clear();
        fourPlayersTime.add(p1num);
        fourPlayersTime.add(p2num);
        fourPlayersTime.add(p3num);
        fourPlayersTime.add(p4num);
        saveFile();

        textstr = textstr + "\np1 " + Integer.toString(fourPlayersTime.get(0)) + " p2 " + Integer.toString(fourPlayersTime.get(1)) + " p3 "  + Integer.toString(fourPlayersTime.get(2)) + " p4 " + Integer.toString(fourPlayersTime.get(3));
        text.setText(textstr);
        p1num = 0;
        p2num = 0;
        p3num = 0;
        p4num = 0;
    }

    private void loadFile() {
        try {
            FileInputStream fis = openFileInput(FFourPlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            fourPlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            fourPlayersTime = new ArrayList<Integer>();
            fourPlayersTime.add(p1num);
            fourPlayersTime.add(p2num);
            fourPlayersTime.add(p3num);
            fourPlayersTime.add(p4num);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveFile() {
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
        textstr = textstr + "\nFile Saved";
        text.setText(textstr);
    }
}
