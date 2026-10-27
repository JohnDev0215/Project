package z2;

/* JADX INFO: compiled from: MathUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static float a(float f7, float f8, float f9, float f10) {
        return (float) Math.hypot(f9 - f7, f10 - f8);
    }

    public static float b(float f7, float f8, float f9, float f10, float f11, float f12) {
        return d(a(f7, f8, f9, f10), a(f7, f8, f11, f10), a(f7, f8, f11, f12), a(f7, f8, f9, f12));
    }

    public static float c(float f7, float f8, float f9) {
        return ((1.0f - f9) * f7) + (f9 * f8);
    }

    private static float d(float f7, float f8, float f9, float f10) {
        return (f7 <= f8 || f7 <= f9 || f7 <= f10) ? (f8 <= f9 || f8 <= f10) ? f9 > f10 ? f9 : f10 : f8 : f7;
    }
}
