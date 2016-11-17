package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class PictureUtils {
    public static Bitmap getScaleBitmap(String path, Activity activity) {                           // <This method checks to see how big the screen is, and then scales the image...
        Point size = new Point();                                                                   //...down to size. The imageView you load into will always be smaller than this size>
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        return getScaleBitmap(path, size.x, size.y);
    }
    public static Bitmap getScaleBitmap(String path, int destWidth, int destHeight) {

        // Read in the dimensions of the image on disk
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        //Figure out how much to scale down by
        int inSampleSize = 1;
        if (srcWidth > srcHeight) {
            inSampleSize = Math.round(srcHeight / destHeight);
        } else {
            inSampleSize = Math.round(srcWidth / destWidth);
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        // Read in and create file bitmap
        return BitmapFactory.decodeFile(path, options);
    }
}
