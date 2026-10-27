package defpackage;

import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
/* JADX INFO: loaded from: classes.dex */
public final class pc2 extends Drawable {
    public static final double a = Math.cos(Math.toRadians(45.0d));
    public static final /* synthetic */ int b = 0;

    public static float a(float f, float f2, boolean z) {
        if (!z) {
            return f * 1.5f;
        }
        return (float) (((1.0d - a) * ((double) f2)) + ((double) (f * 1.5f)));
    }
}
