package com.cwd.imageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.cwd.imageloader.utils.Utils;

/**
 * @author chenweide
 */
public class MemoryCache extends ImageCache {

    private LruCache<String,Bitmap> lruCache;

    public MemoryCache(){
        int cacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024) / 4;
        lruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        url = Utils.hashKey(url);
        lruCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(String url) {
        url = Utils.hashKey(url);
        return lruCache.get(url);
    }
}
