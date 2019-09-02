package com.cwd.imageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cwd.imageloader.utils.Utils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author chenweide
 */
public class DiskCache extends ImageCache {

    private static final long MAX_SIZE = 1024 * 1024 * 150;
    private DiskLruCache diskLruCache;

    public DiskCache(Context context){
        initDiskLruCache(context);
    }

    private void initDiskLruCache(Context context){
        if(diskLruCache == null || diskLruCache.isClosed()){
            File cacheDir = Utils.getDiskCacheDir(context,"image_cache");
            if(!cacheDir.exists()){
                cacheDir.mkdirs();
            }
            try {
                diskLruCache = DiskLruCache.open(cacheDir,Utils.getAppVersion(context),1,MAX_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        url = Utils.hashKey(url);
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(url);
            OutputStream os = editor.newOutputStream(0);
            os.write(Utils.bitmap2Bytes(bitmap));
            editor.commit();
            os.close();
            diskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bitmap get(String url) {
        url = Utils.hashKey(url);
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(url);
            if(snapshot != null){
                InputStream is = snapshot.getInputStream(0);
                return BitmapFactory.decodeStream(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
