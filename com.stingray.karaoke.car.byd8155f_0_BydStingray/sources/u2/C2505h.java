package u2;

import io.sentry.C1458d0;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: renamed from: u2.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2505h implements InterfaceC2497H, q2.l {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public B5.u f22834m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public q2.k f22835n;
    public final /* synthetic */ AbstractC2507j o;

    public C2505h(AbstractC2507j abstractC2507j, Object obj) {
        this.o = abstractC2507j;
        this.f22834m = new B5.u((CopyOnWriteArrayList) abstractC2507j.c.o, 0, (C2492C) null);
        this.f22835n = new q2.k(abstractC2507j.d.c, 0, null);
        this.l = obj;
    }

    @Override // u2.InterfaceC2497H
    public final void B(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y) {
        if (d(i7, c2492c)) {
            B5.u uVar = this.f22834m;
            C2521y c2521yE = e(c2521y, c2492c);
            uVar.getClass();
            uVar.k(new C2494E(uVar, c2516t, c2521yE, 2));
        }
    }

    @Override // q2.l
    public final void C(int i7, C2492C c2492c) {
        if (d(i7, c2492c)) {
            this.f22835n.a();
        }
    }

    @Override // u2.InterfaceC2497H
    public final void P(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y, IOException iOException, boolean z6) {
        if (d(i7, c2492c)) {
            B5.u uVar = this.f22834m;
            C2521y c2521yE = e(c2521y, c2492c);
            uVar.getClass();
            uVar.k(new C2495F(uVar, c2516t, c2521yE, iOException, z6));
        }
    }

    @Override // q2.l
    public final void a(int i7, C2492C c2492c, int i10) {
        if (d(i7, c2492c)) {
            this.f22835n.c(i10);
        }
    }

    @Override // q2.l
    public final void b(int i7, C2492C c2492c) {
        if (d(i7, c2492c)) {
            this.f22835n.e();
        }
    }

    @Override // q2.l
    public final void c(int i7, C2492C c2492c, Exception exc) {
        if (d(i7, c2492c)) {
            this.f22835n.d(exc);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(int r4, u2.C2492C r5) {
        /*
            r3 = this;
            u2.j r0 = r3.o
            java.lang.Object r1 = r3.l
            if (r5 == 0) goto Le
            u2.C r5 = r0.t(r1, r5)
            if (r5 != 0) goto Lf
            r3 = 0
            return r3
        Le:
            r5 = 0
        Lf:
            int r4 = r0.v(r4, r1)
            B5.u r1 = r3.f22834m
            int r2 = r1.f735m
            if (r2 != r4) goto L25
            int r2 = h2.AbstractC1343A.f15195a
            java.lang.Object r1 = r1.f736n
            u2.C r1 = (u2.C2492C) r1
            boolean r1 = java.util.Objects.equals(r1, r5)
            if (r1 != 0) goto L32
        L25:
            B5.u r1 = new B5.u
            B5.u r2 = r0.c
            java.lang.Object r2 = r2.o
            java.util.concurrent.CopyOnWriteArrayList r2 = (java.util.concurrent.CopyOnWriteArrayList) r2
            r1.<init>(r2, r4, r5)
            r3.f22834m = r1
        L32:
            q2.k r1 = r3.f22835n
            int r2 = r1.f21209a
            if (r2 != r4) goto L42
            int r2 = h2.AbstractC1343A.f15195a
            u2.C r1 = r1.f21210b
            boolean r1 = java.util.Objects.equals(r1, r5)
            if (r1 != 0) goto L4d
        L42:
            q2.k r1 = new q2.k
            q2.k r0 = r0.d
            java.util.concurrent.CopyOnWriteArrayList r0 = r0.c
            r1.<init>(r0, r4, r5)
            r3.f22835n = r1
        L4d:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.C2505h.d(int, u2.C):boolean");
    }

    public final C2521y e(C2521y c2521y, C2492C c2492c) {
        AbstractC2507j abstractC2507j = this.o;
        Object obj = this.l;
        long j3 = c2521y.d;
        long jU = abstractC2507j.u(obj, j3, c2492c);
        long j10 = c2521y.f22918e;
        long jU2 = abstractC2507j.u(obj, j10, c2492c);
        if (jU == j3 && jU2 == j10) {
            return c2521y;
        }
        return new C2521y(c2521y.f22916a, c2521y.f22917b, c2521y.c, jU, jU2);
    }

    @Override // q2.l
    public final void n(int i7, C2492C c2492c) {
        if (d(i7, c2492c)) {
            this.f22835n.b();
        }
    }

    @Override // u2.InterfaceC2497H
    public final void s(int i7, C2492C c2492c, C2521y c2521y) {
        if (d(i7, c2492c)) {
            B5.u uVar = this.f22834m;
            C2521y c2521yE = e(c2521y, c2492c);
            uVar.getClass();
            uVar.k(new C1458d0(uVar, 26, c2521yE));
        }
    }

    @Override // u2.InterfaceC2497H
    public final void u(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y) {
        if (d(i7, c2492c)) {
            B5.u uVar = this.f22834m;
            C2521y c2521yE = e(c2521y, c2492c);
            uVar.getClass();
            uVar.k(new C2494E(uVar, c2516t, c2521yE, 0));
        }
    }

    @Override // u2.InterfaceC2497H
    public final void v(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y) {
        if (d(i7, c2492c)) {
            B5.u uVar = this.f22834m;
            C2521y c2521yE = e(c2521y, c2492c);
            uVar.getClass();
            uVar.k(new C2494E(uVar, c2516t, c2521yE, 1));
        }
    }
}
