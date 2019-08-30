package com.cwd.imageloader.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenweide
 */
public class OriginImageRequest implements ImageRequest {

    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 网络请求图片
     * @param url 图片链接
     * @return
     */
    @Override
    public void load(final String url, final ImageRequestListener listener) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                if(listener != null){
                    Bitmap bitmap = downloadImage(url);
                    if(bitmap != null){
                        listener.onSuccess(url,bitmap);
                    }else{
                        listener.onError();
                    }
                }else{
                    throw new IllegalArgumentException("ImageRequestListener not be null");
                }
            }
        });
    }

    private Bitmap downloadImage(String url){
        try {
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
