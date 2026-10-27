package defpackage;

import android.graphics.Color;

/* JADX INFO: renamed from: o2, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1250o2 {
    static {
        new ThreadLocal();
    }

    public static int a(int i, int i2) {
        int iAlpha = Color.alpha(i2);
        int iAlpha2 = Color.alpha(i);
        int i3 = 255 - (((255 - iAlpha2) * (255 - iAlpha)) / 255);
        return Color.argb(i3, b(Color.red(i), iAlpha2, Color.red(i2), iAlpha, i3), b(Color.green(i), iAlpha2, Color.green(i2), iAlpha, i3), b(Color.blue(i), iAlpha2, Color.blue(i2), iAlpha, i3));
    }

    public static int b(int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            return 0;
        }
        return (((255 - i2) * (i3 * i4)) + ((i * 255) * i2)) / (i5 * 255);
    }

    public static int c(int i, int i2) {
        if (i2 < 0 || i2 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i & 16777215) | (i2 << 24);
    }
}
