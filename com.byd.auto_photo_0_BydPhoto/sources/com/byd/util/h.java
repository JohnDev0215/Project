package com.byd.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;

/* JADX INFO: loaded from: classes2.dex */
public class h {
    public static Drawable a(Drawable drawable, int i) {
        return b(drawable, i, 255);
    }

    public static Drawable b(Drawable drawable, int i, int i2) {
        Drawable drawableWrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawableWrap, i);
        if (i2 > 0 && i2 < 255) {
            drawableWrap.setAlpha(i2);
        }
        return drawableWrap;
    }

    public static Drawable c(Drawable drawable, ColorStateList colorStateList) {
        return d(drawable, colorStateList, 255);
    }

    public static Drawable d(Drawable drawable, ColorStateList colorStateList, int i) {
        Drawable drawableWrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(drawableWrap, colorStateList);
        if (i > 0 && i < 255) {
            drawableWrap.setAlpha(i);
        }
        return drawableWrap;
    }
}
