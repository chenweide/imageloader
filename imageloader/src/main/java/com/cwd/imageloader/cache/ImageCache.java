package com.cwd.imageloader.cache;

import android.graphics.Bitmap;

/**
 * @author chenweide
 */
public abstract class ImageCache {

    public abstract void put(String url, Bitmap bitmap);

    public abstract Bitmap get(String url);
}
