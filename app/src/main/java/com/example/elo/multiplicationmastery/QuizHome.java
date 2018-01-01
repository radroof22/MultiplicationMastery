package com.example.elo.multiplicationmastery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by mehta on 6/22/2016.
 */
public class QuizHome extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_choice);
    }


    public void goToTarget(View view) {



            //create intent referring to class
            Intent i = new Intent(this, QuizTarget.class);



            //starts activity
            startActivity(i);


    }

    public void goToTimed(View view) {

        //create intent referring to class
        Intent i = new Intent(this, QuizTime.class);

        i.putExtra("Level","Medium");
        i.putExtra("Score", "0");

        //starts activity
        startActivity(i);
    }

    public void goToNormal(View view) {

        //create intent referring to class
        Intent i = new Intent(this, QuizNormal.class);

        i.putExtra("score","0");

        //starts activity
        startActivity(i);
    }

    public void goToRush(View view) {
        //create intent referring to class
        Intent i = new Intent(this, QuizRush.class);

        i.putExtra("Level","Medium");
        i.putExtra("Score", "0");

        //starts activity
        startActivity(i);
    }
}
