package com.moira.lib.kernal;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.moira.lib.kernal.http.HttpUtils;
import com.moira.lib.kernal.utils.DebugUtils;

/**
 * Created by tdu011 on 11/9/17.
 */

public class MoiraCloudApplication extends Application {
    private static MoiraCloudApplication moiraCloudApplication;

    public static MoiraCloudApplication getInstance() {
        return moiraCloudApplication;
    }

    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        super.onCreate();
        moiraCloudApplication = this;
        HttpUtils.getInstance().init(this, DebugUtils.DEBUG);

        initTextSize();
    }

    /**
     * Forbid system to change font
     */
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
