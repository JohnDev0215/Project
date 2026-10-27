package w2;

import c5.InterfaceC1028e;

/* JADX INFO: renamed from: w2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C2590d implements InterfaceC1028e {
    public final /* synthetic */ o l;

    /* JADX WARN: Removed duplicated region for block: B:13:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006b A[Catch: all -> 0x0069, TryCatch #0 {all -> 0x0069, blocks: (B:4:0x000b, B:6:0x0011, B:8:0x0015, B:11:0x001b, B:37:0x005c, B:39:0x0060, B:41:0x0064, B:46:0x006b, B:48:0x006f, B:50:0x0073, B:52:0x0077, B:54:0x0081, B:56:0x008d, B:59:0x0098), top: B:63:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0097  */
    @Override // c5.InterfaceC1028e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean apply(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 0
            r1 = 2
            r2 = -1
            r3 = 1
            w2.o r7 = r7.l
            e2.p r8 = (e2.C1208p) r8
            java.lang.Object r4 = r7.c
            monitor-enter(r4)
            w2.i r5 = r7.f23264g     // Catch: java.lang.Throwable -> L69
            boolean r5 = r5.f23230m0     // Catch: java.lang.Throwable -> L69
            if (r5 == 0) goto L97
            boolean r5 = r7.f23263f     // Catch: java.lang.Throwable -> L69
            if (r5 != 0) goto L97
            int r5 = r8.f14295C     // Catch: java.lang.Throwable -> L69
            if (r5 == r2) goto L97
            if (r5 <= r1) goto L97
            java.lang.String r5 = r8.f14316n     // Catch: java.lang.Throwable -> L69
            if (r5 != 0) goto L21
        L1f:
            r1 = r0
            goto L58
        L21:
            int r6 = r5.hashCode()
            switch(r6) {
                case -2123537834: goto L49;
                case 187078296: goto L3e;
                case 187078297: goto L35;
                case 1504578661: goto L2a;
                default: goto L28;
            }
        L28:
            r1 = r2
            goto L53
        L2a:
            java.lang.String r1 = "audio/eac3"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L33
            goto L28
        L33:
            r1 = 3
            goto L53
        L35:
            java.lang.String r6 = "audio/ac4"
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L53
            goto L28
        L3e:
            java.lang.String r1 = "audio/ac3"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L47
            goto L28
        L47:
            r1 = r3
            goto L53
        L49:
            java.lang.String r1 = "audio/eac3-joc"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L52
            goto L28
        L52:
            r1 = r0
        L53:
            switch(r1) {
                case 0: goto L57;
                case 1: goto L57;
                case 2: goto L57;
                case 3: goto L57;
                default: goto L56;
            }
        L56:
            goto L1f
        L57:
            r1 = r3
        L58:
            r2 = 32
            if (r1 == 0) goto L6b
            int r1 = h2.AbstractC1343A.f15195a     // Catch: java.lang.Throwable -> L69
            if (r1 < r2) goto L97
            A2.c r1 = r7.f23265h     // Catch: java.lang.Throwable -> L69
            if (r1 == 0) goto L97
            boolean r1 = r1.f311b     // Catch: java.lang.Throwable -> L69
            if (r1 == 0) goto L97
            goto L6b
        L69:
            r7 = move-exception
            goto L9a
        L6b:
            int r1 = h2.AbstractC1343A.f15195a     // Catch: java.lang.Throwable -> L69
            if (r1 < r2) goto L98
            A2.c r1 = r7.f23265h     // Catch: java.lang.Throwable -> L69
            if (r1 == 0) goto L98
            boolean r2 = r1.f311b     // Catch: java.lang.Throwable -> L69
            if (r2 == 0) goto L98
            java.lang.Object r1 = r1.c     // Catch: java.lang.Throwable -> L69
            android.media.Spatializer r1 = (android.media.Spatializer) r1     // Catch: java.lang.Throwable -> L69
            boolean r1 = G1.b.g(r1)     // Catch: java.lang.Throwable -> L69
            if (r1 == 0) goto L98
            A2.c r1 = r7.f23265h     // Catch: java.lang.Throwable -> L69
            java.lang.Object r1 = r1.c     // Catch: java.lang.Throwable -> L69
            android.media.Spatializer r1 = (android.media.Spatializer) r1     // Catch: java.lang.Throwable -> L69
            boolean r1 = G1.b.j(r1)     // Catch: java.lang.Throwable -> L69
            if (r1 == 0) goto L98
            A2.c r1 = r7.f23265h     // Catch: java.lang.Throwable -> L69
            e2.c r7 = r7.f23266i     // Catch: java.lang.Throwable -> L69
            boolean r7 = r1.b(r7, r8)     // Catch: java.lang.Throwable -> L69
            if (r7 == 0) goto L98
        L97:
            r0 = r3
        L98:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L69
            return r0
        L9a:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L69
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: w2.C2590d.apply(java.lang.Object):boolean");
    }
}
