package com.example.givohra.myfingerprintingapplication;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.database.Cursor;
import android.util.*;
import android.widget.TextView;

public class SearchActivity extends Activity {

    public final static String STORE_PREFERENCES="storePrefFinal.txt";
    //private static int counter;
    public int counter=0;
    private SimpleDateFormat s=new SimpleDateFormat("MM/dd/yyyy-hh:mm a");
    public TextView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //SQLiteDatabase db = openOrCreateDatabase("ITEM_Table", MODE_PRIVATE, null);
        //Cursor c = db.rawQuery("SELECT * FROM ITEM_Table",null);

        // DataController dataController=new DataController(getBaseContext());
        // dataController.open();
        // Cursor c = dataController.retrieve();
        //c.moveToFirst();
        //if (c != null) {
        //	do {
        //		for (int i = 0; i < c.getColumnCount(); i++) {

        //			Log.e("", "" + c.getString(i));
        //		}
        //	}while (c.moveToNext());
        //}
        // Show the Up button in the action bar.
        //setupActionBar();

        //SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        //counter=sharedPrefs.getInt("COUNTER", 0);

    }

    @Override
    public void onResume()
    {
        super.onResume();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        counter=sharedPrefs.getInt("COUNTER", 0);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.set_preferences, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onCancel(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void searchItem(View view)
    {
        EditText editText1=(EditText)findViewById(R.id.itemsearch_name);
        String itemsearchname=editText1.getText().toString();

        mSearchView = (TextView)findViewById(R.id.search_view);
        mSearchView.setText("Searching");
        StringBuilder sbMethods = new StringBuilder();

        DataController dataController=new DataController(getBaseContext());
        dataController.open();
        Cursor c = dataController.retrieve(itemsearchname);
        if (c.moveToFirst()) {
            do {
                for (int i = 0; i < c.getColumnCount(); i++) {

                    Log.e("", "" + c.getString(i));
                    sbMethods.insert(0, "Requested Data" + i + ": " + c.getString(i) + "\n");
                }
            }while (c.moveToNext());
            mSearchView.setText(sbMethods.toString());
        }
        else{
            mSearchView.setText("No data found!");
        }
    }

}

