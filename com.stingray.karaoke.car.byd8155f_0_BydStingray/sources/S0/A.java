package S0;

import n7.AbstractC1999n;

/* JADX INFO: loaded from: classes.dex */
public final class A implements Comparable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final A f7795m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final A f7796n;
    public static final A o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final A f7797p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final A f7798q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final A f7799r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final A f7800s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final A f7801t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final A f7802u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final A f7803v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final A f7804w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final A f7805x;
    public final int l;

    static {
        A a10 = new A(100);
        A a11 = new A(200);
        A a12 = new A(300);
        A a13 = new A(400);
        f7795m = a13;
        A a14 = new A(500);
        f7796n = a14;
        A a15 = new A(600);
        o = a15;
        A a16 = new A(700);
        A a17 = new A(800);
        A a18 = new A(900);
        f7797p = a10;
        f7798q = a11;
        f7799r = a12;
        f7800s = a13;
        f7801t = a14;
        f7802u = a15;
        f7803v = a16;
        f7804w = a17;
        f7805x = a18;
        AbstractC1999n.D(a10, a11, a12, a13, a14, a15, a16, a17, a18);
    }

    public A(int i7) {
        this.l = i7;
        if (1 > i7 || i7 >= 1001) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Font weight can be in range [1, 1000]. Current value: ").toString());
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(A a10) {
        return A7.m.g(this.l, a10.l);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof A) {
            return this.l == ((A) obj).l;
        }
        return false;
    }

    public final int hashCode() {
        return this.l;
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.j(new StringBuilder("FontWeight(weight="), this.l, ')');
    }
}
