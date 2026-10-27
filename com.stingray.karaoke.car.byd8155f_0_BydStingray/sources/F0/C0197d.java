package F0;

/* JADX INFO: renamed from: F0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0197d extends AbstractC0193b {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static C0197d f2187p;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public N0.H f2188n;
    public L0.o o;

    @Override // F0.AbstractC0193b
    public final int[] a(int i7) {
        int iC;
        if (c().length() <= 0 || i7 >= c().length()) {
            return null;
        }
        try {
            L0.o oVar = this.o;
            if (oVar == null) {
                A7.m.j("node");
                throw null;
            }
            int iRound = Math.round(oVar.e().b());
            if (i7 <= 0) {
                i7 = 0;
            }
            N0.H h10 = this.f2188n;
            if (h10 == null) {
                A7.m.j("layoutResult");
                throw null;
            }
            int iF = h10.f(i7);
            N0.H h11 = this.f2188n;
            if (h11 == null) {
                A7.m.j("layoutResult");
                throw null;
            }
            float fD = h11.f4349b.d(iF) + iRound;
            N0.H h12 = this.f2188n;
            if (h12 == null) {
                A7.m.j("layoutResult");
                throw null;
            }
            if (h12 == null) {
                A7.m.j("layoutResult");
                throw null;
            }
            if (fD < h12.f4349b.d(r0.f4394f - 1)) {
                N0.H h13 = this.f2188n;
                if (h13 == null) {
                    A7.m.j("layoutResult");
                    throw null;
                }
                iC = h13.f4349b.c(fD);
            } else {
                N0.H h14 = this.f2188n;
                if (h14 == null) {
                    A7.m.j("layoutResult");
                    throw null;
                }
                iC = h14.f4349b.f4394f;
            }
            return b(i7, e(iC - 1, Y0.h.l) + 1);
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    @Override // F0.AbstractC0193b
    public final int[] d(int i7) {
        int iC;
        if (c().length() <= 0 || i7 <= 0) {
            return null;
        }
        try {
            L0.o oVar = this.o;
            if (oVar == null) {
                A7.m.j("node");
                throw null;
            }
            int iRound = Math.round(oVar.e().b());
            int length = c().length();
            if (length <= i7) {
                i7 = length;
            }
            N0.H h10 = this.f2188n;
            if (h10 == null) {
                A7.m.j("layoutResult");
                throw null;
            }
            int iF = h10.f(i7);
            N0.H h11 = this.f2188n;
            if (h11 == null) {
                A7.m.j("layoutResult");
                throw null;
            }
            float fD = h11.f4349b.d(iF) - iRound;
            if (fD > 0.0f) {
                N0.H h12 = this.f2188n;
                if (h12 == null) {
                    A7.m.j("layoutResult");
                    throw null;
                }
                iC = h12.f4349b.c(fD);
            } else {
                iC = 0;
            }
            if (i7 == c().length() && iC < iF) {
                iC++;
            }
            return b(e(iC, Y0.h.f10616m), i7);
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public final int e(int i7, Y0.h hVar) {
        N0.H h10 = this.f2188n;
        if (h10 == null) {
            A7.m.j("layoutResult");
            throw null;
        }
        int i10 = h10.i(i7);
        N0.H h11 = this.f2188n;
        if (h11 == null) {
            A7.m.j("layoutResult");
            throw null;
        }
        if (hVar != h11.j(i10)) {
            N0.H h12 = this.f2188n;
            if (h12 != null) {
                return h12.i(i7);
            }
            A7.m.j("layoutResult");
            throw null;
        }
        if (this.f2188n != null) {
            return r4.e(i7, false) - 1;
        }
        A7.m.j("layoutResult");
        throw null;
    }
}
