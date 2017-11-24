package com.moira.lib.kernal.utils;

import android.view.View;

import java.util.Calendar;

/**
 * Created by tdu011 on 11/9/17.
 */

public abstract class PerfectClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    private int id = -1;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        int mId = v.getId();
        if (id != mId) {
            id = mId;
            lastClickTime = currentTime;
            onNextClickEvent(v);
            return;
        }
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNextClickEvent(v);
        }
    }

    protected abstract void onNextClickEvent(View v);
}
