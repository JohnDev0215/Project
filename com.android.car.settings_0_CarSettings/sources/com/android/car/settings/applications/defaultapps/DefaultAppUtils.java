package com.android.car.settings.applications.defaultapps;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

/* JADX INFO: loaded from: classes5.dex */
public class DefaultAppUtils {
    private DefaultAppUtils() {
    }

    public static Drawable getSafeIcon(Drawable icon, int maxDimension) {
        if (icon == null || (icon instanceof VectorDrawable)) {
            return icon;
        }
        Drawable safeIcon = getSafeDrawable(icon, maxDimension);
        return safeIcon;
    }

    private static Drawable getSafeDrawable(Drawable original, int maxDimension) {
        Bitmap bitmap;
        int actualWidth = original.getMinimumWidth();
        int actualHeight = original.getMinimumHeight();
        if (actualWidth <= maxDimension && actualHeight <= maxDimension) {
            return original;
        }
        float scaleWidth = maxDimension / actualWidth;
        float scaleHeight = maxDimension / actualHeight;
        float scale = Math.min(scaleWidth, scaleHeight);
        int width = (int) (actualWidth * scale);
        int height = (int) (actualHeight * scale);
        if (original instanceof BitmapDrawable) {
            bitmap = Bitmap.createScaledBitmap(((BitmapDrawable) original).getBitmap(), width, height, false);
        } else {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            original.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            original.draw(canvas);
        }
        return new BitmapDrawable((Resources) null, bitmap);
    }
}
