package com.android.systemui.util;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class ViewInvalidUtils {
    private static final float alphaInvalid = 0.45f;
    private static final float alphaValid = 1.0f;

    public static void setInvalid(View view, boolean invalid) {
        if (view == null) {
            return;
        }
        view.setAlpha(invalid ? 0.45f : 1.0f);
        view.setEnabled(!invalid);
    }

    public static void setInvalid(View view) {
        if (view != null) {
            view.setAlpha(0.45f);
            view.setEnabled(false);
        }
    }

    public static void setValid(View view) {
        if (view != null) {
            view.setAlpha(1.0f);
            view.setEnabled(true);
        }
    }

    public static boolean isInvalid(View view) {
        return view != null && view.getAlpha() == 0.45f;
    }

    public static void setAlphaInvalid(View view) {
        if (view != null) {
            view.setAlpha(0.45f);
        }
    }

    public static void setAlphaValid(View view) {
        if (view != null) {
            view.setAlpha(1.0f);
        }
    }

    public static void setInvalid(boolean invalid, View... views) {
        for (View view : views) {
            if (view == null) {
                return;
            }
            view.setAlpha(invalid ? 0.45f : 1.0f);
            view.setEnabled(!invalid);
        }
    }
}
