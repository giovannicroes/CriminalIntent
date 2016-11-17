package com.bignerdranch.android.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.bignerdranch.android.criminalintent.database.CrimeDbSchema.*;

public class CrimeBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "crimes.db";
    public static final int VERSION = 1;
    private static final String TAG = CrimeBaseHelper.class.getSimpleName();

    private static final  String SQL_CREAT_ENTRIES =
            "CREATE TABLE " + CrimeTableColumns.TABLE_NAME + " (" +
                    CrimeTableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CrimeTableColumns.COLUMN_UUID + ", " +
                    CrimeTableColumns.COLUMN_TITLE + ", " +
                    CrimeTableColumns.COLUMN_DATE + ", " +
                    CrimeTableColumns.COLUMN_SOLVED + ", " +
                    CrimeTableColumns.COLUMN_SUSPECT +
                    ")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CrimeTableColumns.TABLE_NAME;

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREAT_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
