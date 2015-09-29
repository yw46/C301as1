package com.example.yishuo.c301as1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        twoPlayer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                if (status == 1){
                    p1num = p1num + 1;
                    p2num = p2num; // then save into file
                    textstr = "Player 1 Pressed First\nPress \"Player 1\" to start a new game" + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num);
                    text.setText(textstr);
                    status = 0;
                    plr = 1;
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
                    textstr = "Player 2 Pressed First\nPress \"Player 2\" to start a new game" + "\np1 " + Integer.toString(p1num) + " p2 " + Integer.toString(p2num);
                    text.setText(textstr);
                    status = 0;
                    plr = 2;
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
}
