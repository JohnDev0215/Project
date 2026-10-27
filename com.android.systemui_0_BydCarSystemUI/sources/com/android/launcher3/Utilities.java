package com.android.launcher3;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public class Utilities {
    public static int getScreenHeight(Context context) {
        return 1080;
    }

    public static float mapRange(float value, float min, float max) {
        return min + (value * (max - min));
    }

    public static float getProgress(float current, float min, float max) {
        return Math.abs(current - min) / Math.abs(max - min);
    }

    public static int boundToRange(int value, int lowerBound, int upperBound) {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }

    public static float boundToRange(float value, float lowerBound, float upperBound) {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }

    public static float mapToRange(float t, float fromMin, float fromMax, float toMin, float toMax, Interpolator interpolator) {
        return (fromMin == fromMax || toMin == toMax) ? toMin : mapRange(interpolator.getInterpolation(getProgress(t, fromMin, fromMax)), toMin, toMax);
    }

    public static void scaleRectAboutCenter(Rect r, float scale) {
        if (scale != 1.0f) {
            int iCenterX = r.centerX();
            int iCenterY = r.centerY();
            r.offset(-iCenterX, -iCenterY);
            scaleRect(r, scale);
            r.offset(iCenterX, iCenterY);
        }
    }

    public static void scaleRect(Rect r, float scale) {
        if (scale != 1.0f) {
            r.left = (int) ((r.left * scale) + 0.5f);
            r.top = (int) ((r.top * scale) + 0.5f);
            r.right = (int) ((r.right * scale) + 0.5f);
            r.bottom = (int) ((r.bottom * scale) + 0.5f);
        }
    }

    public static void scaleRectFAboutCenter(RectF r, float scale) {
        if (scale != 1.0f) {
            float fCenterX = r.centerX();
            float fCenterY = r.centerY();
            r.offset(-fCenterX, -fCenterY);
            r.left *= scale;
            r.top *= scale;
            r.right *= scale;
            r.bottom *= scale;
            r.offset(fCenterX, fCenterY);
        }
    }

    public static boolean isScreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static float dp2Px(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
