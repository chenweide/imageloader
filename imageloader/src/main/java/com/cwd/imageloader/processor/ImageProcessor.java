package com.cwd.imageloader.processor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageProcessor {

    public Bitmap compress(InputStream is,int reqWidth,int reqHeight){
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            byte[] datas = inputStream2ByteArr(is);
            BitmapFactory.decodeByteArray(datas,0,datas.length,options);
            options.inJustDecodeBounds = false;
            options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);
            return BitmapFactory.decodeByteArray(datas,0,datas.length,options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    private byte[] inputStream2ByteArr(InputStream is) throws IOException{
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len;
        while ((len = is.read(data)) != -1){
            os.write(data,0,len);
        }
        is.close();
        os.close();
        return os.toByteArray();
    }
}
