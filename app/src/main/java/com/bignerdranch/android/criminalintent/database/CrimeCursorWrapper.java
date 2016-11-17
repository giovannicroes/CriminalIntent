package com.bignerdranch.android.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.criminalintent.Crime;
import com.bignerdranch.android.criminalintent.database.CrimeDbSchema;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTableColumns.COLUMN_UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTableColumns.COLUMN_TITLE));
        long date = getLong(getColumnIndex(CrimeDbSchema.CrimeTableColumns.COLUMN_DATE));
        int isSolved = getInt(getColumnIndex(CrimeDbSchema.CrimeTableColumns.COLUMN_SOLVED));
        String suspect = getString(getColumnIndex(CrimeDbSchema.CrimeTableColumns.COLUMN_SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setSuspect(suspect);

        return crime;
    }
}
