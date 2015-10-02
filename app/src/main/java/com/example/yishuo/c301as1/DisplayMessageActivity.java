package com.example.yishuo.c301as1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yishuo on 9/23/15.
 */
public class DisplayMessageActivity extends Activity{
    private String textstr;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaymessageactivity);
        Button displayDismiss = (Button) findViewById(R.id.displayDismiss);
        TextView text = (TextView) findViewById(R.id.displayText);
        textstr = "    Test your reaction time by clicking the \"React\" button when \"Click\" is shown on the top.";
        textstr = textstr + "\n    The game waits between 10ms to 2000ms before showing the \"Click\" message.";
        textstr = textstr + " If you click too early then a message \"You pressed too early\" is shown on the top.";
        textstr = textstr + " After you clicking the \"React\" button, the latency between the end of the wait";
        textstr = textstr + " and when you clicked is shown to you and also get recorded.After that, click the";
        textstr = textstr + " \"Reaction\" button to start a new game, or hit the \"Back\"to go back.";
        textstr = textstr + "\n    Enjoy.";
        text.setText(textstr);

        displayDismiss.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMessageActivity.this, SingleButtonActivity.class);
                startActivity(intent);
            }
        });
    }
}
