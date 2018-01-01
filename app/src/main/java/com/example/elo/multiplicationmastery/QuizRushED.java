package com.example.elo.multiplicationmastery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class QuizRushED extends AppCompatActivity {

    int totalProblems = 0;
    int correctQs = 0;
    String min;
    String bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_rushed);

        getTheExtras();

        TextView grTV = (TextView) findViewById(R.id.greetTV);
        Log.i("rohantag", bool);
        if (bool.equals("Loss")){
            grTV.setText("Nice Try");
        }else{
            grTV.setText("Good Job");
        }

        TextView scoreDisplay = (TextView) findViewById(R.id.tvForPer);
        String giveText = Integer.toString(correctQs)+"/"+Integer.toString(totalProblems);
        scoreDisplay.setText("Time: 1 Minute"); ///cahnge here

        TextView scorePart = (TextView) findViewById(R.id.scoreDecimal);

        scorePart.setText("Correct:   "+Integer.toString(correctQs)+"");


    }

    private void getTheExtras() {
        Bundle totExtras = getIntent().getExtras();
        totalProblems = Integer.parseInt(totExtras.getString("Total"));
        correctQs = Integer.parseInt(totExtras.getString("Correct"));
        min = totExtras.getString("Time");
        bool = totExtras.getString("Bool");
    }


    public void goToHomeScreen(View view) {

        Intent i = new Intent(this,MainActivity.class);

        startActivity(i);
    }

    public void retry(View view) {

        Intent i = new Intent(this,QuizTime.class);

        startActivity(i);
    }
}
