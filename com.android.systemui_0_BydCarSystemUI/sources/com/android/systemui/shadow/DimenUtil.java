package com.android.systemui.shadow;

import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public class DimenUtil {
    public static float dp2px(float dpValue) {
        return (dpValue * Resources.getSystem().getDisplayMetrics().density) + 0.5f;
    }
}
