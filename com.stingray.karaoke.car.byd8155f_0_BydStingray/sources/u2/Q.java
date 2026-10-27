package u2;

import e2.C1208p;
import h2.AbstractC1346c;
import java.io.IOException;
import l5.C1778f;
import r2.C2285g;

/* JADX INFO: loaded from: classes.dex */
public final class Q implements a0 {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ T f22703m;

    public Q(T t10, int i7) {
        this.f22703m = t10;
        this.l = i7;
    }

    @Override // u2.a0
    public final boolean k() {
        T t10 = this.f22703m;
        return !t10.D() && t10.f22712F[this.l].i(t10.f22732Z);
    }

    @Override // u2.a0
    public final int p(C1778f c1778f, m2.g gVar, int i7) {
        int i10;
        T t10 = this.f22703m;
        int i11 = this.l;
        if (t10.D()) {
            return -3;
        }
        t10.y(i11);
        Z z6 = t10.f22712F[i11];
        boolean z10 = t10.f22732Z;
        z6.getClass();
        boolean z11 = (i7 & 2) != 0;
        C2285g c2285g = z6.f22770b;
        synchronized (z6) {
            try {
                gVar.f18956q = false;
                int i12 = z6.f22783s;
                i10 = -5;
                if (i12 != z6.f22780p) {
                    C1208p c1208p = ((Y) z6.c.n(z6.f22781q + i12)).f22766a;
                    if (!z11 && c1208p == z6.f22773g) {
                        int iH = z6.h(z6.f22783s);
                        if (z6.j(iH)) {
                            gVar.f15673m = z6.f22778m[iH];
                            if (z6.f22783s == z6.f22780p - 1 && (z10 || z6.f22787w)) {
                                gVar.b(536870912);
                            }
                            gVar.f18957r = z6.f22779n[iH];
                            c2285g.f21787a = z6.l[iH];
                            c2285g.f21788b = z6.f22777k[iH];
                            c2285g.c = z6.o[iH];
                            i10 = -4;
                        } else {
                            gVar.f18956q = true;
                            i10 = -3;
                        }
                    }
                    z6.k(c1208p, c1778f);
                } else {
                    if (!z10 && !z6.f22787w) {
                        C1208p c1208p2 = z6.f22790z;
                        if (c1208p2 == null || (!z11 && c1208p2 == z6.f22773g)) {
                            i10 = -3;
                        }
                        z6.k(c1208p2, c1778f);
                    }
                    gVar.f15673m = 4;
                    gVar.f18957r = Long.MIN_VALUE;
                    i10 = -4;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (i10 == -4 && !gVar.e(4)) {
            boolean z12 = (i7 & 1) != 0;
            if ((i7 & 4) == 0) {
                if (z12) {
                    X x8 = z6.f22769a;
                    X.e(x8.f22763e, gVar, z6.f22770b, x8.c);
                } else {
                    X x9 = z6.f22769a;
                    x9.f22763e = X.e(x9.f22763e, gVar, z6.f22770b, x9.c);
                }
            }
            if (!z12) {
                z6.f22783s++;
            }
        }
        if (i10 == -3) {
            t10.z(i11);
        }
        return i10;
    }

    @Override // u2.a0
    public final void r() throws IOException {
        T t10 = this.f22703m;
        Z z6 = t10.f22712F[this.l];
        q2.h hVar = z6.f22774h;
        if (hVar != null && hVar.getState() == 1) {
            q2.g gVarF = z6.f22774h.f();
            gVarF.getClass();
            throw gVarF;
        }
        int iY = t10.o.y(t10.f22722P);
        x2.n nVar = t10.f22744x;
        IOException iOException = nVar.c;
        if (iOException != null) {
            throw iOException;
        }
        x2.k kVar = nVar.f23758b;
        if (kVar != null) {
            if (iY == Integer.MIN_VALUE) {
                iY = kVar.l;
            }
            IOException iOException2 = kVar.o;
            if (iOException2 != null && kVar.f23751p > iY) {
                throw iOException2;
            }
        }
    }

    @Override // u2.a0
    public final int s(long j3) {
        int iG;
        T t10 = this.f22703m;
        int i7 = this.l;
        boolean z6 = false;
        if (t10.D()) {
            return 0;
        }
        t10.y(i7);
        Z z10 = t10.f22712F[i7];
        boolean z11 = t10.f22732Z;
        synchronized (z10) {
            int iH = z10.h(z10.f22783s);
            int i10 = z10.f22783s;
            int i11 = z10.f22780p;
            if ((i10 != i11) && j3 >= z10.f22779n[iH]) {
                if (j3 <= z10.f22786v || !z11) {
                    iG = z10.g(iH, i11 - i10, j3, true);
                    if (iG == -1) {
                    }
                } else {
                    iG = i11 - i10;
                }
            }
            iG = 0;
        }
        synchronized (z10) {
            if (iG >= 0) {
                try {
                    if (z10.f22783s + iG <= z10.f22780p) {
                        z6 = true;
                    }
                } finally {
                }
            }
            AbstractC1346c.c(z6);
            z10.f22783s += iG;
        }
        if (iG == 0) {
            t10.z(i7);
        }
        return iG;
    }
}
