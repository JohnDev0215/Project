package defpackage;

/* JADX INFO: compiled from: MainDispatchers.kt */
/* JADX INFO: loaded from: classes.dex */
public final class eb1 {
    public static final cb1 a;

    /* JADX WARN: Removed duplicated region for block: B:32:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0073 A[Catch: all -> 0x007b, TRY_ENTER, TryCatch #2 {, blocks: (B:9:0x0011, B:13:0x002a, B:25:0x0061, B:34:0x0073, B:35:0x007a, B:30:0x006b, B:16:0x003a, B:19:0x0045, B:20:0x004c, B:23:0x005b, B:12:0x0024, B:28:0x0066), top: B:42:0x0011, inners: #1 }] */
    static {
        /*
            java.lang.String r0 = "kotlinx.coroutines.fast.service.loader"
            int r1 = defpackage.gy2.a
            r1 = 0
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch: java.lang.SecurityException -> La
            goto Lb
        La:
            r0 = r1
        Lb:
            if (r0 != 0) goto Le
            goto L11
        Le:
            java.lang.Boolean.parseBoolean(r0)
        L11:
            java.util.Iterator r0 = defpackage.C0173a.l()     // Catch: java.lang.Throwable -> L7b
            java.lang.String r2 = "<this>"
            defpackage.i21.e(r0, r2)     // Catch: java.lang.Throwable -> L7b
            ri2 r2 = new ri2     // Catch: java.lang.Throwable -> L7b
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L7b
            boolean r0 = r2 instanceof defpackage.mx     // Catch: java.lang.Throwable -> L7b
            if (r0 == 0) goto L24
            goto L2a
        L24:
            mx r0 = new mx     // Catch: java.lang.Throwable -> L7b
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L7b
            r2 = r0
        L2a:
            java.util.List r0 = defpackage.qi2.k0(r2)     // Catch: java.lang.Throwable -> L7b
            java.util.Iterator r2 = r0.iterator()     // Catch: java.lang.Throwable -> L7b
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L7b
            if (r3 != 0) goto L3a
            r3 = r1
            goto L61
        L3a:
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L7b
            boolean r4 = r2.hasNext()     // Catch: java.lang.Throwable -> L7b
            if (r4 != 0) goto L45
            goto L61
        L45:
            r4 = r3
            db1 r4 = (defpackage.db1) r4     // Catch: java.lang.Throwable -> L7b
            int r4 = r4.getLoadPriority()     // Catch: java.lang.Throwable -> L7b
        L4c:
            java.lang.Object r5 = r2.next()     // Catch: java.lang.Throwable -> L7b
            r6 = r5
            db1 r6 = (defpackage.db1) r6     // Catch: java.lang.Throwable -> L7b
            int r6 = r6.getLoadPriority()     // Catch: java.lang.Throwable -> L7b
            if (r4 >= r6) goto L5b
            r3 = r5
            r4 = r6
        L5b:
            boolean r5 = r2.hasNext()     // Catch: java.lang.Throwable -> L7b
            if (r5 != 0) goto L4c
        L61:
            db1 r3 = (defpackage.db1) r3     // Catch: java.lang.Throwable -> L7b
            if (r3 != 0) goto L66
            goto L6e
        L66:
            cb1 r1 = r3.createDispatcher(r0)     // Catch: java.lang.Throwable -> L6b
            goto L6e
        L6b:
            r3.hintOnError()     // Catch: java.lang.Throwable -> L7b
        L6e:
            if (r1 == 0) goto L73
            defpackage.eb1.a = r1
            return
        L73:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L7b
            java.lang.String r1 = "Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L7b
            throw r0     // Catch: java.lang.Throwable -> L7b
        L7b:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eb1.<clinit>():void");
    }
}
