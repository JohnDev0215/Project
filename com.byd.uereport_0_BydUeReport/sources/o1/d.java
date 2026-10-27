package o1;

/* JADX INFO: compiled from: L.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f6733a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f6734b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String[] f6735c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long[] f6736d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f6737e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f6738f;

    public static void a(String str) {
        if (f6734b) {
            int i8 = f6737e;
            if (i8 == 20) {
                f6738f++;
                return;
            }
            f6735c[i8] = str;
            f6736d[i8] = System.nanoTime();
            k0.a.a(str);
            f6737e++;
        }
    }

    public static float b(String str) {
        int i8 = f6738f;
        if (i8 > 0) {
            f6738f = i8 - 1;
            return 0.0f;
        }
        if (!f6734b) {
            return 0.0f;
        }
        int i9 = f6737e - 1;
        f6737e = i9;
        if (i9 == -1) {
            throw new IllegalStateException("Can't end trace section. There are none.");
        }
        if (str.equals(f6735c[i9])) {
            k0.a.b();
            return (System.nanoTime() - f6736d[f6737e]) / 1000000.0f;
        }
        throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + f6735c[f6737e] + ".");
    }
}
