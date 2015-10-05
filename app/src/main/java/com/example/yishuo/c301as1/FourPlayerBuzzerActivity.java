package com.example.yishuo.c301as1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yishuo on 9/26/15.
 */
public class FourPlayerBuzzerActivity extends Activity{
    private String textstr;
    public TextView text;
    private int status; // 0 if not clicked; 1 if clicked
    private int plr; // who pressed first
    private int p1num;
    private int p2num;
    private int p3num;
    private int p4num;
    private int temp0;
    private int temp1;
    private int temp2;
    private int temp3;

    private FileReadWrite sfile;

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
        sfile = new FileReadWrite();
        sfile.loadFile(this);
        sfile.modeNum = 2;
        sfile.loadFile(this);

        fourPlayer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                if (status == 1){
                    p1num = p1num + 1;
                    p2num = p2num;
                    p3num = p3num;
                    p4num = p4num; // then save into file
                    textstr = "Player 1 Pressed First\nPress \"Player 1\" to start a new game";// + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num) + " p4 " + Integer.toString(p4num);
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
                    textstr = "Player 2 Pressed First\nPress \"Player 2\" to start a new game";// + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num) + " p4 " + Integer.toString(p4num);
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
                    textstr = "Player 3 Pressed First\nPress \"Player 3\" to start a new game";// + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num) + " p4 " + Integer.toString(p4num);
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
                    textstr = "Player 4 Pressed First\nPress \"Player 4\" to start a new game";// + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num) + " p3 " + Integer.toString(p3num) + " p4 " + Integer.toString(p4num);
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
        temp0 = sfile.fourPlayersTime.get(0);
        temp1 = sfile.fourPlayersTime.get(1);
        temp2 = sfile.fourPlayersTime.get(2);
        temp3 = sfile.fourPlayersTime.get(3);
        p1num = p1num + temp0;
        p2num = p2num + temp1;
        p3num = p3num + temp2;
        p4num = p4num + temp3;
        sfile.fourPlayersTime.clear();
        sfile.fourPlayersTime.add(p1num);
        sfile.fourPlayersTime.add(p2num);
        sfile.fourPlayersTime.add(p3num);
        sfile.fourPlayersTime.add(p4num);
        sfile.saveFile(this);
        p1num = 0;
        p2num = 0;
        p3num = 0;
        p4num = 0;
    }
}
