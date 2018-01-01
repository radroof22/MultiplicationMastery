package com.example.elo.multiplicationmastery;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by mehta on 6/22/2016.
 */
public class Table extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_view);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void dispTable(View view) {

        EditText ed = (EditText) findViewById(R.id.numInpTableQ);
        String userQueryStr = ed.getText().toString();
        int userQueryValue = Integer.parseInt(userQueryStr);

        ed.setTextColor(Color.BLACK);

        String totContext = "";

        for(int i = 0; i < 26; i++){
            String strStarter = userQueryStr + " * " + Integer.toString(i) + " = ";
            int ans = i * userQueryValue;
            Log.i("JELLO",userQueryStr);
            strStarter = strStarter + Integer.toString(ans) + "\n";
            totContext = totContext + strStarter;
        }
        TextView txtToUpdate = (TextView) findViewById(R.id.tableDisplayText);
        txtToUpdate.setText(totContext);

    }
}
