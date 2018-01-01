package com.example.elo.multiplicationmastery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;



public class QuizScored extends AppCompatActivity {

    int totalProblems = 0;
    int correctQs = 0;
    String min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_scored);

        getTheExtras();

        TextView scoreDisplay = (TextView) findViewById(R.id.tvForPer);
        String giveText = Integer.toString(correctQs)+"/"+Integer.toString(totalProblems);
        scoreDisplay.setText("Your Score:   "+giveText+""); ///cahnge here

        TextView scorePart = (TextView) findViewById(R.id.scoreDecimal);
        double corrQ = (double) correctQs;
        double totQ = (double) totalProblems;
        double decScore = (double) corrQ / totQ;
        double perScore = decScore * 100;
        perScore = Math.round(perScore);
        Log.i("rohantag", new Double(perScore).toString());
        String propForm = new Double(perScore).toString();
        //propForm = Character.toString(propForm).charAt(0);
        scorePart.setText("Percent:   "+propForm+"%");

        TextView grretTV = (TextView) findViewById(R.id.greetTV);
        String greeet = "Good Job in 1 Minutes You Scored";
        SpannableString ss = new SpannableString(greeet);
        ss.setSpan(new RelativeSizeSpan(1.25f), 0, 9, 0); //make bigger
        grretTV.setText(ss);

    }

    private void getTheExtras() {
        Bundle totExtras = getIntent().getExtras();
        totalProblems = Integer.parseInt(totExtras.getString("Total"));
        correctQs = Integer.parseInt(totExtras.getString("Correct"));
        min = totExtras.getString("Time");
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
