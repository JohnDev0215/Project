package a2;

/* JADX INFO: compiled from: GammaEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static float a(float f8) {
        return f8 <= 0.04045f ? f8 / 12.92f : (float) Math.pow((f8 + 0.055f) / 1.055f, 2.4000000953674316d);
    }

    public static float b(float f8) {
        return f8 <= 0.0031308f ? f8 * 12.92f : (float) ((Math.pow(f8, 0.4166666567325592d) * 1.0549999475479126d) - 0.054999999701976776d);
    }

    public static int c(float f8, int i8, int i9) {
        if (i8 == i9) {
            return i8;
        }
        float f9 = ((i8 >> 24) & 255) / 255.0f;
        float fA = a(((i8 >> 16) & 255) / 255.0f);
        float fA2 = a(((i8 >> 8) & 255) / 255.0f);
        float fA3 = a((i8 & 255) / 255.0f);
        float fA4 = a(((i9 >> 16) & 255) / 255.0f);
        float f10 = f9 + (((((i9 >> 24) & 255) / 255.0f) - f9) * f8);
        float fA5 = fA2 + ((a(((i9 >> 8) & 255) / 255.0f) - fA2) * f8);
        float fA6 = fA3 + (f8 * (a((i9 & 255) / 255.0f) - fA3));
        return (Math.round(b(fA + ((fA4 - fA) * f8)) * 255.0f) << 16) | (Math.round(f10 * 255.0f) << 24) | (Math.round(b(fA5) * 255.0f) << 8) | Math.round(b(fA6) * 255.0f);
    }
}
