package g1;

import x.AbstractC2641j;

/* JADX INFO: renamed from: g1.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1280g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f14721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14722b;
    public final C1278e c;

    public C1280g(Object obj, int i7, C1278e c1278e) {
        this.f14721a = obj;
        this.f14722b = i7;
        this.c = c1278e;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1280g)) {
            return false;
        }
        C1280g c1280g = (C1280g) obj;
        return this.f14721a.equals(c1280g.f14721a) && this.f14722b == c1280g.f14722b && this.c.equals(c1280g.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + AbstractC2641j.b(this.f14722b, this.f14721a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "VerticalAnchor(id=" + this.f14721a + ", index=" + this.f14722b + ", reference=" + this.c + ')';
    }
}
