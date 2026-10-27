package f.b0.h;

import f.b0.g.h;
import f.b0.g.i;
import f.b0.g.k;
import f.q;
import f.u;
import f.w;
import f.y;
import f.z;
import g.j;
import g.o;
import g.p;
import g.q;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements f.b0.g.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f5096a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f.b0.f.f f5097b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.e f5098c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g.d f5099d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5100e = 0;

    /* JADX INFO: compiled from: Http1Codec.java */
    public abstract class b implements p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g.g f5101b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f5102c;

        public b() {
            this.f5101b = new g.g(a.this.f5098c.b());
        }

        @Override // g.p
        public q b() {
            return this.f5101b;
        }

        public final void w(boolean z) throws IOException {
            a aVar = a.this;
            int i2 = aVar.f5100e;
            if (i2 == 6) {
                return;
            }
            if (i2 != 5) {
                throw new IllegalStateException("state: " + a.this.f5100e);
            }
            aVar.g(this.f5101b);
            a aVar2 = a.this;
            aVar2.f5100e = 6;
            f.b0.f.f fVar = aVar2.f5097b;
            if (fVar != null) {
                fVar.p(!z, aVar2);
            }
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public final class c implements o {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g.g f5104b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f5105c;

        public c() {
            this.f5104b = new g.g(a.this.f5099d.b());
        }

        @Override // g.o
        public q b() {
            return this.f5104b;
        }

        @Override // g.o, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (this.f5105c) {
                return;
            }
            this.f5105c = true;
            a.this.f5099d.A("0\r\n\r\n");
            a.this.g(this.f5104b);
            a.this.f5100e = 3;
        }

        @Override // g.o, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (this.f5105c) {
                return;
            }
            a.this.f5099d.flush();
        }

        @Override // g.o
        public void g(g.c cVar, long j) throws IOException {
            if (this.f5105c) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            a.this.f5099d.l(j);
            a.this.f5099d.A("\r\n");
            a.this.f5099d.g(cVar, j);
            a.this.f5099d.A("\r\n");
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public class d extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final HttpUrl f5107e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f5108f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f5109g;

        public d(HttpUrl httpUrl) {
            super();
            this.f5108f = -1L;
            this.f5109g = true;
            this.f5107e = httpUrl;
        }

        @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f5102c) {
                return;
            }
            if (this.f5109g && !f.b0.c.i(this, 100, TimeUnit.MILLISECONDS)) {
                w(false);
            }
            this.f5102c = true;
        }

        @Override // g.p
        public long m(g.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f5102c) {
                throw new IllegalStateException("closed");
            }
            if (!this.f5109g) {
                return -1L;
            }
            long j2 = this.f5108f;
            if (j2 == 0 || j2 == -1) {
                z();
                if (!this.f5109g) {
                    return -1L;
                }
            }
            long jM = a.this.f5098c.m(cVar, Math.min(j, this.f5108f));
            if (jM != -1) {
                this.f5108f -= jM;
                return jM;
            }
            w(false);
            throw new ProtocolException("unexpected end of stream");
        }

        public final void z() throws IOException {
            if (this.f5108f != -1) {
                a.this.f5098c.x();
            }
            try {
                this.f5108f = a.this.f5098c.J();
                String strTrim = a.this.f5098c.x().trim();
                if (this.f5108f < 0 || !(strTrim.isEmpty() || strTrim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f5108f + strTrim + "\"");
                }
                if (this.f5108f == 0) {
                    this.f5109g = false;
                    f.b0.g.e.e(a.this.f5096a.f(), this.f5107e, a.this.n());
                    w(true);
                }
            } catch (NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public final class e implements o {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g.g f5111b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f5112c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f5113d;

        public e(long j) {
            this.f5111b = new g.g(a.this.f5099d.b());
            this.f5113d = j;
        }

        @Override // g.o
        public q b() {
            return this.f5111b;
        }

        @Override // g.o, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f5112c) {
                return;
            }
            this.f5112c = true;
            if (this.f5113d > 0) {
                throw new ProtocolException("unexpected end of stream");
            }
            a.this.g(this.f5111b);
            a.this.f5100e = 3;
        }

        @Override // g.o, java.io.Flushable
        public void flush() throws IOException {
            if (this.f5112c) {
                return;
            }
            a.this.f5099d.flush();
        }

        @Override // g.o
        public void g(g.c cVar, long j) throws IOException {
            if (this.f5112c) {
                throw new IllegalStateException("closed");
            }
            f.b0.c.b(cVar.f0(), 0L, j);
            if (j <= this.f5113d) {
                a.this.f5099d.g(cVar, j);
                this.f5113d -= j;
                return;
            }
            throw new ProtocolException("expected " + this.f5113d + " bytes but received " + j);
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public class f extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f5115e;

        public f(long j) throws IOException {
            super();
            this.f5115e = j;
            if (j == 0) {
                w(true);
            }
        }

        @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f5102c) {
                return;
            }
            if (this.f5115e != 0 && !f.b0.c.i(this, 100, TimeUnit.MILLISECONDS)) {
                w(false);
            }
            this.f5102c = true;
        }

        @Override // g.p
        public long m(g.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f5102c) {
                throw new IllegalStateException("closed");
            }
            long j2 = this.f5115e;
            if (j2 == 0) {
                return -1L;
            }
            long jM = a.this.f5098c.m(cVar, Math.min(j2, j));
            if (jM == -1) {
                w(false);
                throw new ProtocolException("unexpected end of stream");
            }
            long j3 = this.f5115e - jM;
            this.f5115e = j3;
            if (j3 == 0) {
                w(true);
            }
            return jM;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public class g extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f5117e;

        public g() {
            super();
        }

        @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f5102c) {
                return;
            }
            if (!this.f5117e) {
                w(false);
            }
            this.f5102c = true;
        }

        @Override // g.p
        public long m(g.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f5102c) {
                throw new IllegalStateException("closed");
            }
            if (this.f5117e) {
                return -1L;
            }
            long jM = a.this.f5098c.m(cVar, j);
            if (jM != -1) {
                return jM;
            }
            this.f5117e = true;
            w(true);
            return -1L;
        }
    }

    public a(u uVar, f.b0.f.f fVar, g.e eVar, g.d dVar) {
        this.f5096a = uVar;
        this.f5097b = fVar;
        this.f5098c = eVar;
        this.f5099d = dVar;
    }

    @Override // f.b0.g.c
    public void a() throws IOException {
        this.f5099d.flush();
    }

    @Override // f.b0.g.c
    public void b(w wVar) throws IOException {
        o(wVar.d(), i.a(wVar, this.f5097b.d().a().b().type()));
    }

    @Override // f.b0.g.c
    public z c(y yVar) throws IOException {
        return new h(yVar.S(), j.b(h(yVar)));
    }

    @Override // f.b0.g.c
    public void cancel() {
        f.b0.f.c cVarD = this.f5097b.d();
        if (cVarD != null) {
            cVarD.d();
        }
    }

    @Override // f.b0.g.c
    public void d() throws IOException {
        this.f5099d.flush();
    }

    @Override // f.b0.g.c
    public o e(w wVar, long j) {
        if ("chunked".equalsIgnoreCase(wVar.c("Transfer-Encoding"))) {
            return i();
        }
        if (j != -1) {
            return k(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // f.b0.g.c
    public y.a f(boolean z) throws IOException {
        int i2 = this.f5100e;
        if (i2 != 1 && i2 != 3) {
            throw new IllegalStateException("state: " + this.f5100e);
        }
        try {
            k kVarA = k.a(this.f5098c.x());
            y.a aVarI = new y.a().m(kVarA.f5093a).g(kVarA.f5094b).j(kVarA.f5095c).i(n());
            if (z && kVarA.f5094b == 100) {
                return null;
            }
            this.f5100e = 4;
            return aVarI;
        } catch (EOFException e2) {
            IOException iOException = new IOException("unexpected end of stream on " + this.f5097b);
            iOException.initCause(e2);
            throw iOException;
        }
    }

    public void g(g.g gVar) {
        q qVarI = gVar.i();
        gVar.j(q.f5505a);
        qVarI.a();
        qVarI.b();
    }

    public final p h(y yVar) throws IOException {
        if (!f.b0.g.e.c(yVar)) {
            return l(0L);
        }
        if ("chunked".equalsIgnoreCase(yVar.Q("Transfer-Encoding"))) {
            return j(yVar.W().h());
        }
        long jB = f.b0.g.e.b(yVar);
        return jB != -1 ? l(jB) : m();
    }

    public o i() {
        if (this.f5100e == 1) {
            this.f5100e = 2;
            return new c();
        }
        throw new IllegalStateException("state: " + this.f5100e);
    }

    public p j(HttpUrl httpUrl) throws IOException {
        if (this.f5100e == 4) {
            this.f5100e = 5;
            return new d(httpUrl);
        }
        throw new IllegalStateException("state: " + this.f5100e);
    }

    public o k(long j) {
        if (this.f5100e == 1) {
            this.f5100e = 2;
            return new e(j);
        }
        throw new IllegalStateException("state: " + this.f5100e);
    }

    public p l(long j) throws IOException {
        if (this.f5100e == 4) {
            this.f5100e = 5;
            return new f(j);
        }
        throw new IllegalStateException("state: " + this.f5100e);
    }

    public p m() throws IOException {
        if (this.f5100e != 4) {
            throw new IllegalStateException("state: " + this.f5100e);
        }
        f.b0.f.f fVar = this.f5097b;
        if (fVar == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.f5100e = 5;
        fVar.j();
        return new g();
    }

    public f.q n() throws IOException {
        q.a aVar = new q.a();
        while (true) {
            String strX = this.f5098c.x();
            if (strX.length() == 0) {
                return aVar.d();
            }
            f.b0.a.f5010a.a(aVar, strX);
        }
    }

    public void o(f.q qVar, String str) throws IOException {
        if (this.f5100e != 0) {
            throw new IllegalStateException("state: " + this.f5100e);
        }
        this.f5099d.A(str).A("\r\n");
        int iF = qVar.f();
        for (int i2 = 0; i2 < iF; i2++) {
            this.f5099d.A(qVar.c(i2)).A(": ").A(qVar.g(i2)).A("\r\n");
        }
        this.f5099d.A("\r\n");
        this.f5100e = 1;
    }
}
