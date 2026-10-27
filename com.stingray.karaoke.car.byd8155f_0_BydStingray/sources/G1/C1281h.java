package g1;

import C0.InterfaceC0112t;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: g1.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1281h implements InterfaceC0112t {
    public final C1278e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC2782k f14723m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f14724n;

    public C1281h(C1278e c1278e, InterfaceC2782k interfaceC2782k) {
        this.l = c1278e;
        this.f14723m = interfaceC2782k;
        this.f14724n = c1278e.c;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1281h) {
            C1281h c1281h = (C1281h) obj;
            if (A7.m.a(this.l.c, c1281h.l.c) && this.f14723m == c1281h.f14723m) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f14723m.hashCode() + (this.l.c.hashCode() * 31);
    }

    @Override // C0.InterfaceC0112t
    public final Object z() {
        return this.f14724n;
    }
}
