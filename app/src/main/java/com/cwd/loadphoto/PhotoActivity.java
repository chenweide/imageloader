package com.cwd.loadphoto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cwd.imageloader.ImageLoader;
import com.cwd.imageloader.ImageLoaderConfig;
import com.cwd.imageloader.cache.DoubleCache;
import com.cwd.imageloader.cache.MemoryCache;
import com.cwd.imageloader.request.OriginImageRequest;

/**
 * @author chenweide
 */
public class PhotoActivity extends AppCompatActivity {

    private ImageView iv1,iv2,iv3;
    private String[] imgUrls = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567197815843&di=6a9359155985b427bccb2007f3b31279&imgtype=0&src=http%3A%2F%2Fi1.17173.itc.cn%2F2009%2Fkx%2F2009%2F06%2F16%2F20090616171420490.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567197876316&di=333061445998831e6bca3770fdfa2b9a&imgtype=0&src=http%3A%2F%2Fimage.finance.china.cn%2Fupload%2Fimages%2F2014%2F0410%2F085000%2F0_2323627_580fd395d60d023a4cf8b45c31cd1218.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567197889448&di=0e002ef98cf5cf1886674f7e7636d77d&imgtype=0&src=http%3A%2F%2Fimg01.cztv.com%2F201611%2F20%2F84eae5bf1e03cc04222d40aaa2389e99.gif"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        initImageLoader();
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        Button btnLoad = findViewById(R.id.btn_load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImageLoader.getInstance().displayImage(imgUrls[0],iv1);
//                ImageLoader.getInstance().displayImage(imgUrls[1],iv2);
//                ImageLoader.getInstance().displayImage(imgUrls[2],iv3);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon_error);
                iv1.setImageBitmap(bitmap);
            }
        });

    }

    private void initImageLoader(){
        ImageLoaderConfig.Builder builder = new ImageLoaderConfig.Builder();
        builder.setImageCache(new DoubleCache(this))
        .setImageRequest(new OriginImageRequest())
        .setPlaceholder(R.drawable.icon_loading)
        .setError(R.drawable.icon_error);
//        .dontCompress();
        ImageLoader.getInstance().init(builder.build());
    }
}
