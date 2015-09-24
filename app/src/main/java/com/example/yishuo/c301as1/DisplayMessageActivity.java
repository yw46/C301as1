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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaymessageactivity);
        Button displayDismiss = (Button) findViewById(R.id.displayDismiss);
        TextView text = (TextView) findViewById(R.id.displayText);
        text.setText("Yolo");

        displayDismiss.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMessageActivity.this, SingleButtonActivity.class);
                startActivity(intent);
            }
        });
    }
}
