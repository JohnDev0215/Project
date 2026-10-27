package d0;

import android.graphics.Color;

/* JADX INFO: compiled from: ColorUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<double[]> f2646a = new ThreadLocal<>();

    public static int a(int i3, int i4) {
        return 255 - (((255 - i4) * (255 - i3)) / 255);
    }

    public static int b(int i3, int i4) {
        int iAlpha = Color.alpha(i4);
        int iAlpha2 = Color.alpha(i3);
        int iA = a(iAlpha2, iAlpha);
        return Color.argb(iA, c(Color.red(i3), iAlpha2, Color.red(i4), iAlpha, iA), c(Color.green(i3), iAlpha2, Color.green(i4), iAlpha, iA), c(Color.blue(i3), iAlpha2, Color.blue(i4), iAlpha, iA));
    }

    public static int c(int i3, int i4, int i5, int i6, int i7) {
        if (i7 == 0) {
            return 0;
        }
        return (((i3 * 255) * i4) + ((i5 * i6) * (255 - i4))) / (i7 * 255);
    }

    public static int d(int i3, int i4) {
        if (i4 < 0 || i4 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i3 & 16777215) | (i4 << 24);
    }
}
