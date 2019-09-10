package com.cwd.imageloader.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.cwd.imageloader.ImageInfo;
import com.cwd.imageloader.processor.ImageProcessor;

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

    private ImageProcessor mImageProcessor;
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public OriginImageRequest(){
        mImageProcessor = new ImageProcessor();
    }

    /**
     * 网络请求图片
     * @param url 图片链接
     * @return
     */
    @Override
    public void load(final String url, final ImageInfo imageInfo, final ImageRequestListener listener) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                if(listener != null){
                    Bitmap bitmap = downloadImage(url,imageInfo);
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

    private Bitmap downloadImage(String url,ImageInfo imageInfo){
        try {
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            Bitmap bitmap = null;
            if(imageInfo.isDontCompress()){
                bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            }else{
                bitmap = mImageProcessor.compress(conn.getInputStream(),imageInfo.getWidth(),imageInfo.getHeight());
            }
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
