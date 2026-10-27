package k6;

import c6.t;
import g5.r;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import p6.a0;
import p6.b0;
import p6.y;

/* JADX INFO: compiled from: Http2Stream.kt */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final a f7435o = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f7436a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f7437b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f7438c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f7439d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final ArrayDeque<t> f7440e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f7441f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final c f7442g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final b f7443h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final d f7444i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final d f7445j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private k6.b f7446k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private IOException f7447l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final int f7448m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final f f7449n;

    /* JADX INFO: compiled from: Http2Stream.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: Http2Stream.kt */
    public final class b implements y {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final p6.e f7450e = new p6.e();

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private t f7451g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f7452h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private boolean f7453i;

        public b(boolean z6) {
            this.f7453i = z6;
        }

        private final void m(boolean z6) throws IOException {
            long jMin;
            boolean z7;
            synchronized (i.this) {
                i.this.s().r();
                while (i.this.r() >= i.this.q() && !this.f7453i && !this.f7452h && i.this.h() == null) {
                    try {
                        i.this.D();
                    } finally {
                    }
                }
                i.this.s().y();
                i.this.c();
                jMin = Math.min(i.this.q() - i.this.r(), this.f7450e.j0());
                i iVar = i.this;
                iVar.B(iVar.r() + jMin);
                z7 = z6 && jMin == this.f7450e.j0() && i.this.h() == null;
                r rVar = r.f6563a;
            }
            i.this.s().r();
            try {
                i.this.g().K0(i.this.j(), z7, this.f7450e, jMin);
            } finally {
            }
        }

        public final boolean A() {
            return this.f7453i;
        }

        @Override // p6.y
        public b0 c() {
            return i.this.s();
        }

        @Override // p6.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            i iVar = i.this;
            if (d6.b.f5967h && Thread.holdsLock(iVar)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Thread ");
                Thread threadCurrentThread = Thread.currentThread();
                kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
                sb.append(threadCurrentThread.getName());
                sb.append(" MUST NOT hold lock on ");
                sb.append(iVar);
                throw new AssertionError(sb.toString());
            }
            synchronized (i.this) {
                if (this.f7452h) {
                    return;
                }
                boolean z6 = i.this.h() == null;
                r rVar = r.f6563a;
                if (!i.this.o().f7453i) {
                    boolean z7 = this.f7450e.j0() > 0;
                    if (this.f7451g != null) {
                        while (this.f7450e.j0() > 0) {
                            m(false);
                        }
                        f fVarG = i.this.g();
                        int iJ = i.this.j();
                        t tVar = this.f7451g;
                        kotlin.jvm.internal.l.c(tVar);
                        fVarG.L0(iJ, z6, d6.b.I(tVar));
                    } else if (z7) {
                        while (this.f7450e.j0() > 0) {
                            m(true);
                        }
                    } else if (z6) {
                        i.this.g().K0(i.this.j(), true, null, 0L);
                    }
                }
                synchronized (i.this) {
                    this.f7452h = true;
                    r rVar2 = r.f6563a;
                }
                i.this.g().flush();
                i.this.b();
            }
        }

        @Override // p6.y, java.io.Flushable
        public void flush() throws IOException {
            i iVar = i.this;
            if (d6.b.f5967h && Thread.holdsLock(iVar)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Thread ");
                Thread threadCurrentThread = Thread.currentThread();
                kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
                sb.append(threadCurrentThread.getName());
                sb.append(" MUST NOT hold lock on ");
                sb.append(iVar);
                throw new AssertionError(sb.toString());
            }
            synchronized (i.this) {
                i.this.c();
                r rVar = r.f6563a;
            }
            while (this.f7450e.j0() > 0) {
                m(false);
                i.this.g().flush();
            }
        }

        @Override // p6.y
        public void l(p6.e source, long j7) throws IOException {
            kotlin.jvm.internal.l.e(source, "source");
            i iVar = i.this;
            if (!d6.b.f5967h || !Thread.holdsLock(iVar)) {
                this.f7450e.l(source, j7);
                while (this.f7450e.j0() >= 16384) {
                    m(false);
                }
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST NOT hold lock on ");
            sb.append(iVar);
            throw new AssertionError(sb.toString());
        }

        public final boolean z() {
            return this.f7452h;
        }
    }

    /* JADX INFO: compiled from: Http2Stream.kt */
    public final class c implements a0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final p6.e f7455e = new p6.e();

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final p6.e f7456g = new p6.e();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private t f7457h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private boolean f7458i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final long f7459j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private boolean f7460k;

        public c(long j7, boolean z6) {
            this.f7459j = j7;
            this.f7460k = z6;
        }

        private final void K(long j7) {
            i iVar = i.this;
            if (!d6.b.f5967h || !Thread.holdsLock(iVar)) {
                i.this.g().J0(j7);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST NOT hold lock on ");
            sb.append(iVar);
            throw new AssertionError(sb.toString());
        }

        public final void A(p6.g source, long j7) throws EOFException {
            boolean z6;
            boolean z7;
            boolean z8;
            long jJ0;
            kotlin.jvm.internal.l.e(source, "source");
            i iVar = i.this;
            if (d6.b.f5967h && Thread.holdsLock(iVar)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Thread ");
                Thread threadCurrentThread = Thread.currentThread();
                kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
                sb.append(threadCurrentThread.getName());
                sb.append(" MUST NOT hold lock on ");
                sb.append(iVar);
                throw new AssertionError(sb.toString());
            }
            while (j7 > 0) {
                synchronized (i.this) {
                    z6 = this.f7460k;
                    z7 = true;
                    z8 = this.f7456g.j0() + j7 > this.f7459j;
                    r rVar = r.f6563a;
                }
                if (z8) {
                    source.a(j7);
                    i.this.f(k6.b.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z6) {
                    source.a(j7);
                    return;
                }
                long jI = source.i(this.f7455e, j7);
                if (jI == -1) {
                    throw new EOFException();
                }
                j7 -= jI;
                synchronized (i.this) {
                    if (this.f7458i) {
                        jJ0 = this.f7455e.j0();
                        this.f7455e.z();
                    } else {
                        if (this.f7456g.j0() != 0) {
                            z7 = false;
                        }
                        this.f7456g.q0(this.f7455e);
                        if (z7) {
                            i iVar2 = i.this;
                            if (iVar2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                            }
                            iVar2.notifyAll();
                        }
                        jJ0 = 0;
                    }
                }
                if (jJ0 > 0) {
                    K(jJ0);
                }
            }
        }

        public final void G(boolean z6) {
            this.f7460k = z6;
        }

        public final void I(t tVar) {
            this.f7457h = tVar;
        }

        @Override // p6.a0
        public b0 c() {
            return i.this.m();
        }

        @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            long jJ0;
            synchronized (i.this) {
                this.f7458i = true;
                jJ0 = this.f7456g.j0();
                this.f7456g.z();
                i iVar = i.this;
                if (iVar == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                }
                iVar.notifyAll();
                r rVar = r.f6563a;
            }
            if (jJ0 > 0) {
                K(jJ0);
            }
            i.this.b();
        }

        @Override // p6.a0
        public long i(p6.e sink, long j7) throws IOException {
            long jI;
            boolean z6;
            kotlin.jvm.internal.l.e(sink, "sink");
            long j8 = 0;
            if (!(j7 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
            }
            while (true) {
                IOException iOExceptionI = null;
                synchronized (i.this) {
                    i.this.m().r();
                    try {
                        if (i.this.h() != null && (iOExceptionI = i.this.i()) == null) {
                            k6.b bVarH = i.this.h();
                            kotlin.jvm.internal.l.c(bVarH);
                            iOExceptionI = new n(bVarH);
                        }
                        if (this.f7458i) {
                            throw new IOException("stream closed");
                        }
                        if (this.f7456g.j0() > j8) {
                            p6.e eVar = this.f7456g;
                            jI = eVar.i(sink, Math.min(j7, eVar.j0()));
                            i iVar = i.this;
                            iVar.A(iVar.l() + jI);
                            long jL = i.this.l() - i.this.k();
                            if (iOExceptionI == null && jL >= i.this.g().o0().c() / 2) {
                                i.this.g().P0(i.this.j(), jL);
                                i iVar2 = i.this;
                                iVar2.z(iVar2.l());
                            }
                        } else if (this.f7460k || iOExceptionI != null) {
                            jI = -1;
                        } else {
                            i.this.D();
                            jI = -1;
                            z6 = true;
                            i.this.m().y();
                            r rVar = r.f6563a;
                        }
                        z6 = false;
                        i.this.m().y();
                        r rVar2 = r.f6563a;
                    } finally {
                    }
                }
                if (!z6) {
                    if (jI != -1) {
                        K(jI);
                        return jI;
                    }
                    if (iOExceptionI == null) {
                        return -1L;
                    }
                    kotlin.jvm.internal.l.c(iOExceptionI);
                    throw iOExceptionI;
                }
                j8 = 0;
            }
        }

        public final boolean m() {
            return this.f7458i;
        }

        public final boolean z() {
            return this.f7460k;
        }
    }

    /* JADX INFO: compiled from: Http2Stream.kt */
    public final class d extends p6.d {
        public d() {
        }

        @Override // p6.d
        protected IOException t(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // p6.d
        protected void x() {
            i.this.f(k6.b.CANCEL);
            i.this.g().D0();
        }

        public final void y() throws IOException {
            if (s()) {
                throw t(null);
            }
        }
    }

    public i(int i7, f connection, boolean z6, boolean z7, t tVar) {
        kotlin.jvm.internal.l.e(connection, "connection");
        this.f7448m = i7;
        this.f7449n = connection;
        this.f7439d = connection.p0().c();
        ArrayDeque<t> arrayDeque = new ArrayDeque<>();
        this.f7440e = arrayDeque;
        this.f7442g = new c(connection.o0().c(), z7);
        this.f7443h = new b(z6);
        this.f7444i = new d();
        this.f7445j = new d();
        if (tVar == null) {
            if (!t()) {
                throw new IllegalStateException("remotely-initiated streams should have headers".toString());
            }
        } else {
            if (!(!t())) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet".toString());
            }
            arrayDeque.add(tVar);
        }
    }

    private final boolean e(k6.b bVar, IOException iOException) {
        if (d6.b.f5967h && Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST NOT hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        synchronized (this) {
            if (this.f7446k != null) {
                return false;
            }
            if (this.f7442g.z() && this.f7443h.A()) {
                return false;
            }
            this.f7446k = bVar;
            this.f7447l = iOException;
            notifyAll();
            r rVar = r.f6563a;
            this.f7449n.C0(this.f7448m);
            return true;
        }
    }

    public final void A(long j7) {
        this.f7436a = j7;
    }

    public final void B(long j7) {
        this.f7438c = j7;
    }

    public final synchronized t C() {
        t tVarRemoveFirst;
        this.f7444i.r();
        while (this.f7440e.isEmpty() && this.f7446k == null) {
            try {
                D();
            } catch (Throwable th) {
                this.f7444i.y();
                throw th;
            }
        }
        this.f7444i.y();
        if (!(!this.f7440e.isEmpty())) {
            IOException iOException = this.f7447l;
            if (iOException != null) {
                throw iOException;
            }
            k6.b bVar = this.f7446k;
            kotlin.jvm.internal.l.c(bVar);
            throw new n(bVar);
        }
        tVarRemoveFirst = this.f7440e.removeFirst();
        kotlin.jvm.internal.l.d(tVarRemoveFirst, "headersQueue.removeFirst()");
        return tVarRemoveFirst;
    }

    public final void D() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public final b0 E() {
        return this.f7445j;
    }

    public final void a(long j7) {
        this.f7439d += j7;
        if (j7 > 0) {
            notifyAll();
        }
    }

    public final void b() {
        boolean z6;
        boolean zU;
        if (d6.b.f5967h && Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST NOT hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        synchronized (this) {
            z6 = !this.f7442g.z() && this.f7442g.m() && (this.f7443h.A() || this.f7443h.z());
            zU = u();
            r rVar = r.f6563a;
        }
        if (z6) {
            d(k6.b.CANCEL, null);
        } else {
            if (zU) {
                return;
            }
            this.f7449n.C0(this.f7448m);
        }
    }

    public final void c() throws IOException {
        if (this.f7443h.z()) {
            throw new IOException("stream closed");
        }
        if (this.f7443h.A()) {
            throw new IOException("stream finished");
        }
        if (this.f7446k != null) {
            IOException iOException = this.f7447l;
            if (iOException != null) {
                throw iOException;
            }
            k6.b bVar = this.f7446k;
            kotlin.jvm.internal.l.c(bVar);
            throw new n(bVar);
        }
    }

    public final void d(k6.b rstStatusCode, IOException iOException) {
        kotlin.jvm.internal.l.e(rstStatusCode, "rstStatusCode");
        if (e(rstStatusCode, iOException)) {
            this.f7449n.N0(this.f7448m, rstStatusCode);
        }
    }

    public final void f(k6.b errorCode) {
        kotlin.jvm.internal.l.e(errorCode, "errorCode");
        if (e(errorCode, null)) {
            this.f7449n.O0(this.f7448m, errorCode);
        }
    }

    public final f g() {
        return this.f7449n;
    }

    public final synchronized k6.b h() {
        return this.f7446k;
    }

    public final IOException i() {
        return this.f7447l;
    }

    public final int j() {
        return this.f7448m;
    }

    public final long k() {
        return this.f7437b;
    }

    public final long l() {
        return this.f7436a;
    }

    public final d m() {
        return this.f7444i;
    }

    public final y n() {
        synchronized (this) {
            if (!(this.f7441f || t())) {
                throw new IllegalStateException("reply before requesting the sink".toString());
            }
            r rVar = r.f6563a;
        }
        return this.f7443h;
    }

    public final b o() {
        return this.f7443h;
    }

    public final c p() {
        return this.f7442g;
    }

    public final long q() {
        return this.f7439d;
    }

    public final long r() {
        return this.f7438c;
    }

    public final d s() {
        return this.f7445j;
    }

    public final boolean t() {
        return this.f7449n.j0() == ((this.f7448m & 1) == 1);
    }

    public final synchronized boolean u() {
        if (this.f7446k != null) {
            return false;
        }
        if ((this.f7442g.z() || this.f7442g.m()) && (this.f7443h.A() || this.f7443h.z())) {
            if (this.f7441f) {
                return false;
            }
        }
        return true;
    }

    public final b0 v() {
        return this.f7444i;
    }

    public final void w(p6.g source, int i7) {
        kotlin.jvm.internal.l.e(source, "source");
        if (!d6.b.f5967h || !Thread.holdsLock(this)) {
            this.f7442g.A(source, i7);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread threadCurrentThread = Thread.currentThread();
        kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
        sb.append(threadCurrentThread.getName());
        sb.append(" MUST NOT hold lock on ");
        sb.append(this);
        throw new AssertionError(sb.toString());
    }

    public final void x(t headers, boolean z6) {
        boolean zU;
        kotlin.jvm.internal.l.e(headers, "headers");
        if (d6.b.f5967h && Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST NOT hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        synchronized (this) {
            if (this.f7441f && z6) {
                this.f7442g.I(headers);
            } else {
                this.f7441f = true;
                this.f7440e.add(headers);
            }
            if (z6) {
                this.f7442g.G(true);
            }
            zU = u();
            notifyAll();
            r rVar = r.f6563a;
        }
        if (zU) {
            return;
        }
        this.f7449n.C0(this.f7448m);
    }

    public final synchronized void y(k6.b errorCode) {
        kotlin.jvm.internal.l.e(errorCode, "errorCode");
        if (this.f7446k == null) {
            this.f7446k = errorCode;
            notifyAll();
        }
    }

    public final void z(long j7) {
        this.f7437b = j7;
    }
}
