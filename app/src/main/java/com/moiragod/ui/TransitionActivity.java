package com.moiragod.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.moira.lib.kernal.utils.CommonUtils;
import com.moira.lib.kernal.utils.PerfectClickListener;
import com.moiragod.app.R;
import com.moiragod.app.databinding.ActivityTransitionBinding;
import com.moiragod.constant.ConstantsImageUrl;

import java.util.Random;

/**
 * Created by tdu011 on 11/9/17.
 */

public class TransitionActivity extends AppCompatActivity {

    private ActivityTransitionBinding mBinding;
    private boolean animationEnd;
    private boolean isIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_transition);


        // 先显示默认图
        mBinding.ivDefultPic.setImageDrawable(CommonUtils.getDrawable(R.drawable.img_transition_default));
        RequestOptions glideOpt = new RequestOptions()
                .placeholder(R.drawable.img_transition_default)
                .error(R.drawable.img_transition_default);
        //这里随机图片的显示
        int randomNum = new Random().nextInt(ConstantsImageUrl.TRANSITION_URLS.length);
        Glide.with(this)
                .load(ConstantsImageUrl.TRANSITION_URLS[randomNum])
                .apply(glideOpt)
                .into(mBinding.ivPic);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.ivDefultPic.setVisibility(View.GONE);
            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        }, 3500);

        mBinding.tvJump.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNextClickEvent(View v) {
                toMainActivity();
            }
        });
    }

    private void toMainActivity() {
        if (isIn) {
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
        isIn = true;
    }
}
