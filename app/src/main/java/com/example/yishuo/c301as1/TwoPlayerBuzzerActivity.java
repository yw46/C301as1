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
public class TwoPlayerBuzzerActivity extends Activity {
    private String textstr;
    public TextView text;
    private int status; // 0 if not clicked
                          // 1 if clicked
    private int plr; // who pressed first
    private int p1num;
    private int p2num;
    private static final String FTwoPlayers = "f2.sav";
    private ArrayList<Integer> twoPlayersTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twoplayerbuzzer);
        text = (TextView) findViewById(R.id.twoPlayerText);
        text.setText("Press any button to start a new game");
        Button twoPlayer1 = (Button) findViewById(R.id.twoPlayer1);
        Button twoPlayer2 = (Button) findViewById(R.id.twoPlayer2);
        p1num = 0; // load from file
        p2num = 0;
        status = 0;
        plr = 0;
        loadFile();
        twoPlayer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                if (status == 1){
                    p1num = p1num + 1;
                    p2num = p2num; // then save into file
                    textstr = "Player 1 Pressed First\nPress \"Player 1\" to start a new game";// + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num);
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
        twoPlayer2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (status == 1){
                    p1num = p1num;
                    p2num = p2num + 1; // then save into file
                    textstr = "Player 2 Pressed First\nPress \"Player 2\" to start a new game";// + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num);
                    text.setText(textstr);
                    status = 0;
                    plr = 2;
                    record();
                } else if ((status == 0) && (plr == 2)) {
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
    }

    private void record() {
        int temp0 = twoPlayersTime.get(0);
        int temp1 = twoPlayersTime.get(1);
        p1num = p1num + temp0;
        p2num = p2num + temp1;
        twoPlayersTime.clear();
        twoPlayersTime.add(p1num);
        twoPlayersTime.add(p2num);
        saveFile();
        //textstr = textstr + "\np1 " + Integer.toString(twoPlayersTime.get(0)) + " p2 " + Integer.toString(twoPlayersTime.get(1));
        //text.setText(textstr);
        p1num = 0;
        p2num = 0;
    }

    private void loadFile() {
        try {
            FileInputStream fis = openFileInput(FTwoPlayers);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            twoPlayersTime = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            twoPlayersTime = new ArrayList<Integer>();
            twoPlayersTime.add(p1num);
            twoPlayersTime.add(p2num);
        }
    }

    private void saveFile() {
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
        //textstr = textstr + "\nFile Saved";
        //text.setText(textstr);
    }
}
