package org.chromium.ui.gfx;

import android.graphics.Bitmap;
import android.util.Log;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class BitmapHelper {

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            a = iArr;
            try {
                iArr[Bitmap.Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Bitmap.Config.ARGB_4444.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Bitmap.Config.ARGB_8888.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[Bitmap.Config.RGB_565.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @CalledByNative
    public static Bitmap createBitmap(int i, int i2, int i3, boolean z) {
        try {
            return Bitmap.createBitmap(i, i2, i3 != 4 ? i3 != 7 ? i3 != 8 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.ALPHA_8 : Bitmap.Config.ARGB_4444 : Bitmap.Config.RGB_565);
        } catch (OutOfMemoryError e) {
            if (!z) {
                throw e;
            }
            Log.w("cr_BitmapHelper", "createBitmap OOM-ed", e);
            return null;
        }
    }

    @CalledByNative
    public static int getBitmapFormatForConfig(Bitmap.Config config) {
        int i = a.a[config.ordinal()];
        int i2 = 1;
        if (i == 1) {
            return 8;
        }
        if (i == 2) {
            return 7;
        }
        if (i != 3) {
            i2 = 4;
            if (i != 4) {
                return 0;
            }
        }
        return i2;
    }

    @CalledByNative
    public static int getByteCount(Bitmap bitmap) {
        return bitmap.getByteCount();
    }
}
