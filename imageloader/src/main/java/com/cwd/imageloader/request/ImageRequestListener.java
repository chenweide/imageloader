package com.cwd.imageloader.request;

import android.graphics.Bitmap;

/**
 * @author chenweide
 */
public interface ImageRequestListener {

    void onSuccess(String url, Bitmap bitmap);
    void onError();
}
