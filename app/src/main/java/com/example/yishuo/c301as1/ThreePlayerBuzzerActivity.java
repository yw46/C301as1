package com.example.yishuo.c301as1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by yishuo on 9/26/15.
 */
public class ThreePlayerBuzzerActivity extends Activity {
    private String textstr;
    public TextView text;
    private int status; // 0 if not clicked; 1 if clicked
    private int plr; // who pressed first
    private int p1num;
    private int p2num;
    private int p3num;
    private int temp0;
    private int temp1;
    private int temp2;

    private FileReadWrite sfile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threeplayerbuzzer);
        text = (TextView) findViewById(R.id.threePlayerText);
        text.setText("Press any button to start a new game");
        Button threePlayer1 = (Button) findViewById(R.id.threePlayer1);
        Button threePlayer2 = (Button) findViewById(R.id.threePlayer2);
        Button threePlayer3 = (Button) findViewById(R.id.threePlayer3);
        p1num = 0; // load from file
        p2num = 0;
        p3num = 0;
        status = 0;
        plr = 0;
        sfile = new FileReadWrite();
        sfile.loadFile(this);
        sfile.modeNum = 2;
        sfile.loadFile(this);

        threePlayer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                if (status == 1) {
                    p1num = p1num + 1;
                    p2num = p2num;
                    p3num = p3num; // then save into file
                    textstr = "Player 1 Pressed First\nPress \"Player 1\" to start a new game";// + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num);
                    text.setText(textstr);
                    status = 0;
                    plr = 1;
                    record();
                } else if ((status == 0) && (plr == 1)) {
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

        threePlayer2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                if (status == 1) {
                    p1num = p1num;
                    p2num = p2num + 1;
                    p3num = p3num; // then save into file
                    textstr = "Player 2 Pressed First\nPress \"Player 2\" to start a new game";// + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num);
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

        threePlayer3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                if (status == 1) {
                    p1num = p1num;
                    p2num = p2num;
                    p3num = p3num + 1; // then save into file
                    textstr = "Player 3 Pressed First\nPress \"Player 3\" to start a new game";// + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num);
                    text.setText(textstr);
                    status = 0;
                    plr = 3;
                    record();
                } else if ((status == 0) && (plr == 3)) {
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
    }

    private void record() {
        temp0 = sfile.threePlayersTime.get(0);
        temp1 = sfile.threePlayersTime.get(1);
        temp2 = sfile.threePlayersTime.get(2);
        p1num = p1num + temp0;
        p2num = p2num + temp1;
        p3num = p3num + temp2;
        sfile.threePlayersTime.clear();
        sfile.threePlayersTime.add(p1num);
        sfile.threePlayersTime.add(p2num);
        sfile.threePlayersTime.add(p3num);
        sfile.saveFile(this);
        p1num = 0;
        p2num = 0;
        p3num = 0;
    }
}
