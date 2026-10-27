package org.chromium.ui.base;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceFormFactor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MINIMUM_LARGE_TABLET_WIDTH_DP = 720;
    public static final int MINIMUM_TABLET_WIDTH_DP = 600;
    private static Float sDensity;
    private static Boolean sIsLargeTablet;
    private static Boolean sIsTablet;
    private static Integer sMinimumTabletWidthPx;

    public static boolean isTablet(Context context) {
        if (sIsTablet == null) {
            sIsTablet = Boolean.valueOf(getSmallestDeviceWidthDp(context) >= 600);
        }
        return sIsTablet.booleanValue();
    }

    public static boolean isLargeTablet(Context context) {
        if (sIsLargeTablet == null) {
            sIsLargeTablet = Boolean.valueOf(getSmallestDeviceWidthDp(context) >= 720);
        }
        return sIsLargeTablet.booleanValue();
    }

    public static int getSmallestDeviceWidthDp(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        return Math.round(Math.min(displayMetrics.heightPixels / displayMetrics.density, displayMetrics.widthPixels / displayMetrics.density));
    }

    public static int getMinimumTabletWidthPx(Context context) {
        if (sMinimumTabletWidthPx == null) {
            sMinimumTabletWidthPx = Integer.valueOf(Math.round(context.getResources().getDisplayMetrics().density * 600.0f));
        }
        return sMinimumTabletWidthPx.intValue();
    }

    public static void resetValuesIfNeeded(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        Float f2 = sDensity;
        if (f2 != null && f2.floatValue() != f) {
            sIsTablet = null;
            sIsLargeTablet = null;
            sMinimumTabletWidthPx = null;
        }
        sDensity = Float.valueOf(f);
    }
}
