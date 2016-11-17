package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;
import com.bignerdranch.android.criminalintent.database.CrimeCursorWrapper;
import com.bignerdranch.android.criminalintent.database.CrimeDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;                                                              // s prefix means static variable

    //private List<Crime> mCrimes;  //DEMOLITION (PAGE 263)
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {                                                   // Singleton (page 169)
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {                                                             // (1-2)private constructor, so other classes cannot ...
        mContext = context.getApplicationContext();                                                 //Database (page 259)
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();                            //Database (page 259)
        //mCrimes = new ArrayList<>();     //DEMOLITION (PAGE 263)                                  // (2-2)create a CrimeLab bypassing the get() method (page 169)
//        for (int i = 0; i < 100; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);                                // Not necessary anymore because of the method addCrime (below)(page 247)
//            crime.setSolved(i % 2 == 0); // Every other one
//            mCrimes.add(crime);
//        }
    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeDbSchema.CrimeTableColumns.TABLE_NAME, null, values);
        //mCrimes.add(c);   DEMOLITION (PAGE 263)
    }

    public void deleteCrime(Crime c) {                                                            //Challenge Delete action toolbar page 255
        mDatabase.delete(CrimeDbSchema.CrimeTableColumns.TABLE_NAME, null,null);
    }

    public List<Crime> getCrimes() {
        //return mCrimes;                   DEMOLITION (PAGE 263)
        //return new ArrayList<>();         Delete page269
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally{
                cursor.close();
            }
                return crimes;
    }

    public Crime getCrime(UUID id) {
//        for (Crime crime : mCrimes) {
//            if (crime.getId().equals(id)) {          //DEMOLITION (PAGE 263)
//                return crime;
//            }
//        }
//        return null;                                  // delete, page 270

        CrimeCursorWrapper cursor = queryCrimes(
                CrimeDbSchema.CrimeTableColumns.COLUMN_UUID+ " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Crime crime) {
        File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //File externalFilesDir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), crime.getPhotoFilename()); TEST MET ELIZA

        if (externalFilesDir == null) {                                                             // return null if there are no external storage (page 297)
            return null;
        }
        return new File(externalFilesDir, crime.getPhotoFilename());
        //return externalFilesDir;                                              TEST MET ELIZA

    }

    public void updateCrime (Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTableColumns.TABLE_NAME, values,
                CrimeDbSchema.CrimeTableColumns.COLUMN_UUID + " = ?",                               // =? -> where is
                new String[] { uuidString});
    }

    private static ContentValues getContentValues (Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.CrimeTableColumns.COLUMN_UUID, crime.getId().toString());
        values.put(CrimeDbSchema.CrimeTableColumns.COLUMN_TITLE, crime.getTitle());
        values.put(CrimeDbSchema.CrimeTableColumns.COLUMN_DATE, crime.getDate().getTime());
        values.put(CrimeDbSchema.CrimeTableColumns.COLUMN_SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeDbSchema.CrimeTableColumns.COLUMN_SUSPECT, crime.getSuspect());

        return values;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTableColumns.TABLE_NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );

        return new CrimeCursorWrapper(cursor);
    }
}
