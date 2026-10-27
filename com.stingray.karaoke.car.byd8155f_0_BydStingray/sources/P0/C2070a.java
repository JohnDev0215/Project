package p0;

import A7.m;
import a1.k;
import n0.InterfaceC1889r;

/* JADX INFO: renamed from: p0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2070a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a1.b f20636a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public k f20637b;
    public InterfaceC1889r c;
    public long d;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2070a)) {
            return false;
        }
        C2070a c2070a = (C2070a) obj;
        return m.a(this.f20636a, c2070a.f20636a) && this.f20637b == c2070a.f20637b && m.a(this.c, c2070a.c) && m0.f.a(this.d, c2070a.d);
    }

    public final int hashCode() {
        return Long.hashCode(this.d) + ((this.c.hashCode() + ((this.f20637b.hashCode() + (this.f20636a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "DrawParams(density=" + this.f20636a + ", layoutDirection=" + this.f20637b + ", canvas=" + this.c + ", size=" + ((Object) m0.f.f(this.d)) + ')';
    }
}
