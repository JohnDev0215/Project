package D;

import h2.AbstractC1347d;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class r implements C0.K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0.c f1103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f1104b;

    public r(g0.c cVar, boolean z6) {
        this.f1103a = cVar;
        this.f1104b = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return A7.m.a(this.f1103a, rVar.f1103a) && this.f1104b == rVar.f1104b;
    }

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        int iJ;
        int i7;
        C0.V vB;
        boolean zIsEmpty = list.isEmpty();
        n7.v vVar = n7.v.l;
        if (zIsEmpty) {
            return m10.m0(a1.a.j(j3), a1.a.i(j3), vVar, C0131m.o);
        }
        long jA = this.f1104b ? j3 : a1.a.a(j3, 0, 0, 0, 0, 10);
        if (list.size() == 1) {
            C0.J j10 = (C0.J) list.get(0);
            Object objI = j10.i();
            C0129k c0129k = objI instanceof C0129k ? (C0129k) objI : null;
            if (c0129k != null ? c0129k.f1083z : false) {
                iJ = a1.a.j(j3);
                i7 = a1.a.i(j3);
                int iJ2 = a1.a.j(j3);
                int i10 = a1.a.i(j3);
                if (!(iJ2 >= 0 && i10 >= 0)) {
                    w0.c.e0("width(" + iJ2 + ") and height(" + i10 + ") must be >= 0");
                    throw null;
                }
                vB = j10.b(io.sentry.config.a.U(iJ2, iJ2, i10, i10));
            } else {
                vB = j10.b(jA);
                iJ = Math.max(a1.a.j(j3), vB.l);
                i7 = Math.max(a1.a.i(j3), vB.f871m);
            }
            int i11 = iJ;
            int i12 = i7;
            return m10.m0(i11, i12, vVar, new C0134p(vB, j10, m10, i11, i12, this));
        }
        C0.V[] vArr = new C0.V[list.size()];
        A7.x xVar = new A7.x();
        xVar.l = a1.a.j(j3);
        A7.x xVar2 = new A7.x();
        xVar2.l = a1.a.i(j3);
        int size = list.size();
        boolean z6 = false;
        for (int i13 = 0; i13 < size; i13++) {
            C0.J j11 = (C0.J) list.get(i13);
            Object objI2 = j11.i();
            C0129k c0129k2 = objI2 instanceof C0129k ? (C0129k) objI2 : null;
            if (c0129k2 != null ? c0129k2.f1083z : false) {
                z6 = true;
            } else {
                C0.V vB2 = j11.b(jA);
                vArr[i13] = vB2;
                xVar.l = Math.max(xVar.l, vB2.l);
                xVar2.l = Math.max(xVar2.l, vB2.f871m);
            }
        }
        if (z6) {
            int i14 = xVar.l;
            int i15 = i14 != Integer.MAX_VALUE ? i14 : 0;
            int i16 = xVar2.l;
            long jE = io.sentry.config.a.e(i15, i14, i16 != Integer.MAX_VALUE ? i16 : 0, i16);
            int size2 = list.size();
            for (int i17 = 0; i17 < size2; i17++) {
                C0.J j12 = (C0.J) list.get(i17);
                Object objI3 = j12.i();
                C0129k c0129k3 = objI3 instanceof C0129k ? (C0129k) objI3 : null;
                if (c0129k3 != null ? c0129k3.f1083z : false) {
                    vArr[i17] = j12.b(jE);
                }
            }
        }
        return m10.m0(xVar.l, xVar2.l, vVar, new C0135q(vArr, list, m10, xVar, xVar2, this));
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f1104b) + (this.f1103a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BoxMeasurePolicy(alignment=");
        sb.append(this.f1103a);
        sb.append(", propagateMinConstraints=");
        return AbstractC1347d.i(sb, this.f1104b, ')');
    }
}
