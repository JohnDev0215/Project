package z;

import android.graphics.Color;

/* JADX INFO: compiled from: ColorUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<double[]> f10020a = new ThreadLocal<>();

    private static int a(int i7, int i8) {
        return 255 - (((255 - i8) * (255 - i7)) / 255);
    }

    public static int b(int i7, int i8) {
        int iAlpha = Color.alpha(i8);
        int iAlpha2 = Color.alpha(i7);
        int iA = a(iAlpha2, iAlpha);
        return Color.argb(iA, c(Color.red(i7), iAlpha2, Color.red(i8), iAlpha, iA), c(Color.green(i7), iAlpha2, Color.green(i8), iAlpha, iA), c(Color.blue(i7), iAlpha2, Color.blue(i8), iAlpha, iA));
    }

    private static int c(int i7, int i8, int i9, int i10, int i11) {
        if (i11 == 0) {
            return 0;
        }
        return (((i7 * 255) * i8) + ((i9 * i10) * (255 - i8))) / (i11 * 255);
    }

    public static int d(int i7, int i8) {
        if (i8 < 0 || i8 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i7 & 16777215) | (i8 << 24);
    }
}
