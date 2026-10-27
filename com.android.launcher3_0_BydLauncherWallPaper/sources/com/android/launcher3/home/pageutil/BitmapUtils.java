package com.android.launcher3.home.pageutil;

import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import com.android.launcher3.home.HomeApplication;

/* JADX INFO: loaded from: classes4.dex */
public class BitmapUtils {
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int i = options.outHeight;
        int i2 = options.outWidth;
        int i3 = 1;
        if (i > reqHeight || i2 > reqWidth) {
            int i4 = i / 2;
            int i5 = i2 / 2;
            while (i4 / i3 >= reqHeight && i5 / i3 >= reqWidth) {
                i3 *= 2;
            }
        }
        return i3;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options) {
        DisplayMetrics displayMetrics = HomeApplication.getInstance().getResources().getDisplayMetrics();
        return calculateInSampleSize(options, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }
}
