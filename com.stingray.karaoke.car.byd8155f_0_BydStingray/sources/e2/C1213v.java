package e2;

import h2.AbstractC1343A;

/* JADX INFO: renamed from: e2.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1213v {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C1213v f14343h = new C1213v(new C1212u());

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f14344i = Integer.toString(0, 36);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f14345j = Integer.toString(1, 36);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f14346k = Integer.toString(2, 36);
    public static final String l = Integer.toString(3, 36);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f14347m = Integer.toString(4, 36);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f14348n = Integer.toString(5, 36);
    public static final String o = Integer.toString(6, 36);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f14349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f14350b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f14351e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f14352f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f14353g;

    public C1213v(C1212u c1212u) {
        this.f14349a = AbstractC1343A.T(c1212u.f14340a);
        this.c = AbstractC1343A.T(c1212u.f14341b);
        this.f14350b = c1212u.f14340a;
        this.d = c1212u.f14341b;
        this.f14351e = c1212u.c;
        this.f14352f = c1212u.d;
        this.f14353g = c1212u.f14342e;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1213v)) {
            return false;
        }
        C1213v c1213v = (C1213v) obj;
        return this.f14350b == c1213v.f14350b && this.d == c1213v.d && this.f14351e == c1213v.f14351e && this.f14352f == c1213v.f14352f && this.f14353g == c1213v.f14353g;
    }

    public final int hashCode() {
        long j3 = this.f14350b;
        int i7 = ((int) (j3 ^ (j3 >>> 32))) * 31;
        long j10 = this.d;
        return ((((((i7 + ((int) ((j10 >>> 32) ^ j10))) * 31) + (this.f14351e ? 1 : 0)) * 31) + (this.f14352f ? 1 : 0)) * 31) + (this.f14353g ? 1 : 0);
    }
}
