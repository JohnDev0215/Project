package com.byd.vrsettings.utils;

import android.content.Context;
import android.os.SystemClock;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class FastClickUtils {
    private static final int FAST_CLICK_DELAY_TIME = 300;
    private static long lastClickTime;
    private static Toast toast;

    public static void toastShow(Context context, CharSequence charSequence, int i) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        Toast toastMakeText = Toast.makeText(context, charSequence, i);
        toast = toastMakeText;
        toastMakeText.show();
    }

    public static boolean isFastClick() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - lastClickTime < 300) {
            return true;
        }
        lastClickTime = jElapsedRealtime;
        return false;
    }
}
