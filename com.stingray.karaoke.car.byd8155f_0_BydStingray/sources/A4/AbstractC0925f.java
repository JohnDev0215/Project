package a4;

import android.graphics.PointF;
import h2.AbstractC1347d;

/* JADX INFO: renamed from: a4.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0925f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PointF f11108a = new PointF();

    public static PointF a(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    public static float b(float f10, float f11, float f12) {
        return Math.max(f11, Math.min(f12, f10));
    }

    public static int c(int i7) {
        return Math.max(0, Math.min(255, i7));
    }

    public static int d(float f10, float f11) {
        int i7 = (int) f10;
        int i10 = (int) f11;
        int i11 = i7 / i10;
        int i12 = i7 % i10;
        if (!((i7 ^ i10) >= 0) && i12 != 0) {
            i11--;
        }
        return i7 - (i10 * i11);
    }

    public static float e(float f10, float f11, float f12) {
        return AbstractC1347d.a(f11, f10, f12, f10);
    }
}
