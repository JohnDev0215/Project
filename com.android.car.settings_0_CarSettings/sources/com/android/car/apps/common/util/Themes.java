package com.android.car.apps.common.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes5.dex */
public class Themes {
    public static int getAttrColor(Context context, int attr) {
        return getAttrColor(context, 0, attr);
    }

    public static int getAttrColor(Context context, int styleResId, int attr) {
        TypedArray ta = context.obtainStyledAttributes(styleResId, new int[]{attr});
        int colorAccent = ta.getColor(0, 0);
        ta.recycle();
        return colorAccent;
    }

    public static ColorStateList getAttrColorStateList(Context context, int attr) {
        return getAttrColorStateList(context, 0, attr);
    }

    public static ColorStateList getAttrColorStateList(Context context, int styleResId, int attr) {
        TypedArray ta = context.obtainStyledAttributes(styleResId, new int[]{attr});
        ColorStateList colorStateList = ta.getColorStateList(0);
        ta.recycle();
        return colorStateList;
    }

    public static boolean getAttrBoolean(Context context, int attr) {
        return getAttrBoolean(context, 0, attr);
    }

    public static boolean getAttrBoolean(Context context, int styleResId, int attr) {
        TypedArray ta = context.obtainStyledAttributes(styleResId, new int[]{attr});
        boolean value = ta.getBoolean(0, false);
        ta.recycle();
        return value;
    }

    public static Drawable getAttrDrawable(Context context, int attr) {
        return getAttrDrawable(context, 0, attr);
    }

    public static Drawable getAttrDrawable(Context context, int styleResId, int attr) {
        TypedArray ta = context.obtainStyledAttributes(styleResId, new int[]{attr});
        Drawable value = ta.getDrawable(0);
        ta.recycle();
        return value;
    }

    public static int getAttrInteger(Context context, int attr) {
        return getAttrInteger(context, 0, attr);
    }

    public static int getAttrInteger(Context context, int styleResId, int attr) {
        TypedArray ta = context.obtainStyledAttributes(styleResId, new int[]{attr});
        int value = ta.getInteger(0, 0);
        ta.recycle();
        return value;
    }

    public static int getAttrResourceId(Context context, int attr) {
        return getAttrResourceId(context, 0, attr);
    }

    public static int getAttrResourceId(Context context, int styleResId, int attr) {
        TypedArray ta = context.obtainStyledAttributes(styleResId, new int[]{attr});
        int resId = ta.getResourceId(0, 0);
        ta.recycle();
        return resId;
    }

    public static int getAttrDimensionPixelSize(Context context, int attr) {
        return getAttrDimensionPixelSize(context, 0, attr);
    }

    public static int getAttrDimensionPixelSize(Context context, int styleResId, int attr) {
        TypedArray ta = context.obtainStyledAttributes(styleResId, new int[]{attr});
        int dimensionPixelSize = ta.getDimensionPixelSize(0, 0);
        ta.recycle();
        return dimensionPixelSize;
    }
}
