package com.samsung.speedchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    Animation animShake;
    Queue<Integer> numbers;
    Button start;
    TextView question;
    LinearLayout linearLayout;
    Button qButton1;
    Button qButton2;
    Button qButton3;
    Button qButton4;
    TextView statistic;
    int wrongCount = 0;
    int rightCount = 0;
    int sum;
    private static final Integer COUNT = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        numbers = new LinkedList<>();
        qButton1 = findViewById(R.id.qButton1);
        qButton2 = findViewById(R.id.qButton2);
        qButton3 = findViewById(R.id.qButton3);
        qButton4 = findViewById(R.id.qButton4);
        statistic = findViewById(R.id.statisticTestView);
        start = findViewById(R.id.startButton);
        start.setBackgroundColor(Color.parseColor("#f0c4b6"));
        question = findViewById(R.id.questionTextView);
        linearLayout = findViewById(R.id.buttonsLinearLayout);
        statistic.setText("Wrong 0, right 0");
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(8);
        qButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickQButton(qButton1);
            }
        });
        qButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickQButton(qButton2);
            }
        });
        qButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickQButton(qButton3);
            }
        });
        qButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickQButton(qButton4);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateNumber();
                setStandardColor();
                start.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                statistic.setVisibility(View.VISIBLE);
                question.setText(getQuestion());
                progressBar.setVisibility(View.VISIBLE);
                logic();

            }
        });







    }
    private String getQuestion(){

        int n1 = numbers.poll();
        int n2 = numbers.poll();
        String str = String.valueOf(n1) + " + " + String.valueOf(n2) ;
        sum = n1 + n2;
        qButton3.setText(String.valueOf(sum));
        Log.i("Sum", String.valueOf(sum));
        return str;
    }

   private void generateNumber(){
       for (int i = 0; i < COUNT; i++) {
           double a = Math.random() * 89+10;
           numbers.add((int) a);
       }
   }

   private void logic(){
        //String sumStr = String.valueOf(sum);
        qButton1.setText(String.valueOf(sum+1));
        qButton2.setText(String.valueOf(sum-1));
        qButton4.setText(String.valueOf(sum+5));
        setStandardColor();


        //question.setText(getQuestion());
   }

   private void clickQButton(Button button){
        button.startAnimation(animShake);
        progressStatus +=1;
        progressBar.setProgress(progressStatus);
        try {
            question.setText(getQuestion());

            if ( Integer.parseInt(button.getText().toString()) == sum){
                getQuestion();
                logic();
                rightCount += 1;
                button.setBackgroundColor(Color.parseColor("#2ded60"));

            }else {
                getQuestion();
                logic();
                wrongCount += 1;
                button.setBackgroundColor(Color.parseColor("#ed5a2d"));

            }
            Log.i("AAAAA", button.getText().toString());
            statistic.setText("Wrong " + String.valueOf(wrongCount) + ", right " + String.valueOf(rightCount));


        }catch (Exception e){
            Intent intent = new Intent(this, TimeResultActivity.class);
            intent.putExtra("result","Wrong " + String.valueOf(wrongCount) + ", right " + String.valueOf(rightCount));
            intent.putExtra("time", "0");
            startActivity(intent);
        }

   }


   private void setStandardColor(){
       qButton1.setBackgroundColor(Color.parseColor("#f0c4b6"));
       qButton2.setBackgroundColor(Color.parseColor("#f0c4b6"));
       qButton3.setBackgroundColor(Color.parseColor("#f0c4b6"));
       qButton4.setBackgroundColor(Color.parseColor("#f0c4b6"));
   }



   //numbre.poll();

}