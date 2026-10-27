package f;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: AsyncTimeout.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f3689e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f3690f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @Nullable
    public static a f3691g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3692h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @Nullable
    public a f3693i;
    public long j;

    /* JADX INFO: renamed from: f.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AsyncTimeout.java */
    public class C0085a implements p {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ p f3694c;

        public C0085a(p pVar) {
            this.f3694c = pVar;
        }

        @Override // f.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            a.this.k();
            try {
                try {
                    this.f3694c.close();
                    a.this.m(true);
                } catch (IOException e2) {
                    throw a.this.l(e2);
                }
            } catch (Throwable th) {
                a.this.m(false);
                throw th;
            }
        }

        @Override // f.p
        public r e() {
            return a.this;
        }

        @Override // f.p, java.io.Flushable
        public void flush() throws IOException {
            a.this.k();
            try {
                try {
                    this.f3694c.flush();
                    a.this.m(true);
                } catch (IOException e2) {
                    throw a.this.l(e2);
                }
            } catch (Throwable th) {
                a.this.m(false);
                throw th;
            }
        }

        @Override // f.p
        public void h(f.c cVar, long j) throws IOException {
            s.b(cVar.s, 0L, j);
            while (true) {
                long j2 = 0;
                if (j <= 0) {
                    return;
                }
                n nVar = cVar.f3701d;
                while (true) {
                    if (j2 >= 65536) {
                        break;
                    }
                    n nVar2 = cVar.f3701d;
                    j2 += (long) (nVar2.f3723c - nVar2.f3722b);
                    if (j2 >= j) {
                        j2 = j;
                        break;
                    }
                    nVar = nVar.f3726f;
                }
                a.this.k();
                try {
                    try {
                        this.f3694c.h(cVar, j2);
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
            return "AsyncTimeout.sink(" + this.f3694c + ")";
        }
    }

    /* JADX INFO: compiled from: AsyncTimeout.java */
    public class b implements q {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ q f3696c;

        public b(q qVar) {
            this.f3696c = qVar;
        }

        @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                try {
                    this.f3696c.close();
                    a.this.m(true);
                } catch (IOException e2) {
                    throw a.this.l(e2);
                }
            } catch (Throwable th) {
                a.this.m(false);
                throw th;
            }
        }

        @Override // f.q
        public r e() {
            return a.this;
        }

        @Override // f.q
        public long n(f.c cVar, long j) throws IOException {
            a.this.k();
            try {
                try {
                    long jN = this.f3696c.n(cVar, j);
                    a.this.m(true);
                    return jN;
                } catch (IOException e2) {
                    throw a.this.l(e2);
                }
            } catch (Throwable th) {
                a.this.m(false);
                throw th;
            }
        }

        public String toString() {
            return "AsyncTimeout.source(" + this.f3696c + ")";
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
                java.lang.Class<f.a> r0 = f.a.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L0
                f.a r1 = f.a.i()     // Catch: java.lang.Throwable -> L19
                if (r1 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L0
            Lb:
                f.a r2 = f.a.f3691g     // Catch: java.lang.Throwable -> L19
                if (r1 != r2) goto L14
                r1 = 0
                f.a.f3691g = r1     // Catch: java.lang.Throwable -> L19
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
            throw new UnsupportedOperationException("Method not decompiled: f.a.c.run():void");
        }
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f3689e = millis;
        f3690f = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    @Nullable
    public static a i() throws InterruptedException {
        a aVar = f3691g.f3693i;
        if (aVar == null) {
            long jNanoTime = System.nanoTime();
            a.class.wait(f3689e);
            if (f3691g.f3693i != null || System.nanoTime() - jNanoTime < f3690f) {
                return null;
            }
            return f3691g;
        }
        long jP = aVar.p(System.nanoTime());
        if (jP > 0) {
            long j = jP / 1000000;
            a.class.wait(j, (int) (jP - (1000000 * j)));
            return null;
        }
        f3691g.f3693i = aVar.f3693i;
        aVar.f3693i = null;
        return aVar;
    }

    public static synchronized boolean j(a aVar) {
        a aVar2 = f3691g;
        while (aVar2 != null) {
            a aVar3 = aVar2.f3693i;
            if (aVar3 == aVar) {
                aVar2.f3693i = aVar.f3693i;
                aVar.f3693i = null;
                return false;
            }
            aVar2 = aVar3;
        }
        return true;
    }

    public static synchronized void q(a aVar, long j, boolean z) {
        if (f3691g == null) {
            f3691g = new a();
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
        a aVar2 = f3691g;
        while (true) {
            a aVar3 = aVar2.f3693i;
            if (aVar3 == null || jP < aVar3.p(jNanoTime)) {
                break;
            } else {
                aVar2 = aVar2.f3693i;
            }
        }
        aVar.f3693i = aVar2.f3693i;
        aVar2.f3693i = aVar;
        if (aVar2 == f3691g) {
            a.class.notify();
        }
    }

    public final void k() {
        if (this.f3692h) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jH = h();
        boolean zE = e();
        if (jH != 0 || zE) {
            this.f3692h = true;
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
        if (!this.f3692h) {
            return false;
        }
        this.f3692h = false;
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

    public final p r(p pVar) {
        return new C0085a(pVar);
    }

    public final q s(q qVar) {
        return new b(qVar);
    }

    public void t() {
    }
}
