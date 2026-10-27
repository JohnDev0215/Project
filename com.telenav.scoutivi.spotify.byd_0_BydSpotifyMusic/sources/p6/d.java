package p6;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: AsyncTimeout.kt */
/* JADX INFO: loaded from: classes.dex */
public class d extends b0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final long f8855i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final long f8856j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static d f8857k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f8858l = new a(null);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f8859f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private d f8860g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f8861h;

    /* JADX INFO: compiled from: AsyncTimeout.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean d(d dVar) {
            synchronized (d.class) {
                for (d dVar2 = d.f8857k; dVar2 != null; dVar2 = dVar2.f8860g) {
                    if (dVar2.f8860g == dVar) {
                        dVar2.f8860g = dVar.f8860g;
                        dVar.f8860g = null;
                        return false;
                    }
                }
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void e(d dVar, long j7, boolean z6) {
            synchronized (d.class) {
                if (d.f8857k == null) {
                    d.f8857k = new d();
                    new b().start();
                }
                long jNanoTime = System.nanoTime();
                if (j7 != 0 && z6) {
                    dVar.f8861h = Math.min(j7, dVar.c() - jNanoTime) + jNanoTime;
                } else if (j7 != 0) {
                    dVar.f8861h = j7 + jNanoTime;
                } else {
                    if (!z6) {
                        throw new AssertionError();
                    }
                    dVar.f8861h = dVar.c();
                }
                long jU = dVar.u(jNanoTime);
                d dVar2 = d.f8857k;
                kotlin.jvm.internal.l.c(dVar2);
                while (dVar2.f8860g != null) {
                    d dVar3 = dVar2.f8860g;
                    kotlin.jvm.internal.l.c(dVar3);
                    if (jU < dVar3.u(jNanoTime)) {
                        break;
                    }
                    dVar2 = dVar2.f8860g;
                    kotlin.jvm.internal.l.c(dVar2);
                }
                dVar.f8860g = dVar2.f8860g;
                dVar2.f8860g = dVar;
                if (dVar2 == d.f8857k) {
                    d.class.notify();
                }
                g5.r rVar = g5.r.f6563a;
            }
        }

        public final d c() throws InterruptedException {
            d dVar = d.f8857k;
            kotlin.jvm.internal.l.c(dVar);
            d dVar2 = dVar.f8860g;
            if (dVar2 == null) {
                long jNanoTime = System.nanoTime();
                d.class.wait(d.f8855i);
                d dVar3 = d.f8857k;
                kotlin.jvm.internal.l.c(dVar3);
                if (dVar3.f8860g != null || System.nanoTime() - jNanoTime < d.f8856j) {
                    return null;
                }
                return d.f8857k;
            }
            long jU = dVar2.u(System.nanoTime());
            if (jU > 0) {
                long j7 = jU / 1000000;
                d.class.wait(j7, (int) (jU - (1000000 * j7)));
                return null;
            }
            d dVar4 = d.f8857k;
            kotlin.jvm.internal.l.c(dVar4);
            dVar4.f8860g = dVar2.f8860g;
            dVar2.f8860g = null;
            return dVar2;
        }
    }

    /* JADX INFO: compiled from: AsyncTimeout.kt */
    private static final class b extends Thread {
        public b() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            d dVarC;
            while (true) {
                try {
                    synchronized (d.class) {
                        dVarC = d.f8858l.c();
                        if (dVarC == d.f8857k) {
                            d.f8857k = null;
                            return;
                        }
                        g5.r rVar = g5.r.f6563a;
                    }
                    if (dVarC != null) {
                        dVarC.x();
                    }
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    /* JADX INFO: compiled from: AsyncTimeout.kt */
    public static final class c implements y {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ y f8863g;

        c(y yVar) {
            this.f8863g = yVar;
        }

        @Override // p6.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            d dVar = d.this;
            dVar.r();
            try {
                this.f8863g.close();
                g5.r rVar = g5.r.f6563a;
                if (dVar.s()) {
                    throw dVar.m(null);
                }
            } catch (IOException e7) {
                if (!dVar.s()) {
                    throw e7;
                }
                throw dVar.m(e7);
            } finally {
                dVar.s();
            }
        }

        @Override // p6.y, java.io.Flushable
        public void flush() throws IOException {
            d dVar = d.this;
            dVar.r();
            try {
                this.f8863g.flush();
                g5.r rVar = g5.r.f6563a;
                if (dVar.s()) {
                    throw dVar.m(null);
                }
            } catch (IOException e7) {
                if (!dVar.s()) {
                    throw e7;
                }
                throw dVar.m(e7);
            } finally {
                dVar.s();
            }
        }

        @Override // p6.y
        public void l(e source, long j7) throws IOException {
            kotlin.jvm.internal.l.e(source, "source");
            p6.c.b(source.j0(), 0L, j7);
            while (true) {
                long j8 = 0;
                if (j7 <= 0) {
                    return;
                }
                v vVar = source.f8866e;
                kotlin.jvm.internal.l.c(vVar);
                while (true) {
                    if (j8 >= 65536) {
                        break;
                    }
                    j8 += (long) (vVar.f8904c - vVar.f8903b);
                    if (j8 >= j7) {
                        j8 = j7;
                        break;
                    } else {
                        vVar = vVar.f8907f;
                        kotlin.jvm.internal.l.c(vVar);
                    }
                }
                d dVar = d.this;
                dVar.r();
                try {
                    this.f8863g.l(source, j8);
                    g5.r rVar = g5.r.f6563a;
                    if (dVar.s()) {
                        throw dVar.m(null);
                    }
                    j7 -= j8;
                } catch (IOException e7) {
                    if (!dVar.s()) {
                        throw e7;
                    }
                    throw dVar.m(e7);
                } finally {
                    dVar.s();
                }
            }
        }

        @Override // p6.y
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public d c() {
            return d.this;
        }

        public String toString() {
            return "AsyncTimeout.sink(" + this.f8863g + ')';
        }
    }

    /* JADX INFO: renamed from: p6.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AsyncTimeout.kt */
    public static final class C0136d implements a0 {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ a0 f8865g;

        C0136d(a0 a0Var) {
            this.f8865g = a0Var;
        }

        @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            d dVar = d.this;
            dVar.r();
            try {
                this.f8865g.close();
                g5.r rVar = g5.r.f6563a;
                if (dVar.s()) {
                    throw dVar.m(null);
                }
            } catch (IOException e7) {
                if (!dVar.s()) {
                    throw e7;
                }
                throw dVar.m(e7);
            } finally {
                dVar.s();
            }
        }

        @Override // p6.a0
        public long i(e sink, long j7) throws IOException {
            kotlin.jvm.internal.l.e(sink, "sink");
            d dVar = d.this;
            dVar.r();
            try {
                long jI = this.f8865g.i(sink, j7);
                if (dVar.s()) {
                    throw dVar.m(null);
                }
                return jI;
            } catch (IOException e7) {
                if (dVar.s()) {
                    throw dVar.m(e7);
                }
                throw e7;
            } finally {
                dVar.s();
            }
        }

        @Override // p6.a0
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public d c() {
            return d.this;
        }

        public String toString() {
            return "AsyncTimeout.source(" + this.f8865g + ')';
        }
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f8855i = millis;
        f8856j = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long u(long j7) {
        return this.f8861h - j7;
    }

    public final IOException m(IOException iOException) {
        return t(iOException);
    }

    public final void r() {
        if (!(!this.f8859f)) {
            throw new IllegalStateException("Unbalanced enter/exit".toString());
        }
        long jH = h();
        boolean zE = e();
        if (jH != 0 || zE) {
            this.f8859f = true;
            f8858l.e(this, jH, zE);
        }
    }

    public final boolean s() {
        if (!this.f8859f) {
            return false;
        }
        this.f8859f = false;
        return f8858l.d(this);
    }

    protected IOException t(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final y v(y sink) {
        kotlin.jvm.internal.l.e(sink, "sink");
        return new c(sink);
    }

    public final a0 w(a0 source) {
        kotlin.jvm.internal.l.e(source, "source");
        return new C0136d(source);
    }

    protected void x() {
    }
}
