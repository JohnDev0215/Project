package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class AsyncTimeout extends Timeout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final long f5758e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final long f5759f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @Nullable
    static AsyncTimeout f5760g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f5761h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @Nullable
    private AsyncTimeout f5762i;
    private long j;

    private static final class Watchdog extends Thread {
        Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
        
            r1.v();
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
                java.lang.Class<okio.AsyncTimeout> r0 = okio.AsyncTimeout.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L0
                okio.AsyncTimeout r1 = okio.AsyncTimeout.k()     // Catch: java.lang.Throwable -> L19
                if (r1 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L0
            Lb:
                okio.AsyncTimeout r2 = okio.AsyncTimeout.f5760g     // Catch: java.lang.Throwable -> L19
                if (r1 != r2) goto L14
                r1 = 0
                okio.AsyncTimeout.f5760g = r1     // Catch: java.lang.Throwable -> L19
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                return
            L14:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                r1.v()     // Catch: java.lang.InterruptedException -> L0
                goto L0
            L19:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                throw r1     // Catch: java.lang.InterruptedException -> L0
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.Watchdog.run():void");
        }
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f5758e = millis;
        f5759f = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    @Nullable
    static AsyncTimeout k() throws InterruptedException {
        AsyncTimeout asyncTimeout = f5760g.f5762i;
        if (asyncTimeout == null) {
            long jNanoTime = System.nanoTime();
            AsyncTimeout.class.wait(f5758e);
            if (f5760g.f5762i != null || System.nanoTime() - jNanoTime < f5759f) {
                return null;
            }
            return f5760g;
        }
        long jR = asyncTimeout.r(System.nanoTime());
        if (jR > 0) {
            long j = jR / 1000000;
            AsyncTimeout.class.wait(j, (int) (jR - (1000000 * j)));
            return null;
        }
        f5760g.f5762i = asyncTimeout.f5762i;
        asyncTimeout.f5762i = null;
        return asyncTimeout;
    }

    private static synchronized boolean l(AsyncTimeout asyncTimeout) {
        AsyncTimeout asyncTimeout2 = f5760g;
        while (asyncTimeout2 != null) {
            AsyncTimeout asyncTimeout3 = asyncTimeout2.f5762i;
            if (asyncTimeout3 == asyncTimeout) {
                asyncTimeout2.f5762i = asyncTimeout.f5762i;
                asyncTimeout.f5762i = null;
                return false;
            }
            asyncTimeout2 = asyncTimeout3;
        }
        return true;
    }

    private long r(long j) {
        return this.j - j;
    }

    private static synchronized void s(AsyncTimeout asyncTimeout, long j, boolean z) {
        if (f5760g == null) {
            f5760g = new AsyncTimeout();
            new Watchdog().start();
        }
        long jNanoTime = System.nanoTime();
        if (j != 0 && z) {
            asyncTimeout.j = Math.min(j, asyncTimeout.c() - jNanoTime) + jNanoTime;
        } else if (j != 0) {
            asyncTimeout.j = j + jNanoTime;
        } else {
            if (!z) {
                throw new AssertionError();
            }
            asyncTimeout.j = asyncTimeout.c();
        }
        long jR = asyncTimeout.r(jNanoTime);
        AsyncTimeout asyncTimeout2 = f5760g;
        while (true) {
            AsyncTimeout asyncTimeout3 = asyncTimeout2.f5762i;
            if (asyncTimeout3 == null || jR < asyncTimeout3.r(jNanoTime)) {
                break;
            } else {
                asyncTimeout2 = asyncTimeout2.f5762i;
            }
        }
        asyncTimeout.f5762i = asyncTimeout2.f5762i;
        asyncTimeout2.f5762i = asyncTimeout;
        if (asyncTimeout2 == f5760g) {
            AsyncTimeout.class.notify();
        }
    }

    public final void m() {
        if (this.f5761h) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jI = i();
        boolean zE = e();
        if (jI != 0 || zE) {
            this.f5761h = true;
            s(this, jI, zE);
        }
    }

    final IOException n(IOException iOException) {
        return !p() ? iOException : q(iOException);
    }

    final void o(boolean z) throws IOException {
        if (p() && z) {
            throw q(null);
        }
    }

    public final boolean p() {
        if (!this.f5761h) {
            return false;
        }
        this.f5761h = false;
        return l(this);
    }

    protected IOException q(@Nullable IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final Sink t(final Sink sink) {
        return new Sink() { // from class: okio.AsyncTimeout.1
            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                AsyncTimeout.this.m();
                try {
                    try {
                        sink.close();
                        AsyncTimeout.this.o(true);
                    } catch (IOException e2) {
                        throw AsyncTimeout.this.n(e2);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.o(false);
                    throw th;
                }
            }

            @Override // okio.Sink
            public Timeout e() {
                return AsyncTimeout.this;
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() throws IOException {
                AsyncTimeout.this.m();
                try {
                    try {
                        sink.flush();
                        AsyncTimeout.this.o(true);
                    } catch (IOException e2) {
                        throw AsyncTimeout.this.n(e2);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.o(false);
                    throw th;
                }
            }

            @Override // okio.Sink
            public void h(Buffer buffer, long j) throws IOException {
                Util.b(buffer.f5771d, 0L, j);
                while (true) {
                    long j2 = 0;
                    if (j <= 0) {
                        return;
                    }
                    Segment segment = buffer.f5770c;
                    while (true) {
                        if (j2 >= 65536) {
                            break;
                        }
                        j2 += (long) (segment.f5844c - segment.f5843b);
                        if (j2 >= j) {
                            j2 = j;
                            break;
                        }
                        segment = segment.f5847f;
                    }
                    AsyncTimeout.this.m();
                    try {
                        try {
                            sink.h(buffer, j2);
                            j -= j2;
                            AsyncTimeout.this.o(true);
                        } catch (IOException e2) {
                            throw AsyncTimeout.this.n(e2);
                        }
                    } catch (Throwable th) {
                        AsyncTimeout.this.o(false);
                        throw th;
                    }
                }
            }

            public String toString() {
                return "AsyncTimeout.sink(" + sink + ")";
            }
        };
    }

    public final Source u(final Source source) {
        return new Source() { // from class: okio.AsyncTimeout.2
            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                AsyncTimeout.this.m();
                try {
                    try {
                        source.close();
                        AsyncTimeout.this.o(true);
                    } catch (IOException e2) {
                        throw AsyncTimeout.this.n(e2);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.o(false);
                    throw th;
                }
            }

            @Override // okio.Source
            public Timeout e() {
                return AsyncTimeout.this;
            }

            @Override // okio.Source
            public long p(Buffer buffer, long j) throws IOException {
                AsyncTimeout.this.m();
                try {
                    try {
                        long jP = source.p(buffer, j);
                        AsyncTimeout.this.o(true);
                        return jP;
                    } catch (IOException e2) {
                        throw AsyncTimeout.this.n(e2);
                    }
                } catch (Throwable th) {
                    AsyncTimeout.this.o(false);
                    throw th;
                }
            }

            public String toString() {
                return "AsyncTimeout.source(" + source + ")";
            }
        };
    }

    protected void v() {
    }
}
