package com.cwd.imageloader;

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

    public static class Builder {

        private ImageLoaderConfig config;

        public Builder(){
            config = new ImageLoaderConfig();
        }

        public Builder setImageCache(ImageCache imageCache){
            config.mImageCache = imageCache;
            return this;
        }

        public Builder setImageRequest(ImageRequest imageRequest){
            config.mImageRequest = imageRequest;
            return this;
        }

        public Builder setPlaceholder(@DrawableRes int placehoder){
            config.placeholder = placehoder;
            return this;
        }

        public Builder setError(@DrawableRes int error){
            config.error = error;
            return this;
        }

        public ImageLoaderConfig build(){
            return config;
        }
    }
}
