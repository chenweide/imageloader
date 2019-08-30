package com.cwd.imageloader.request;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * @author chenweide
 */
public interface ImageRequest {

    void load(String url,ImageRequestListener listener);
}
