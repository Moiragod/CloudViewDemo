package com.moiragod.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.moira.lib.kernal.utils.ShareUtils;
import com.moiragod.app.R;
import com.moiragod.app.databinding.ActivityNavHomePageBinding;
import com.moiragod.view.statusbar.StatusBarUtil;

/**
 * Created by tdu011 on 11/13/17.
 */

public class NavHomePageActivity extends AppCompatActivity {

    private ActivityNavHomePageBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_nav_home_page);

        mBinding.toolbarLayout.setTitle(getString(R.string.app_name));
        StatusBarUtil.setTranslucentForImageView(this, 0, mBinding.toolbar);
        mBinding.fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.share(v.getContext(), "Moira Cloud with me");
            }
        });
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, NavHomePageActivity.class);
        mContext.startActivity(intent);
    }
}
