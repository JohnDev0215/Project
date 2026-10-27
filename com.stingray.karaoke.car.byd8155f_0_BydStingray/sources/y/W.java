package y;

import S8.AbstractC0727y;
import y0.AbstractC2737o;
import y0.C2730h;
import y0.EnumC2731i;

/* JADX INFO: loaded from: classes.dex */
public final class W extends g0.n implements E0.n0 {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C.k f23871y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C.h f23872z;

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object H0(y.W r4, s7.AbstractC2410c r5) throws java.lang.Throwable {
        /*
            r4.getClass()
            boolean r0 = r5 instanceof y.S
            if (r0 == 0) goto L16
            r0 = r5
            y.S r0 = (y.S) r0
            int r1 = r0.f23866p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.f23866p = r1
            goto L1b
        L16:
            y.S r0 = new y.S
            r0.<init>(r4, r5)
        L1b:
            java.lang.Object r5 = r0.f23865n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f23866p
            r3 = 1
            if (r2 == 0) goto L38
            if (r2 != r3) goto L30
            C.h r4 = r0.f23864m
            y.W r0 = r0.l
            h8.AbstractC1386c.u(r5)
            r5 = r4
            r4 = r0
            goto L53
        L30:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L38:
            h8.AbstractC1386c.u(r5)
            C.h r5 = r4.f23872z
            if (r5 != 0) goto L55
            C.h r5 = new C.h
            r5.<init>()
            C.k r2 = r4.f23871y
            r0.l = r4
            r0.f23864m = r5
            r0.f23866p = r3
            java.lang.Object r0 = r2.a(r5, r0)
            if (r0 != r1) goto L53
            goto L57
        L53:
            r4.f23872z = r5
        L55:
            m7.q r1 = m7.q.f19267a
        L57:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: y.W.H0(y.W, s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object I0(y.W r4, s7.AbstractC2410c r5) throws java.lang.Throwable {
        /*
            r4.getClass()
            boolean r0 = r5 instanceof y.T
            if (r0 == 0) goto L16
            r0 = r5
            y.T r0 = (y.T) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.o = r1
            goto L1b
        L16:
            y.T r0 = new y.T
            r0.<init>(r4, r5)
        L1b:
            java.lang.Object r5 = r0.f23867m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            y.W r4 = r0.l
            h8.AbstractC1386c.u(r5)
            goto L4d
        L2c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L34:
            h8.AbstractC1386c.u(r5)
            C.h r5 = r4.f23872z
            if (r5 == 0) goto L50
            C.i r2 = new C.i
            r2.<init>(r5)
            C.k r5 = r4.f23871y
            r0.l = r4
            r0.o = r3
            java.lang.Object r5 = r5.a(r2, r0)
            if (r5 != r1) goto L4d
            goto L52
        L4d:
            r5 = 0
            r4.f23872z = r5
        L50:
            m7.q r1 = m7.q.f19267a
        L52:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: y.W.I0(y.W, s7.c):java.lang.Object");
    }

    @Override // g0.n
    public final void A0() {
        J0();
    }

    public final void J0() {
        C.h hVar = this.f23872z;
        if (hVar != null) {
            this.f23871y.b(new C.i(hVar));
            this.f23872z = null;
        }
    }

    @Override // E0.n0
    public final void R(C2730h c2730h, EnumC2731i enumC2731i, long j3) {
        if (enumC2731i == EnumC2731i.f24067m) {
            int i7 = c2730h.d;
            if (AbstractC2737o.d(i7, 4)) {
                AbstractC0727y.w(v0(), null, null, new U(this, null), 3);
            } else if (AbstractC2737o.d(i7, 5)) {
                AbstractC0727y.w(v0(), null, null, new V(this, null), 3);
            }
        }
    }

    @Override // E0.n0
    public final void e0() {
        J0();
    }
}
