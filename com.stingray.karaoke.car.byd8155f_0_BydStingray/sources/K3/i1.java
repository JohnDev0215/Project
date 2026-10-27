package k3;

import android.os.Bundle;
import android.os.IBinder;
import d5.C1077F;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class i1 implements InterfaceC1677x0 {
    public final InterfaceC1651k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f17998m;

    public i1(InterfaceC1651k interfaceC1651k, int i7) {
        this.l = interfaceC1651k;
        this.f17998m = i7;
    }

    @Override // k3.InterfaceC1677x0
    public final void a() {
        this.l.a();
    }

    @Override // k3.InterfaceC1677x0
    public final void b(int i7, List list) {
        C1077F c1077fU = d5.I.u();
        for (int i10 = 0; i10 < list.size(); i10++) {
            c1077fU.a(((C1627b) list.get(i10)).b());
        }
        this.l.n(i7, c1077fU.h());
    }

    @Override // k3.InterfaceC1677x0
    public final void c(int i7, q1 q1Var, e2.S s6, boolean z6, boolean z10) {
        Bundle bundleT;
        int i10 = this.f17998m;
        AbstractC1346c.j(i10 != 0);
        boolean z11 = z6 || !s6.a(17);
        boolean z12 = z10 || !s6.a(30);
        InterfaceC1651k interfaceC1651k = this.l;
        if (i10 < 2) {
            interfaceC1651k.T0(i7, q1Var.q(s6, z6, true).t(i10), z11);
            return;
        }
        q1 q1VarQ = q1Var.q(s6, z6, z10);
        if (interfaceC1651k instanceof BinderC1658n0) {
            bundleT = new Bundle();
            bundleT.putBinder(q1.f18066l0, new p1(q1VarQ));
        } else {
            bundleT = q1VarQ.t(i10);
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(o1.d, z11);
        bundle.putBoolean(o1.f18025e, z12);
        interfaceC1651k.d1(i7, bundleT, bundle);
    }

    @Override // k3.InterfaceC1677x0
    public final void e(int i7, B1 b12, boolean z6, boolean z10, int i10) {
        this.l.S0(i7, b12.a(z6, z10).c(i10));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != i1.class) {
            return false;
        }
        IBinder iBinderAsBinder = this.l.asBinder();
        IBinder iBinderAsBinder2 = ((i1) obj).l.asBinder();
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(iBinderAsBinder, iBinderAsBinder2);
    }

    @Override // k3.InterfaceC1677x0
    public final void f(int i7) {
        this.l.f(i7);
    }

    @Override // k3.InterfaceC1677x0
    public final void g(int i7, C1 c1) {
        this.l.i1(i7, c1.b());
    }

    public final int hashCode() {
        return Objects.hash(this.l.asBinder());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0063, code lost:
    
        if (r2 != 4) goto L26;
     */
    @Override // k3.InterfaceC1677x0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j(int r8, k3.r r9) {
        /*
            r7 = this;
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = k3.r.f18093g
            int r2 = r9.f18098a
            r0.putInt(r1, r2)
            java.lang.String r1 = k3.r.f18094h
            long r2 = r9.f18099b
            r0.putLong(r1, r2)
            k3.o0 r1 = r9.f18100e
            if (r1 == 0) goto L3d
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.lang.String r3 = k3.C1660o0.f18019e
            android.os.Bundle r4 = r1.f18023a
            r2.putBundle(r3, r4)
            java.lang.String r3 = k3.C1660o0.f18020f
            boolean r4 = r1.f18024b
            r2.putBoolean(r3, r4)
            java.lang.String r3 = k3.C1660o0.f18021g
            boolean r4 = r1.c
            r2.putBoolean(r3, r4)
            java.lang.String r3 = k3.C1660o0.f18022h
            boolean r1 = r1.d
            r2.putBoolean(r3, r1)
            java.lang.String r1 = k3.r.f18095i
            r0.putBundle(r1, r2)
        L3d:
            k3.A1 r1 = r9.f18101f
            if (r1 == 0) goto L4a
            android.os.Bundle r1 = r1.b()
            java.lang.String r2 = k3.r.l
            r0.putBundle(r2, r1)
        L4a:
            java.lang.String r1 = k3.r.f18097k
            int r2 = r9.d
            r0.putInt(r1, r2)
            java.lang.Object r9 = r9.c
            if (r9 != 0) goto L56
            goto L99
        L56:
            r1 = 1
            if (r2 == r1) goto L9f
            java.lang.String r1 = k3.r.f18096j
            r3 = 2
            r4 = 0
            if (r2 == r3) goto L90
            r3 = 3
            if (r2 == r3) goto L66
            r9 = 4
            if (r2 == r9) goto L9f
            goto L99
        L66:
            e2.f r2 = new e2.f
            d5.I r9 = (d5.I) r9
            d5.F r3 = d5.I.u()
            r5 = r4
        L6f:
            int r6 = r9.size()
            if (r5 >= r6) goto L85
            java.lang.Object r6 = r9.get(r5)
            e2.F r6 = (e2.F) r6
            android.os.Bundle r6 = r6.d(r4)
            r3.a(r6)
            int r5 = r5 + 1
            goto L6f
        L85:
            d5.d0 r9 = r3.h()
            r2.<init>(r9)
            r0.putBinder(r1, r2)
            goto L99
        L90:
            e2.F r9 = (e2.F) r9
            android.os.Bundle r9 = r9.d(r4)
            r0.putBundle(r1, r9)
        L99:
            k3.k r7 = r7.l
            r7.t(r8, r0)
            return
        L9f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.i1.j(int, k3.r):void");
    }

    @Override // k3.InterfaceC1677x0
    public final void k(int i7, e2.S s6) {
        this.l.N0(i7, s6.d());
    }
}
