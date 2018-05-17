package com.example.aipham.dangky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";

    public static final String TABLE_CREATE = "create table contacts ( id integer primary key  , " +
            "username text, password text);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

//    public int checkduplicate (String string)
//    {
//        db = this.getReadableDatabase();
//        String querry = "select username from "+TABLE_NAME;
//        Cursor cursor = db.rawQuery(querry,null);
//        String a;
//
//        if (cursor.moveToFirst())
//        {
//            do {
//                a = cursor.getString(2);
//                if (a.equals(string)) {
//                    return 1;
//                    break;
//
//                }
//            }
//                while (cursor.moveToNext()) ;
//
//
//
//        }else return 0;

    public void insertContact(Contact c){
    db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String querry = "select * from contacts";
        Cursor cursor = db.rawQuery(querry,null);
        int count  = cursor.getCount();
        values.put(COL_1, count);
        values.put(COL_2, c.getUsername());
        values.put(COL_3, c.getPass());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       String querry = "DROP TABLE IF EXISTS "+ TABLE_NAME;
       db.execSQL(querry);
       this.onCreate(db);

    }
}
