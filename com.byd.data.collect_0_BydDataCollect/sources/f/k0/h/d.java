package f.k0.h;

import f.e0;
import f.g0;
import f.h0;
import f.t;
import java.io.IOException;
import java.net.ProtocolException;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/* JADX INFO: compiled from: Exchange.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a */
    final k f5165a;

    /* JADX INFO: renamed from: b */
    final f.i f5166b;

    /* JADX INFO: renamed from: c */
    final t f5167c;

    /* JADX INFO: renamed from: d */
    final e f5168d;

    /* JADX INFO: renamed from: e */
    final f.k0.i.c f5169e;

    /* JADX INFO: renamed from: f */
    private boolean f5170f;

    /* JADX INFO: compiled from: Exchange.java */
    private final class a extends ForwardingSink {

        /* JADX INFO: renamed from: c */
        private boolean f5171c;

        /* JADX INFO: renamed from: d */
        private long f5172d;

        /* JADX INFO: renamed from: e */
        private long f5173e;

        /* JADX INFO: renamed from: f */
        private boolean f5174f;

        a(Sink sink, long j) {
            super(sink);
            this.f5172d = j;
        }

        @Nullable
        private IOException a(@Nullable IOException iOException) {
            if (this.f5171c) {
                return iOException;
            }
            this.f5171c = true;
            return d.this.a(this.f5173e, false, true, iOException);
        }

        @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f5174f) {
                return;
            }
            this.f5174f = true;
            long j = this.f5172d;
            if (j != -1 && this.f5173e != j) {
                throw new ProtocolException("unexpected end of stream");
            }
            try {
                super.close();
                a(null);
            } catch (IOException e2) {
                throw a(e2);
            }
        }

        @Override // okio.ForwardingSink, okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            try {
                super.flush();
            } catch (IOException e2) {
                throw a(e2);
            }
        }

        @Override // okio.ForwardingSink, okio.Sink
        public void h(Buffer buffer, long j) throws IOException {
            if (this.f5174f) {
                throw new IllegalStateException("closed");
            }
            long j2 = this.f5172d;
            if (j2 == -1 || this.f5173e + j <= j2) {
                try {
                    super.h(buffer, j);
                    this.f5173e += j;
                    return;
                } catch (IOException e2) {
                    throw a(e2);
                }
            }
            throw new ProtocolException("expected " + this.f5172d + " bytes but received " + (this.f5173e + j));
        }
    }

    /* JADX INFO: compiled from: Exchange.java */
    final class b extends ForwardingSource {

        /* JADX INFO: renamed from: c */
        private final long f5176c;

        /* JADX INFO: renamed from: d */
        private long f5177d;

        /* JADX INFO: renamed from: e */
        private boolean f5178e;

        /* JADX INFO: renamed from: f */
        private boolean f5179f;

        b(Source source, long j) {
            super(source);
            this.f5176c = j;
            if (j == 0) {
                d(null);
            }
        }

        @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f5179f) {
                return;
            }
            this.f5179f = true;
            try {
                super.close();
                d(null);
            } catch (IOException e2) {
                throw d(e2);
            }
        }

        @Nullable
        IOException d(@Nullable IOException iOException) {
            if (this.f5178e) {
                return iOException;
            }
            this.f5178e = true;
            return d.this.a(this.f5177d, true, false, iOException);
        }

        @Override // okio.ForwardingSource, okio.Source
        public long p(Buffer buffer, long j) throws IOException {
            if (this.f5179f) {
                throw new IllegalStateException("closed");
            }
            try {
                long jP = a().p(buffer, j);
                if (jP == -1) {
                    d(null);
                    return -1L;
                }
                long j2 = this.f5177d + jP;
                long j3 = this.f5176c;
                if (j3 != -1 && j2 > j3) {
                    throw new ProtocolException("expected " + this.f5176c + " bytes but received " + j2);
                }
                this.f5177d = j2;
                if (j2 == j3) {
                    d(null);
                }
                return jP;
            } catch (IOException e2) {
                throw d(e2);
            }
        }
    }

    public d(k kVar, f.i iVar, t tVar, e eVar, f.k0.i.c cVar) {
        this.f5165a = kVar;
        this.f5166b = iVar;
        this.f5167c = tVar;
        this.f5168d = eVar;
        this.f5169e = cVar;
    }

    @Nullable
    IOException a(long j, boolean z, boolean z2, @Nullable IOException iOException) {
        if (iOException != null) {
            o(iOException);
        }
        if (z2) {
            if (iOException != null) {
                this.f5167c.o(this.f5166b, iOException);
            } else {
                this.f5167c.m(this.f5166b, j);
            }
        }
        if (z) {
            if (iOException != null) {
                this.f5167c.t(this.f5166b, iOException);
            } else {
                this.f5167c.r(this.f5166b, j);
            }
        }
        return this.f5165a.g(this, z2, z, iOException);
    }

    public void b() {
        this.f5169e.cancel();
    }

    public f c() {
        return this.f5169e.b();
    }

    public Sink d(e0 e0Var, boolean z) {
        this.f5170f = z;
        long jA = e0Var.a().a();
        this.f5167c.n(this.f5166b);
        return new a(this.f5169e.e(e0Var, jA), jA);
    }

    public void e() {
        this.f5169e.cancel();
        this.f5165a.g(this, true, true, null);
    }

    public void f() throws IOException {
        try {
            this.f5169e.a();
        } catch (IOException e2) {
            this.f5167c.o(this.f5166b, e2);
            o(e2);
            throw e2;
        }
    }

    public void g() throws IOException {
        try {
            this.f5169e.d();
        } catch (IOException e2) {
            this.f5167c.o(this.f5166b, e2);
            o(e2);
            throw e2;
        }
    }

    public boolean h() {
        return this.f5170f;
    }

    public void i() {
        this.f5169e.b().q();
    }

    public void j() {
        this.f5165a.g(this, true, false, null);
    }

    public h0 k(g0 g0Var) throws IOException {
        try {
            this.f5167c.s(this.f5166b);
            String strC = g0Var.C("Content-Type");
            long jF = this.f5169e.f(g0Var);
            return new f.k0.i.h(strC, jF, Okio.c(new b(this.f5169e.g(g0Var), jF)));
        } catch (IOException e2) {
            this.f5167c.t(this.f5166b, e2);
            o(e2);
            throw e2;
        }
    }

    @Nullable
    public g0.a l(boolean z) throws IOException {
        try {
            g0.a aVarH = this.f5169e.h(z);
            if (aVarH != null) {
                f.k0.c.f5131a.g(aVarH, this);
            }
            return aVarH;
        } catch (IOException e2) {
            this.f5167c.t(this.f5166b, e2);
            o(e2);
            throw e2;
        }
    }

    public void m(g0 g0Var) {
        this.f5167c.u(this.f5166b, g0Var);
    }

    public void n() {
        this.f5167c.v(this.f5166b);
    }

    void o(IOException iOException) {
        this.f5168d.h();
        this.f5169e.b().w(iOException);
    }

    public void p(e0 e0Var) throws IOException {
        try {
            this.f5167c.q(this.f5166b);
            this.f5169e.c(e0Var);
            this.f5167c.p(this.f5166b, e0Var);
        } catch (IOException e2) {
            this.f5167c.o(this.f5166b, e2);
            o(e2);
            throw e2;
        }
    }
}
