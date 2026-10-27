package com.byd.music.utils;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: loaded from: classes5.dex */
public class OrientationUtil {
    public static int densityDpi;

    public static boolean isLandscape() {
        return Utils.getApp().getResources().getConfiguration().orientation == 2;
    }

    public static int getSpanCount(int i, int i2) {
        return !isLandscape() ? i2 : i;
    }

    public static boolean isPortrait() {
        return Utils.getApp().getResources().getConfiguration().orientation == 1;
    }

    public static boolean isMultiWindowMode(Activity activity) {
        if (activity == null) {
            return false;
        }
        if (activity.isInMultiWindowMode()) {
            return true;
        }
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        Point point = new Point();
        activity.getDisplay().getRealSize(point);
        return i * i2 <= ((point.x * point.y) * 2) / 3;
    }

    public static float getWindowScale(Activity activity) {
        if (activity == null) {
            return 1.0f;
        }
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        LogUtil.d("OrientationUtil", "width = " + i);
        LogUtil.d("OrientationUtil", "height = " + i2);
        float fMin = (float) Math.min(i, i2);
        activity.getDisplay().getRealSize(new Point());
        return fMin / Math.min(r1.x, r1.y);
    }
}
