package com.android.settingslib.utils;

import android.content.Context;
import android.content.res.TypedArray;

/* JADX INFO: loaded from: classes3.dex */
public class ColorUtil {
    public static float getDisabledAlpha(Context context) {
        TypedArray ta = context.obtainStyledAttributes(new int[]{android.R.attr.disabledAlpha});
        float alpha = ta.getFloat(0, 0.0f);
        ta.recycle();
        return alpha;
    }
}
