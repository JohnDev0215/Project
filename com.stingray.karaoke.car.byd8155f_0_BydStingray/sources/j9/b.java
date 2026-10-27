package j9;

import r9.C2322l;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final C2322l d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C2322l f17502e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C2322l f17503f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C2322l f17504g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C2322l f17505h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C2322l f17506i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2322l f17507a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2322l f17508b;
    public final int c;

    static {
        C2322l c2322l = C2322l.o;
        d = m6.b.p(":");
        f17502e = m6.b.p(":status");
        f17503f = m6.b.p(":method");
        f17504g = m6.b.p(":path");
        f17505h = m6.b.p(":scheme");
        f17506i = m6.b.p(":authority");
    }

    public b(C2322l c2322l, C2322l c2322l2) {
        A7.m.f(c2322l, "name");
        A7.m.f(c2322l2, "value");
        this.f17507a = c2322l;
        this.f17508b = c2322l2;
        this.c = c2322l2.d() + c2322l.d() + 32;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return A7.m.a(this.f17507a, bVar.f17507a) && A7.m.a(this.f17508b, bVar.f17508b);
    }

    public final int hashCode() {
        return this.f17508b.hashCode() + (this.f17507a.hashCode() * 31);
    }

    public final String toString() {
        return this.f17507a.r() + ": " + this.f17508b.r();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b(String str, String str2) {
        this(m6.b.p(str), m6.b.p(str2));
        A7.m.f(str, "name");
        A7.m.f(str2, "value");
        C2322l c2322l = C2322l.o;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b(C2322l c2322l, String str) {
        this(c2322l, m6.b.p(str));
        A7.m.f(c2322l, "name");
        A7.m.f(str, "value");
        C2322l c2322l2 = C2322l.o;
    }
}
