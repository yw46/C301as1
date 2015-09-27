package com.example.yishuo.c301as1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yishuo on 9/26/15.
 */
public class BuzzerNumPlayer extends Activity{
    public TextView text;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzernumplayer);
        text = (TextView) findViewById(R.id.buzzerNumText);
        text.setText("How many players");
        Button twoPlayers = (Button) findViewById(R.id.twoPlayers);
        Button threePlayers = (Button) findViewById(R.id.threePlayers);
        Button fourPlayers = (Button) findViewById(R.id.fourPlayers);
        twoPlayers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BuzzerNumPlayer.this, TwoPlayerBuzzerActivity.class);
                startActivity(intent);
            }
        });
        threePlayers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BuzzerNumPlayer.this, ThreePlayerBuzzerActivity.class);
                startActivity(intent);
            }
        });
        fourPlayers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BuzzerNumPlayer.this, FourPlayerBuzzerActivity.class);
                startActivity(intent);
            }
        });
    }
}
