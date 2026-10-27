package a6;

/* JADX INFO: compiled from: MathUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static float a(float f8, float f9, float f10, float f11) {
        return (float) Math.hypot(f10 - f8, f11 - f9);
    }

    public static float b(float f8, float f9, float f10, float f11, float f12, float f13) {
        return e(a(f8, f9, f10, f11), a(f8, f9, f12, f11), a(f8, f9, f12, f13), a(f8, f9, f10, f13));
    }

    public static boolean c(float f8, float f9, float f10) {
        return f8 + f10 >= f9;
    }

    public static float d(float f8, float f9, float f10) {
        return ((1.0f - f10) * f8) + (f10 * f9);
    }

    public static float e(float f8, float f9, float f10, float f11) {
        return (f8 <= f9 || f8 <= f10 || f8 <= f11) ? (f9 <= f10 || f9 <= f11) ? f10 > f11 ? f10 : f11 : f9 : f8;
    }
}
