package K0;

import O0.x;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3500a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3501b;
    public final Object c;

    public j(int i7, e eVar) {
        this.f3500a = i7;
        this.c = eVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float a(int r6, boolean r7, boolean r8, boolean r9) {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            java.lang.Object r2 = r5.c
            O0.x r2 = (O0.x) r2
            if (r7 == 0) goto L1d
            android.text.Layout r3 = r2.f5050f
            int r3 = O0.s.c(r3, r6, r7)
            android.text.Layout r4 = r2.f5050f
            int r4 = r4.getLineStart(r3)
            int r3 = r2.f(r3)
            if (r6 == r4) goto L1f
            if (r6 != r3) goto L1d
            goto L1f
        L1d:
            r3 = r1
            goto L20
        L1f:
            r3 = r0
        L20:
            int r4 = r6 * 4
            if (r9 == 0) goto L28
            if (r3 == 0) goto L2d
            r0 = r1
            goto L2d
        L28:
            if (r3 == 0) goto L2c
            r0 = 2
            goto L2d
        L2c:
            r0 = 3
        L2d:
            int r4 = r4 + r0
            int r0 = r5.f3500a
            if (r0 != r4) goto L35
            float r5 = r5.f3501b
            return r5
        L35:
            if (r9 == 0) goto L3c
            float r6 = r2.h(r6, r7)
            goto L40
        L3c:
            float r6 = r2.i(r6, r7)
        L40:
            if (r8 == 0) goto L46
            r5.f3500a = r4
            r5.f3501b = r6
        L46:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: K0.j.a(int, boolean, boolean, boolean):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object b(float r5, s7.AbstractC2410c r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof K0.i
            if (r0 == 0) goto L13
            r0 = r6
            K0.i r0 = (K0.i) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            K0.i r0 = new K0.i
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.f3498m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            K0.j r4 = r0.l
            h8.AbstractC1386c.u(r6)
            goto L48
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            h8.AbstractC1386c.u(r6)
            java.lang.Float r6 = new java.lang.Float
            r6.<init>(r5)
            r0.l = r4
            r0.o = r3
            java.lang.Object r5 = r4.c
            K0.e r5 = (K0.e) r5
            java.lang.Object r6 = r5.invoke(r6, r0)
            if (r6 != r1) goto L48
            return r1
        L48:
            java.lang.Number r6 = (java.lang.Number) r6
            float r5 = r6.floatValue()
            float r6 = r4.f3501b
            float r6 = r6 + r5
            r4.f3501b = r6
            m7.q r4 = m7.q.f19267a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: K0.j.b(float, s7.c):java.lang.Object");
    }

    public j(x xVar) {
        this.c = xVar;
        this.f3500a = -1;
    }
}
