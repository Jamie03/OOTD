package com.example.pat4008.ootd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bAdd;
    Button bDelete;
    Button bOOTD;
    Button bView;
    Button CheckDB;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bAdd = (Button) findViewById(R.id.button_addClothes);
        bDelete = (Button) findViewById(R.id.button_deleteClothes);
        bOOTD = (Button) findViewById(R.id.OOTD);
        bView = (Button) findViewById(R.id.button_viewall);
        CheckDB = (Button) findViewById(R.id.button_check);

        databaseHelper = new DatabaseHelper(getBaseContext());

        bAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), CaptureActivity.class);
                startActivity(i);
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), CaptureActivity.class);
                startActivity(i);
            }
        });

        bOOTD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), CaptureActivity.class);
                startActivity(i);
            }
        });

        bView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), CaptureActivity.class);
                startActivity(i);
            }
        });

        //<-------------checking inside DB ---------------------------->
        CheckDB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent dbmanager = new Intent(getBaseContext(),AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });
        //<-------------checking inside DB ---------------------------->
    }

}
