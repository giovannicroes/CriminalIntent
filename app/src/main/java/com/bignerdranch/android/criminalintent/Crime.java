package com.bignerdranch.android.criminalintent;

import android.icu.text.DateFormat;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mId;
    private String mTittle;
    private Date mDate;                                                                                // The date that a crime occurred
    private boolean mSolved;                                                                           // whether the crime has been solved
    private String mSuspect;

    public Crime() {
        //Generate unique identifier
//        mId = UUID.randomUUID();                                          removed @ page 268
//        mDate = new Date();                                                                            // The (default) current date that a crime has occurred
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTittle;
    }

    public void setTitle(String title) {
        mTittle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    //String formatedDate = DateFormat.getDateInstance().format(mCrime.getDate());

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId() + ".jpg";
    }
}
