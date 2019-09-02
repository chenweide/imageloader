package com.cwd.imageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.cwd.imageloader.utils.Utils;

/**
 * @author chenweide
 */
public class DoubleCache extends ImageCache {

    private MemoryCache memoryCache;
    private DiskCache diskCache;

    public DoubleCache(Context context){
        memoryCache = new MemoryCache();
        diskCache = new DiskCache(context);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        url = Utils.hashKey(url);
        memoryCache.put(url,bitmap);
        diskCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(String url) {
        url = Utils.hashKey(url);
        Bitmap bitmap = memoryCache.get(url);
        if(bitmap == null){
            bitmap = diskCache.get(url);
            if(bitmap == null){
                return null;
            }
        }
        return bitmap;
    }
}
