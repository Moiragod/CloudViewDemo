package com.moiragod.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.moira.lib.kernal.base.BaseActivity;
import com.moira.lib.kernal.utils.BaseTools;
import com.moira.lib.kernal.utils.CommonUtils;
import com.moira.lib.kernal.utils.PerfectClickListener;
import com.moiragod.app.R;
import com.moiragod.app.databinding.ActivityNavAboutBinding;
import com.moiragod.view.webview.WebViewActivity;

/**
 * Created by tdu011 on 11/13/17.
 */
public class NavAboutActivity extends BaseActivity<ActivityNavAboutBinding> {

    private static String string_url_update_log = "http://jingbin.me/2016/12/30/%E6%9B%B4%E6%96%B0%E6%97%A5%E5%BF%97-%E4%BA%91%E9%98%85/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_about);
        showContentView();
        setTitle("About Moira Cloud");
        mInputBinding.tvVersionName.setText("Current version:" + BaseTools.getVersionName());


        // 直接写在布局文件里会很耗内存
        Glide.with(this).load(R.drawable.ic_cloudreader).into(mInputBinding.ivIcon);
        mInputBinding.tvGankio.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        mInputBinding.tvGankio.getPaint().setAntiAlias(true);//抗锯齿
        mInputBinding.tvDouban.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        mInputBinding.tvDouban.getPaint().setAntiAlias(true);//抗锯齿

        initListener();
    }

    private void initListener() {
        mInputBinding.tvGankio.setOnClickListener(listener);
        mInputBinding.tvDouban.setOnClickListener(listener);
        mInputBinding.tvAboutStar.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNextClickEvent(View v) {
//                BaseTools.openLink(v.getContext(), CommonUtils.getString(R.string.string_url_cloudreader));
            }
        });

        mInputBinding.tvFunction.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNextClickEvent(View v) {
//                BaseTools.openLink(v.getContext(), string_url_update_log);
            }
        });

        mInputBinding.tvNewVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BaseTools.openLink(v.getContext(), CommonUtils.getString(R.string.string_url_new_version));
            }
        });
    }

    private PerfectClickListener listener = new PerfectClickListener() {
        @Override
        protected void onNextClickEvent(View v) {
            String url = null;
            switch (v.getId()) {
                case R.id.tv_gankio:
//                    url = CommonUtils.getString(R.string.string_url_gankio);
                    break;
                case R.id.tv_douban:
//                    url = CommonUtils.getString(R.string.string_url_douban);
                    break;
                case R.id.tv_about_star:
                    url = CommonUtils.getString(R.string.cloud_main_url);
                    break;
                case R.id.tv_function:// 更新日志
                    url = string_url_update_log;
                    break;
            }
            WebViewActivity.loadUrl(v.getContext(), url, "Loading...");
        }
    };

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, NavAboutActivity.class);
        mContext.startActivity(intent);
    }
}