package com.bignerdranch.android.criminalintent.database;

import android.provider.BaseColumns;

public class CrimeDbSchema {

    public CrimeDbSchema() {
    }

    public static final class CrimeTableColumns implements BaseColumns {                            // _ID will be created via de 'BaseColumns'
        public static final String TABLE_NAME = "crimes";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_SOLVED = "solved";
        public static final String COLUMN_SUSPECT = "suspect";
    }
}
