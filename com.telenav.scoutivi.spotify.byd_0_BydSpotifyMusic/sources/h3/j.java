package h3;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: LoggerPrinter.java */
/* JADX INFO: loaded from: classes.dex */
class j implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ThreadLocal<String> f6626a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<f> f6627b = new ArrayList();

    j() {
    }

    private String j(String str, Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(str, objArr);
    }

    private String k() {
        String str = this.f6626a.get();
        if (str == null) {
            return null;
        }
        this.f6626a.remove();
        return str;
    }

    private synchronized void m(int i7, Throwable th, String str, Object... objArr) {
        m.a(str);
        l(i7, k(), j(str, objArr), th);
    }

    @Override // h3.l
    public l a(String str) {
        if (str != null) {
            this.f6626a.set(str);
        }
        return this;
    }

    @Override // h3.l
    public void b(String str, Object... objArr) {
        m(4, null, str, objArr);
    }

    @Override // h3.l
    public void c(String str, Object... objArr) {
        m(2, null, str, objArr);
    }

    @Override // h3.l
    public void d(String str, Object... objArr) {
        e(null, str, objArr);
    }

    @Override // h3.l
    public void e(Throwable th, String str, Object... objArr) {
        m(6, th, str, objArr);
    }

    @Override // h3.l
    public void f(f fVar) {
        this.f6627b.add((f) m.a(fVar));
    }

    @Override // h3.l
    public void g(Object obj) {
        m(3, null, m.f(obj), new Object[0]);
    }

    @Override // h3.l
    public void h(String str, Object... objArr) {
        m(5, null, str, objArr);
    }

    @Override // h3.l
    public void i(String str, Object... objArr) {
        m(3, null, str, objArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0039 A[Catch: all -> 0x004b, TryCatch #0 {, blocks: (B:5:0x0005, B:8:0x0021, B:9:0x0025, B:12:0x002d, B:13:0x0033, B:15:0x0039, B:17:0x0045), top: B:24:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void l(int r3, java.lang.String r4, java.lang.String r5, java.lang.Throwable r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r6 == 0) goto L1d
            if (r5 == 0) goto L1d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4b
            r0.<init>()     // Catch: java.lang.Throwable -> L4b
            r0.append(r5)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r5 = " : "
            r0.append(r5)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r5 = h3.m.c(r6)     // Catch: java.lang.Throwable -> L4b
            r0.append(r5)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r5 = r0.toString()     // Catch: java.lang.Throwable -> L4b
        L1d:
            if (r6 == 0) goto L25
            if (r5 != 0) goto L25
            java.lang.String r5 = h3.m.c(r6)     // Catch: java.lang.Throwable -> L4b
        L25:
            boolean r6 = h3.m.d(r5)     // Catch: java.lang.Throwable -> L4b
            if (r6 == 0) goto L2d
            java.lang.String r5 = "Empty/NULL log message"
        L2d:
            java.util.List<h3.f> r6 = r2.f6627b     // Catch: java.lang.Throwable -> L4b
            java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Throwable -> L4b
        L33:
            boolean r0 = r6.hasNext()     // Catch: java.lang.Throwable -> L4b
            if (r0 == 0) goto L49
            java.lang.Object r0 = r6.next()     // Catch: java.lang.Throwable -> L4b
            h3.f r0 = (h3.f) r0     // Catch: java.lang.Throwable -> L4b
            boolean r1 = r0.b(r3, r4)     // Catch: java.lang.Throwable -> L4b
            if (r1 == 0) goto L33
            r0.a(r3, r4, r5)     // Catch: java.lang.Throwable -> L4b
            goto L33
        L49:
            monitor-exit(r2)
            return
        L4b:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.j.l(int, java.lang.String, java.lang.String, java.lang.Throwable):void");
    }
}
