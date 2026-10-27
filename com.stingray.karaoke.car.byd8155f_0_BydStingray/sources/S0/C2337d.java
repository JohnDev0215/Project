package s0;

import h2.AbstractC1347d;
import n0.AbstractC1861K;
import n0.C1891t;
import x.AbstractC2641j;

/* JADX INFO: renamed from: s0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2337d {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final l9.n f22011k = new l9.n(9);
    public static int l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f22013b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f22014e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C2330D f22015f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f22016g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f22017h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f22018i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f22019j;

    public C2337d(String str, float f10, float f11, float f12, float f13, C2330D c2330d, long j3, int i7, boolean z6) {
        int i10;
        synchronized (f22011k) {
            i10 = l;
            l = i10 + 1;
        }
        this.f22012a = str;
        this.f22013b = f10;
        this.c = f11;
        this.d = f12;
        this.f22014e = f13;
        this.f22015f = c2330d;
        this.f22016g = j3;
        this.f22017h = i7;
        this.f22018i = z6;
        this.f22019j = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2337d)) {
            return false;
        }
        C2337d c2337d = (C2337d) obj;
        return A7.m.a(this.f22012a, c2337d.f22012a) && a1.e.a(this.f22013b, c2337d.f22013b) && a1.e.a(this.c, c2337d.c) && this.d == c2337d.d && this.f22014e == c2337d.f22014e && this.f22015f.equals(c2337d.f22015f) && C1891t.c(this.f22016g, c2337d.f22016g) && AbstractC1861K.p(this.f22017h, c2337d.f22017h) && this.f22018i == c2337d.f22018i;
    }

    public final int hashCode() {
        int iHashCode = (this.f22015f.hashCode() + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(this.f22012a.hashCode() * 31, 31, this.f22013b), 31, this.c), 31, this.d), 31, this.f22014e)) * 31;
        int i7 = C1891t.l;
        return Boolean.hashCode(this.f22018i) + AbstractC2641j.b(this.f22017h, AbstractC1347d.c(iHashCode, 31, this.f22016g), 31);
    }
}
