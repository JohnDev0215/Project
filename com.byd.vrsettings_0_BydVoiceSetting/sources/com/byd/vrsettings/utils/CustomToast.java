package com.byd.vrsettings.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class CustomToast {
    private static Handler handler = new Handler();
    private static boolean isShowing = false;
    private static Runnable runnable = new Runnable() { // from class: com.byd.vrsettings.utils.CustomToast.1
        @Override // java.lang.Runnable
        public void run() {
            if (CustomToast.toast != null) {
                CustomToast.toast.cancel();
            }
            boolean unused = CustomToast.isShowing = false;
        }
    };
    private static Toast toast;

    public static void showToast(Context context, String str, int i) {
        if (isShowing) {
            return;
        }
        isShowing = true;
        handler.removeCallbacks(runnable);
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        toast = Toast.makeText(context, str, 0);
        handler.postDelayed(runnable, i);
        toast.show();
    }
}
