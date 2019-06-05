package com.wanggang.tetris;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.view.CropImageView;
import com.wanggang.tetris.util.ToastUtils;

import java.io.File;

/**
 * Created by wanggang on 17/10/14.
 */

public class TetrisApp extends Application {

    private static boolean DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler.getInstance().init(this);
        ToastUtils.init(this);

        initImagePicker();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(myImageLoader);   //设置图片加载器
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    private ImageLoader myImageLoader = new ImageLoader() {
        @Override
        public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
            Glide.with(activity)
                    .load(Uri.fromFile(new File(path)))
                    .placeholder(R.mipmap.icon_tetris)
                    .error(R.mipmap.icon_tetris)
                    .override(300, 300)
                    .into(imageView);
        }

        @Override
        public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {

        }

        @Override
        public void clearMemoryCache() {

        }
    };
}
