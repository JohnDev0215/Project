package org.chromium.ui.gfx;

import android.graphics.Bitmap;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("gfx")
public class BitmapHelper {
    private static Bitmap createBitmap(int i, int i2, int i3) {
        return Bitmap.createBitmap(i, i2, getBitmapConfigForFormat(i3));
    }

    /* JADX INFO: renamed from: org.chromium.ui.gfx.BitmapHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            $SwitchMap$android$graphics$Bitmap$Config = iArr;
            try {
                iArr[Bitmap.Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_8888.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static int getBitmapFormatForConfig(Bitmap.Config config) {
        int i = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()];
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

    private static Bitmap.Config getBitmapConfigForFormat(int i) {
        if (i == 1) {
            return Bitmap.Config.ALPHA_8;
        }
        if (i == 2) {
            return Bitmap.Config.ARGB_4444;
        }
        if (i == 4) {
            return Bitmap.Config.RGB_565;
        }
        return Bitmap.Config.ARGB_8888;
    }

    private static int getByteCount(Bitmap bitmap) {
        return bitmap.getByteCount();
    }
}
