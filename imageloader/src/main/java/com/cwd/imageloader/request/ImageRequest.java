package com.cwd.imageloader.request;


import com.cwd.imageloader.ImageInfo;

/**
 * @author chenweide
 */
public interface ImageRequest {

    void load(String url, ImageInfo imageInfo, ImageRequestListener listener);
}
