package com.byd.foundation.utils;

import android.content.Context;
import android.widget.Toast;

/* JADX INFO: loaded from: classes2.dex */
public class ToastUtil {
    private static long newTime;
    private static String oldMsg;
    private static long oldTime;
    private static Toast toast;

    public static void showShortToast(Context context, int i) {
        showShortToast(context, context.getString(i));
    }

    public static void showShortToast(Context context, String str) {
        long jCurrentTimeMillis;
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), str, 0);
            oldMsg = str;
            jCurrentTimeMillis = System.currentTimeMillis();
        } else {
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            newTime = jCurrentTimeMillis2;
            if (jCurrentTimeMillis2 - oldTime <= 2000) {
                if (!oldMsg.equals(str)) {
                    toast.setText(str);
                }
                toast.show();
            }
            toast = Toast.makeText(context.getApplicationContext(), str, 0);
            oldMsg = str;
            jCurrentTimeMillis = newTime;
        }
        oldTime = jCurrentTimeMillis;
        toast.show();
    }
}
