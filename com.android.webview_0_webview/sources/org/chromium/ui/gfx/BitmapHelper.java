package org.chromium.ui.gfx;

import android.graphics.Bitmap;
import defpackage.AbstractC1050kO;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BitmapHelper {
    public static Bitmap createBitmap(int i, int i2, int i3) {
        return Bitmap.createBitmap(i, i2, i3 != 1 ? i3 != 2 ? i3 != 4 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_4444 : Bitmap.Config.ALPHA_8);
    }

    public static int getBitmapFormatForConfig(Bitmap.Config config) {
        int i = AbstractC1050kO.a[config.ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    i2 = 4;
                    if (i != 4) {
                        return 0;
                    }
                }
            }
        }
        return i2;
    }

    public static int getByteCount(Bitmap bitmap) {
        return bitmap.getByteCount();
    }
}
