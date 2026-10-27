package f.b0.i;

import g.o;
import g.p;
import g.q;
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
    public long f5222b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5223c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e f5224d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<f.b0.i.a> f5225e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<f.b0.i.a> f5226f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5227g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b f5228h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a f5229i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f5221a = 0;
    public final c j = new c();
    public final c k = new c();
    public ErrorCode l = null;

    /* JADX INFO: compiled from: Http2Stream.java */
    public final class a implements o {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g.c f5230b = new g.c();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f5231c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f5232d;

        public a() {
        }

        @Override // g.o
        public q b() {
            return g.this.k;
        }

        @Override // g.o, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (g.this) {
                if (this.f5231c) {
                    return;
                }
                if (!g.this.f5229i.f5232d) {
                    if (this.f5230b.f0() > 0) {
                        while (this.f5230b.f0() > 0) {
                            w(true);
                        }
                    } else {
                        g gVar = g.this;
                        gVar.f5224d.d0(gVar.f5223c, true, null, 0L);
                    }
                }
                synchronized (g.this) {
                    this.f5231c = true;
                }
                g.this.f5224d.flush();
                g.this.b();
            }
        }

        @Override // g.o, java.io.Flushable
        public void flush() throws IOException {
            synchronized (g.this) {
                g.this.c();
            }
            while (this.f5230b.f0() > 0) {
                w(false);
                g.this.f5224d.flush();
            }
        }

        @Override // g.o
        public void g(g.c cVar, long j) throws IOException {
            this.f5230b.g(cVar, j);
            while (this.f5230b.f0() >= 16384) {
                w(false);
            }
        }

        public final void w(boolean z) throws IOException {
            g gVar;
            long jMin;
            g gVar2;
            synchronized (g.this) {
                g.this.k.k();
                while (true) {
                    try {
                        gVar = g.this;
                        if (gVar.f5222b > 0 || this.f5232d || this.f5231c || gVar.l != null) {
                            break;
                        } else {
                            gVar.r();
                        }
                    } finally {
                    }
                }
                gVar.k.u();
                g.this.c();
                jMin = Math.min(g.this.f5222b, this.f5230b.f0());
                gVar2 = g.this;
                gVar2.f5222b -= jMin;
            }
            gVar2.k.k();
            try {
                g gVar3 = g.this;
                gVar3.f5224d.d0(gVar3.f5223c, z && jMin == this.f5230b.f0(), this.f5230b, jMin);
            } finally {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Stream.java */
    public final class b implements p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g.c f5234b = new g.c();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final g.c f5235c = new g.c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final long f5236d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f5237e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f5238f;

        public b(long j) {
            this.f5236d = j;
        }

        public final void O() throws IOException {
            g.this.j.k();
            while (this.f5235c.f0() == 0 && !this.f5238f && !this.f5237e) {
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

        @Override // g.p
        public q b() {
            return g.this.j;
        }

        @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (g.this) {
                this.f5237e = true;
                this.f5235c.O();
                g.this.notifyAll();
            }
            g.this.b();
        }

        @Override // g.p
        public long m(g.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            synchronized (g.this) {
                O();
                w();
                if (this.f5235c.f0() == 0) {
                    return -1L;
                }
                g.c cVar2 = this.f5235c;
                long jM = cVar2.m(cVar, Math.min(j, cVar2.f0()));
                g gVar = g.this;
                long j2 = gVar.f5221a + jM;
                gVar.f5221a = j2;
                if (j2 >= gVar.f5224d.p.d() / 2) {
                    g gVar2 = g.this;
                    gVar2.f5224d.i0(gVar2.f5223c, gVar2.f5221a);
                    g.this.f5221a = 0L;
                }
                synchronized (g.this.f5224d) {
                    e eVar = g.this.f5224d;
                    long j3 = eVar.n + jM;
                    eVar.n = j3;
                    if (j3 >= eVar.p.d() / 2) {
                        e eVar2 = g.this.f5224d;
                        eVar2.i0(0, eVar2.n);
                        g.this.f5224d.n = 0L;
                    }
                }
                return jM;
            }
        }

        public final void w() throws IOException {
            if (this.f5237e) {
                throw new IOException("stream closed");
            }
            if (g.this.l != null) {
                throw new StreamResetException(g.this.l);
            }
        }

        public void z(g.e eVar, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (g.this) {
                    z = this.f5238f;
                    z2 = true;
                    z3 = this.f5235c.f0() + j > this.f5236d;
                }
                if (z3) {
                    eVar.o(j);
                    g.this.f(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    eVar.o(j);
                    return;
                }
                long jM = eVar.m(this.f5234b, j);
                if (jM == -1) {
                    throw new EOFException();
                }
                j -= jM;
                synchronized (g.this) {
                    if (this.f5235c.f0() != 0) {
                        z2 = false;
                    }
                    this.f5235c.j(this.f5234b);
                    if (z2) {
                        g.this.notifyAll();
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Stream.java */
    public class c extends g.a {
        public c() {
        }

        @Override // g.a
        public IOException o(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // g.a
        public void t() {
            g.this.f(ErrorCode.CANCEL);
        }

        public void u() throws IOException {
            if (n()) {
                throw o(null);
            }
        }
    }

    public g(int i2, e eVar, boolean z, boolean z2, List<f.b0.i.a> list) {
        Objects.requireNonNull(eVar, "connection == null");
        Objects.requireNonNull(list, "requestHeaders == null");
        this.f5223c = i2;
        this.f5224d = eVar;
        this.f5222b = eVar.q.d();
        b bVar = new b(eVar.p.d());
        this.f5228h = bVar;
        a aVar = new a();
        this.f5229i = aVar;
        bVar.f5238f = z2;
        aVar.f5232d = z;
        this.f5225e = list;
    }

    public void a(long j) {
        this.f5222b += j;
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
            f.b0.i.g$b r0 = r2.f5228h     // Catch: java.lang.Throwable -> L2f
            boolean r1 = r0.f5238f     // Catch: java.lang.Throwable -> L2f
            if (r1 != 0) goto L17
            boolean r0 = r0.f5237e     // Catch: java.lang.Throwable -> L2f
            if (r0 == 0) goto L17
            f.b0.i.g$a r0 = r2.f5229i     // Catch: java.lang.Throwable -> L2f
            boolean r1 = r0.f5232d     // Catch: java.lang.Throwable -> L2f
            if (r1 != 0) goto L15
            boolean r0 = r0.f5231c     // Catch: java.lang.Throwable -> L2f
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
            f.b0.i.e r0 = r2.f5224d
            int r1 = r2.f5223c
            r0.Z(r1)
        L2e:
            return
        L2f:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2f
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: f.b0.i.g.b():void");
    }

    public void c() throws IOException {
        a aVar = this.f5229i;
        if (aVar.f5231c) {
            throw new IOException("stream closed");
        }
        if (aVar.f5232d) {
            throw new IOException("stream finished");
        }
        if (this.l != null) {
            throw new StreamResetException(this.l);
        }
    }

    public void d(ErrorCode errorCode) throws IOException {
        if (e(errorCode)) {
            this.f5224d.g0(this.f5223c, errorCode);
        }
    }

    public final boolean e(ErrorCode errorCode) {
        synchronized (this) {
            if (this.l != null) {
                return false;
            }
            if (this.f5228h.f5238f && this.f5229i.f5232d) {
                return false;
            }
            this.l = errorCode;
            notifyAll();
            this.f5224d.Z(this.f5223c);
            return true;
        }
    }

    public void f(ErrorCode errorCode) {
        if (e(errorCode)) {
            this.f5224d.h0(this.f5223c, errorCode);
        }
    }

    public int g() {
        return this.f5223c;
    }

    public o h() {
        synchronized (this) {
            if (!this.f5227g && !j()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f5229i;
    }

    public p i() {
        return this.f5228h;
    }

    public boolean j() {
        return this.f5224d.f5162c == ((this.f5223c & 1) == 1);
    }

    public synchronized boolean k() {
        if (this.l != null) {
            return false;
        }
        b bVar = this.f5228h;
        if (bVar.f5238f || bVar.f5237e) {
            a aVar = this.f5229i;
            if (aVar.f5232d || aVar.f5231c) {
                if (this.f5227g) {
                    return false;
                }
            }
        }
        return true;
    }

    public q l() {
        return this.j;
    }

    public void m(g.e eVar, int i2) throws IOException {
        this.f5228h.z(eVar, i2);
    }

    public void n() {
        boolean zK;
        synchronized (this) {
            this.f5228h.f5238f = true;
            zK = k();
            notifyAll();
        }
        if (zK) {
            return;
        }
        this.f5224d.Z(this.f5223c);
    }

    public void o(List<f.b0.i.a> list) {
        boolean zK;
        synchronized (this) {
            zK = true;
            this.f5227g = true;
            if (this.f5226f == null) {
                this.f5226f = list;
                zK = k();
                notifyAll();
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.f5226f);
                arrayList.add(null);
                arrayList.addAll(list);
                this.f5226f = arrayList;
            }
        }
        if (zK) {
            return;
        }
        this.f5224d.Z(this.f5223c);
    }

    public synchronized void p(ErrorCode errorCode) {
        if (this.l == null) {
            this.l = errorCode;
            notifyAll();
        }
    }

    public synchronized List<f.b0.i.a> q() throws IOException {
        List<f.b0.i.a> list;
        if (!j()) {
            throw new IllegalStateException("servers cannot read response headers");
        }
        this.j.k();
        while (this.f5226f == null && this.l == null) {
            try {
                r();
            } catch (Throwable th) {
                this.j.u();
                throw th;
            }
        }
        this.j.u();
        list = this.f5226f;
        if (list == null) {
            throw new StreamResetException(this.l);
        }
        this.f5226f = null;
        return list;
    }

    public void r() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    public q s() {
        return this.k;
    }
}
