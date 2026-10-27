package m;

import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
/* JADX INFO: loaded from: classes.dex */
class f extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final double f8024a = Math.cos(Math.toRadians(45.0d));

    static float a(float f7, float f8, boolean z6) {
        return z6 ? (float) (((double) f7) + ((1.0d - f8024a) * ((double) f8))) : f7;
    }

    static float b(float f7, float f8, boolean z6) {
        return z6 ? (float) (((double) (f7 * 1.5f)) + ((1.0d - f8024a) * ((double) f8))) : f7 * 1.5f;
    }
}
