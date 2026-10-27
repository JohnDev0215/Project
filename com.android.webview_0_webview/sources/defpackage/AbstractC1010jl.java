package defpackage;

/* JADX INFO: renamed from: jl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1010jl {
    public int a;
    public int b = 100;
    public int c = Integer.MAX_VALUE;
    public C1066kl d;

    public AbstractC1010jl(AbstractC0787fl abstractC0787fl) {
    }

    public static int b(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long c(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static AbstractC1010jl f(byte[] bArr) {
        int length = bArr.length;
        C0843gl c0843gl = new C0843gl(bArr, 0, length, false, null);
        try {
            c0843gl.h(length);
            return c0843gl;
        } catch (C0155Il e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract void a(int i);

    public abstract int d();

    public abstract boolean e();

    public abstract void g(int i);

    public abstract int h(int i);

    public abstract boolean i();

    public abstract AbstractC0731el j();

    public abstract double k();

    public abstract int l();

    public abstract int m();

    public abstract long n();

    public abstract float o();

    public abstract int p();

    public abstract long q();

    public abstract int r();

    public abstract long s();

    public abstract int t();

    public abstract long u();

    public abstract String v();

    public abstract String w();

    public abstract int x();

    public abstract int y();

    public abstract long z();
}
