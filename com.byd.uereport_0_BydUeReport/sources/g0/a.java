package g0;

import android.graphics.Color;

/* JADX INFO: compiled from: ColorUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<double[]> f4980a = new ThreadLocal<>();

    public static int a(int i8, int i9) {
        return 255 - (((255 - i9) * (255 - i8)) / 255);
    }

    public static int b(int i8, int i9) {
        int iAlpha = Color.alpha(i9);
        int iAlpha2 = Color.alpha(i8);
        int iA = a(iAlpha2, iAlpha);
        return Color.argb(iA, c(Color.red(i8), iAlpha2, Color.red(i9), iAlpha, iA), c(Color.green(i8), iAlpha2, Color.green(i9), iAlpha, iA), c(Color.blue(i8), iAlpha2, Color.blue(i9), iAlpha, iA));
    }

    public static int c(int i8, int i9, int i10, int i11, int i12) {
        if (i12 == 0) {
            return 0;
        }
        return (((i8 * 255) * i9) + ((i10 * i11) * (255 - i9))) / (i12 * 255);
    }

    public static int d(int i8, int i9) {
        if (i9 < 0 || i9 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i8 & 16777215) | (i9 << 24);
    }
}
