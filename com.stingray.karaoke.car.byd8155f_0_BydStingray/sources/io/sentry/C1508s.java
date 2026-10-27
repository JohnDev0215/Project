package io.sentry;

import java.io.File;

/* JADX INFO: renamed from: io.sentry.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1508s extends AbstractC1487n {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final E f16876e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final T f16877f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final K f16878g;

    public C1508s(E e10, T t10, K k10, long j3, int i7) {
        super(e10, k10, j3, i7);
        h6.e.q(e10, "Hub is required.");
        this.f16876e = e10;
        h6.e.q(t10, "Serializer is required.");
        this.f16877f = t10;
        h6.e.q(k10, "Logger is required.");
        this.f16878g = k10;
    }

    public static void c(C1508s c1508s, File file, io.sentry.hints.g gVar) {
        c1508s.getClass();
        boolean zA = gVar.a();
        K k10 = c1508s.f16878g;
        if (zA) {
            k10.k(EnumC1495p1.INFO, "File not deleted since retry was marked. %s.", file.getAbsolutePath());
            return;
        }
        try {
            if (!file.delete()) {
                k10.k(EnumC1495p1.ERROR, "Failed to delete '%s' %s", file.getAbsolutePath(), "after trying to capture it");
            }
        } catch (Throwable th) {
            k10.p(EnumC1495p1.ERROR, th, "Failed to delete '%s' %s", file.getAbsolutePath(), "after trying to capture it");
        }
        k10.k(EnumC1495p1.DEBUG, "Deleted file %s.", file.getAbsolutePath());
    }

    @Override // io.sentry.AbstractC1487n
    public final boolean a(String str) {
        return str.endsWith(".envelope");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b2, code lost:
    
        if (r1 != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b4, code lost:
    
        c(r7, r8, (io.sentry.hints.g) r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0115, code lost:
    
        if (r1 != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0137, code lost:
    
        if (r1 != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0158, code lost:
    
        if (r1 != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x015c, code lost:
    
        return;
     */
    @Override // io.sentry.AbstractC1487n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(java.io.File r8, io.sentry.C1523x r9) {
        /*
            Method dump skipped, instruction units count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.C1508s.b(java.io.File, io.sentry.x):void");
    }
}
