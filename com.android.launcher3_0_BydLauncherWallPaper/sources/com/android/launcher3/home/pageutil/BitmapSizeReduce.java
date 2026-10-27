package com.android.launcher3.home.pageutil;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.android.launcher3.util.Logger;

/* JADX INFO: loaded from: classes4.dex */
public class BitmapSizeReduce {
    private static final String TAG = "BitmapSizeReduce";

    public static Bitmap getResizedBitmap(Context context, int iamgeResId, int maxBitmapWidth, int maxBitmapHeight, boolean hasAlphaChannel, Bitmap inBitmap) throws Throwable {
        int i;
        Resources resources = context.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, iamgeResId, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (i2 > maxBitmapWidth || i3 > maxBitmapHeight) {
            i = 1;
            while (true) {
                if (i2 / i <= maxBitmapWidth && i3 / i <= maxBitmapHeight) {
                    break;
                }
                i *= 2;
            }
            Logger.e(TAG, "getResizedBitmap inSampleSize=" + i);
        } else {
            i = 1;
        }
        options.inSampleSize = i;
        if (!hasAlphaChannel) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        options.inJustDecodeBounds = false;
        options.inMutable = true;
        options.inBitmap = inBitmap;
        return BitmapFactory.decodeResource(resources, iamgeResId, options);
    }
}
