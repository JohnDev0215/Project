package com.byd.music.style;

import android.app.UiStyleConfig;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: loaded from: classes5.dex */
public class BydUiStyleConfig {
    public static final int STYLE_DENZA = 1;
    public static final int STYLE_DYNASTY = 0;
    public static final int STYLE_FANCY = 3;
    public static final int STYLE_OCEAN = 2;
    private static int mUiStyle = UiStyleConfig.getInstance(Utils.getApp()).getUiStyle();

    public static int getUiStyle() {
        return mUiStyle;
    }

    public static boolean isDynasty() {
        return getUiStyle() == 0;
    }

    public static boolean isOcean() {
        return getUiStyle() == 2;
    }

    public static boolean isDenza() {
        return getUiStyle() == 1;
    }

    public static boolean isFancy() {
        return getUiStyle() == 3;
    }
}
