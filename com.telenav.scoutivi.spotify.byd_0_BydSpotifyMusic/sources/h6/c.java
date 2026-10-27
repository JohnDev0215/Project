package h6;

import c6.b0;
import c6.c0;
import c6.r;
import c6.z;
import java.io.IOException;
import java.net.ProtocolException;
import kotlin.jvm.internal.l;
import p6.a0;
import p6.o;
import p6.y;

/* JADX INFO: compiled from: Exchange.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f6663a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f6664b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e f6665c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final r f6666d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d f6667e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final i6.d f6668f;

    /* JADX INFO: compiled from: Exchange.kt */
    private final class a extends p6.i {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f6669g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private long f6670h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private boolean f6671i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final long f6672j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        final /* synthetic */ c f6673k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(c cVar, y delegate, long j7) {
            super(delegate);
            l.e(delegate, "delegate");
            this.f6673k = cVar;
            this.f6672j = j7;
        }

        private final <E extends IOException> E m(E e7) {
            if (this.f6669g) {
                return e7;
            }
            this.f6669g = true;
            return (E) this.f6673k.a(this.f6670h, false, true, e7);
        }

        @Override // p6.i, p6.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f6671i) {
                return;
            }
            this.f6671i = true;
            long j7 = this.f6672j;
            if (j7 != -1 && this.f6670h != j7) {
                throw new ProtocolException("unexpected end of stream");
            }
            try {
                super.close();
                m(null);
            } catch (IOException e7) {
                throw m(e7);
            }
        }

        @Override // p6.i, p6.y, java.io.Flushable
        public void flush() throws IOException {
            try {
                super.flush();
            } catch (IOException e7) {
                throw m(e7);
            }
        }

        @Override // p6.i, p6.y
        public void l(p6.e source, long j7) throws IOException {
            l.e(source, "source");
            if (!(!this.f6671i)) {
                throw new IllegalStateException("closed".toString());
            }
            long j8 = this.f6672j;
            if (j8 == -1 || this.f6670h + j7 <= j8) {
                try {
                    super.l(source, j7);
                    this.f6670h += j7;
                    return;
                } catch (IOException e7) {
                    throw m(e7);
                }
            }
            throw new ProtocolException("expected " + this.f6672j + " bytes but received " + (this.f6670h + j7));
        }
    }

    /* JADX INFO: compiled from: Exchange.kt */
    public final class b extends p6.j {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private long f6674g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f6675h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private boolean f6676i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private boolean f6677j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final long f6678k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        final /* synthetic */ c f6679l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(c cVar, a0 delegate, long j7) {
            super(delegate);
            l.e(delegate, "delegate");
            this.f6679l = cVar;
            this.f6678k = j7;
            this.f6675h = true;
            if (j7 == 0) {
                z(null);
            }
        }

        @Override // p6.j, p6.a0, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f6677j) {
                return;
            }
            this.f6677j = true;
            try {
                super.close();
                z(null);
            } catch (IOException e7) {
                throw z(e7);
            }
        }

        @Override // p6.a0
        public long i(p6.e sink, long j7) throws IOException {
            l.e(sink, "sink");
            if (!(!this.f6677j)) {
                throw new IllegalStateException("closed".toString());
            }
            try {
                long jI = m().i(sink, j7);
                if (this.f6675h) {
                    this.f6675h = false;
                    this.f6679l.i().v(this.f6679l.g());
                }
                if (jI == -1) {
                    z(null);
                    return -1L;
                }
                long j8 = this.f6674g + jI;
                long j9 = this.f6678k;
                if (j9 != -1 && j8 > j9) {
                    throw new ProtocolException("expected " + this.f6678k + " bytes but received " + j8);
                }
                this.f6674g = j8;
                if (j8 == j9) {
                    z(null);
                }
                return jI;
            } catch (IOException e7) {
                throw z(e7);
            }
        }

        public final <E extends IOException> E z(E e7) {
            if (this.f6676i) {
                return e7;
            }
            this.f6676i = true;
            if (e7 == null && this.f6675h) {
                this.f6675h = false;
                this.f6679l.i().v(this.f6679l.g());
            }
            return (E) this.f6679l.a(this.f6674g, true, false, e7);
        }
    }

    public c(e call, r eventListener, d finder, i6.d codec) {
        l.e(call, "call");
        l.e(eventListener, "eventListener");
        l.e(finder, "finder");
        l.e(codec, "codec");
        this.f6665c = call;
        this.f6666d = eventListener;
        this.f6667e = finder;
        this.f6668f = codec;
        this.f6664b = codec.h();
    }

    private final void s(IOException iOException) {
        this.f6667e.h(iOException);
        this.f6668f.h().G(this.f6665c, iOException);
    }

    public final <E extends IOException> E a(long j7, boolean z6, boolean z7, E e7) {
        if (e7 != null) {
            s(e7);
        }
        if (z7) {
            if (e7 != null) {
                this.f6666d.r(this.f6665c, e7);
            } else {
                this.f6666d.p(this.f6665c, j7);
            }
        }
        if (z6) {
            if (e7 != null) {
                this.f6666d.w(this.f6665c, e7);
            } else {
                this.f6666d.u(this.f6665c, j7);
            }
        }
        return (E) this.f6665c.t(this, z7, z6, e7);
    }

    public final void b() {
        this.f6668f.cancel();
    }

    public final y c(z request, boolean z6) {
        l.e(request, "request");
        this.f6663a = z6;
        c6.a0 a0VarA = request.a();
        l.c(a0VarA);
        long jA = a0VarA.a();
        this.f6666d.q(this.f6665c);
        return new a(this, this.f6668f.c(request, jA), jA);
    }

    public final void d() {
        this.f6668f.cancel();
        this.f6665c.t(this, true, true, null);
    }

    public final void e() throws IOException {
        try {
            this.f6668f.d();
        } catch (IOException e7) {
            this.f6666d.r(this.f6665c, e7);
            s(e7);
            throw e7;
        }
    }

    public final void f() throws IOException {
        try {
            this.f6668f.e();
        } catch (IOException e7) {
            this.f6666d.r(this.f6665c, e7);
            s(e7);
            throw e7;
        }
    }

    public final e g() {
        return this.f6665c;
    }

    public final f h() {
        return this.f6664b;
    }

    public final r i() {
        return this.f6666d;
    }

    public final d j() {
        return this.f6667e;
    }

    public final boolean k() {
        return !l.a(this.f6667e.d().l().h(), this.f6664b.z().a().l().h());
    }

    public final boolean l() {
        return this.f6663a;
    }

    public final void m() {
        this.f6668f.h().y();
    }

    public final void n() {
        this.f6665c.t(this, true, false, null);
    }

    public final c0 o(b0 response) throws IOException {
        l.e(response, "response");
        try {
            String strU = b0.U(response, "Content-Type", null, 2, null);
            long jB = this.f6668f.b(response);
            return new i6.h(strU, jB, o.b(new b(this, this.f6668f.g(response), jB)));
        } catch (IOException e7) {
            this.f6666d.w(this.f6665c, e7);
            s(e7);
            throw e7;
        }
    }

    public final b0.a p(boolean z6) throws IOException {
        try {
            b0.a aVarF = this.f6668f.f(z6);
            if (aVarF != null) {
                aVarF.l(this);
            }
            return aVarF;
        } catch (IOException e7) {
            this.f6666d.w(this.f6665c, e7);
            s(e7);
            throw e7;
        }
    }

    public final void q(b0 response) {
        l.e(response, "response");
        this.f6666d.x(this.f6665c, response);
    }

    public final void r() {
        this.f6666d.y(this.f6665c);
    }

    public final void t(z request) throws IOException {
        l.e(request, "request");
        try {
            this.f6666d.t(this.f6665c);
            this.f6668f.a(request);
            this.f6666d.s(this.f6665c, request);
        } catch (IOException e7) {
            this.f6666d.r(this.f6665c, e7);
            s(e7);
            throw e7;
        }
    }
}
