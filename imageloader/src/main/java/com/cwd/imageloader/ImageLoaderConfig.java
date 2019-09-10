package com.cwd.imageloader;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;

import com.cwd.imageloader.cache.ImageCache;
import com.cwd.imageloader.request.ImageRequest;

/**
 * @author chenweide
 */
public class ImageLoaderConfig {

    private ImageCache mImageCache;
    private ImageRequest mImageRequest;
    private int placeholder;
    private int error;
    private boolean dontCompress = false;

    public ImageCache getImageCache() {
        return mImageCache;
    }

    public void setImageCache(ImageCache imageCache) {
        this.mImageCache = imageCache;
    }

    public ImageRequest getImageRequest() {
        return mImageRequest;
    }

    public void setImageRequest(ImageRequest imageRequest) {
        this.mImageRequest = imageRequest;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(int placeholder) {
        this.placeholder = placeholder;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public boolean isDontCompress() {
        return dontCompress;
    }

    public void setDontCompress(boolean dontCompress) {
        this.dontCompress = dontCompress;
    }

    public static class Builder {

        private ImageLoaderConfig config;

        public Builder(){
            config = new ImageLoaderConfig();
        }

        /**
         * 设置缓存方式，通过实现ImageCache自定义缓存方式
         * @param imageCache
         * @return
         */
        public Builder setImageCache(ImageCache imageCache){
            config.mImageCache = imageCache;
            return this;
        }

        public Builder setImageRequest(ImageRequest imageRequest){
            config.mImageRequest = imageRequest;
            return this;
        }

        /**
         * 加载中占位图
         * @param placehoder
         * @return
         */
        public Builder setPlaceholder(@DrawableRes int placehoder){
            config.placeholder = placehoder;
            return this;
        }

        /**
         * 加载失败占位图
         * @param error
         * @return
         */
        public Builder setError(@DrawableRes int error){
            config.error = error;
            return this;
        }

        /**
         * 图片不进行压缩
         * @return
         */
        public Builder dontCompress(){
            config.dontCompress = true;
            return this;
        }

        public ImageLoaderConfig build(){
            return config;
        }
    }
}
