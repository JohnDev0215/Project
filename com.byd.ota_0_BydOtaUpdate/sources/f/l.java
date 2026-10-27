package f;

import java.io.IOException;
import java.util.Objects;
import okio.ByteString;

/* JADX INFO: compiled from: RealBufferedSink.java */
/* JADX INFO: loaded from: classes.dex */
public final class l implements d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f3716c = new c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p f3717d;
    public boolean s;

    public l(p pVar) {
        Objects.requireNonNull(pVar, "sink == null");
        this.f3717d = pVar;
    }

    @Override // f.d
    public d A(int i2) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        this.f3716c.A(i2);
        return l();
    }

    @Override // f.d
    public c a() {
        return this.f3716c;
    }

    @Override // f.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.s) {
            return;
        }
        Throwable th = null;
        try {
            c cVar = this.f3716c;
            long j = cVar.s;
            if (j > 0) {
                this.f3717d.h(cVar, j);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.f3717d.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.s = true;
        if (th != null) {
            s.e(th);
        }
    }

    @Override // f.p
    public r e() {
        return this.f3717d.e();
    }

    @Override // f.d
    public d f(byte[] bArr) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        this.f3716c.f(bArr);
        return l();
    }

    @Override // f.d, f.p, java.io.Flushable
    public void flush() throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        c cVar = this.f3716c;
        long j = cVar.s;
        if (j > 0) {
            this.f3717d.h(cVar, j);
        }
        this.f3717d.flush();
    }

    @Override // f.d
    public d g(byte[] bArr, int i2, int i3) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        this.f3716c.g(bArr, i2, i3);
        return l();
    }

    @Override // f.p
    public void h(c cVar, long j) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        this.f3716c.h(cVar, j);
        l();
    }

    @Override // f.d
    public d i(ByteString byteString) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        this.f3716c.i(byteString);
        return l();
    }

    @Override // f.d
    public long k(q qVar) throws IOException {
        if (qVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jN = qVar.n(this.f3716c, 8192L);
            if (jN == -1) {
                return j;
            }
            j += jN;
            l();
        }
    }

    @Override // f.d
    public d l() throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        long J = this.f3716c.J();
        if (J > 0) {
            this.f3717d.h(this.f3716c, J);
        }
        return this;
    }

    @Override // f.d
    public d m(long j) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        this.f3716c.m(j);
        return l();
    }

    @Override // f.d
    public d q(int i2) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        this.f3716c.q(i2);
        return l();
    }

    @Override // f.d
    public d r(int i2) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        this.f3716c.r(i2);
        return l();
    }

    public String toString() {
        return "buffer(" + this.f3717d + ")";
    }

    @Override // f.d
    public d w(String str) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        this.f3716c.w(str);
        return l();
    }

    @Override // f.d
    public d y(long j) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        this.f3716c.y(j);
        return l();
    }
}
