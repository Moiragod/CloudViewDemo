package com.moiragod.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.moira.lib.kernal.utils.GlideCircleTransform;
import com.moiragod.app.R;

/**
 * Created by tdu011 on 11/13/17.
 */

public class ImageLoadUtils {

    private static ImageLoadUtils instance;

    private static RequestOptions glideOpt = new RequestOptions();

    private ImageLoadUtils() {
    }

    public static ImageLoadUtils getInstance() {
        if (instance == null) {
            instance = new ImageLoadUtils();
        }
        return instance;
    }

    /**
     * 显示随机的图片(每日推荐)
     *
     * @param imgNumber 有几张图片要显示,对应默认图
     * @param imageUrl  显示图片的url
     * @param imageView 对应图片控件
     */
    public static void displayRandom(int imgNumber, String imageUrl, ImageView imageView) {
        glideOpt.placeholder(getMusicDefaultPic(imgNumber))
                .error(getMusicDefaultPic(imgNumber));
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(glideOpt)
                .transition(new DrawableTransitionOptions().crossFade(1500))
                .into(imageView);
    }

    private static int getMusicDefaultPic(int imgNumber) {
        switch (imgNumber) {
            case 1:
                return R.drawable.music_run_21;
            case 2:
                return R.drawable.music_run_43;
            case 3:
                return R.drawable.music_run_11;
        }
        return R.drawable.titlebar_discover_normal;
    }

    public static void displayCircle(ImageView imageView, String imageUrl) {
        glideOpt.error(R.drawable.ic_avatar_default)
                .transform(new GlideCircleTransform(imageView.getContext()));
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(glideOpt)
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(imageView);
    }
}
