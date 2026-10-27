package e.d0.i;

import f.p;
import f.q;
import f.r;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

/* JADX INFO: compiled from: Http2Stream.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f3495b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3496c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e f3497d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<e.d0.i.a> f3498e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<e.d0.i.a> f3499f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3500g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b f3501h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a f3502i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3494a = 0;
    public final c j = new c();
    public final c k = new c();
    public ErrorCode l = null;

    /* JADX INFO: compiled from: Http2Stream.java */
    public final class a implements p {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final f.c f3503c = new f.c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f3504d;
        public boolean s;

        public a() {
        }

        public final void c(boolean z) throws IOException {
            g gVar;
            long jMin;
            g gVar2;
            synchronized (g.this) {
                g.this.k.k();
                while (true) {
                    try {
                        gVar = g.this;
                        if (gVar.f3495b > 0 || this.s || this.f3504d || gVar.l != null) {
                            break;
                        } else {
                            gVar.r();
                        }
                    } finally {
                    }
                }
                gVar.k.u();
                g.this.c();
                jMin = Math.min(g.this.f3495b, this.f3503c.Y());
                gVar2 = g.this;
                gVar2.f3495b -= jMin;
            }
            gVar2.k.k();
            try {
                g gVar3 = g.this;
                gVar3.f3497d.U(gVar3.f3496c, z && jMin == this.f3503c.Y(), this.f3503c, jMin);
            } finally {
            }
        }

        @Override // f.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (g.this) {
                if (this.f3504d) {
                    return;
                }
                if (!g.this.f3502i.s) {
                    if (this.f3503c.Y() > 0) {
                        while (this.f3503c.Y() > 0) {
                            c(true);
                        }
                    } else {
                        g gVar = g.this;
                        gVar.f3497d.U(gVar.f3496c, true, null, 0L);
                    }
                }
                synchronized (g.this) {
                    this.f3504d = true;
                }
                g.this.f3497d.flush();
                g.this.b();
            }
        }

        @Override // f.p
        public r e() {
            return g.this.k;
        }

        @Override // f.p, java.io.Flushable
        public void flush() throws IOException {
            synchronized (g.this) {
                g.this.c();
            }
            while (this.f3503c.Y() > 0) {
                c(false);
                g.this.f3497d.flush();
            }
        }

        @Override // f.p
        public void h(f.c cVar, long j) throws IOException {
            this.f3503c.h(cVar, j);
            while (this.f3503c.Y() >= 16384) {
                c(false);
            }
        }
    }

    /* JADX INFO: compiled from: Http2Stream.java */
    public final class b implements q {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final f.c f3505c = new f.c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final f.c f3506d = new f.c();
        public final long s;
        public boolean x;
        public boolean y;

        public b(long j) {
            this.s = j;
        }

        public final void c() throws IOException {
            if (this.x) {
                throw new IOException("stream closed");
            }
            if (g.this.l != null) {
                throw new StreamResetException(g.this.l);
            }
        }

        @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (g.this) {
                this.x = true;
                this.f3506d.H();
                g.this.notifyAll();
            }
            g.this.b();
        }

        public void d(f.e eVar, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (g.this) {
                    z = this.y;
                    z2 = true;
                    z3 = this.f3506d.Y() + j > this.s;
                }
                if (z3) {
                    eVar.skip(j);
                    g.this.f(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    eVar.skip(j);
                    return;
                }
                long jN = eVar.n(this.f3505c, j);
                if (jN == -1) {
                    throw new EOFException();
                }
                j -= jN;
                synchronized (g.this) {
                    if (this.f3506d.Y() != 0) {
                        z2 = false;
                    }
                    this.f3506d.k(this.f3505c);
                    if (z2) {
                        g.this.notifyAll();
                    }
                }
            }
        }

        @Override // f.q
        public r e() {
            return g.this.j;
        }

        @Override // f.q
        public long n(f.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            synchronized (g.this) {
                s();
                c();
                if (this.f3506d.Y() == 0) {
                    return -1L;
                }
                f.c cVar2 = this.f3506d;
                long jN = cVar2.n(cVar, Math.min(j, cVar2.Y()));
                g gVar = g.this;
                long j2 = gVar.f3494a + jN;
                gVar.f3494a = j2;
                if (j2 >= gVar.f3497d.N3.d() / 2) {
                    g gVar2 = g.this;
                    gVar2.f3497d.Z(gVar2.f3496c, gVar2.f3494a);
                    g.this.f3494a = 0L;
                }
                synchronized (g.this.f3497d) {
                    e eVar = g.this.f3497d;
                    long j3 = eVar.L3 + jN;
                    eVar.L3 = j3;
                    if (j3 >= eVar.N3.d() / 2) {
                        e eVar2 = g.this.f3497d;
                        eVar2.Z(0, eVar2.L3);
                        g.this.f3497d.L3 = 0L;
                    }
                }
                return jN;
            }
        }

        public final void s() throws IOException {
            g.this.j.k();
            while (this.f3506d.Y() == 0 && !this.y && !this.x) {
                try {
                    g gVar = g.this;
                    if (gVar.l != null) {
                        break;
                    } else {
                        gVar.r();
                    }
                } finally {
                    g.this.j.u();
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Stream.java */
    public class c extends f.a {
        public c() {
        }

        @Override // f.a
        public IOException o(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // f.a
        public void t() {
            g.this.f(ErrorCode.CANCEL);
        }

        public void u() throws IOException {
            if (n()) {
                throw o(null);
            }
        }
    }

    public g(int i2, e eVar, boolean z, boolean z2, List<e.d0.i.a> list) {
        Objects.requireNonNull(eVar, "connection == null");
        Objects.requireNonNull(list, "requestHeaders == null");
        this.f3496c = i2;
        this.f3497d = eVar;
        this.f3495b = eVar.O3.d();
        b bVar = new b(eVar.N3.d());
        this.f3501h = bVar;
        a aVar = new a();
        this.f3502i = aVar;
        bVar.y = z2;
        aVar.s = z;
        this.f3498e = list;
    }

    public void a(long j) {
        this.f3495b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b() throws java.io.IOException {
        /*
            r2 = this;
            monitor-enter(r2)
            e.d0.i.g$b r0 = r2.f3501h     // Catch: java.lang.Throwable -> L2f
            boolean r1 = r0.y     // Catch: java.lang.Throwable -> L2f
            if (r1 != 0) goto L17
            boolean r0 = r0.x     // Catch: java.lang.Throwable -> L2f
            if (r0 == 0) goto L17
            e.d0.i.g$a r0 = r2.f3502i     // Catch: java.lang.Throwable -> L2f
            boolean r1 = r0.s     // Catch: java.lang.Throwable -> L2f
            if (r1 != 0) goto L15
            boolean r0 = r0.f3504d     // Catch: java.lang.Throwable -> L2f
            if (r0 == 0) goto L17
        L15:
            r0 = 1
            goto L18
        L17:
            r0 = 0
        L18:
            boolean r1 = r2.k()     // Catch: java.lang.Throwable -> L2f
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2f
            if (r0 == 0) goto L25
            okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.CANCEL
            r2.d(r0)
            goto L2e
        L25:
            if (r1 != 0) goto L2e
            e.d0.i.e r0 = r2.f3497d
            int r1 = r2.f3496c
            r0.Q(r1)
        L2e:
            return
        L2f:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2f
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: e.d0.i.g.b():void");
    }

    public void c() throws IOException {
        a aVar = this.f3502i;
        if (aVar.f3504d) {
            throw new IOException("stream closed");
        }
        if (aVar.s) {
            throw new IOException("stream finished");
        }
        if (this.l != null) {
            throw new StreamResetException(this.l);
        }
    }

    public void d(ErrorCode errorCode) throws IOException {
        if (e(errorCode)) {
            this.f3497d.X(this.f3496c, errorCode);
        }
    }

    public final boolean e(ErrorCode errorCode) {
        synchronized (this) {
            if (this.l != null) {
                return false;
            }
            if (this.f3501h.y && this.f3502i.s) {
                return false;
            }
            this.l = errorCode;
            notifyAll();
            this.f3497d.Q(this.f3496c);
            return true;
        }
    }

    public void f(ErrorCode errorCode) {
        if (e(errorCode)) {
            this.f3497d.Y(this.f3496c, errorCode);
        }
    }

    public int g() {
        return this.f3496c;
    }

    public p h() {
        synchronized (this) {
            if (!this.f3500g && !j()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f3502i;
    }

    public q i() {
        return this.f3501h;
    }

    public boolean j() {
        return this.f3497d.f3470d == ((this.f3496c & 1) == 1);
    }

    public synchronized boolean k() {
        if (this.l != null) {
            return false;
        }
        b bVar = this.f3501h;
        if (bVar.y || bVar.x) {
            a aVar = this.f3502i;
            if (aVar.s || aVar.f3504d) {
                if (this.f3500g) {
                    return false;
                }
            }
        }
        return true;
    }

    public r l() {
        return this.j;
    }

    public void m(f.e eVar, int i2) throws IOException {
        this.f3501h.d(eVar, i2);
    }

    public void n() {
        boolean zK;
        synchronized (this) {
            this.f3501h.y = true;
            zK = k();
            notifyAll();
        }
        if (zK) {
            return;
        }
        this.f3497d.Q(this.f3496c);
    }

    public void o(List<e.d0.i.a> list) {
        boolean zK;
        synchronized (this) {
            zK = true;
            this.f3500g = true;
            if (this.f3499f == null) {
                this.f3499f = list;
                zK = k();
                notifyAll();
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.f3499f);
                arrayList.add(null);
                arrayList.addAll(list);
                this.f3499f = arrayList;
            }
        }
        if (zK) {
            return;
        }
        this.f3497d.Q(this.f3496c);
    }

    public synchronized void p(ErrorCode errorCode) {
        if (this.l == null) {
            this.l = errorCode;
            notifyAll();
        }
    }

    public synchronized List<e.d0.i.a> q() throws IOException {
        List<e.d0.i.a> list;
        if (!j()) {
            throw new IllegalStateException("servers cannot read response headers");
        }
        this.j.k();
        while (this.f3499f == null && this.l == null) {
            try {
                r();
            } catch (Throwable th) {
                this.j.u();
                throw th;
            }
        }
        this.j.u();
        list = this.f3499f;
        if (list == null) {
            throw new StreamResetException(this.l);
        }
        this.f3499f = null;
        return list;
    }

    public void r() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    public r s() {
        return this.k;
    }
}
