package com.byd.foundation.utils;

import android.content.Context;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class ToastUtil {
    private static long newTime;
    private static String oldMsg;
    private static long oldTime;
    private static Toast toast;

    public static void showShortToast(Context context, int i) {
        showShortToast(context, context.getString(i));
    }

    public static void showShortToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), str, 0);
            oldMsg = str;
            oldTime = System.currentTimeMillis();
        } else {
            long jCurrentTimeMillis = System.currentTimeMillis();
            newTime = jCurrentTimeMillis;
            if (jCurrentTimeMillis - oldTime > 2000) {
                toast = Toast.makeText(context.getApplicationContext(), str, 0);
                oldMsg = str;
                oldTime = newTime;
            } else if (!oldMsg.equals(str)) {
                toast.setText(str);
                oldMsg = str;
                oldTime = newTime;
            }
        }
        toast.show();
    }
}
