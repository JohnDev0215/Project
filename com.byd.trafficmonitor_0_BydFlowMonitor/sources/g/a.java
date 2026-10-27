package g;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: AsyncTimeout.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f5458e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f5459f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @Nullable
    public static a f5460g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f5461h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @Nullable
    public a f5462i;
    public long j;

    /* JADX INFO: renamed from: g.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AsyncTimeout.java */
    public class C0105a implements o {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ o f5463b;

        public C0105a(o oVar) {
            this.f5463b = oVar;
        }

        @Override // g.o
        public q b() {
            return a.this;
        }

        @Override // g.o, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            a.this.k();
            try {
                try {
                    this.f5463b.close();
                    a.this.m(true);
                } catch (IOException e2) {
                    throw a.this.l(e2);
                }
            } catch (Throwable th) {
                a.this.m(false);
                throw th;
            }
        }

        @Override // g.o, java.io.Flushable
        public void flush() throws IOException {
            a.this.k();
            try {
                try {
                    this.f5463b.flush();
                    a.this.m(true);
                } catch (IOException e2) {
                    throw a.this.l(e2);
                }
            } catch (Throwable th) {
                a.this.m(false);
                throw th;
            }
        }

        @Override // g.o
        public void g(g.c cVar, long j) throws IOException {
            r.b(cVar.f5471d, 0L, j);
            while (true) {
                long j2 = 0;
                if (j <= 0) {
                    return;
                }
                m mVar = cVar.f5470c;
                while (true) {
                    if (j2 >= 65536) {
                        break;
                    }
                    j2 += (long) (mVar.f5498c - mVar.f5497b);
                    if (j2 >= j) {
                        j2 = j;
                        break;
                    }
                    mVar = mVar.f5501f;
                }
                a.this.k();
                try {
                    try {
                        this.f5463b.g(cVar, j2);
                        j -= j2;
                        a.this.m(true);
                    } catch (IOException e2) {
                        throw a.this.l(e2);
                    }
                } catch (Throwable th) {
                    a.this.m(false);
                    throw th;
                }
            }
        }

        public String toString() {
            return "AsyncTimeout.sink(" + this.f5463b + ")";
        }
    }

    /* JADX INFO: compiled from: AsyncTimeout.java */
    public class b implements p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ p f5465b;

        public b(p pVar) {
            this.f5465b = pVar;
        }

        @Override // g.p
        public q b() {
            return a.this;
        }

        @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            a.this.k();
            try {
                try {
                    this.f5465b.close();
                    a.this.m(true);
                } catch (IOException e2) {
                    throw a.this.l(e2);
                }
            } catch (Throwable th) {
                a.this.m(false);
                throw th;
            }
        }

        @Override // g.p
        public long m(g.c cVar, long j) throws IOException {
            a.this.k();
            try {
                try {
                    long jM = this.f5465b.m(cVar, j);
                    a.this.m(true);
                    return jM;
                } catch (IOException e2) {
                    throw a.this.l(e2);
                }
            } catch (Throwable th) {
                a.this.m(false);
                throw th;
            }
        }

        public String toString() {
            return "AsyncTimeout.source(" + this.f5465b + ")";
        }
    }

    /* JADX INFO: compiled from: AsyncTimeout.java */
    public static final class c extends Thread {
        public c() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
        
            r1.t();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r3 = this;
            L0:
                java.lang.Class<g.a> r0 = g.a.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L0
                g.a r1 = g.a.i()     // Catch: java.lang.Throwable -> L19
                if (r1 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L0
            Lb:
                g.a r2 = g.a.f5460g     // Catch: java.lang.Throwable -> L19
                if (r1 != r2) goto L14
                r1 = 0
                g.a.f5460g = r1     // Catch: java.lang.Throwable -> L19
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                return
            L14:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                r1.t()     // Catch: java.lang.InterruptedException -> L0
                goto L0
            L19:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                throw r1     // Catch: java.lang.InterruptedException -> L0
            */
            throw new UnsupportedOperationException("Method not decompiled: g.a.c.run():void");
        }
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f5458e = millis;
        f5459f = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    @Nullable
    public static a i() throws InterruptedException {
        a aVar = f5460g.f5462i;
        if (aVar == null) {
            long jNanoTime = System.nanoTime();
            a.class.wait(f5458e);
            if (f5460g.f5462i != null || System.nanoTime() - jNanoTime < f5459f) {
                return null;
            }
            return f5460g;
        }
        long jP = aVar.p(System.nanoTime());
        if (jP > 0) {
            long j = jP / 1000000;
            a.class.wait(j, (int) (jP - (1000000 * j)));
            return null;
        }
        f5460g.f5462i = aVar.f5462i;
        aVar.f5462i = null;
        return aVar;
    }

    public static synchronized boolean j(a aVar) {
        a aVar2 = f5460g;
        while (aVar2 != null) {
            a aVar3 = aVar2.f5462i;
            if (aVar3 == aVar) {
                aVar2.f5462i = aVar.f5462i;
                aVar.f5462i = null;
                return false;
            }
            aVar2 = aVar3;
        }
        return true;
    }

    public static synchronized void q(a aVar, long j, boolean z) {
        if (f5460g == null) {
            f5460g = new a();
            new c().start();
        }
        long jNanoTime = System.nanoTime();
        if (j != 0 && z) {
            aVar.j = Math.min(j, aVar.c() - jNanoTime) + jNanoTime;
        } else if (j != 0) {
            aVar.j = j + jNanoTime;
        } else {
            if (!z) {
                throw new AssertionError();
            }
            aVar.j = aVar.c();
        }
        long jP = aVar.p(jNanoTime);
        a aVar2 = f5460g;
        while (true) {
            a aVar3 = aVar2.f5462i;
            if (aVar3 == null || jP < aVar3.p(jNanoTime)) {
                break;
            } else {
                aVar2 = aVar2.f5462i;
            }
        }
        aVar.f5462i = aVar2.f5462i;
        aVar2.f5462i = aVar;
        if (aVar2 == f5460g) {
            a.class.notify();
        }
    }

    public final void k() {
        if (this.f5461h) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jH = h();
        boolean zE = e();
        if (jH != 0 || zE) {
            this.f5461h = true;
            q(this, jH, zE);
        }
    }

    public final IOException l(IOException iOException) throws IOException {
        return !n() ? iOException : o(iOException);
    }

    public final void m(boolean z) throws IOException {
        if (n() && z) {
            throw o(null);
        }
    }

    public final boolean n() {
        if (!this.f5461h) {
            return false;
        }
        this.f5461h = false;
        return j(this);
    }

    public IOException o(@Nullable IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final long p(long j) {
        return this.j - j;
    }

    public final o r(o oVar) {
        return new C0105a(oVar);
    }

    public final p s(p pVar) {
        return new b(pVar);
    }

    public void t() {
    }
}
