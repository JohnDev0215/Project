package K;

/* JADX INFO: loaded from: classes.dex */
public final class G0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final N0.H f3161a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0.r f3162b = null;
    public C0.r c;

    public G0(N0.H h10, C0.r rVar) {
        this.f3161a = h10;
        this.c = rVar;
    }

    public final long a(long j3) {
        m0.d dVarO;
        C0.r rVar = this.f3162b;
        m0.d dVar = m0.d.f18881e;
        if (rVar != null) {
            if (rVar.D()) {
                C0.r rVar2 = this.c;
                dVarO = rVar2 != null ? rVar2.o(rVar, true) : null;
            } else {
                dVarO = dVar;
            }
            if (dVarO != null) {
                dVar = dVarO;
            }
        }
        float fD = m0.c.d(j3);
        float fD2 = dVar.f18882a;
        if (fD >= fD2) {
            float fD3 = m0.c.d(j3);
            fD2 = dVar.c;
            if (fD3 <= fD2) {
                fD2 = m0.c.d(j3);
            }
        }
        float fE = m0.c.e(j3);
        float fE2 = dVar.f18883b;
        if (fE >= fE2) {
            float fE3 = m0.c.e(j3);
            fE2 = dVar.d;
            if (fE3 <= fE2) {
                fE2 = m0.c.e(j3);
            }
        }
        return h6.e.a(fD2, fE2);
    }

    public final int b(long j3, boolean z6) {
        if (z6) {
            j3 = a(j3);
        }
        return this.f3161a.f4349b.e(d(j3));
    }

    public final boolean c(long j3) {
        long jD = d(a(j3));
        float fE = m0.c.e(jD);
        N0.H h10 = this.f3161a;
        int iC = h10.f4349b.c(fE);
        return m0.c.d(jD) >= h10.g(iC) && m0.c.d(jD) <= h10.h(iC);
    }

    public final long d(long j3) {
        C0.r rVar;
        C0.r rVar2 = this.f3162b;
        if (rVar2 != null) {
            if (!rVar2.D()) {
                rVar2 = null;
            }
            if (rVar2 != null && (rVar = this.c) != null) {
                C0.r rVar3 = rVar.D() ? rVar : null;
                if (rVar3 != null) {
                    return rVar2.k(rVar3, j3);
                }
            }
        }
        return j3;
    }

    public final long e(long j3) {
        C0.r rVar;
        C0.r rVar2 = this.f3162b;
        if (rVar2 != null) {
            if (!rVar2.D()) {
                rVar2 = null;
            }
            if (rVar2 != null && (rVar = this.c) != null) {
                C0.r rVar3 = rVar.D() ? rVar : null;
                if (rVar3 != null) {
                    return rVar3.k(rVar2, j3);
                }
            }
        }
        return j3;
    }
}
