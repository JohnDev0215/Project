package g.a.d.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {
    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        e.x(iArr, iArr2, iArr3);
        e.w(iArr, 6, iArr2, 6, iArr3, 12);
        int iD = e.d(iArr3, 6, iArr3, 12);
        int iC = iD + e.c(iArr3, 18, iArr3, 12, e.c(iArr3, 0, iArr3, 6, 0) + iD);
        int[] iArrG = e.g();
        int[] iArrG2 = e.g();
        boolean z = e.k(iArr, 6, iArr, 0, iArrG, 0) != e.k(iArr2, 6, iArr2, 0, iArrG2, 0);
        int[] iArrI = e.i();
        e.x(iArrG, iArrG2, iArrI);
        m.f(24, iC + (z ? m.d(12, iArrI, 0, iArr3, 6) : m.M(12, iArrI, 0, iArr3, 6)), iArr3, 18);
    }

    public static void b(int[] iArr, int[] iArr2) {
        e.D(iArr, iArr2);
        e.C(iArr, 6, iArr2, 12);
        int iD = e.d(iArr2, 6, iArr2, 12);
        int iC = iD + e.c(iArr2, 18, iArr2, 12, e.c(iArr2, 0, iArr2, 6, 0) + iD);
        int[] iArrG = e.g();
        e.k(iArr, 6, iArr, 0, iArrG, 0);
        int[] iArrI = e.i();
        e.D(iArrG, iArrI);
        m.f(24, iC + m.M(12, iArrI, 0, iArr2, 6), iArr2, 18);
    }
}
