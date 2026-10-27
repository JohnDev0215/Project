package j0;

import E0.InterfaceC0163m;
import E0.s0;
import e7.f;
import g0.n;

/* JADX INFO: renamed from: j0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1536d extends n implements s0, InterfaceC0163m {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C1536d f16998y;

    @Override // g0.n
    public final void A0() {
        this.f16998y = null;
    }

    public final boolean H0(f fVar) {
        C1536d c1536d = this.f16998y;
        if (c1536d == null) {
            return false;
        }
        return c1536d.H0(fVar);
    }

    public final void I0(f fVar) {
        C1536d c1536d = this.f16998y;
        if (c1536d != null) {
            c1536d.I0(fVar);
        }
    }

    public final void J0(f fVar) {
        C1536d c1536d = this.f16998y;
        if (c1536d != null) {
            c1536d.J0(fVar);
        }
        this.f16998y = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void K0(e7.f r5) {
        /*
            r4 = this;
            j0.d r0 = r4.f16998y
            if (r0 == 0) goto L1d
            java.lang.Object r1 = r5.f14480m
            android.view.DragEvent r1 = (android.view.DragEvent) r1
            float r2 = r1.getX()
            float r1 = r1.getY()
            long r1 = h6.e.a(r2, r1)
            boolean r1 = h6.m.e(r0, r1)
            r2 = 1
            if (r1 != r2) goto L1d
            r1 = r0
            goto L3a
        L1d:
            g0.n r1 = r4.l
            boolean r1 = r1.f14695x
            if (r1 != 0) goto L25
            r1 = 0
            goto L38
        L25:
            A7.z r1 = new A7.z
            r1.<init>()
            A.k r2 = new A.k
            r3 = 16
            r2.<init>(r1, r4, r5, r3)
            E0.AbstractC0156f.x(r4, r2)
            java.lang.Object r1 = r1.l
            E0.s0 r1 = (E0.s0) r1
        L38:
            j0.d r1 = (j0.C1536d) r1
        L3a:
            if (r1 == 0) goto L45
            if (r0 != 0) goto L45
            r1.I0(r5)
            r1.K0(r5)
            goto L66
        L45:
            if (r1 != 0) goto L4d
            if (r0 == 0) goto L4d
            r0.J0(r5)
            goto L66
        L4d:
            boolean r2 = A7.m.a(r1, r0)
            if (r2 != 0) goto L61
            if (r1 == 0) goto L5b
            r1.I0(r5)
            r1.K0(r5)
        L5b:
            if (r0 == 0) goto L66
            r0.J0(r5)
            goto L66
        L61:
            if (r1 == 0) goto L66
            r1.K0(r5)
        L66:
            r4.f16998y = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: j0.C1536d.K0(e7.f):void");
    }

    public final void L0(f fVar) {
        C1536d c1536d = this.f16998y;
        if (c1536d != null) {
            c1536d.L0(fVar);
        }
    }

    @Override // E0.s0
    public final Object r() {
        return C1534b.f16995a;
    }
}
