package S0;

import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0703o f7814a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A f7815b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f7816e;

    public L(AbstractC0703o abstractC0703o, A a10, int i7, int i10, Object obj) {
        this.f7814a = abstractC0703o;
        this.f7815b = a10;
        this.c = i7;
        this.d = i10;
        this.f7816e = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof L)) {
            return false;
        }
        L l = (L) obj;
        return A7.m.a(this.f7814a, l.f7814a) && A7.m.a(this.f7815b, l.f7815b) && w.a(this.c, l.c) && x.a(this.d, l.d) && A7.m.a(this.f7816e, l.f7816e);
    }

    public final int hashCode() {
        AbstractC0703o abstractC0703o = this.f7814a;
        int iB = AbstractC2641j.b(this.d, AbstractC2641j.b(this.c, (((abstractC0703o == null ? 0 : abstractC0703o.hashCode()) * 31) + this.f7815b.l) * 31, 31), 31);
        Object obj = this.f7816e;
        return iB + (obj != null ? obj.hashCode() : 0);
    }

    public final String toString() {
        return "TypefaceRequest(fontFamily=" + this.f7814a + ", fontWeight=" + this.f7815b + ", fontStyle=" + ((Object) w.b(this.c)) + ", fontSynthesis=" + ((Object) x.b(this.d)) + ", resourceLoaderCacheKey=" + this.f7816e + ')';
    }
}
