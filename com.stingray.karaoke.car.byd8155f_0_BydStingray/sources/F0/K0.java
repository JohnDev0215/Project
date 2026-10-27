package F0;

/* JADX INFO: loaded from: classes.dex */
public abstract class K0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final T.R0 f2046a = new T.R0(U.f2127P);

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(M.v r4, M.e r5, s7.AbstractC2410c r6) {
        /*
            boolean r0 = r6 instanceof F0.I0
            if (r0 == 0) goto L13
            r0 = r6
            F0.I0 r0 = (F0.I0) r0
            int r1 = r0.f2041m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f2041m = r1
            goto L18
        L13:
            F0.I0 r0 = new F0.I0
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r1 = r0.f2041m
            r2 = 1
            if (r1 == 0) goto L34
            if (r1 == r2) goto L2b
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2b:
            h8.AbstractC1386c.u(r6)
            A9.k r4 = new A9.k
            r4.<init>()
            throw r4
        L34:
            h8.AbstractC1386c.u(r6)
            r6 = r4
            g0.n r6 = (g0.n) r6
            g0.n r6 = r6.l
            boolean r6 = r6.f14695x
            if (r6 == 0) goto L63
            androidx.compose.ui.node.Owner r6 = E0.AbstractC0156f.u(r4)
            E0.F r4 = E0.AbstractC0156f.t(r4)
            T.w r4 = r4.f1467F
            b0.e r4 = (b0.e) r4
            r4.getClass()
            T.R0 r1 = F0.K0.f2046a
            java.lang.Object r4 = T.C0735d.P(r4, r1)
            if (r4 != 0) goto L5d
            r0.f2041m = r2
            b(r6, r5, r0)
            return
        L5d:
            java.lang.ClassCastException r4 = new java.lang.ClassCastException
            r4.<init>()
            throw r4
        L63:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "establishTextInputSession called from an unattached node"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.K0.a(M.v, M.e, s7.c):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(androidx.compose.ui.node.Owner r4, M.e r5, s7.AbstractC2410c r6) {
        /*
            boolean r0 = r6 instanceof F0.J0
            if (r0 == 0) goto L13
            r0 = r6
            F0.J0 r0 = (F0.J0) r0
            int r1 = r0.f2044m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f2044m = r1
            goto L18
        L13:
            F0.J0 r0 = new F0.J0
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r1 = r0.f2044m
            r2 = 1
            if (r1 == 0) goto L40
            if (r1 == r2) goto L37
            r4 = 2
            if (r1 == r4) goto L2e
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2e:
            h8.AbstractC1386c.u(r6)
            A9.k r4 = new A9.k
            r4.<init>()
            throw r4
        L37:
            h8.AbstractC1386c.u(r6)
            A9.k r4 = new A9.k
            r4.<init>()
            throw r4
        L40:
            h8.AbstractC1386c.u(r6)
            r0.f2044m = r2
            androidx.compose.ui.platform.AndroidComposeView r4 = (androidx.compose.ui.platform.AndroidComposeView) r4
            r4.E(r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.K0.b(androidx.compose.ui.node.Owner, M.e, s7.c):void");
    }
}
