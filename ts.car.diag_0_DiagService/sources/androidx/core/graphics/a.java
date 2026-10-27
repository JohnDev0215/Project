package androidx.core.graphics;

import android.graphics.Color;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.byd.mediacenter.api.media.MediaControl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<double[]> f479a = new ThreadLocal<>();

    public static void a(@IntRange(from = 0, to = 255) int i, @IntRange(from = 0, to = 255) int i2, @IntRange(from = 0, to = 255) int i3, @NonNull double[] dArr) {
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d = ((double) i) / 255.0d;
        double dPow = d < 0.04045d ? d / 12.92d : Math.pow((d + 0.055d) / 1.055d, 2.4d);
        double d2 = ((double) i2) / 255.0d;
        double dPow2 = d2 < 0.04045d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
        double d3 = ((double) i3) / 255.0d;
        double dPow3 = d3 < 0.04045d ? d3 / 12.92d : Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
        dArr[0] = ((0.4124d * dPow) + (0.3576d * dPow2) + (0.1805d * dPow3)) * 100.0d;
        dArr[1] = ((0.2126d * dPow) + (0.7152d * dPow2) + (0.0722d * dPow3)) * 100.0d;
        dArr[2] = ((dPow * 0.0193d) + (dPow2 * 0.1192d) + (dPow3 * 0.9505d)) * 100.0d;
    }

    public static double b(@ColorInt int i, @ColorInt int i2) {
        if (Color.alpha(i2) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(i2));
        }
        if (Color.alpha(i) < 255) {
            i = f(i, i2);
        }
        double dC = c(i) + 0.05d;
        double dC2 = c(i2) + 0.05d;
        return Math.max(dC, dC2) / Math.min(dC, dC2);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public static double c(@ColorInt int i) {
        double[] dArrH = h();
        d(i, dArrH);
        return dArrH[1] / 100.0d;
    }

    @VisibleForTesting
    static float circularInterpolate(float f, float f2, float f3) {
        if (Math.abs(f2 - f) > 180.0f) {
            if (f2 > f) {
                f += 360.0f;
            } else {
                f2 += 360.0f;
            }
        }
        return (f + ((f2 - f) * f3)) % 360.0f;
    }

    public static void d(@ColorInt int i, @NonNull double[] dArr) {
        a(Color.red(i), Color.green(i), Color.blue(i), dArr);
    }

    private static int e(int i, int i2) {
        return 255 - (((255 - i2) * (255 - i)) / MediaControl.ACTION_NONE);
    }

    public static int f(@ColorInt int i, @ColorInt int i2) {
        int iAlpha = Color.alpha(i2);
        int iAlpha2 = Color.alpha(i);
        int iE = e(iAlpha2, iAlpha);
        return Color.argb(iE, g(Color.red(i), iAlpha2, Color.red(i2), iAlpha, iE), g(Color.green(i), iAlpha2, Color.green(i2), iAlpha, iE), g(Color.blue(i), iAlpha2, Color.blue(i2), iAlpha, iE));
    }

    private static int g(int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            return 0;
        }
        return (((i * MediaControl.ACTION_NONE) * i2) + ((i3 * i4) * (255 - i2))) / (i5 * MediaControl.ACTION_NONE);
    }

    private static double[] h() {
        double[] dArr = f479a.get();
        if (dArr != null) {
            return dArr;
        }
        double[] dArr2 = new double[3];
        f479a.set(dArr2);
        return dArr2;
    }

    @ColorInt
    public static int i(@ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        if (i2 < 0 || i2 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i & 16777215) | (i2 << 24);
    }
}
