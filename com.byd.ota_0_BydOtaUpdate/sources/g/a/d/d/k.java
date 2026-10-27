package g.a.d.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {
    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        g.y(iArr, iArr2, iArr3);
        g.x(iArr, 8, iArr2, 8, iArr3, 16);
        int iE = g.e(iArr3, 8, iArr3, 16);
        int iC = iE + g.c(iArr3, 24, iArr3, 16, g.c(iArr3, 0, iArr3, 8, 0) + iE);
        int[] iArrH = g.h();
        int[] iArrH2 = g.h();
        boolean z = g.l(iArr, 8, iArr, 0, iArrH, 0) != g.l(iArr2, 8, iArr2, 0, iArrH2, 0);
        int[] iArrJ = g.j();
        g.y(iArrH, iArrH2, iArrJ);
        m.f(32, iC + (z ? m.d(16, iArrJ, 0, iArr3, 8) : m.M(16, iArrJ, 0, iArr3, 8)), iArr3, 24);
    }

    public static void b(int[] iArr, int[] iArr2) {
        g.F(iArr, iArr2);
        g.E(iArr, 8, iArr2, 16);
        int iE = g.e(iArr2, 8, iArr2, 16);
        int iC = iE + g.c(iArr2, 24, iArr2, 16, g.c(iArr2, 0, iArr2, 8, 0) + iE);
        int[] iArrH = g.h();
        g.l(iArr, 8, iArr, 0, iArrH, 0);
        int[] iArrJ = g.j();
        g.F(iArrH, iArrJ);
        m.f(32, iC + m.M(16, iArrJ, 0, iArr2, 8), iArr2, 24);
    }
}
