package com.byd.profile;

import android.content.Context;
import com.byd.profile.dialog.ToastWindow;

/* JADX INFO: loaded from: classes2.dex */
public class ToastManager {
    public static final int IMAGE = 1;
    public static final int NORMAL = 0;
    private static ToastWindow sToastWindow;

    public static void show(Context context, int textRes) {
        ToastWindow toastWindow = sToastWindow;
        if (toastWindow != null) {
            toastWindow.dismiss();
        }
        ToastWindow toastWindow2 = new ToastWindow(context, textRes);
        sToastWindow = toastWindow2;
        toastWindow2.show();
    }

    public static void show(Context context, int textRes, int x, int y, int gravity) {
        ToastWindow toastWindow = sToastWindow;
        if (toastWindow != null) {
            toastWindow.dismiss();
        }
        ToastWindow toastWindow2 = new ToastWindow(context, textRes);
        sToastWindow = toastWindow2;
        toastWindow2.show(x, y, gravity);
    }

    public static void showImage(Context context, int iconRes, int textRes) {
        ToastWindow toastWindow = sToastWindow;
        if (toastWindow != null) {
            toastWindow.dismiss();
        }
        ToastWindow toastWindow2 = new ToastWindow(context, iconRes, textRes);
        sToastWindow = toastWindow2;
        toastWindow2.show();
    }
}
