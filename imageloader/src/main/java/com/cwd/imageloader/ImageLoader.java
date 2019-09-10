package com.cwd.imageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.cwd.imageloader.cache.ImageCache;
import com.cwd.imageloader.processor.ImageProcessor;
import com.cwd.imageloader.request.ImageRequest;
import com.cwd.imageloader.request.ImageRequestListener;

import java.util.LinkedList;

/**
 * @author chenweide
 */
public class ImageLoader {

    private ImageLoaderConfig mConfig;
    private ImageCache mImageCache;
    private ImageRequest mImageRequest;
    private int placeholder;
    private int error;
    private boolean dontCompress;
    private ImageInfo mImageInfo;

    private LinkedList<ImageRequest> requestQueue = new LinkedList<>();

    private ImageLoader(){}

    public static ImageLoader getInstance(){
        return ImageLoaderHolder.sInstance;
    }

    private static class ImageLoaderHolder{
        private volatile static ImageLoader sInstance = new ImageLoader();
    }

    /**
     * 根据配置类进行初始化
     * @param config
     */
    public void init(ImageLoaderConfig config){
        this.mConfig = config;
        checkConfig();
    }

    private void checkConfig(){
        if(mConfig !=null){
            this.mImageCache = mConfig.getImageCache();
            this.mImageRequest = mConfig.getImageRequest();
            this.placeholder = mConfig.getPlaceholder();
            this.error = mConfig.getError();
            this.dontCompress = mConfig.isDontCompress();
        }else{
            throw new IllegalStateException("请先调用 ImageLoader.init() 进行初始化");
        }
    }

    public void displayImage(String url, final ImageView imageView){
        ImageInfo imageInfo = generateImageInfo(imageView);
        setImageLoadStatus(imageView,LoadStatus.LOADING);
        imageView.setTag(url);
        //已有内存缓存则使用内存缓存
        Bitmap bitmap = mImageCache.get(url);
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
        }else{
            //本地没有缓存则从网络获取
            imageView.setTag(url);
            mImageRequest.load(url,imageInfo, new ImageRequestListener() {
                @Override
                public void onSuccess(String url, Bitmap bitmap) {
                    if(imageView.getTag().equals(url)){
                        imageView.setImageBitmap(bitmap);
                        mImageCache.put(url,bitmap);
                    }
                }

                @Override
                public void onError() {
                    setImageLoadStatus(imageView,LoadStatus.ERROR);
                }
            });

        }
    }

    private void setImageLoadStatus(ImageView imageView,LoadStatus loadStatus){
        if(loadStatus == LoadStatus.LOADING){
            imageView.setImageResource(placeholder);
        }else if(loadStatus == LoadStatus.ERROR){
            imageView.setImageResource(error);
        }
    }

    private ImageInfo generateImageInfo(ImageView imageView){
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setWidth(imageView.getMeasuredWidth());
        imageInfo.setHeight(imageView.getMeasuredHeight());
        imageInfo.setDontCompress(dontCompress);
        return imageInfo;
    }

}
