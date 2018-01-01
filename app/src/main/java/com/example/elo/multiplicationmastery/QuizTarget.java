package com.example.elo.multiplicationmastery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/*

Things to Fix:

3. Have a button to take user straight to home screen

*/

public class QuizTarget extends AppCompatActivity{
    Boolean but1Click = false;
    Boolean but2Click = false;
    Boolean but3Click = false;
    Boolean but4Click = false;
    String clickedBtn = "";
    int amntCorrect = 0;
    int totQ = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();

    }


    public String getScore(){
        Bundle scoreData = getIntent().getExtras();
        String scoreStr = scoreData.getString("score");

        return scoreStr;
    }
    public String getTot(){
        Bundle scoreData = getIntent().getExtras();
        String scoreStr = scoreData.getString("total");

        return scoreStr;
    }




    private int[] getRandomProblem() {

        int num1 = 0;
        int num2 = 0;

        //generate and return hard nums
        num1 = (int)((Math.random() * 12) + 1);
        num2 =(int)((Math.random() * 12) + 1);


        int[] nums = {num1,num2};
        return nums;

    }

    //fix rand alorgithm
    public int randomWrongNum(int first, int sec){
        int ans = first * sec;
        int wrongMult = (ans-10) + (int)(Math.random() * (ans+10)); ;
        int wrongNum = wrongMult;
        return wrongNum;
    }

    private void setUp() {
        setContentView(R.layout.quiz_norm);


        Button tv1 = (Button) findViewById(R.id.choice1);
        Button tv2 = (Button) findViewById(R.id.choice2);
        Button tv3 = (Button) findViewById(R.id.choice3);
        Button tv4 = (Button) findViewById(R.id.choice4);
        TextView probText = (TextView) findViewById(R.id.probleText);
        TextView scTv = (TextView) findViewById(R.id.scoreTVH);

        int scoreint;
        int tempTot;
        try{
            scoreint = Integer.parseInt(getScore());
            tempTot = Integer.parseInt(getTot());
        }catch( Exception e){
            scoreint = amntCorrect;
            tempTot = totQ;
        }
        // cs / 3
        scTv.setText(Integer.toString(scoreint)+"/"+Integer.toString(tempTot));
        amntCorrect=scoreint;
        totQ = tempTot;


        List<Button> btns = new ArrayList<Button>();
        btns.add(tv1);
        btns.add(tv2);
        btns.add(tv3);
        btns.add(tv4);





        int[] probNums = getRandomProblem();
        String realAns = Integer.toString(probNums[0] * probNums[1]);
        List<Integer> badNums = new ArrayList<Integer>();


        for (int i = 0;i<3;i++){
            int notActualNum = randomWrongNum(probNums[0], probNums[1]);
            badNums.add(notActualNum);
        }
        badNums.add(parseInt(realAns));



        int cBTNS = 3;
        while (indexOf(btns) > 0){



            // get random num according to index of btns list
            double indexSF =  Math.random()*indexOf(btns);
            int iSf = (int) Math.round(indexSF) ;

            if (indexSF <= iSf){
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
        for (Button looper : btns){

            i++;
        }
        return i;
    }


    public void but4Click(View view) {
        but4Click=true;
        clickedBtn = "4";
        gameCheck();
    }

    public void but3Click(View view) {
        but3Click=true;
        clickedBtn = "3";
        gameCheck();
    }

    public void but2Click(View view) {
        but2Click=true;
        clickedBtn = "2";
        gameCheck();
    }

    public void but1Click(View view) {
        but1Click=true;
        clickedBtn = "1";
        gameCheck();
    }

    public void gameCheck(){
        Button tv1 = (Button) findViewById(R.id.choice1);
        Button tv2 = (Button) findViewById(R.id.choice2);
        Button tv3 = (Button) findViewById(R.id.choice3);
        Button tv4 = (Button) findViewById(R.id.choice4);
        TextView probText = (TextView) findViewById(R.id.probleText);

        TextView QTV = (TextView) findViewById(R.id.probleText);
        String[] QTVsplit = QTV.getText().toString().split(" ");
        int realAns = parseInt(QTVsplit[0]) * parseInt(QTVsplit[2]);

        int userChoice = 0;

        if (but4Click == true){
            String btnTxt = tv4.getText().toString();
            userChoice = parseInt(btnTxt);
        }
        if (but3Click == true){
            String btnTxt = tv3.getText().toString();
            userChoice = parseInt(btnTxt);
        }
        if (but2Click == true){
            String btnTxt = tv2.getText().toString();
            userChoice = parseInt(btnTxt);
        }
        if (but1Click == true){
            String btnTxt = tv1.getText().toString();
            userChoice = parseInt(btnTxt);
        }

        Log.i("rohantag",Boolean.toString(userChoice == realAns));


        if (userChoice != realAns){
            wrongAns();
        }else{
            correctAns();
        }



    }

    private void correctAns() {

        if (clickedBtn == "1") {
            Button btn = (Button) findViewById(R.id.choice1);
            btn.setBackgroundColor(Color.GREEN);
        }
        if ( clickedBtn == "2") {
            Button btn = (Button) findViewById(R.id.choice2);
            btn.setBackgroundColor(Color.GREEN);
        }
        if ( clickedBtn == "3"){
            Button btn = (Button) findViewById(R.id.choice3);
            btn.setBackgroundColor(Color.GREEN);
        }
        if ( clickedBtn == "4"){
            Button btn = (Button) findViewById(R.id.choice4);
            btn.setBackgroundColor(Color.GREEN);
        }

        amntCorrect = amntCorrect + 1;

        Log.i("rohantag", Integer.toString(amntCorrect));

        try {
            Thread.sleep(500);

            //1000 milliseconds is one second.

            Intent i = new Intent(this, QuizTarget.class);


            i.putExtra("score",Integer.toString(amntCorrect));
            i.putExtra("total",Integer.toString(totQ));
            //starts activity
            startActivity(i);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }


    }

    private void wrongAns() {

        if (clickedBtn == "1") {
            Button btn = (Button) findViewById(R.id.choice1);
            btn.setBackgroundColor(Color.RED);
        }
        if ( clickedBtn == "2") {
            Button btn = (Button) findViewById(R.id.choice2);
            btn.setBackgroundColor(Color.RED);
        }
        if ( clickedBtn == "3"){
            Button btn = (Button) findViewById(R.id.choice3);
            btn.setBackgroundColor(Color.RED);
        }
        if ( clickedBtn == "4"){
            Button btn = (Button) findViewById(R.id.choice4);
            btn.setBackgroundColor(Color.RED);
        }
        Log.i("rohantag","Inc");

        try {
            Thread.sleep(500);                 //1000 milliseconds is one second.
            Intent i = new Intent(this, QuizTarget.class);


            i.putExtra("total",Integer.toString(totQ));

            i.putExtra("score",Integer.toString(amntCorrect));
            //starts activity
            startActivity(i);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }

    public void goHome(View view) {
        Intent i = new Intent(this, MainActivity.class);



        //starts activity
        startActivity(i);
    }
}
