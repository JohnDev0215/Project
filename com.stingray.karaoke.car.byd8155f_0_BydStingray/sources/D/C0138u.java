package D;

import C0.g0;

/* JADX INFO: renamed from: D.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0138u implements InterfaceC0136s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0 f1110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f1111b;

    public C0138u(g0 g0Var, long j3) {
        this.f1110a = g0Var;
        this.f1111b = j3;
    }

    public final float a() {
        long j3 = this.f1111b;
        if (!a1.a.c(j3)) {
            return Float.POSITIVE_INFINITY;
        }
        return this.f1110a.p0(a1.a.g(j3));
    }

    public final float b() {
        long j3 = this.f1111b;
        if (!a1.a.d(j3)) {
            return Float.POSITIVE_INFINITY;
        }
        return this.f1110a.p0(a1.a.h(j3));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0138u)) {
            return false;
        }
        C0138u c0138u = (C0138u) obj;
        return A7.m.a(this.f1110a, c0138u.f1110a) && a1.a.b(this.f1111b, c0138u.f1111b);
    }

    public final int hashCode() {
        return Long.hashCode(this.f1111b) + (this.f1110a.hashCode() * 31);
    }

    public final String toString() {
        return "BoxWithConstraintsScopeImpl(density=" + this.f1110a + ", constraints=" + ((Object) a1.a.l(this.f1111b)) + ')';
    }
}
