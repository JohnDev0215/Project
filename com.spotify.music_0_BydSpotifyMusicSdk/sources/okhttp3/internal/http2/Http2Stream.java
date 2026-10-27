package okhttp3.internal.http2;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import p.de2;
import p.fo;
import p.pd2;
import p.tl2;
import p.u4;
import p.uc;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Http2Stream {
    public final int a;
    public final Http2Connection b;
    public long c;
    public long d;
    public long e;
    public long f;
    public final ArrayDeque g;
    public boolean h;
    public final FramingSource i;
    public final FramingSink j;
    public final StreamTimeout k;
    public final StreamTimeout l;
    public ErrorCode m;
    public IOException n;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public final class FramingSink implements pd2 {
        public final boolean c;
        public final fo e = new fo();
        public boolean k;

        public FramingSink(boolean z) {
            this.c = z;
        }

        @Override // p.pd2
        public final void Y(fo foVar, long j) throws IOException {
            v03.j(foVar, "source");
            byte[] bArr = Util.a;
            fo foVar2 = this.e;
            foVar2.Y(foVar, j);
            while (foVar2.e >= 16384) {
                b(false);
            }
        }

        public final void b(boolean z) throws IOException {
            long jMin;
            boolean z2;
            Http2Stream http2Stream = Http2Stream.this;
            synchronized (http2Stream) {
                http2Stream.l.i();
                while (http2Stream.e >= http2Stream.f && !this.c && !this.k) {
                    try {
                        synchronized (http2Stream) {
                            ErrorCode errorCode = http2Stream.m;
                            if (errorCode != null) {
                                break;
                            } else {
                                http2Stream.k();
                            }
                        }
                    } catch (Throwable th) {
                        http2Stream.l.m();
                        throw th;
                    }
                }
                http2Stream.l.m();
                http2Stream.b();
                jMin = Math.min(http2Stream.f - http2Stream.e, this.e.e);
                http2Stream.e += jMin;
                z2 = z && jMin == this.e.e;
            }
            Http2Stream.this.l.i();
            try {
                Http2Stream http2Stream2 = Http2Stream.this;
                http2Stream2.b.X(http2Stream2.a, z2, this.e, jMin);
            } finally {
                Http2Stream.this.l.m();
            }
        }

        @Override // p.pd2, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            boolean z;
            Http2Stream http2Stream = Http2Stream.this;
            byte[] bArr = Util.a;
            synchronized (http2Stream) {
                if (this.k) {
                    return;
                }
                synchronized (http2Stream) {
                    z = http2Stream.m == null;
                }
                Http2Stream http2Stream2 = Http2Stream.this;
                if (!http2Stream2.j.c) {
                    if (this.e.e > 0) {
                        while (this.e.e > 0) {
                            b(true);
                        }
                    } else if (z) {
                        http2Stream2.b.X(http2Stream2.a, true, null, 0L);
                    }
                }
                synchronized (Http2Stream.this) {
                    this.k = true;
                }
                Http2Stream.this.b.flush();
                Http2Stream.this.a();
            }
        }

        @Override // p.pd2
        public final tl2 e() {
            return Http2Stream.this.l;
        }

        @Override // p.pd2, java.io.Flushable
        public final void flush() throws IOException {
            Http2Stream http2Stream = Http2Stream.this;
            byte[] bArr = Util.a;
            synchronized (http2Stream) {
                http2Stream.b();
            }
            while (this.e.e > 0) {
                b(false);
                Http2Stream.this.b.flush();
            }
        }
    }

    public final class FramingSource implements de2 {
        public final long c;
        public boolean e;
        public final fo k = new fo();
        public final fo l = new fo();
        public boolean m;

        public FramingSource(long j, boolean z) {
            this.c = j;
            this.e = z;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            long j;
            Http2Stream http2Stream = Http2Stream.this;
            synchronized (http2Stream) {
                this.m = true;
                fo foVar = this.l;
                j = foVar.e;
                foVar.X();
                http2Stream.notifyAll();
            }
            if (j > 0) {
                byte[] bArr = Util.a;
                Http2Stream.this.b.J(j);
            }
            Http2Stream.this.a();
        }

        @Override // p.de2
        public final tl2 e() {
            return Http2Stream.this.k;
        }

        /* JADX WARN: Removed duplicated region for block: B:45:0x008a A[LOOP:0: B:4:0x0010->B:45:0x008a, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x008d A[SYNTHETIC] */
        @Override // p.de2
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final long t(p.fo r16, long r17) throws java.lang.Throwable {
            /*
                r15 = this;
                r1 = r15
                r0 = r16
                r2 = r17
                java.lang.String r4 = "sink"
                p.v03.j(r0, r4)
                r4 = 0
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 < 0) goto Lac
            L10:
                okhttp3.internal.http2.Http2Stream r6 = okhttp3.internal.http2.Http2Stream.this
                monitor-enter(r6)
                okhttp3.internal.http2.Http2Stream$StreamTimeout r7 = r6.k     // Catch: java.lang.Throwable -> L96
                r7.i()     // Catch: java.lang.Throwable -> L96
                monitor-enter(r6)     // Catch: java.lang.Throwable -> L33
                okhttp3.internal.http2.ErrorCode r7 = r6.m     // Catch: java.lang.Throwable -> La0
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L33
                if (r7 == 0) goto L3a
                boolean r7 = r1.e     // Catch: java.lang.Throwable -> L33
                if (r7 != 0) goto L3a
                java.io.IOException r7 = r6.n     // Catch: java.lang.Throwable -> L33
                if (r7 != 0) goto L3b
                okhttp3.internal.http2.StreamResetException r7 = new okhttp3.internal.http2.StreamResetException     // Catch: java.lang.Throwable -> L33
                monitor-enter(r6)     // Catch: java.lang.Throwable -> L33
                okhttp3.internal.http2.ErrorCode r8 = r6.m     // Catch: java.lang.Throwable -> L36
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L33
                p.v03.h(r8)     // Catch: java.lang.Throwable -> L33
                r7.<init>(r8)     // Catch: java.lang.Throwable -> L33
                goto L3b
            L33:
                r0 = move-exception
                goto La4
            L36:
                r0 = move-exception
                r2 = r0
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L33
                throw r2     // Catch: java.lang.Throwable -> L33
            L3a:
                r7 = 0
            L3b:
                boolean r8 = r1.m     // Catch: java.lang.Throwable -> L33
                if (r8 != 0) goto L98
                p.fo r8 = r1.l     // Catch: java.lang.Throwable -> L33
                long r9 = r8.e     // Catch: java.lang.Throwable -> L33
                int r11 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
                r12 = -1
                r14 = 0
                if (r11 <= 0) goto L77
                long r9 = java.lang.Math.min(r2, r9)     // Catch: java.lang.Throwable -> L33
                long r8 = r8.t(r0, r9)     // Catch: java.lang.Throwable -> L33
                long r10 = r6.c     // Catch: java.lang.Throwable -> L33
                long r10 = r10 + r8
                r6.c = r10     // Catch: java.lang.Throwable -> L33
                long r4 = r6.d     // Catch: java.lang.Throwable -> L33
                long r10 = r10 - r4
                if (r7 != 0) goto L82
                okhttp3.internal.http2.Http2Connection r4 = r6.b     // Catch: java.lang.Throwable -> L33
                okhttp3.internal.http2.Settings r4 = r4.z     // Catch: java.lang.Throwable -> L33
                int r4 = r4.a()     // Catch: java.lang.Throwable -> L33
                int r4 = r4 / 2
                long r4 = (long) r4     // Catch: java.lang.Throwable -> L33
                int r4 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
                if (r4 < 0) goto L82
                okhttp3.internal.http2.Http2Connection r4 = r6.b     // Catch: java.lang.Throwable -> L33
                int r5 = r6.a     // Catch: java.lang.Throwable -> L33
                r4.E0(r5, r10)     // Catch: java.lang.Throwable -> L33
                long r4 = r6.c     // Catch: java.lang.Throwable -> L33
                r6.d = r4     // Catch: java.lang.Throwable -> L33
                goto L82
            L77:
                boolean r4 = r1.e     // Catch: java.lang.Throwable -> L33
                if (r4 != 0) goto L81
                if (r7 != 0) goto L81
                r6.k()     // Catch: java.lang.Throwable -> L33
                r14 = 1
            L81:
                r8 = r12
            L82:
                okhttp3.internal.http2.Http2Stream$StreamTimeout r4 = r6.k     // Catch: java.lang.Throwable -> L96
                r4.m()     // Catch: java.lang.Throwable -> L96
                monitor-exit(r6)
                if (r14 == 0) goto L8d
                r4 = 0
                goto L10
            L8d:
                int r0 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
                if (r0 == 0) goto L92
                return r8
            L92:
                if (r7 != 0) goto L95
                return r12
            L95:
                throw r7
            L96:
                r0 = move-exception
                goto Laa
            L98:
                java.io.IOException r0 = new java.io.IOException     // Catch: java.lang.Throwable -> L33
                java.lang.String r2 = "stream closed"
                r0.<init>(r2)     // Catch: java.lang.Throwable -> L33
                throw r0     // Catch: java.lang.Throwable -> L33
            La0:
                r0 = move-exception
                r2 = r0
                monitor-exit(r6)     // Catch: java.lang.Throwable -> L33
                throw r2     // Catch: java.lang.Throwable -> L33
            La4:
                okhttp3.internal.http2.Http2Stream$StreamTimeout r2 = r6.k     // Catch: java.lang.Throwable -> L96
                r2.m()     // Catch: java.lang.Throwable -> L96
                throw r0     // Catch: java.lang.Throwable -> L96
            Laa:
                monitor-exit(r6)
                throw r0
            Lac:
                java.lang.String r0 = "byteCount < 0: "
                java.lang.String r0 = p.u4.g(r0, r2)
                java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
                java.lang.String r0 = r0.toString()
                r2.<init>(r0)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.t(p.fo, long):long");
        }
    }

    public final class StreamTimeout extends uc {
        public StreamTimeout() {
        }

        @Override // p.uc
        public final IOException k(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // p.uc
        public final void l() {
            Http2Stream.this.e(ErrorCode.CANCEL);
            final Http2Connection http2Connection = Http2Stream.this.b;
            synchronized (http2Connection) {
                long j = http2Connection.x;
                long j2 = http2Connection.w;
                if (j < j2) {
                    return;
                }
                http2Connection.w = j2 + 1;
                http2Connection.y = System.nanoTime() + ((long) 1000000000);
                TaskQueue taskQueue = http2Connection.q;
                final String strM = u4.m(new StringBuilder(), http2Connection.l, " ping");
                taskQueue.c(new Task(strM) { // from class: okhttp3.internal.http2.Http2Connection$sendDegradedPingLater$$inlined$execute$default$1
                    @Override // okhttp3.internal.concurrent.Task
                    public final long a() {
                        Http2Connection http2Connection2 = http2Connection;
                        http2Connection2.getClass();
                        try {
                            http2Connection2.G.E(2, 0, false);
                            return -1L;
                        } catch (IOException e) {
                            http2Connection2.c(e);
                            return -1L;
                        }
                    }
                }, 0L);
            }
        }

        public final void m() throws IOException {
            if (j()) {
                throw k(null);
            }
        }
    }

    static {
        new Companion(0);
    }

    public Http2Stream(int i, Http2Connection http2Connection, boolean z, boolean z2, Headers headers) {
        this.a = i;
        this.b = http2Connection;
        this.f = http2Connection.A.a();
        ArrayDeque arrayDeque = new ArrayDeque();
        this.g = arrayDeque;
        this.i = new FramingSource(http2Connection.z.a(), z2);
        this.j = new FramingSink(z);
        this.k = new StreamTimeout();
        this.l = new StreamTimeout();
        if (headers == null) {
            if (!g()) {
                throw new IllegalStateException("remotely-initiated streams should have headers".toString());
            }
        } else {
            if (!(!g())) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet".toString());
            }
            arrayDeque.add(headers);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            r2 = this;
            byte[] r0 = okhttp3.internal.Util.a
            monitor-enter(r2)
            okhttp3.internal.http2.Http2Stream$FramingSource r0 = r2.i     // Catch: java.lang.Throwable -> L19
            boolean r1 = r0.e     // Catch: java.lang.Throwable -> L19
            if (r1 != 0) goto L1b
            boolean r0 = r0.m     // Catch: java.lang.Throwable -> L19
            if (r0 == 0) goto L1b
            okhttp3.internal.http2.Http2Stream$FramingSink r0 = r2.j     // Catch: java.lang.Throwable -> L19
            boolean r1 = r0.c     // Catch: java.lang.Throwable -> L19
            if (r1 != 0) goto L17
            boolean r0 = r0.k     // Catch: java.lang.Throwable -> L19
            if (r0 == 0) goto L1b
        L17:
            r0 = 1
            goto L1c
        L19:
            r0 = move-exception
            goto L34
        L1b:
            r0 = 0
        L1c:
            boolean r1 = r2.h()     // Catch: java.lang.Throwable -> L19
            monitor-exit(r2)
            if (r0 == 0) goto L2a
            okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.CANCEL
            r1 = 0
            r2.c(r0, r1)
            goto L33
        L2a:
            if (r1 != 0) goto L33
            okhttp3.internal.http2.Http2Connection r0 = r2.b
            int r1 = r2.a
            r0.q(r1)
        L33:
            return
        L34:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.a():void");
    }

    public final void b() throws IOException {
        FramingSink framingSink = this.j;
        if (framingSink.k) {
            throw new IOException("stream closed");
        }
        if (framingSink.c) {
            throw new IOException("stream finished");
        }
        if (this.m != null) {
            IOException iOException = this.n;
            if (iOException != null) {
                throw iOException;
            }
            ErrorCode errorCode = this.m;
            v03.h(errorCode);
            throw new StreamResetException(errorCode);
        }
    }

    public final void c(ErrorCode errorCode, IOException iOException) {
        if (d(errorCode, iOException)) {
            Http2Connection http2Connection = this.b;
            http2Connection.getClass();
            http2Connection.G.J(this.a, errorCode);
        }
    }

    public final boolean d(ErrorCode errorCode, IOException iOException) {
        byte[] bArr = Util.a;
        synchronized (this) {
            if (this.m != null) {
                return false;
            }
            this.m = errorCode;
            this.n = iOException;
            notifyAll();
            if (this.i.e) {
                if (this.j.c) {
                    return false;
                }
            }
            this.b.q(this.a);
            return true;
        }
    }

    public final void e(ErrorCode errorCode) {
        if (d(errorCode, null)) {
            this.b.o0(this.a, errorCode);
        }
    }

    public final FramingSink f() {
        synchronized (this) {
            if (!this.h && !g()) {
                throw new IllegalStateException("reply before requesting the sink".toString());
            }
        }
        return this.j;
    }

    public final boolean g() {
        return this.b.c == ((this.a & 1) == 1);
    }

    public final synchronized boolean h() {
        if (this.m != null) {
            return false;
        }
        FramingSource framingSource = this.i;
        if (framingSource.e || framingSource.m) {
            FramingSink framingSink = this.j;
            if (framingSink.c || framingSink.k) {
                if (this.h) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void i(Headers headers, boolean z) {
        boolean zH;
        v03.j(headers, "headers");
        byte[] bArr = Util.a;
        synchronized (this) {
            try {
                if (this.h && z) {
                    this.i.getClass();
                } else {
                    this.h = true;
                    this.g.add(headers);
                }
                if (z) {
                    this.i.e = true;
                }
                zH = h();
                notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (zH) {
            return;
        }
        this.b.q(this.a);
    }

    public final synchronized void j(ErrorCode errorCode) {
        if (this.m == null) {
            this.m = errorCode;
            notifyAll();
        }
    }

    public final void k() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }
}
