package com.cwd.imageloader;

import com.cwd.imageloader.cache.ImageCache;
import com.cwd.imageloader.request.ImageRequest;

/**
 * @author chenweide
 */
public class ImageLoaderConfig {

    private ImageCache mImageCache;
    private ImageRequest mImageRequest;

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

        public ImageLoaderConfig build(){
            return config;
        }
    }
}
