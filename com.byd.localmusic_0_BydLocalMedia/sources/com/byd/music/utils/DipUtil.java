package com.byd.music.utils;

import android.util.TypedValue;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: loaded from: classes5.dex */
public class DipUtil {
    public static final int ITEM_DECORATION_WITH_LANDSCAPE = dp2px(15.0f);
    public static final int ITEM_DECORATION_HEIGHT_LANDSCAPE = dp2px(16.0f);
    public static final int ITEM_DECORATION_WITH_PORTRAIT = dp2px(13.0f);

    public static int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, Utils.getApp().getResources().getDisplayMetrics());
    }

    public static int px2dp(float f) {
        return (int) ((f / Utils.getApp().getResources().getDisplayMetrics().density) + 0.5f);
    }
}
