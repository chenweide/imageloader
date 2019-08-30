package com.cwd.loadphoto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cwd.imageloader.ImageLoader;
import com.cwd.imageloader.ImageLoaderConfig;
import com.cwd.imageloader.cache.MemoryCache;
import com.cwd.imageloader.request.OriginImageRequest;

/**
 * @author chenweide
 */
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enter(View view){
        startActivity(new Intent(this,PhotoActivity.class));
    }

}
