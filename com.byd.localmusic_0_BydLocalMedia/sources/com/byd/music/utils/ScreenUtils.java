package com.byd.music.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/* JADX INFO: loaded from: classes5.dex */
public class ScreenUtils {
    private static DisplayMetrics displayMetrics;
    static DisplayMetrics dm;
    private static String TAG = ScreenUtils.class.getName();
    static int density = 0;
    static float realDensity = 0.0f;

    private static DisplayMetrics getDisplayMetrics(Context context) {
        if (displayMetrics == null) {
            displayMetrics = context.getResources().getDisplayMetrics();
        }
        return displayMetrics;
    }

    public static int getDensity(Context context) {
        int i = density;
        if (i != 0) {
            return i;
        }
        density = getDisplayMetrics(context).densityDpi;
        LogUtil.e("1111111111", "屏幕密度 density:" + getDisplayMetrics(context).density);
        getPixel(context);
        getSize(context);
        return density;
    }

    public static float getProxDensity(Context context) {
        float f = realDensity;
        if (f != 0.0f) {
            return f;
        }
        realDensity = getDisplayMetrics(context).density;
        getPixel(context);
        getSize(context);
        return realDensity;
    }

    public static float[] getPixel(Context context) {
        float f = getDisplayMetrics(context).xdpi;
        float f2 = getDisplayMetrics(context).ydpi;
        LogUtil.e(TAG, "每英寸像素  xdpi：" + f + ",ydpi:" + f2);
        return new float[]{f, f2};
    }

    public static Point getSize(Context context) {
        Point point = new Point();
        point.x = getDisplayMetrics(context).widthPixels;
        point.y = getDisplayMetrics(context).heightPixels;
        LogUtil.e(TAG, "分辨率：x：" + point.x + ",y:" + point.y);
        return point;
    }

    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }
}
