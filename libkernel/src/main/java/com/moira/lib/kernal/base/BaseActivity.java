package com.moira.lib.kernal.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.moira.lib.kernal.R;
import com.moira.lib.kernal.databinding.ActivityBaseBinding;
import com.moira.lib.kernal.statusbar.StatusBarUtil;
import com.moira.lib.kernal.utils.CommonUtils;
import com.moira.lib.kernal.utils.PerfectClickListener;


import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tdu011 on 11/13/17.
 */

public class BaseActivity<SV extends ViewDataBinding> extends AppCompatActivity {

    // 布局view
    protected SV mInputBinding;
    private LinearLayout mProgressBar;
    private View mRefreshView;
    private ActivityBaseBinding mBaseBinding;
    private AnimationDrawable mAnimationDrawable;
    private CompositeSubscription mCompositeSubscription;

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base, null, false);
        mInputBinding = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);

        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mInputBinding.getRoot().setLayoutParams(params);
        RelativeLayout mContainer = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(mInputBinding.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());

        // 设置透明状态栏
        StatusBarUtil.setColor(this, CommonUtils.getColor(R.color.colorTheme),0);
        mProgressBar = getView(R.id.ll_progress_bar);
        mRefreshView = getView(R.id.ll_error_refresh);
        ImageView img = getView(R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        setToolBar();
        // 点击加载失败布局
        mRefreshView.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNextClickEvent(View v) {
                showLoading();
                onRefresh();
            }
        });
        mInputBinding.getRoot().setVisibility(View.GONE);
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(mBaseBinding.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mBaseBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setTitle(CharSequence text) {
        mBaseBinding.toolBar.setTitle(text);
    }

    protected void showLoading() {
        if (mProgressBar.getVisibility() != View.VISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (mInputBinding.getRoot().getVisibility() != View.GONE) {
            mInputBinding.getRoot().setVisibility(View.GONE);
        }
        if (mRefreshView.getVisibility() != View.GONE) {
            mRefreshView.setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (mProgressBar.getVisibility() != View.GONE) {
            mProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefreshView.getVisibility() != View.GONE) {
            mRefreshView.setVisibility(View.GONE);
        }
        if (mInputBinding.getRoot().getVisibility() != View.VISIBLE) {
            mInputBinding.getRoot().setVisibility(View.VISIBLE);
        }
    }

    protected void showError() {
        if (mProgressBar.getVisibility() != View.GONE) {
            mProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefreshView.getVisibility() != View.VISIBLE) {
            mRefreshView.setVisibility(View.VISIBLE);
        }
        if (mInputBinding.getRoot().getVisibility() != View.GONE) {
            mInputBinding.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    public void removeSubscription() {
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }


}
