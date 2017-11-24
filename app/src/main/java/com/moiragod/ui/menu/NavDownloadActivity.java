package com.moiragod.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moira.lib.kernal.base.BaseActivity;
import com.moira.lib.kernal.utils.PerfectClickListener;
import com.moira.lib.kernal.utils.QRCodeUtil;
import com.moira.lib.kernal.utils.ShareUtils;
import com.moiragod.app.R;
import com.moiragod.app.databinding.ActivityNavDownloadBinding;


/**
 * Created by tdu011 on 11/13/17.
 */

public class NavDownloadActivity extends BaseActivity<ActivityNavDownloadBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_download);
        showContentView();

        setTitle("扫码下载");
        String url = "https://talentexchange.pwc.com/";
        QRCodeUtil.showThreadImage(this, url, mInputBinding.ivErweima, R.drawable.ic_barcode_bg);
        mInputBinding.tvShare.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNextClickEvent(View v) {
                ShareUtils.share(v.getContext(), "Moira Cloud");
            }
        });
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, NavDownloadActivity.class);
        mContext.startActivity(intent);
    }
}
