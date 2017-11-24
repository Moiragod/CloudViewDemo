package com.moiragod.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.moira.lib.kernal.base.BaseActivity;
import com.moira.lib.kernal.utils.CommonUtils;
import com.moira.lib.kernal.utils.PerfectClickListener;
import com.moiragod.app.R;
import com.moiragod.app.databinding.ActivityNavFeedbackBinding;

import com.moiragod.view.webview.WebViewActivity;

/**
 * Created by tdu011 on 11/13/17.
 */

public class NavFeedbackActivity extends BaseActivity<ActivityNavFeedbackBinding> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_feedback);
        setTitle("Feedback");
        showContentView();

        mInputBinding.tvIssues.setOnClickListener(listener);
        mInputBinding.tvFacebook.setOnClickListener(listener);
        mInputBinding.tvQq.setOnClickListener(listener);
        mInputBinding.tvEmail.setOnClickListener(listener);
        mInputBinding.tvFaq.setOnClickListener(listener);
    }

    private PerfectClickListener listener = new PerfectClickListener() {
        @Override
        protected void onNextClickEvent(View v) {
            switch (v.getId()) {
                case R.id.tv_issues:
                    WebViewActivity.loadUrl(v.getContext(), CommonUtils.getString(R.string.issue_url), "Issues");
                    break;
                case R.id.tv_qq:
                    String url = "mqqwpa://im/chat?chat_type=wpa&uin=770413277";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    break;
                case R.id.tv_email:
                    Intent data = new Intent(Intent.ACTION_SENDTO);
                    data.setData(Uri.parse("mailto:jingbin127@163.com"));
                    startActivity(data);
                    break;
                case R.id.tv_facebook:
                    WebViewActivity.loadUrl(v.getContext(),CommonUtils.getString(R.string.facebook_url), "loading...");
                    break;
                case R.id.tv_faq:
                    break;
            }
        }
    };

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, NavFeedbackActivity.class);
        mContext.startActivity(intent);
    }
}
