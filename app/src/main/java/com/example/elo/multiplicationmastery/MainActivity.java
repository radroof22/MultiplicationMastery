package com.example.elo.multiplicationmastery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    /*
        1. Rapid Fire- 20 Question in X Time
        2. Log of Activity
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToTable(View view) {
        //create intent referring to class
        Intent i = new Intent(this, Table.class);

        //starts activity
        startActivity(i);
    }


    public void goToQuiz(View view) {

        //create intent referring to class
        Intent i = new Intent(this, QuizHome.class);

        //starts activity
        startActivity(i);
    }
}
