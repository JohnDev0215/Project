package c4;

import java.io.Closeable;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Closeable {
    public final io.sentry.instrumentation.file.e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Charset f12395m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public byte[] f12396n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f12397p;

    public e(io.sentry.instrumentation.file.e eVar, Charset charset) {
        if (charset == null) {
            throw null;
        }
        if (!charset.equals(f.f12398a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.l = eVar;
        this.f12395m = charset;
        this.f12396n = new byte[8192];
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String b() {
        /*
            r8 = this;
            io.sentry.instrumentation.file.e r0 = r8.l
            monitor-enter(r0)
            byte[] r1 = r8.f12396n     // Catch: java.lang.Throwable -> L3e
            if (r1 == 0) goto La4
            int r2 = r8.o     // Catch: java.lang.Throwable -> L3e
            int r3 = r8.f12397p     // Catch: java.lang.Throwable -> L3e
            r4 = 0
            r5 = -1
            if (r2 < r3) goto L23
            int r2 = r1.length     // Catch: java.lang.Throwable -> L3e
            io.sentry.instrumentation.file.e r3 = r8.l     // Catch: java.lang.Throwable -> L3e
            int r1 = r3.read(r1, r4, r2)     // Catch: java.lang.Throwable -> L3e
            if (r1 == r5) goto L1d
            r8.o = r4     // Catch: java.lang.Throwable -> L3e
            r8.f12397p = r1     // Catch: java.lang.Throwable -> L3e
            goto L23
        L1d:
            java.io.EOFException r8 = new java.io.EOFException     // Catch: java.lang.Throwable -> L3e
            r8.<init>()     // Catch: java.lang.Throwable -> L3e
            throw r8     // Catch: java.lang.Throwable -> L3e
        L23:
            int r1 = r8.o     // Catch: java.lang.Throwable -> L3e
        L25:
            int r2 = r8.f12397p     // Catch: java.lang.Throwable -> L3e
            r3 = 10
            if (r1 == r2) goto L56
            byte[] r2 = r8.f12396n     // Catch: java.lang.Throwable -> L3e
            r6 = r2[r1]     // Catch: java.lang.Throwable -> L3e
            if (r6 != r3) goto L53
            int r3 = r8.o     // Catch: java.lang.Throwable -> L3e
            if (r1 == r3) goto L40
            int r4 = r1 + (-1)
            r5 = r2[r4]     // Catch: java.lang.Throwable -> L3e
            r6 = 13
            if (r5 != r6) goto L40
            goto L41
        L3e:
            r8 = move-exception
            goto Lac
        L40:
            r4 = r1
        L41:
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L3e
            int r4 = r4 - r3
            java.nio.charset.Charset r6 = r8.f12395m     // Catch: java.lang.Throwable -> L3e
            java.lang.String r6 = r6.name()     // Catch: java.lang.Throwable -> L3e
            r5.<init>(r2, r3, r4, r6)     // Catch: java.lang.Throwable -> L3e
            int r1 = r1 + 1
            r8.o = r1     // Catch: java.lang.Throwable -> L3e
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
            return r5
        L53:
            int r1 = r1 + 1
            goto L25
        L56:
            c4.d r1 = new c4.d     // Catch: java.lang.Throwable -> L3e
            int r2 = r8.f12397p     // Catch: java.lang.Throwable -> L3e
            int r6 = r8.o     // Catch: java.lang.Throwable -> L3e
            int r2 = r2 - r6
            int r2 = r2 + 80
            r1.<init>(r8, r2)     // Catch: java.lang.Throwable -> L3e
        L62:
            byte[] r2 = r8.f12396n     // Catch: java.lang.Throwable -> L3e
            int r6 = r8.o     // Catch: java.lang.Throwable -> L3e
            int r7 = r8.f12397p     // Catch: java.lang.Throwable -> L3e
            int r7 = r7 - r6
            r1.write(r2, r6, r7)     // Catch: java.lang.Throwable -> L3e
            r8.f12397p = r5     // Catch: java.lang.Throwable -> L3e
            byte[] r2 = r8.f12396n     // Catch: java.lang.Throwable -> L3e
            int r6 = r2.length     // Catch: java.lang.Throwable -> L3e
            io.sentry.instrumentation.file.e r7 = r8.l     // Catch: java.lang.Throwable -> L3e
            int r2 = r7.read(r2, r4, r6)     // Catch: java.lang.Throwable -> L3e
            if (r2 == r5) goto L9e
            r8.o = r4     // Catch: java.lang.Throwable -> L3e
            r8.f12397p = r2     // Catch: java.lang.Throwable -> L3e
            r2 = r4
        L7e:
            int r6 = r8.f12397p     // Catch: java.lang.Throwable -> L3e
            if (r2 == r6) goto L62
            byte[] r6 = r8.f12396n     // Catch: java.lang.Throwable -> L3e
            r7 = r6[r2]     // Catch: java.lang.Throwable -> L3e
            if (r7 != r3) goto L9b
            int r3 = r8.o     // Catch: java.lang.Throwable -> L3e
            if (r2 == r3) goto L91
            int r4 = r2 - r3
            r1.write(r6, r3, r4)     // Catch: java.lang.Throwable -> L3e
        L91:
            int r2 = r2 + 1
            r8.o = r2     // Catch: java.lang.Throwable -> L3e
            java.lang.String r8 = r1.toString()     // Catch: java.lang.Throwable -> L3e
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
            return r8
        L9b:
            int r2 = r2 + 1
            goto L7e
        L9e:
            java.io.EOFException r8 = new java.io.EOFException     // Catch: java.lang.Throwable -> L3e
            r8.<init>()     // Catch: java.lang.Throwable -> L3e
            throw r8     // Catch: java.lang.Throwable -> L3e
        La4:
            java.io.IOException r8 = new java.io.IOException     // Catch: java.lang.Throwable -> L3e
            java.lang.String r1 = "LineReader is closed"
            r8.<init>(r1)     // Catch: java.lang.Throwable -> L3e
            throw r8     // Catch: java.lang.Throwable -> L3e
        Lac:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: c4.e.b():java.lang.String");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.l) {
            try {
                if (this.f12396n != null) {
                    this.f12396n = null;
                    this.l.close();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
