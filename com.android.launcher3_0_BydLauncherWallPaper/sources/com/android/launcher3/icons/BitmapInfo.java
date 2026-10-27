package com.android.launcher3.icons;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes4.dex */
public class BitmapInfo {
    public static final Bitmap LOW_RES_ICON = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
    public int color;
    public Bitmap icon;

    public void applyTo(BitmapInfo info) {
        info.icon = this.icon;
        info.color = this.color;
    }

    public final boolean isLowRes() {
        return LOW_RES_ICON == this.icon;
    }

    public static BitmapInfo fromBitmap(Bitmap bitmap) {
        return fromBitmap(bitmap, null);
    }

    public static BitmapInfo fromBitmap(Bitmap bitmap, ColorExtractor dominantColorExtractor) {
        BitmapInfo bitmapInfo = new BitmapInfo();
        bitmapInfo.icon = bitmap;
        bitmapInfo.color = dominantColorExtractor != null ? dominantColorExtractor.findDominantColorByHue(bitmap) : 0;
        return bitmapInfo;
    }
}
