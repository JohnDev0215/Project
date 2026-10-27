package com.amazon.mp3.view;

import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class ContentDisabledHelper {
    public static void setContentEnabled(View view, boolean enabled) {
        if (enabled) {
            view.setLayerType(0, null);
            view.setAlpha(1.0f);
        } else {
            view.setLayerType(2, null);
            view.setAlpha(0.3f);
        }
    }
}
