package com.samsung.speedchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimeResultActivity extends AppCompatActivity {
    String result;
    String time;
    TextView resultTextView;
    TextView timeTextView;
    Button again;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_result);
        Intent intent = getIntent();
        result = intent.getStringExtra("result");
        time = intent.getStringExtra("time");
        resultTextView = findViewById(R.id.resultTextView);
        timeTextView = findViewById(R.id.timeTextView);

        resultTextView.setText(result);
        timeTextView.setText("Your time " + time);
        again = findViewById(R.id.againButton);
        again.setBackgroundColor(Color.parseColor("#f0c4b6"));

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}