package com.moira.lib.kernal.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * If production, you can change DEBUG = false;
 * for developing, keep DEBUG = true
 */
public class DebugUtils {
    public static final String TAG = DebugUtils.class.getName();
    public static final boolean DEBUG = true; //false

    public static void toast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void debug(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void debug(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void error(String tag, String error) {

        if (DEBUG) {

            Log.e(tag, error);
        }
    }

    public static void error(String error) {

        if (DEBUG) {

            Log.e(TAG, error);
        }
    }
}