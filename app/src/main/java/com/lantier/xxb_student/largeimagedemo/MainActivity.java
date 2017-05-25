package com.lantier.xxb_student.largeimagedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lantier.xxb_student.largeimagedemo.customview.LargeImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView ivSmall;
    private ImageView ivLarge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        init();
//        initView();
//        initData();
    }

    private void init() {
        LargeImageView ivLargeCustom = (LargeImageView) findViewById(R.id.iv_large_custom);
        try {
            InputStream open = getAssets().open("qm.jpg");
            ivLargeCustom.setInputStream(open);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {
            InputStream is = getAssets().open("tangyan.jpg");

            //获取图片的宽高
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is,null,options);
            int height = options.outHeight;
            int width = options.outWidth;
            options.inJustDecodeBounds = false;
            Bitmap bitmap1 = BitmapFactory.decodeStream(is);
            ivSmall.setImageBitmap(bitmap1);

            //设置图片显示在设备中心
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(is ,false);
            BitmapFactory.Options op = new BitmapFactory.Options();
            op.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(width / 2 - 20, height / 2 - 20, width / 2 + 20, height / 2 + 20), op);
            ivLarge.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        ivSmall = (ImageView) findViewById(R.id.iv_small);
        ivLarge = (ImageView) findViewById(R.id.iv_large);
    }
}
