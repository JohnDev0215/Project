package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class It {
    public static int a(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    public static void b(String str, int i, int i2, int i3, int i4) {
        Lt.a.e(str, i, i2, i3, i4);
    }

    public static void c(String str, long j, long j2, long j3, int i) {
        Lt.a.e(str, a(j), a(j2), a(j3), i);
    }

    public static void d(String str, int i, int i2) {
        Lt.a.d(str, i, 1, i2, i2 + 1);
    }

    public static void e(String str, long j) {
        c(str, j, 1L, 10000L, 50);
    }
}
