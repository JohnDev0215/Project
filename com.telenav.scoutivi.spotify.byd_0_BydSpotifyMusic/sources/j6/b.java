package j6;

import c6.b0;
import c6.n;
import c6.t;
import c6.u;
import c6.x;
import c6.z;
import i6.i;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.l;
import p6.a0;
import p6.b0;
import p6.k;
import p6.y;
import y5.p;
import y5.q;

/* JADX INFO: compiled from: Http1ExchangeCodec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b implements i6.d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final d f7155h = new d(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f7156a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j6.a f7157b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private t f7158c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final x f7159d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final h6.f f7160e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final p6.g f7161f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final p6.f f7162g;

    /* JADX INFO: compiled from: Http1ExchangeCodec.kt */
    private abstract class a implements a0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final k f7163e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f7164g;

        public a() {
            this.f7163e = new k(b.this.f7161f.c());
        }

        protected final void A(boolean z6) {
            this.f7164g = z6;
        }

        @Override // p6.a0
        public b0 c() {
            return this.f7163e;
        }

        @Override // p6.a0
        public long i(p6.e sink, long j7) throws IOException {
            l.e(sink, "sink");
            try {
                return b.this.f7161f.i(sink, j7);
            } catch (IOException e7) {
                b.this.h().y();
                z();
                throw e7;
            }
        }

        protected final boolean m() {
            return this.f7164g;
        }

        public final void z() {
            if (b.this.f7156a == 6) {
                return;
            }
            if (b.this.f7156a == 5) {
                b.this.r(this.f7163e);
                b.this.f7156a = 6;
            } else {
                throw new IllegalStateException("state: " + b.this.f7156a);
            }
        }
    }

    /* JADX INFO: renamed from: j6.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Http1ExchangeCodec.kt */
    private final class C0093b implements y {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final k f7166e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f7167g;

        public C0093b() {
            this.f7166e = new k(b.this.f7162g.c());
        }

        @Override // p6.y
        public b0 c() {
            return this.f7166e;
        }

        @Override // p6.y, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            if (this.f7167g) {
                return;
            }
            this.f7167g = true;
            b.this.f7162g.H("0\r\n\r\n");
            b.this.r(this.f7166e);
            b.this.f7156a = 3;
        }

        @Override // p6.y, java.io.Flushable
        public synchronized void flush() {
            if (this.f7167g) {
                return;
            }
            b.this.f7162g.flush();
        }

        @Override // p6.y
        public void l(p6.e source, long j7) {
            l.e(source, "source");
            if (!(!this.f7167g)) {
                throw new IllegalStateException("closed".toString());
            }
            if (j7 == 0) {
                return;
            }
            b.this.f7162g.j(j7);
            b.this.f7162g.H("\r\n");
            b.this.f7162g.l(source, j7);
            b.this.f7162g.H("\r\n");
        }
    }

    /* JADX INFO: compiled from: Http1ExchangeCodec.kt */
    private final class c extends a {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private long f7169i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private boolean f7170j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final u f7171k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        final /* synthetic */ b f7172l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(b bVar, u url) {
            super();
            l.e(url, "url");
            this.f7172l = bVar;
            this.f7171k = url;
            this.f7169i = -1L;
            this.f7170j = true;
        }

        private final void G() throws ProtocolException {
            if (this.f7169i != -1) {
                this.f7172l.f7161f.D();
            }
            try {
                this.f7169i = this.f7172l.f7161f.T();
                String strD = this.f7172l.f7161f.D();
                if (strD == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String string = q.z0(strD).toString();
                if (this.f7169i >= 0) {
                    if (!(string.length() > 0) || p.A(string, ";", false, 2, null)) {
                        if (this.f7169i == 0) {
                            this.f7170j = false;
                            b bVar = this.f7172l;
                            bVar.f7158c = bVar.f7157b.a();
                            x xVar = this.f7172l.f7159d;
                            l.c(xVar);
                            n nVarK = xVar.k();
                            u uVar = this.f7171k;
                            t tVar = this.f7172l.f7158c;
                            l.c(tVar);
                            i6.e.f(nVarK, uVar, tVar);
                            z();
                            return;
                        }
                        return;
                    }
                }
                throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f7169i + string + '\"');
            } catch (NumberFormatException e7) {
                throw new ProtocolException(e7.getMessage());
            }
        }

        @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (m()) {
                return;
            }
            if (this.f7170j && !d6.b.o(this, 100, TimeUnit.MILLISECONDS)) {
                this.f7172l.h().y();
                z();
            }
            A(true);
        }

        @Override // j6.b.a, p6.a0
        public long i(p6.e sink, long j7) throws IOException {
            l.e(sink, "sink");
            if (!(j7 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
            }
            if (!(!m())) {
                throw new IllegalStateException("closed".toString());
            }
            if (!this.f7170j) {
                return -1L;
            }
            long j8 = this.f7169i;
            if (j8 == 0 || j8 == -1) {
                G();
                if (!this.f7170j) {
                    return -1L;
                }
            }
            long jI = super.i(sink, Math.min(j7, this.f7169i));
            if (jI != -1) {
                this.f7169i -= jI;
                return jI;
            }
            this.f7172l.h().y();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            z();
            throw protocolException;
        }
    }

    /* JADX INFO: compiled from: Http1ExchangeCodec.kt */
    public static final class d {
        private d() {
        }

        public /* synthetic */ d(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: Http1ExchangeCodec.kt */
    private final class e extends a {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private long f7173i;

        public e(long j7) {
            super();
            this.f7173i = j7;
            if (j7 == 0) {
                z();
            }
        }

        @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (m()) {
                return;
            }
            if (this.f7173i != 0 && !d6.b.o(this, 100, TimeUnit.MILLISECONDS)) {
                b.this.h().y();
                z();
            }
            A(true);
        }

        @Override // j6.b.a, p6.a0
        public long i(p6.e sink, long j7) throws IOException {
            l.e(sink, "sink");
            if (!(j7 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
            }
            if (!(!m())) {
                throw new IllegalStateException("closed".toString());
            }
            long j8 = this.f7173i;
            if (j8 == 0) {
                return -1L;
            }
            long jI = super.i(sink, Math.min(j8, j7));
            if (jI == -1) {
                b.this.h().y();
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                z();
                throw protocolException;
            }
            long j9 = this.f7173i - jI;
            this.f7173i = j9;
            if (j9 == 0) {
                z();
            }
            return jI;
        }
    }

    /* JADX INFO: compiled from: Http1ExchangeCodec.kt */
    private final class f implements y {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final k f7175e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f7176g;

        public f() {
            this.f7175e = new k(b.this.f7162g.c());
        }

        @Override // p6.y
        public b0 c() {
            return this.f7175e;
        }

        @Override // p6.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f7176g) {
                return;
            }
            this.f7176g = true;
            b.this.r(this.f7175e);
            b.this.f7156a = 3;
        }

        @Override // p6.y, java.io.Flushable
        public void flush() {
            if (this.f7176g) {
                return;
            }
            b.this.f7162g.flush();
        }

        @Override // p6.y
        public void l(p6.e source, long j7) {
            l.e(source, "source");
            if (!(!this.f7176g)) {
                throw new IllegalStateException("closed".toString());
            }
            d6.b.h(source.j0(), 0L, j7);
            b.this.f7162g.l(source, j7);
        }
    }

    /* JADX INFO: compiled from: Http1ExchangeCodec.kt */
    private final class g extends a {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private boolean f7178i;

        public g() {
            super();
        }

        @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (m()) {
                return;
            }
            if (!this.f7178i) {
                z();
            }
            A(true);
        }

        @Override // j6.b.a, p6.a0
        public long i(p6.e sink, long j7) throws IOException {
            l.e(sink, "sink");
            if (!(j7 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
            }
            if (!(!m())) {
                throw new IllegalStateException("closed".toString());
            }
            if (this.f7178i) {
                return -1L;
            }
            long jI = super.i(sink, j7);
            if (jI != -1) {
                return jI;
            }
            this.f7178i = true;
            z();
            return -1L;
        }
    }

    public b(x xVar, h6.f connection, p6.g source, p6.f sink) {
        l.e(connection, "connection");
        l.e(source, "source");
        l.e(sink, "sink");
        this.f7159d = xVar;
        this.f7160e = connection;
        this.f7161f = source;
        this.f7162g = sink;
        this.f7157b = new j6.a(source);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void r(k kVar) {
        b0 b0VarI = kVar.i();
        kVar.j(b0.f8850d);
        b0VarI.a();
        b0VarI.b();
    }

    private final boolean s(z zVar) {
        return p.o("chunked", zVar.d("Transfer-Encoding"), true);
    }

    private final boolean t(c6.b0 b0Var) {
        return p.o("chunked", c6.b0.U(b0Var, "Transfer-Encoding", null, 2, null), true);
    }

    private final y u() {
        if (this.f7156a == 1) {
            this.f7156a = 2;
            return new C0093b();
        }
        throw new IllegalStateException(("state: " + this.f7156a).toString());
    }

    private final a0 v(u uVar) {
        if (this.f7156a == 4) {
            this.f7156a = 5;
            return new c(this, uVar);
        }
        throw new IllegalStateException(("state: " + this.f7156a).toString());
    }

    private final a0 w(long j7) {
        if (this.f7156a == 4) {
            this.f7156a = 5;
            return new e(j7);
        }
        throw new IllegalStateException(("state: " + this.f7156a).toString());
    }

    private final y x() {
        if (this.f7156a == 1) {
            this.f7156a = 2;
            return new f();
        }
        throw new IllegalStateException(("state: " + this.f7156a).toString());
    }

    private final a0 y() {
        if (this.f7156a == 4) {
            this.f7156a = 5;
            h().y();
            return new g();
        }
        throw new IllegalStateException(("state: " + this.f7156a).toString());
    }

    public final void A(t headers, String requestLine) {
        l.e(headers, "headers");
        l.e(requestLine, "requestLine");
        if (!(this.f7156a == 0)) {
            throw new IllegalStateException(("state: " + this.f7156a).toString());
        }
        this.f7162g.H(requestLine).H("\r\n");
        int size = headers.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.f7162g.H(headers.d(i7)).H(": ").H(headers.f(i7)).H("\r\n");
        }
        this.f7162g.H("\r\n");
        this.f7156a = 1;
    }

    @Override // i6.d
    public void a(z request) {
        l.e(request, "request");
        i iVar = i.f7064a;
        Proxy.Type type = h().z().b().type();
        l.d(type, "connection.route().proxy.type()");
        A(request.e(), iVar.a(request, type));
    }

    @Override // i6.d
    public long b(c6.b0 response) {
        l.e(response, "response");
        if (!i6.e.b(response)) {
            return 0L;
        }
        if (t(response)) {
            return -1L;
        }
        return d6.b.r(response);
    }

    @Override // i6.d
    public y c(z request, long j7) throws ProtocolException {
        l.e(request, "request");
        if (request.a() != null && request.a().c()) {
            throw new ProtocolException("Duplex connections are not supported for HTTP/1");
        }
        if (s(request)) {
            return u();
        }
        if (j7 != -1) {
            return x();
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // i6.d
    public void cancel() {
        h().d();
    }

    @Override // i6.d
    public void d() {
        this.f7162g.flush();
    }

    @Override // i6.d
    public void e() {
        this.f7162g.flush();
    }

    @Override // i6.d
    public b0.a f(boolean z6) {
        int i7 = this.f7156a;
        boolean z7 = true;
        if (i7 != 1 && i7 != 3) {
            z7 = false;
        }
        if (!z7) {
            throw new IllegalStateException(("state: " + this.f7156a).toString());
        }
        try {
            i6.k kVarA = i6.k.f7067d.a(this.f7157b.b());
            b0.a aVarK = new b0.a().p(kVarA.f7068a).g(kVarA.f7069b).m(kVarA.f7070c).k(this.f7157b.a());
            if (z6 && kVarA.f7069b == 100) {
                return null;
            }
            if (kVarA.f7069b == 100) {
                this.f7156a = 3;
                return aVarK;
            }
            this.f7156a = 4;
            return aVarK;
        } catch (EOFException e7) {
            throw new IOException("unexpected end of stream on " + h().z().a().l().n(), e7);
        }
    }

    @Override // i6.d
    public a0 g(c6.b0 response) {
        l.e(response, "response");
        if (!i6.e.b(response)) {
            return w(0L);
        }
        if (t(response)) {
            return v(response.g0().i());
        }
        long jR = d6.b.r(response);
        return jR != -1 ? w(jR) : y();
    }

    @Override // i6.d
    public h6.f h() {
        return this.f7160e;
    }

    public final void z(c6.b0 response) {
        l.e(response, "response");
        long jR = d6.b.r(response);
        if (jR == -1) {
            return;
        }
        a0 a0VarW = w(jR);
        d6.b.G(a0VarW, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        a0VarW.close();
    }
}
