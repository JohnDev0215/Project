package f.k0.j;

import f.b0;
import f.e0;
import f.g0;
import f.k0.i.i;
import f.k0.i.k;
import f.w;
import f.x;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* JADX INFO: compiled from: Http1ExchangeCodec.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements f.k0.i.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b0 f5249a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f.k0.h.f f5250b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final BufferedSource f5251c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final BufferedSink f5252d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f5253e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f5254f = 262144;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private w f5255g;

    /* JADX INFO: compiled from: Http1ExchangeCodec.java */
    private abstract class b implements Source {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected final ForwardingTimeout f5256b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected boolean f5257c;

        private b() {
            this.f5256b = new ForwardingTimeout(a.this.f5251c.e());
        }

        final void a() {
            if (a.this.f5253e == 6) {
                return;
            }
            if (a.this.f5253e == 5) {
                a.this.s(this.f5256b);
                a.this.f5253e = 6;
            } else {
                throw new IllegalStateException("state: " + a.this.f5253e);
            }
        }

        @Override // okio.Source
        public Timeout e() {
            return this.f5256b;
        }

        @Override // okio.Source
        public long p(Buffer buffer, long j) throws IOException {
            try {
                return a.this.f5251c.p(buffer, j);
            } catch (IOException e2) {
                a.this.f5250b.q();
                a();
                throw e2;
            }
        }
    }

    /* JADX INFO: compiled from: Http1ExchangeCodec.java */
    private final class c implements Sink {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ForwardingTimeout f5259b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f5260c;

        c() {
            this.f5259b = new ForwardingTimeout(a.this.f5252d.e());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            if (this.f5260c) {
                return;
            }
            this.f5260c = true;
            a.this.f5252d.D("0\r\n\r\n");
            a.this.s(this.f5259b);
            a.this.f5253e = 3;
        }

        @Override // okio.Sink
        public Timeout e() {
            return this.f5259b;
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() {
            if (this.f5260c) {
                return;
            }
            a.this.f5252d.flush();
        }

        @Override // okio.Sink
        public void h(Buffer buffer, long j) {
            if (this.f5260c) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            a.this.f5252d.o(j);
            a.this.f5252d.D("\r\n");
            a.this.f5252d.h(buffer, j);
            a.this.f5252d.D("\r\n");
        }
    }

    /* JADX INFO: compiled from: Http1ExchangeCodec.java */
    private class d extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final x f5262e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f5263f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f5264g;

        d(x xVar) {
            super();
            this.f5263f = -1L;
            this.f5264g = true;
            this.f5262e = xVar;
        }

        private void d() throws ProtocolException {
            if (this.f5263f != -1) {
                a.this.f5251c.z();
            }
            try {
                this.f5263f = a.this.f5251c.M();
                String strTrim = a.this.f5251c.z().trim();
                if (this.f5263f < 0 || !(strTrim.isEmpty() || strTrim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f5263f + strTrim + "\"");
                }
                if (this.f5263f == 0) {
                    this.f5264g = false;
                    a aVar = a.this;
                    aVar.f5255g = aVar.z();
                    f.k0.i.e.e(a.this.f5249a.k(), this.f5262e, a.this.f5255g);
                    a();
                }
            } catch (NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f5257c) {
                return;
            }
            if (this.f5264g && !f.k0.e.o(this, 100, TimeUnit.MILLISECONDS)) {
                a.this.f5250b.q();
                a();
            }
            this.f5257c = true;
        }

        @Override // f.k0.j.a.b, okio.Source
        public long p(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f5257c) {
                throw new IllegalStateException("closed");
            }
            if (!this.f5264g) {
                return -1L;
            }
            long j2 = this.f5263f;
            if (j2 == 0 || j2 == -1) {
                d();
                if (!this.f5264g) {
                    return -1L;
                }
            }
            long jP = super.p(buffer, Math.min(j, this.f5263f));
            if (jP != -1) {
                this.f5263f -= jP;
                return jP;
            }
            a.this.f5250b.q();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            a();
            throw protocolException;
        }
    }

    /* JADX INFO: compiled from: Http1ExchangeCodec.java */
    private class e extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f5266e;

        e(long j) {
            super();
            this.f5266e = j;
            if (j == 0) {
                a();
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f5257c) {
                return;
            }
            if (this.f5266e != 0 && !f.k0.e.o(this, 100, TimeUnit.MILLISECONDS)) {
                a.this.f5250b.q();
                a();
            }
            this.f5257c = true;
        }

        @Override // f.k0.j.a.b, okio.Source
        public long p(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f5257c) {
                throw new IllegalStateException("closed");
            }
            long j2 = this.f5266e;
            if (j2 == 0) {
                return -1L;
            }
            long jP = super.p(buffer, Math.min(j2, j));
            if (jP == -1) {
                a.this.f5250b.q();
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                a();
                throw protocolException;
            }
            long j3 = this.f5266e - jP;
            this.f5266e = j3;
            if (j3 == 0) {
                a();
            }
            return jP;
        }
    }

    /* JADX INFO: compiled from: Http1ExchangeCodec.java */
    private final class f implements Sink {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ForwardingTimeout f5268b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f5269c;

        private f() {
            this.f5268b = new ForwardingTimeout(a.this.f5252d.e());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f5269c) {
                return;
            }
            this.f5269c = true;
            a.this.s(this.f5268b);
            a.this.f5253e = 3;
        }

        @Override // okio.Sink
        public Timeout e() {
            return this.f5268b;
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            if (this.f5269c) {
                return;
            }
            a.this.f5252d.flush();
        }

        @Override // okio.Sink
        public void h(Buffer buffer, long j) {
            if (this.f5269c) {
                throw new IllegalStateException("closed");
            }
            f.k0.e.e(buffer.g0(), 0L, j);
            a.this.f5252d.h(buffer, j);
        }
    }

    /* JADX INFO: compiled from: Http1ExchangeCodec.java */
    private class g extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f5271e;

        private g() {
            super();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f5257c) {
                return;
            }
            if (!this.f5271e) {
                a();
            }
            this.f5257c = true;
        }

        @Override // f.k0.j.a.b, okio.Source
        public long p(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f5257c) {
                throw new IllegalStateException("closed");
            }
            if (this.f5271e) {
                return -1L;
            }
            long jP = super.p(buffer, j);
            if (jP != -1) {
                return jP;
            }
            this.f5271e = true;
            a();
            return -1L;
        }
    }

    public a(b0 b0Var, f.k0.h.f fVar, BufferedSource bufferedSource, BufferedSink bufferedSink) {
        this.f5249a = b0Var;
        this.f5250b = fVar;
        this.f5251c = bufferedSource;
        this.f5252d = bufferedSink;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(ForwardingTimeout forwardingTimeout) {
        Timeout timeoutK = forwardingTimeout.k();
        forwardingTimeout.l(Timeout.f5853a);
        timeoutK.a();
        timeoutK.b();
    }

    private Sink t() {
        if (this.f5253e == 1) {
            this.f5253e = 2;
            return new c();
        }
        throw new IllegalStateException("state: " + this.f5253e);
    }

    private Source u(x xVar) {
        if (this.f5253e == 4) {
            this.f5253e = 5;
            return new d(xVar);
        }
        throw new IllegalStateException("state: " + this.f5253e);
    }

    private Source v(long j) {
        if (this.f5253e == 4) {
            this.f5253e = 5;
            return new e(j);
        }
        throw new IllegalStateException("state: " + this.f5253e);
    }

    private Sink w() {
        if (this.f5253e == 1) {
            this.f5253e = 2;
            return new f();
        }
        throw new IllegalStateException("state: " + this.f5253e);
    }

    private Source x() {
        if (this.f5253e == 4) {
            this.f5253e = 5;
            this.f5250b.q();
            return new g();
        }
        throw new IllegalStateException("state: " + this.f5253e);
    }

    private String y() {
        String strR = this.f5251c.r(this.f5254f);
        this.f5254f -= (long) strR.length();
        return strR;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public w z() {
        w.a aVar = new w.a();
        while (true) {
            String strY = y();
            if (strY.length() == 0) {
                return aVar.f();
            }
            f.k0.c.f5131a.a(aVar, strY);
        }
    }

    public void A(g0 g0Var) {
        long jB = f.k0.i.e.b(g0Var);
        if (jB == -1) {
            return;
        }
        Source sourceV = v(jB);
        f.k0.e.E(sourceV, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        sourceV.close();
    }

    public void B(w wVar, String str) {
        if (this.f5253e != 0) {
            throw new IllegalStateException("state: " + this.f5253e);
        }
        this.f5252d.D(str).D("\r\n");
        int iH = wVar.h();
        for (int i2 = 0; i2 < iH; i2++) {
            this.f5252d.D(wVar.e(i2)).D(": ").D(wVar.i(i2)).D("\r\n");
        }
        this.f5252d.D("\r\n");
        this.f5253e = 1;
    }

    @Override // f.k0.i.c
    public void a() {
        this.f5252d.flush();
    }

    @Override // f.k0.i.c
    public f.k0.h.f b() {
        return this.f5250b;
    }

    @Override // f.k0.i.c
    public void c(e0 e0Var) {
        B(e0Var.d(), i.a(e0Var, this.f5250b.r().b().type()));
    }

    @Override // f.k0.i.c
    public void cancel() {
        f.k0.h.f fVar = this.f5250b;
        if (fVar != null) {
            fVar.d();
        }
    }

    @Override // f.k0.i.c
    public void d() {
        this.f5252d.flush();
    }

    @Override // f.k0.i.c
    public Sink e(e0 e0Var, long j) throws ProtocolException {
        if (e0Var.a() != null && e0Var.a().h()) {
            throw new ProtocolException("Duplex connections are not supported for HTTP/1");
        }
        if ("chunked".equalsIgnoreCase(e0Var.c("Transfer-Encoding"))) {
            return t();
        }
        if (j != -1) {
            return w();
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // f.k0.i.c
    public long f(g0 g0Var) {
        if (!f.k0.i.e.c(g0Var)) {
            return 0L;
        }
        if ("chunked".equalsIgnoreCase(g0Var.C("Transfer-Encoding"))) {
            return -1L;
        }
        return f.k0.i.e.b(g0Var);
    }

    @Override // f.k0.i.c
    public Source g(g0 g0Var) {
        if (!f.k0.i.e.c(g0Var)) {
            return v(0L);
        }
        if ("chunked".equalsIgnoreCase(g0Var.C("Transfer-Encoding"))) {
            return u(g0Var.Y().i());
        }
        long jB = f.k0.i.e.b(g0Var);
        return jB != -1 ? v(jB) : x();
    }

    @Override // f.k0.i.c
    public g0.a h(boolean z) throws IOException {
        int i2 = this.f5253e;
        if (i2 != 1 && i2 != 3) {
            throw new IllegalStateException("state: " + this.f5253e);
        }
        try {
            k kVarA = k.a(y());
            g0.a aVarJ = new g0.a().o(kVarA.f5246a).g(kVarA.f5247b).l(kVarA.f5248c).j(z());
            if (z && kVarA.f5247b == 100) {
                return null;
            }
            if (kVarA.f5247b == 100) {
                this.f5253e = 3;
                return aVarJ;
            }
            this.f5253e = 4;
            return aVarJ;
        } catch (EOFException e2) {
            f.k0.h.f fVar = this.f5250b;
            throw new IOException("unexpected end of stream on " + (fVar != null ? fVar.r().a().l().B() : "unknown"), e2);
        }
    }
}
