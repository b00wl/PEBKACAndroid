package com.example.givohra.myfingerprintingapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataController
{
    public static final String COL_ITEMNAME="itemname";
    public static final String COL_ITEMDESC="itemdesc";
    public static final String COL_ITEMREVIEW="itemreview";
    public static final String TABLE_NAME="ITEM_Table";
    public static final String DATABASE_NAME="secure.db";
    public static final int DATABASE_VERSION=4;
    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ITEMNAME + " text, " + COL_ITEMDESC + " text, " + COL_ITEMREVIEW + " text" + ")";

    DataBaseHelper dbHelper;
    Context context;
    SQLiteDatabase db;
    String passphrase = "pass";

    public DataController(Context context)
    {
        this.context=context;
        dbHelper=new DataBaseHelper(context);

    }

    public DataController open()
    {
        //db=dbHelper.getWritableDatabase(passphrase);
        db=dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public long insert(String itemname , String itemdesc, String itemreview)
    {
        ContentValues content=new ContentValues();

        content.put(COL_ITEMNAME, itemname);
        content.put(COL_ITEMDESC, itemdesc);
        content.put(COL_ITEMREVIEW, itemreview);


        //db.execSQL(TABLE_CREATE);
        return db.insert("ITEM_Table", null, content);
    }

    public Cursor retrieve(String itemsearchname)
    {
        return db.query(TABLE_NAME, new String[]{COL_ITEMNAME,COL_ITEMDESC,COL_ITEMREVIEW}, "itemname LIKE ? "  , new String[] {"%"+itemsearchname+"%"} , null, null, null);
    }

    private static class DataBaseHelper extends SQLiteOpenHelper
    {

        public DataBaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            try
            {
                //db.execSQL("DROP TABLE IF EXISTS ITEM_Table");
                db.execSQL(TABLE_CREATE);
            }
            catch(SQLiteException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS ITEM_Table");
            onCreate(db);
        }

    }

}