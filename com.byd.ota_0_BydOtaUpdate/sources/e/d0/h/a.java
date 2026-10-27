package e.d0.h;

import e.a0;
import e.b0;
import e.d0.g.i;
import e.s;
import e.w;
import e.y;
import f.h;
import f.k;
import f.p;
import f.q;
import f.r;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements e.d0.g.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w f3416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e.d0.f.f f3417b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f.e f3418c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f.d f3419d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3420e = 0;

    /* JADX INFO: compiled from: Http1Codec.java */
    public abstract class b implements q {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final h f3421c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f3422d;

        public b() {
            this.f3421c = new h(a.this.f3418c.e());
        }

        public final void c(boolean z) throws IOException {
            a aVar = a.this;
            int i2 = aVar.f3420e;
            if (i2 == 6) {
                return;
            }
            if (i2 != 5) {
                throw new IllegalStateException("state: " + a.this.f3420e);
            }
            aVar.g(this.f3421c);
            a aVar2 = a.this;
            aVar2.f3420e = 6;
            e.d0.f.f fVar = aVar2.f3417b;
            if (fVar != null) {
                fVar.p(!z, aVar2);
            }
        }

        @Override // f.q
        public r e() {
            return this.f3421c;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public final class c implements p {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final h f3423c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f3424d;

        public c() {
            this.f3423c = new h(a.this.f3419d.e());
        }

        @Override // f.p, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (this.f3424d) {
                return;
            }
            this.f3424d = true;
            a.this.f3419d.w("0\r\n\r\n");
            a.this.g(this.f3423c);
            a.this.f3420e = 3;
        }

        @Override // f.p
        public r e() {
            return this.f3423c;
        }

        @Override // f.p, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (this.f3424d) {
                return;
            }
            a.this.f3419d.flush();
        }

        @Override // f.p
        public void h(f.c cVar, long j) throws IOException {
            if (this.f3424d) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            a.this.f3419d.m(j);
            a.this.f3419d.w("\r\n");
            a.this.f3419d.h(cVar, j);
            a.this.f3419d.w("\r\n");
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public class d extends b {
        public boolean E3;
        public final HttpUrl x;
        public long y;

        public d(HttpUrl httpUrl) {
            super();
            this.y = -1L;
            this.E3 = true;
            this.x = httpUrl;
        }

        @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f3422d) {
                return;
            }
            if (this.E3 && !e.d0.c.i(this, 100, TimeUnit.MILLISECONDS)) {
                c(false);
            }
            this.f3422d = true;
        }

        public final void d() throws IOException {
            if (this.y != -1) {
                a.this.f3418c.t();
            }
            try {
                this.y = a.this.f3418c.E();
                String strTrim = a.this.f3418c.t().trim();
                if (this.y < 0 || !(strTrim.isEmpty() || strTrim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.y + strTrim + "\"");
                }
                if (this.y == 0) {
                    this.E3 = false;
                    e.d0.g.e.e(a.this.f3416a.i(), this.x, a.this.n());
                    c(true);
                }
            } catch (NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
        }

        @Override // f.q
        public long n(f.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f3422d) {
                throw new IllegalStateException("closed");
            }
            if (!this.E3) {
                return -1L;
            }
            long j2 = this.y;
            if (j2 == 0 || j2 == -1) {
                d();
                if (!this.E3) {
                    return -1L;
                }
            }
            long jN = a.this.f3418c.n(cVar, Math.min(j, this.y));
            if (jN != -1) {
                this.y -= jN;
                return jN;
            }
            c(false);
            throw new ProtocolException("unexpected end of stream");
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public final class e implements p {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final h f3425c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f3426d;
        public long s;

        public e(long j) {
            this.f3425c = new h(a.this.f3419d.e());
            this.s = j;
        }

        @Override // f.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f3426d) {
                return;
            }
            this.f3426d = true;
            if (this.s > 0) {
                throw new ProtocolException("unexpected end of stream");
            }
            a.this.g(this.f3425c);
            a.this.f3420e = 3;
        }

        @Override // f.p
        public r e() {
            return this.f3425c;
        }

        @Override // f.p, java.io.Flushable
        public void flush() throws IOException {
            if (this.f3426d) {
                return;
            }
            a.this.f3419d.flush();
        }

        @Override // f.p
        public void h(f.c cVar, long j) throws IOException {
            if (this.f3426d) {
                throw new IllegalStateException("closed");
            }
            e.d0.c.b(cVar.Y(), 0L, j);
            if (j <= this.s) {
                a.this.f3419d.h(cVar, j);
                this.s -= j;
                return;
            }
            throw new ProtocolException("expected " + this.s + " bytes but received " + j);
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public class f extends b {
        public long x;

        public f(long j) throws IOException {
            super();
            this.x = j;
            if (j == 0) {
                c(true);
            }
        }

        @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f3422d) {
                return;
            }
            if (this.x != 0 && !e.d0.c.i(this, 100, TimeUnit.MILLISECONDS)) {
                c(false);
            }
            this.f3422d = true;
        }

        @Override // f.q
        public long n(f.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f3422d) {
                throw new IllegalStateException("closed");
            }
            long j2 = this.x;
            if (j2 == 0) {
                return -1L;
            }
            long jN = a.this.f3418c.n(cVar, Math.min(j2, j));
            if (jN == -1) {
                c(false);
                throw new ProtocolException("unexpected end of stream");
            }
            long j3 = this.x - jN;
            this.x = j3;
            if (j3 == 0) {
                c(true);
            }
            return jN;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    public class g extends b {
        public boolean x;

        public g() {
            super();
        }

        @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f3422d) {
                return;
            }
            if (!this.x) {
                c(false);
            }
            this.f3422d = true;
        }

        @Override // f.q
        public long n(f.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f3422d) {
                throw new IllegalStateException("closed");
            }
            if (this.x) {
                return -1L;
            }
            long jN = a.this.f3418c.n(cVar, j);
            if (jN != -1) {
                return jN;
            }
            this.x = true;
            c(true);
            return -1L;
        }
    }

    public a(w wVar, e.d0.f.f fVar, f.e eVar, f.d dVar) {
        this.f3416a = wVar;
        this.f3417b = fVar;
        this.f3418c = eVar;
        this.f3419d = dVar;
    }

    @Override // e.d0.g.c
    public void a() throws IOException {
        this.f3419d.flush();
    }

    @Override // e.d0.g.c
    public void b(y yVar) throws IOException {
        o(yVar.d(), i.a(yVar, this.f3417b.d().b().b().type()));
    }

    @Override // e.d0.g.c
    public b0 c(a0 a0Var) throws IOException {
        return new e.d0.g.h(a0Var.J(), k.b(h(a0Var)));
    }

    @Override // e.d0.g.c
    public void cancel() {
        e.d0.f.c cVarD = this.f3417b.d();
        if (cVarD != null) {
            cVarD.e();
        }
    }

    @Override // e.d0.g.c
    public void d() throws IOException {
        this.f3419d.flush();
    }

    @Override // e.d0.g.c
    public p e(y yVar, long j) {
        if ("chunked".equalsIgnoreCase(yVar.c("Transfer-Encoding"))) {
            return i();
        }
        if (j != -1) {
            return k(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // e.d0.g.c
    public a0.a f(boolean z) throws IOException {
        int i2 = this.f3420e;
        if (i2 != 1 && i2 != 3) {
            throw new IllegalStateException("state: " + this.f3420e);
        }
        try {
            e.d0.g.k kVarA = e.d0.g.k.a(this.f3418c.t());
            a0.a aVarI = new a0.a().m(kVarA.f3413a).g(kVarA.f3414b).j(kVarA.f3415c).i(n());
            if (z && kVarA.f3414b == 100) {
                return null;
            }
            this.f3420e = 4;
            return aVarI;
        } catch (EOFException e2) {
            IOException iOException = new IOException("unexpected end of stream on " + this.f3417b);
            iOException.initCause(e2);
            throw iOException;
        }
    }

    public void g(h hVar) {
        r rVarI = hVar.i();
        hVar.j(r.f3730a);
        rVarI.a();
        rVarI.b();
    }

    public final q h(a0 a0Var) throws IOException {
        if (!e.d0.g.e.c(a0Var)) {
            return l(0L);
        }
        if ("chunked".equalsIgnoreCase(a0Var.H("Transfer-Encoding"))) {
            return j(a0Var.O().h());
        }
        long jB = e.d0.g.e.b(a0Var);
        return jB != -1 ? l(jB) : m();
    }

    public p i() {
        if (this.f3420e == 1) {
            this.f3420e = 2;
            return new c();
        }
        throw new IllegalStateException("state: " + this.f3420e);
    }

    public q j(HttpUrl httpUrl) throws IOException {
        if (this.f3420e == 4) {
            this.f3420e = 5;
            return new d(httpUrl);
        }
        throw new IllegalStateException("state: " + this.f3420e);
    }

    public p k(long j) {
        if (this.f3420e == 1) {
            this.f3420e = 2;
            return new e(j);
        }
        throw new IllegalStateException("state: " + this.f3420e);
    }

    public q l(long j) throws IOException {
        if (this.f3420e == 4) {
            this.f3420e = 5;
            return new f(j);
        }
        throw new IllegalStateException("state: " + this.f3420e);
    }

    public q m() throws IOException {
        if (this.f3420e != 4) {
            throw new IllegalStateException("state: " + this.f3420e);
        }
        e.d0.f.f fVar = this.f3417b;
        if (fVar == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.f3420e = 5;
        fVar.j();
        return new g();
    }

    public s n() throws IOException {
        s.a aVar = new s.a();
        while (true) {
            String strT = this.f3418c.t();
            if (strT.length() == 0) {
                return aVar.d();
            }
            e.d0.a.f3334a.a(aVar, strT);
        }
    }

    public void o(s sVar, String str) throws IOException {
        if (this.f3420e != 0) {
            throw new IllegalStateException("state: " + this.f3420e);
        }
        this.f3419d.w(str).w("\r\n");
        int iF = sVar.f();
        for (int i2 = 0; i2 < iF; i2++) {
            this.f3419d.w(sVar.c(i2)).w(": ").w(sVar.g(i2)).w("\r\n");
        }
        this.f3419d.w("\r\n");
        this.f3420e = 1;
    }
}
