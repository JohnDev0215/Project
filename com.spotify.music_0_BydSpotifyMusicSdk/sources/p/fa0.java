package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class fa0 {
    public static final tz2 a = new tz2("UNDEFINED", 3);
    public static final tz2 b = new tz2("REUSABLE_CLAIMED", 3);

    /* JADX WARN: Removed duplicated region for block: B:33:0x0095 A[Catch: all -> 0x0074, DONT_GENERATE, TryCatch #2 {all -> 0x0074, blocks: (B:17:0x004f, B:19:0x005d, B:21:0x0063, B:34:0x0098, B:24:0x0076, B:26:0x0084, B:31:0x008f, B:33:0x0095, B:39:0x00a5, B:42:0x00ae, B:41:0x00ab, B:29:0x008a), top: B:54:0x004f, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(p.c00 r6, java.lang.Object r7, p.ry0 r8) {
        /*
            boolean r0 = r6 instanceof p.ea0
            if (r0 == 0) goto Lb8
            p.ea0 r6 = (p.ea0) r6
            java.lang.Throwable r0 = kotlin.Result.a(r7)
            if (r0 != 0) goto L16
            if (r8 == 0) goto L14
            p.iv r0 = new p.iv
            r0.<init>(r7, r8)
            goto L1d
        L14:
            r0 = r7
            goto L1d
        L16:
            p.hv r8 = new p.hv
            r1 = 0
            r8.<init>(r0, r1)
            r0 = r8
        L1d:
            p.c00 r8 = r6.m
            r8.getContext()
            kotlinx.coroutines.b r1 = r6.l
            boolean r2 = r1.I0()
            r3 = 1
            if (r2 == 0) goto L38
            r6.n = r0
            r6.k = r3
            p.y10 r7 = r8.getContext()
            r1.G0(r7, r6)
            goto Lbb
        L38:
            p.pi0 r1 = p.kk2.a()
            boolean r2 = r1.O0()
            if (r2 == 0) goto L4b
            r6.n = r0
            r6.k = r3
            r1.L0(r6)
            goto Lbb
        L4b:
            r1.N0(r3)
            r2 = 0
            p.y10 r4 = r8.getContext()     // Catch: java.lang.Throwable -> L74
            p.qo r5 = p.qo.o     // Catch: java.lang.Throwable -> L74
            p.w10 r4 = r4.E0(r5)     // Catch: java.lang.Throwable -> L74
            p.p61 r4 = (p.p61) r4     // Catch: java.lang.Throwable -> L74
            if (r4 == 0) goto L76
            boolean r5 = r4.b()     // Catch: java.lang.Throwable -> L74
            if (r5 != 0) goto L76
            kotlinx.coroutines.g r4 = (kotlinx.coroutines.g) r4     // Catch: java.lang.Throwable -> L74
            java.util.concurrent.CancellationException r7 = r4.B()     // Catch: java.lang.Throwable -> L74
            r6.b(r0, r7)     // Catch: java.lang.Throwable -> L74
            kotlin.Result$Failure r7 = kotlin.a.b(r7)     // Catch: java.lang.Throwable -> L74
            r6.k(r7)     // Catch: java.lang.Throwable -> L74
            goto L98
        L74:
            r7 = move-exception
            goto Laf
        L76:
            java.lang.Object r0 = r6.o     // Catch: java.lang.Throwable -> L74
            p.y10 r4 = r8.getContext()     // Catch: java.lang.Throwable -> L74
            java.lang.Object r0 = kotlinx.coroutines.internal.c.c(r4, r0)     // Catch: java.lang.Throwable -> L74
            p.tz2 r5 = kotlinx.coroutines.internal.c.a     // Catch: java.lang.Throwable -> L74
            if (r0 == r5) goto L89
            p.jq2 r5 = kotlinx.coroutines.a.e(r8, r4, r0)     // Catch: java.lang.Throwable -> L74
            goto L8a
        L89:
            r5 = r2
        L8a:
            r8.k(r7)     // Catch: java.lang.Throwable -> La2
            if (r5 == 0) goto L95
            boolean r7 = r5.b0()     // Catch: java.lang.Throwable -> L74
            if (r7 == 0) goto L98
        L95:
            kotlinx.coroutines.internal.c.a(r4, r0)     // Catch: java.lang.Throwable -> L74
        L98:
            boolean r7 = r1.Q0()     // Catch: java.lang.Throwable -> L74
            if (r7 != 0) goto L98
        L9e:
            r1.K0(r3)
            goto Lbb
        La2:
            r7 = move-exception
            if (r5 == 0) goto Lab
            boolean r8 = r5.b0()     // Catch: java.lang.Throwable -> L74
            if (r8 == 0) goto Lae
        Lab:
            kotlinx.coroutines.internal.c.a(r4, r0)     // Catch: java.lang.Throwable -> L74
        Lae:
            throw r7     // Catch: java.lang.Throwable -> L74
        Laf:
            r6.g(r7, r2)     // Catch: java.lang.Throwable -> Lb3
            goto L9e
        Lb3:
            r6 = move-exception
            r1.K0(r3)
            throw r6
        Lb8:
            r6.k(r7)
        Lbb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.fa0.a(p.c00, java.lang.Object, p.ry0):void");
    }
}
