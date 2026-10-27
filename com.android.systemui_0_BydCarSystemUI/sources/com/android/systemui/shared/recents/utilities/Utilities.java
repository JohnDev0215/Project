package com.android.systemui.shared.recents.utilities;

import android.graphics.Color;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public class Utilities {
    public static void postAtFrontOfQueueAsynchronously(Handler h, Runnable r) {
        h.sendMessageAtFrontOfQueue(h.obtainMessage().setCallback(r));
    }

    public static float computeContrastBetweenColors(int bg, int fg) {
        float fRed = Color.red(bg) / 255.0f;
        float fGreen = Color.green(bg) / 255.0f;
        float fBlue = Color.blue(bg) / 255.0f;
        float fPow = ((fRed < 0.03928f ? fRed / 12.92f : (float) Math.pow((fRed + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.2126f) + ((fGreen < 0.03928f ? fGreen / 12.92f : (float) Math.pow((fGreen + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.7152f) + ((fBlue < 0.03928f ? fBlue / 12.92f : (float) Math.pow((fBlue + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.0722f);
        float fRed2 = Color.red(fg) / 255.0f;
        float fGreen2 = Color.green(fg) / 255.0f;
        float fBlue2 = Color.blue(fg) / 255.0f;
        return Math.abs((((((fRed2 < 0.03928f ? fRed2 / 12.92f : (float) Math.pow((fRed2 + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.2126f) + ((fGreen2 < 0.03928f ? fGreen2 / 12.92f : (float) Math.pow((fGreen2 + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.7152f)) + ((fBlue2 < 0.03928f ? fBlue2 / 12.92f : (float) Math.pow((fBlue2 + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.0722f)) + 0.05f) / (fPow + 0.05f));
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }
}
