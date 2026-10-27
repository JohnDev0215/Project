package com.amazon.identity.frc.FrcCookiesManager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

/* JADX INFO: compiled from: WindowManagerUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d {
    private static final String TAG = "com.amazon.identity.frc.FrcCookiesManager.d";

    private d() {
    }

    private static DisplayMetrics e(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (defaultDisplay != null) {
            defaultDisplay.getMetrics(displayMetrics);
        } else {
            Log.e(TAG, "getDefaultDisplay() returned is null.");
        }
        return displayMetrics;
    }

    public static String f(Context context) {
        return Integer.toString(e(context).widthPixels);
    }

    public static String g(Context context) {
        return Integer.toString(e(context).heightPixels);
    }
}
