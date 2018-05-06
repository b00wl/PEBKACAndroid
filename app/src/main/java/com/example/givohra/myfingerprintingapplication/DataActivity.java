package com.example.givohra.myfingerprintingapplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class DataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void openPreference(View view)
    {
        Intent intent=new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    public void saveInDatabase(View view)
    {
        Intent intent =new Intent(this,SQLiteActivity.class);
        startActivity(intent);
    }

    public void exitApp(View view)
    {
        //finish();
        System.exit(0);
    }
}