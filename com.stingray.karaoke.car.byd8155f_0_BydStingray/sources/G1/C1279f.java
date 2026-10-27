package g1;

import x.AbstractC2641j;

/* JADX INFO: renamed from: g1.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1279f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f14719a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14720b;
    public final o c;

    public C1279f(Object obj, int i7, o oVar) {
        this.f14719a = obj;
        this.f14720b = i7;
        this.c = oVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1279f)) {
            return false;
        }
        C1279f c1279f = (C1279f) obj;
        return this.f14719a.equals(c1279f.f14719a) && this.f14720b == c1279f.f14720b && this.c.equals(c1279f.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + AbstractC2641j.b(this.f14720b, this.f14719a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "HorizontalAnchor(id=" + this.f14719a + ", index=" + this.f14720b + ", reference=" + this.c + ')';
    }
}
