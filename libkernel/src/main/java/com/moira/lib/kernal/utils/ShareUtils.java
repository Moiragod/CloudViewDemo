package com.moira.lib.kernal.utils;

import android.content.Context;
import android.content.Intent;

import com.moira.lib.kernal.R;

/**
 * Created by tdu011 on 11/13/17.
 */

public class ShareUtils {
    public static void share(Context context, int stringRes) {
        share(context, context.getString(stringRes));
    }

    public static void share(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(
                Intent.createChooser(intent, "Share"));
    }

}
