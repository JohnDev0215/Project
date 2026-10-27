package f.k0.k;

import f.w;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import javax.annotation.Nullable;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* JADX INFO: compiled from: Http2Stream.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    long f5384a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f5385b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f5386c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final f f5387d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Deque<w> f5388e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f5389f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final b f5390g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final a f5391h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final c f5392i;
    final c j;

    @Nullable
    f.k0.k.b k;

    @Nullable
    IOException l;

    /* JADX INFO: compiled from: Http2Stream.java */
    final class a implements Sink {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Buffer f5393b = new Buffer();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private w f5394c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f5395d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f5396e;

        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void a(boolean r12) throws java.io.IOException {
            /*
                r11 = this;
                f.k0.k.i r0 = f.k0.k.i.this
                monitor-enter(r0)
                f.k0.k.i r1 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L7f
                f.k0.k.i$c r1 = r1.j     // Catch: java.lang.Throwable -> L7f
                r1.m()     // Catch: java.lang.Throwable -> L7f
            La:
                f.k0.k.i r1 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L76
                long r2 = r1.f5385b     // Catch: java.lang.Throwable -> L76
                r4 = 0
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 > 0) goto L24
                boolean r2 = r11.f5396e     // Catch: java.lang.Throwable -> L76
                if (r2 != 0) goto L24
                boolean r2 = r11.f5395d     // Catch: java.lang.Throwable -> L76
                if (r2 != 0) goto L24
                f.k0.k.b r2 = r1.k     // Catch: java.lang.Throwable -> L76
                if (r2 != 0) goto L24
                r1.q()     // Catch: java.lang.Throwable -> L76
                goto La
            L24:
                f.k0.k.i$c r1 = r1.j     // Catch: java.lang.Throwable -> L7f
                r1.w()     // Catch: java.lang.Throwable -> L7f
                f.k0.k.i r1 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L7f
                r1.c()     // Catch: java.lang.Throwable -> L7f
                f.k0.k.i r1 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L7f
                long r1 = r1.f5385b     // Catch: java.lang.Throwable -> L7f
                okio.Buffer r3 = r11.f5393b     // Catch: java.lang.Throwable -> L7f
                long r3 = r3.g0()     // Catch: java.lang.Throwable -> L7f
                long r9 = java.lang.Math.min(r1, r3)     // Catch: java.lang.Throwable -> L7f
                f.k0.k.i r1 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L7f
                long r2 = r1.f5385b     // Catch: java.lang.Throwable -> L7f
                long r2 = r2 - r9
                r1.f5385b = r2     // Catch: java.lang.Throwable -> L7f
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L7f
                f.k0.k.i$c r0 = r1.j
                r0.m()
                if (r12 == 0) goto L59
                okio.Buffer r12 = r11.f5393b     // Catch: java.lang.Throwable -> L57
                long r0 = r12.g0()     // Catch: java.lang.Throwable -> L57
                int r12 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r12 != 0) goto L59
                r12 = 1
                goto L5a
            L57:
                r12 = move-exception
                goto L6e
            L59:
                r12 = 0
            L5a:
                r7 = r12
                f.k0.k.i r12 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L57
                f.k0.k.f r5 = r12.f5387d     // Catch: java.lang.Throwable -> L57
                int r6 = r12.f5386c     // Catch: java.lang.Throwable -> L57
                okio.Buffer r8 = r11.f5393b     // Catch: java.lang.Throwable -> L57
                r5.q0(r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L57
                f.k0.k.i r12 = f.k0.k.i.this
                f.k0.k.i$c r12 = r12.j
                r12.w()
                return
            L6e:
                f.k0.k.i r0 = f.k0.k.i.this
                f.k0.k.i$c r0 = r0.j
                r0.w()
                throw r12
            L76:
                r12 = move-exception
                f.k0.k.i r1 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L7f
                f.k0.k.i$c r1 = r1.j     // Catch: java.lang.Throwable -> L7f
                r1.w()     // Catch: java.lang.Throwable -> L7f
                throw r12     // Catch: java.lang.Throwable -> L7f
            L7f:
                r12 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L7f
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: f.k0.k.i.a.a(boolean):void");
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (i.this) {
                if (this.f5395d) {
                    return;
                }
                if (!i.this.f5391h.f5396e) {
                    boolean z = this.f5393b.g0() > 0;
                    if (this.f5394c != null) {
                        while (this.f5393b.g0() > 0) {
                            a(false);
                        }
                        i iVar = i.this;
                        iVar.f5387d.r0(iVar.f5386c, true, f.k0.e.I(this.f5394c));
                    } else if (z) {
                        while (this.f5393b.g0() > 0) {
                            a(true);
                        }
                    } else {
                        i iVar2 = i.this;
                        iVar2.f5387d.q0(iVar2.f5386c, true, null, 0L);
                    }
                }
                synchronized (i.this) {
                    this.f5395d = true;
                }
                i.this.f5387d.flush();
                i.this.b();
            }
        }

        @Override // okio.Sink
        public Timeout e() {
            return i.this.j;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (i.this) {
                i.this.c();
            }
            while (this.f5393b.g0() > 0) {
                a(false);
                i.this.f5387d.flush();
            }
        }

        @Override // okio.Sink
        public void h(Buffer buffer, long j) throws IOException {
            this.f5393b.h(buffer, j);
            while (this.f5393b.g0() >= 16384) {
                a(false);
            }
        }
    }

    /* JADX INFO: compiled from: Http2Stream.java */
    private final class b implements Source {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Buffer f5398b = new Buffer();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Buffer f5399c = new Buffer();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final long f5400d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private w f5401e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f5402f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        boolean f5403g;

        b(long j) {
            this.f5400d = j;
        }

        private void j(long j) {
            i.this.f5387d.p0(j);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            long jG0;
            synchronized (i.this) {
                this.f5402f = true;
                jG0 = this.f5399c.g0();
                this.f5399c.R();
                i.this.notifyAll();
            }
            if (jG0 > 0) {
                j(jG0);
            }
            i.this.b();
        }

        void d(BufferedSource bufferedSource, long j) throws EOFException {
            boolean z;
            boolean z2;
            boolean z3;
            long jG0;
            while (j > 0) {
                synchronized (i.this) {
                    z = this.f5403g;
                    z2 = true;
                    z3 = this.f5399c.g0() + j > this.f5400d;
                }
                if (z3) {
                    bufferedSource.s(j);
                    i.this.f(f.k0.k.b.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    bufferedSource.s(j);
                    return;
                }
                long jP = bufferedSource.p(this.f5398b, j);
                if (jP == -1) {
                    throw new EOFException();
                }
                j -= jP;
                synchronized (i.this) {
                    if (this.f5402f) {
                        jG0 = this.f5398b.g0();
                        this.f5398b.R();
                    } else {
                        if (this.f5399c.g0() != 0) {
                            z2 = false;
                        }
                        this.f5399c.m(this.f5398b);
                        if (z2) {
                            i.this.notifyAll();
                        }
                        jG0 = 0;
                    }
                }
                if (jG0 > 0) {
                    j(jG0);
                }
            }
        }

        @Override // okio.Source
        public Timeout e() {
            return i.this.f5392i;
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x008c  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0090  */
        @Override // okio.Source
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public long p(okio.Buffer r12, long r13) throws java.lang.Throwable {
            /*
                r11 = this;
                r0 = 0
                int r2 = (r13 > r0 ? 1 : (r13 == r0 ? 0 : -1))
                if (r2 < 0) goto La8
            L6:
                r2 = 0
                f.k0.k.i r3 = f.k0.k.i.this
                monitor-enter(r3)
                f.k0.k.i r4 = f.k0.k.i.this     // Catch: java.lang.Throwable -> La5
                f.k0.k.i$c r4 = r4.f5392i     // Catch: java.lang.Throwable -> La5
                r4.m()     // Catch: java.lang.Throwable -> La5
                f.k0.k.i r4 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L9c
                f.k0.k.b r5 = r4.k     // Catch: java.lang.Throwable -> L9c
                if (r5 == 0) goto L25
                java.io.IOException r2 = r4.l     // Catch: java.lang.Throwable -> L9c
                if (r2 == 0) goto L1c
                goto L25
            L1c:
                f.k0.k.n r2 = new f.k0.k.n     // Catch: java.lang.Throwable -> L9c
                f.k0.k.i r4 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L9c
                f.k0.k.b r4 = r4.k     // Catch: java.lang.Throwable -> L9c
                r2.<init>(r4)     // Catch: java.lang.Throwable -> L9c
            L25:
                boolean r4 = r11.f5402f     // Catch: java.lang.Throwable -> L9c
                if (r4 != 0) goto L94
                okio.Buffer r4 = r11.f5399c     // Catch: java.lang.Throwable -> L9c
                long r4 = r4.g0()     // Catch: java.lang.Throwable -> L9c
                int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                r5 = -1
                if (r4 <= 0) goto L6b
                okio.Buffer r4 = r11.f5399c     // Catch: java.lang.Throwable -> L9c
                long r7 = r4.g0()     // Catch: java.lang.Throwable -> L9c
                long r13 = java.lang.Math.min(r13, r7)     // Catch: java.lang.Throwable -> L9c
                long r12 = r4.p(r12, r13)     // Catch: java.lang.Throwable -> L9c
                f.k0.k.i r14 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L9c
                long r7 = r14.f5384a     // Catch: java.lang.Throwable -> L9c
                long r7 = r7 + r12
                r14.f5384a = r7     // Catch: java.lang.Throwable -> L9c
                if (r2 != 0) goto L80
                f.k0.k.f r14 = r14.f5387d     // Catch: java.lang.Throwable -> L9c
                f.k0.k.m r14 = r14.v     // Catch: java.lang.Throwable -> L9c
                int r14 = r14.d()     // Catch: java.lang.Throwable -> L9c
                int r14 = r14 / 2
                long r9 = (long) r14     // Catch: java.lang.Throwable -> L9c
                int r14 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                if (r14 < 0) goto L80
                f.k0.k.i r14 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L9c
                f.k0.k.f r4 = r14.f5387d     // Catch: java.lang.Throwable -> L9c
                int r7 = r14.f5386c     // Catch: java.lang.Throwable -> L9c
                long r8 = r14.f5384a     // Catch: java.lang.Throwable -> L9c
                r4.v0(r7, r8)     // Catch: java.lang.Throwable -> L9c
                f.k0.k.i r14 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L9c
                r14.f5384a = r0     // Catch: java.lang.Throwable -> L9c
                goto L80
            L6b:
                boolean r4 = r11.f5403g     // Catch: java.lang.Throwable -> L9c
                if (r4 != 0) goto L7f
                if (r2 != 0) goto L7f
                f.k0.k.i r2 = f.k0.k.i.this     // Catch: java.lang.Throwable -> L9c
                r2.q()     // Catch: java.lang.Throwable -> L9c
                f.k0.k.i r2 = f.k0.k.i.this     // Catch: java.lang.Throwable -> La5
                f.k0.k.i$c r2 = r2.f5392i     // Catch: java.lang.Throwable -> La5
                r2.w()     // Catch: java.lang.Throwable -> La5
                monitor-exit(r3)     // Catch: java.lang.Throwable -> La5
                goto L6
            L7f:
                r12 = r5
            L80:
                f.k0.k.i r14 = f.k0.k.i.this     // Catch: java.lang.Throwable -> La5
                f.k0.k.i$c r14 = r14.f5392i     // Catch: java.lang.Throwable -> La5
                r14.w()     // Catch: java.lang.Throwable -> La5
                monitor-exit(r3)     // Catch: java.lang.Throwable -> La5
                int r14 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
                if (r14 == 0) goto L90
                r11.j(r12)
                return r12
            L90:
                if (r2 != 0) goto L93
                return r5
            L93:
                throw r2
            L94:
                java.io.IOException r12 = new java.io.IOException     // Catch: java.lang.Throwable -> L9c
                java.lang.String r13 = "stream closed"
                r12.<init>(r13)     // Catch: java.lang.Throwable -> L9c
                throw r12     // Catch: java.lang.Throwable -> L9c
            L9c:
                r12 = move-exception
                f.k0.k.i r13 = f.k0.k.i.this     // Catch: java.lang.Throwable -> La5
                f.k0.k.i$c r13 = r13.f5392i     // Catch: java.lang.Throwable -> La5
                r13.w()     // Catch: java.lang.Throwable -> La5
                throw r12     // Catch: java.lang.Throwable -> La5
            La5:
                r12 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> La5
                throw r12
            La8:
                java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "byteCount < 0: "
                r0.append(r1)
                r0.append(r13)
                java.lang.String r13 = r0.toString()
                r12.<init>(r13)
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: f.k0.k.i.b.p(okio.Buffer, long):long");
        }
    }

    /* JADX INFO: compiled from: Http2Stream.java */
    class c extends AsyncTimeout {
        c() {
        }

        @Override // okio.AsyncTimeout
        protected IOException q(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // okio.AsyncTimeout
        protected void v() {
            i.this.f(f.k0.k.b.CANCEL);
            i.this.f5387d.l0();
        }

        public void w() throws IOException {
            if (p()) {
                throw q(null);
            }
        }
    }

    i(int i2, f fVar, boolean z, boolean z2, @Nullable w wVar) {
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f5388e = arrayDeque;
        this.f5392i = new c();
        this.j = new c();
        Objects.requireNonNull(fVar, "connection == null");
        this.f5386c = i2;
        this.f5387d = fVar;
        this.f5385b = fVar.w.d();
        b bVar = new b(fVar.v.d());
        this.f5390g = bVar;
        a aVar = new a();
        this.f5391h = aVar;
        bVar.f5403g = z2;
        aVar.f5396e = z;
        if (wVar != null) {
            arrayDeque.add(wVar);
        }
        if (j() && wVar != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        }
        if (!j() && wVar == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    private boolean e(f.k0.k.b bVar, @Nullable IOException iOException) {
        synchronized (this) {
            if (this.k != null) {
                return false;
            }
            if (this.f5390g.f5403g && this.f5391h.f5396e) {
                return false;
            }
            this.k = bVar;
            this.l = iOException;
            notifyAll();
            this.f5387d.k0(this.f5386c);
            return true;
        }
    }

    void a(long j) {
        this.f5385b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void b() {
        /*
            r2 = this;
            monitor-enter(r2)
            f.k0.k.i$b r0 = r2.f5390g     // Catch: java.lang.Throwable -> L30
            boolean r1 = r0.f5403g     // Catch: java.lang.Throwable -> L30
            if (r1 != 0) goto L17
            boolean r0 = r0.f5402f     // Catch: java.lang.Throwable -> L30
            if (r0 == 0) goto L17
            f.k0.k.i$a r0 = r2.f5391h     // Catch: java.lang.Throwable -> L30
            boolean r1 = r0.f5396e     // Catch: java.lang.Throwable -> L30
            if (r1 != 0) goto L15
            boolean r0 = r0.f5395d     // Catch: java.lang.Throwable -> L30
            if (r0 == 0) goto L17
        L15:
            r0 = 1
            goto L18
        L17:
            r0 = 0
        L18:
            boolean r1 = r2.k()     // Catch: java.lang.Throwable -> L30
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L30
            if (r0 == 0) goto L26
            f.k0.k.b r0 = f.k0.k.b.CANCEL
            r1 = 0
            r2.d(r0, r1)
            goto L2f
        L26:
            if (r1 != 0) goto L2f
            f.k0.k.f r0 = r2.f5387d
            int r1 = r2.f5386c
            r0.k0(r1)
        L2f:
            return
        L30:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L30
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: f.k0.k.i.b():void");
    }

    void c() throws IOException {
        a aVar = this.f5391h;
        if (aVar.f5395d) {
            throw new IOException("stream closed");
        }
        if (aVar.f5396e) {
            throw new IOException("stream finished");
        }
        if (this.k != null) {
            IOException iOException = this.l;
            if (iOException == null) {
                throw new n(this.k);
            }
        }
    }

    public void d(f.k0.k.b bVar, @Nullable IOException iOException) {
        if (e(bVar, iOException)) {
            this.f5387d.t0(this.f5386c, bVar);
        }
    }

    public void f(f.k0.k.b bVar) {
        if (e(bVar, null)) {
            this.f5387d.u0(this.f5386c, bVar);
        }
    }

    public int g() {
        return this.f5386c;
    }

    public Sink h() {
        synchronized (this) {
            if (!this.f5389f && !j()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f5391h;
    }

    public Source i() {
        return this.f5390g;
    }

    public boolean j() {
        return this.f5387d.f5314c == ((this.f5386c & 1) == 1);
    }

    public synchronized boolean k() {
        if (this.k != null) {
            return false;
        }
        b bVar = this.f5390g;
        if (bVar.f5403g || bVar.f5402f) {
            a aVar = this.f5391h;
            if (aVar.f5396e || aVar.f5395d) {
                if (this.f5389f) {
                    return false;
                }
            }
        }
        return true;
    }

    public Timeout l() {
        return this.f5392i;
    }

    void m(BufferedSource bufferedSource, int i2) {
        this.f5390g.d(bufferedSource, i2);
    }

    void n(w wVar, boolean z) {
        boolean zK;
        synchronized (this) {
            if (this.f5389f && z) {
                this.f5390g.f5401e = wVar;
            } else {
                this.f5389f = true;
                this.f5388e.add(wVar);
            }
            if (z) {
                this.f5390g.f5403g = true;
            }
            zK = k();
            notifyAll();
        }
        if (zK) {
            return;
        }
        this.f5387d.k0(this.f5386c);
    }

    synchronized void o(f.k0.k.b bVar) {
        if (this.k == null) {
            this.k = bVar;
            notifyAll();
        }
    }

    public synchronized w p() throws IOException {
        this.f5392i.m();
        while (this.f5388e.isEmpty() && this.k == null) {
            try {
                q();
            } catch (Throwable th) {
                this.f5392i.w();
                throw th;
            }
        }
        this.f5392i.w();
        if (this.f5388e.isEmpty()) {
            IOException iOException = this.l;
            if (iOException != null) {
                throw iOException;
            }
            throw new n(this.k);
        }
        return this.f5388e.removeFirst();
    }

    void q() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public Timeout r() {
        return this.j;
    }
}
