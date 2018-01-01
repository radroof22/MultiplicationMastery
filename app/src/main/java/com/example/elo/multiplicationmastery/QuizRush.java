package com.example.elo.multiplicationmastery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;



public class QuizRush extends AppCompatActivity {
    Boolean but1Click = false;
    Boolean but2Click = false;
    Boolean but3Click = false;
    Boolean but4Click = false;
    String clickedBtn = "";
    int amntCorrect = 0;
    int totQ = 0;
    long startTime;
    long elapsedTime;
    int minutes;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startTime = System.currentTimeMillis();
        setUp();
        elapsedTime = 0L;


    }


    public String getScore() {
        Bundle scoreData = getIntent().getExtras();
        String min = scoreData.getString("Time");
        Log.i("rohantag","TIME:"+min);
        String scoreStr = scoreData.getString("score");

        return scoreStr;
    }

    public String getTot() {
        Bundle scoreData = getIntent().getExtras();
        String scoreStr = scoreData.getString("total");

        return scoreStr;
    }


    private int[] getRandomProblem() {

        int num1 = 0;
        int num2 = 0;

        //generate and return hard nums
        num1 = (int) ((Math.random() * 12) + 1);
        num2 = (int) ((Math.random() * 12) + 1);


        int[] nums = {num1, num2};
        return nums;

    }

    //fix rand alorgithm
    public int randomWrongNum(int first, int sec) {
        int ans = first * sec;
        int wrongMult = (ans - 10) + (int) (Math.random() * (ans + 10));
        ;
        int wrongNum = wrongMult;
        return wrongNum;
    }

    private void setUp() {
        setContentView(R.layout.quiz_min);

        Log.i("rohantag",Integer.toString(amntCorrect));
        Log.i("rohantag",Integer.toString(totQ));

        //min,sec/min,milli/sec
        while (elapsedTime >= 1*60*1000){
            Log.i("rohantag", "***********");
            Log.i("rohantag",Integer.toString(amntCorrect));
            Log.i("rohantag",Integer.toString(totQ));


            Intent i = new Intent(this,QuizRushED.class);
            i.putExtra("Correct",Integer.toString(amntCorrect));
            i.putExtra("Total",Integer.toString(totQ));
            i.putExtra("Time",Integer.toString(minutes));
            i.putExtra("Bool","Loss");
            startActivity(i);
            catc();
            break;
        }

        //check if student has finished already
        while (amntCorrect >= 15){
            //go to screen w/ win
            Intent i = new Intent(this,QuizRushED.class);
            i.putExtra("Correct",Integer.toString(amntCorrect));
            i.putExtra("Total",Integer.toString(totQ));
            i.putExtra("Time",Integer.toString(minutes));
            i.putExtra("Bool","Win");
            startActivity(i);
            catc();
            break;
        }
        elapsedTime = (new Date()).getTime() - startTime;

        Button tv1 = (Button) findViewById(R.id.choice1);
        Button tv2 = (Button) findViewById(R.id.choice2);
        Button tv3 = (Button) findViewById(R.id.choice3);
        Button tv4 = (Button) findViewById(R.id.choice4);
        TextView probText = (TextView) findViewById(R.id.probleText);

        int scoreint;
        int tempTot;
        try {
            scoreint = Integer.parseInt(getScore());
            tempTot = Integer.parseInt(getTot());
        } catch (Exception e) {
            scoreint = amntCorrect;
            tempTot = totQ;
        }
        // cs / 3
        amntCorrect = scoreint;
        totQ = tempTot;


        List<Button> btns = new ArrayList<Button>();
        btns.add(tv1);
        btns.add(tv2);
        btns.add(tv3);
        btns.add(tv4);


        int[] probNums = getRandomProblem();
        String realAns = Integer.toString(probNums[0] * probNums[1]);
        List<Integer> badNums = new ArrayList<Integer>();


        for (int i = 0; i < 3; i++) {
            int notActualNum = randomWrongNum(probNums[0], probNums[1]);
            badNums.add(notActualNum);
        }
        badNums.add(parseInt(realAns));


        int cBTNS = 3;
        while (indexOf(btns) > 0) {


            // get random num according to index of btns list
            double indexSF = Math.random() * indexOf(btns);
            int iSf = (int) Math.round(indexSF);

            if (indexSF <= iSf) {
                iSf--;
            }

            //assign num
            btns.get(cBTNS).setText(badNums.get(iSf).toString());
            //remove used num and button
            badNums.remove(iSf);
            btns.remove(cBTNS);
            cBTNS--;

        }

        probText.setText(probNums[0] + " * " + probNums[1]);
        totQ = totQ + 1;
    }


    private int indexOf(List<Button> btns) {
        int i = 0;
        for (Button looper : btns) {

            i++;
        }
        return i;
    }


    public void but4Click(View view) {
        but4Click = true;
        clickedBtn = "4";
        gameCheck();
    }

    public void but3Click(View view) {
        but3Click = true;
        clickedBtn = "3";
        gameCheck();
    }

    public void but2Click(View view) {
        but2Click = true;
        clickedBtn = "2";
        gameCheck();
    }

    public void but1Click(View view) {
        but1Click = true;
        clickedBtn = "1";
        gameCheck();
    }

    public void gameCheck() {
        Log.i("rohantag","GAME check");
        Button tv1 = (Button) findViewById(R.id.choice1);
        Button tv2 = (Button) findViewById(R.id.choice2);
        Button tv3 = (Button) findViewById(R.id.choice3);
        Button tv4 = (Button) findViewById(R.id.choice4);

        TextView QTV = (TextView) findViewById(R.id.probleText);
        String[] QTVsplit = QTV.getText().toString().split(" ");
        Log.i("rohantag", QTV.getText().toString());
        int realAns = Integer.parseInt(QTVsplit[0]) * Integer.parseInt(QTVsplit[2]);

        int userChoice = 0;

        if (clickedBtn == "4") {
            String btnTxt = tv4.getText().toString();
            userChoice = Integer.parseInt(btnTxt);
        }
        else if (clickedBtn == "3") {
            String btnTxt = tv3.getText().toString();
            userChoice = Integer.parseInt(btnTxt);
        }
        else if (clickedBtn == "2") {
            String btnTxt = tv2.getText().toString();
            userChoice = Integer.parseInt(btnTxt);
        }
        else if(clickedBtn == "1"){
            String btnTxt = tv1.getText().toString();
            userChoice = Integer.parseInt(btnTxt);
        }

        Log.i("rohantag",Integer.toString(userChoice)+" )( " + Integer.toString(realAns));

        if (userChoice == realAns) {
            correctAns();
        } else {
            wrongAns();
        }


    }

    private void correctAns() {
        Log.i("rohantag","correct");
        Log.i("score", Integer.toString(amntCorrect+1));
        Log.i("total", Integer.toString(totQ));
        if (clickedBtn == "1") {
            Button btn = (Button) findViewById(R.id.choice1);
            btn.setBackgroundColor(Color.GREEN);
        }
        if (clickedBtn == "2") {
            Button btn = (Button) findViewById(R.id.choice2);
            btn.setBackgroundColor(Color.GREEN);
        }
        if (clickedBtn == "3") {
            Button btn = (Button) findViewById(R.id.choice3);
            btn.setBackgroundColor(Color.GREEN);
        }
        if (clickedBtn == "4") {
            Button btn = (Button) findViewById(R.id.choice4);
            btn.setBackgroundColor(Color.GREEN);
        }

        amntCorrect = amntCorrect + 1;


        try {
            Thread.sleep(500);

            //1000 milliseconds is one second.

            setUp();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }


    }

    private void wrongAns() {
        Log.i("score", Integer.toString(amntCorrect));
        Log.i("total", Integer.toString(totQ));
        if (clickedBtn == "1") {
            Button btn = (Button) findViewById(R.id.choice1);
            btn.setBackgroundColor(Color.RED);
        }
        if (clickedBtn == "2") {
            Button btn = (Button) findViewById(R.id.choice2);
            btn.setBackgroundColor(Color.RED);
        }
        if (clickedBtn == "3") {
            Button btn = (Button) findViewById(R.id.choice3);
            btn.setBackgroundColor(Color.RED);
        }
        if (clickedBtn == "4") {
            Button btn = (Button) findViewById(R.id.choice4);
            btn.setBackgroundColor(Color.RED);
        }

        try {
            Thread.sleep(500);                 //1000 milliseconds is one second.

            //starts activity
            //startActivity(i);
            setUp();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }

    public void goHome(View view) {
        Intent i = new Intent(this, MainActivity.class);


        //starts activity
        startActivity(i);
    }

    public void catc(){

    }

}
