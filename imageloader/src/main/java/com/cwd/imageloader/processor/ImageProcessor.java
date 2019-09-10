package com.cwd.imageloader.processor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageProcessor {

    public Bitmap compress(InputStream is,int reqWidth,int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is,null,options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);
        return BitmapFactory.decodeStream(is,null,options);
    }

    public int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;
        int inSampleSize = 1;
        while (imageWidth > reqWidth && imageHeight > reqHeight){
            imageWidth /= 2;
            imageHeight /= 2;
            inSampleSize *= 2;
        }
        return inSampleSize;
    }
}
