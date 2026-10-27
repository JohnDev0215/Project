package io.sentry;

import h8.AbstractC1387d;
import java.io.File;
import java.nio.charset.Charset;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class D0 extends AbstractC1487n {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Charset f15829i = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final E f15830e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final I f15831f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final T f15832g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final K f15833h;

    public D0(E e10, I i7, T t10, K k10, long j3, int i10) {
        super(e10, k10, j3, i10);
        h6.e.q(e10, "Hub is required.");
        this.f15830e = e10;
        h6.e.q(i7, "Envelope reader is required.");
        this.f15831f = i7;
        h6.e.q(t10, "Serializer is required.");
        this.f15832g = t10;
        h6.e.q(k10, "Logger is required.");
        this.f15833h = k10;
    }

    public static /* synthetic */ void c(D0 d02, File file, io.sentry.hints.g gVar) {
        K k10 = d02.f15833h;
        if (gVar.a()) {
            return;
        }
        try {
            if (file.delete()) {
                return;
            }
            k10.k(EnumC1495p1.ERROR, "Failed to delete: %s", file.getAbsolutePath());
        } catch (RuntimeException e10) {
            k10.p(EnumC1495p1.ERROR, e10, "Failed to delete: %s", file.getAbsolutePath());
        }
    }

    @Override // io.sentry.AbstractC1487n
    public final boolean a(String str) {
        return (str == null || str.startsWith("session") || str.startsWith("previous_session") || str.startsWith("startup_crash")) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0093, code lost:
    
        if (r1 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0096, code lost:
    
        return;
     */
    @Override // io.sentry.AbstractC1487n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(java.io.File r7, io.sentry.C1523x r8) {
        /*
            r6 = this;
            java.lang.Class<io.sentry.hints.g> r0 = io.sentry.hints.g.class
            java.lang.String r1 = r7.getName()
            boolean r1 = r6.a(r1)
            io.sentry.K r2 = r6.f15833h
            if (r1 != 0) goto L1e
            io.sentry.p1 r6 = io.sentry.EnumC1495p1.DEBUG
            java.lang.String r7 = r7.getAbsolutePath()
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.String r8 = "File '%s' should be ignored."
            r2.k(r6, r8, r7)
            return
        L1e:
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            io.sentry.I r3 = r6.f15831f     // Catch: java.lang.Throwable -> L40
            io.sentry.n1 r3 = r3.a(r1)     // Catch: java.lang.Throwable -> L40
            if (r3 != 0) goto L42
            io.sentry.p1 r3 = io.sentry.EnumC1495p1.ERROR     // Catch: java.lang.Throwable -> L40
            java.lang.String r4 = "Stream from path %s resulted in a null envelope."
            java.lang.String r5 = r7.getAbsolutePath()     // Catch: java.lang.Throwable -> L40
            java.lang.Object[] r5 = new java.lang.Object[]{r5}     // Catch: java.lang.Throwable -> L40
            r2.k(r3, r4, r5)     // Catch: java.lang.Throwable -> L40
            goto L54
        L40:
            r3 = move-exception
            goto L75
        L42:
            r6.e(r3, r8)     // Catch: java.lang.Throwable -> L40
            io.sentry.p1 r3 = io.sentry.EnumC1495p1.DEBUG     // Catch: java.lang.Throwable -> L40
            java.lang.String r4 = "File '%s' is done."
            java.lang.String r5 = r7.getAbsolutePath()     // Catch: java.lang.Throwable -> L40
            java.lang.Object[] r5 = new java.lang.Object[]{r5}     // Catch: java.lang.Throwable -> L40
            r2.k(r3, r4, r5)     // Catch: java.lang.Throwable -> L40
        L54:
            r1.close()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.lang.Object r1 = h8.AbstractC1387d.g(r8)
            java.lang.Object r8 = h8.AbstractC1387d.g(r8)
            boolean r8 = r0.isInstance(r8)
            if (r8 == 0) goto L6d
            if (r1 == 0) goto L6d
        L67:
            io.sentry.hints.g r1 = (io.sentry.hints.g) r1
            c(r6, r7, r1)
            goto L96
        L6d:
            n7.AbstractC1985B.o(r0, r1, r2)
            goto L96
        L71:
            r1 = move-exception
            goto L97
        L73:
            r1 = move-exception
            goto L7e
        L75:
            r1.close()     // Catch: java.lang.Throwable -> L79
            goto L7d
        L79:
            r1 = move-exception
            r3.addSuppressed(r1)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
        L7d:
            throw r3     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
        L7e:
            io.sentry.p1 r3 = io.sentry.EnumC1495p1.ERROR     // Catch: java.lang.Throwable -> L71
            java.lang.String r4 = "Error processing envelope."
            r2.s(r3, r4, r1)     // Catch: java.lang.Throwable -> L71
            java.lang.Object r1 = h8.AbstractC1387d.g(r8)
            java.lang.Object r8 = h8.AbstractC1387d.g(r8)
            boolean r8 = r0.isInstance(r8)
            if (r8 == 0) goto L6d
            if (r1 == 0) goto L6d
            goto L67
        L96:
            return
        L97:
            java.lang.Object r3 = h8.AbstractC1387d.g(r8)
            java.lang.Object r8 = h8.AbstractC1387d.g(r8)
            boolean r8 = r0.isInstance(r8)
            if (r8 == 0) goto Lad
            if (r3 == 0) goto Lad
            io.sentry.hints.g r3 = (io.sentry.hints.g) r3
            c(r6, r7, r3)
            goto Lb0
        Lad:
            n7.AbstractC1985B.o(r0, r3, r2)
        Lb0:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.D0.b(java.io.File, io.sentry.x):void");
    }

    public final B8.t d(a2 a2Var) {
        String str;
        K k10 = this.f15833h;
        if (a2Var != null && (str = a2Var.f16009s) != null) {
            try {
                Double dValueOf = Double.valueOf(Double.parseDouble(str));
                if (h6.i.w(dValueOf, false)) {
                    return new B8.t(Boolean.TRUE, dValueOf);
                }
                k10.k(EnumC1495p1.ERROR, "Invalid sample rate parsed from TraceContext: %s", str);
            } catch (Exception unused) {
                k10.k(EnumC1495p1.ERROR, "Unable to parse sample rate from TraceContext: %s", str);
            }
        }
        return new B8.t(Boolean.TRUE, (Double) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0264 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x023c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(io.sentry.C1489n1 r20, io.sentry.C1523x r21) {
        /*
            Method dump skipped, instruction units count: 637
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.D0.e(io.sentry.n1, io.sentry.x):void");
    }

    public final boolean f(C1523x c1523x) {
        Object objG = AbstractC1387d.g(c1523x);
        if (objG instanceof io.sentry.hints.f) {
            return ((io.sentry.hints.f) objG).d();
        }
        AbstractC1985B.o(io.sentry.hints.f.class, objG, this.f15833h);
        return true;
    }
}
