package g;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import okio.ByteString;

/* JADX INFO: compiled from: RealBufferedSink.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f5489b = new c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o f5490c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5491d;

    public k(o oVar) {
        Objects.requireNonNull(oVar, "sink == null");
        this.f5490c = oVar;
    }

    @Override // g.d
    public d A(String str) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        this.f5489b.A(str);
        return k();
    }

    @Override // g.d
    public d C(long j) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        this.f5489b.C(j);
        return k();
    }

    @Override // g.d
    public d E(int i2) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        this.f5489b.E(i2);
        return k();
    }

    @Override // g.d
    public c a() {
        return this.f5489b;
    }

    @Override // g.o
    public q b() {
        return this.f5490c.b();
    }

    @Override // g.d
    public d c(byte[] bArr) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        this.f5489b.c(bArr);
        return k();
    }

    @Override // g.o, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f5491d) {
            return;
        }
        Throwable th = null;
        try {
            c cVar = this.f5489b;
            long j = cVar.f5471d;
            if (j > 0) {
                this.f5490c.g(cVar, j);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.f5490c.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.f5491d = true;
        if (th != null) {
            r.e(th);
        }
    }

    @Override // g.d
    public d d(byte[] bArr, int i2, int i3) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        this.f5489b.d(bArr, i2, i3);
        return k();
    }

    @Override // g.d, g.o, java.io.Flushable
    public void flush() throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        c cVar = this.f5489b;
        long j = cVar.f5471d;
        if (j > 0) {
            this.f5490c.g(cVar, j);
        }
        this.f5490c.flush();
    }

    @Override // g.o
    public void g(c cVar, long j) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        this.f5489b.g(cVar, j);
        k();
    }

    @Override // g.d
    public d h(ByteString byteString) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        this.f5489b.h(byteString);
        return k();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f5491d;
    }

    @Override // g.d
    public long j(p pVar) throws IOException {
        if (pVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jM = pVar.m(this.f5489b, 8192L);
            if (jM == -1) {
                return j;
            }
            j += jM;
            k();
        }
    }

    @Override // g.d
    public d k() throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        long jQ = this.f5489b.Q();
        if (jQ > 0) {
            this.f5490c.g(this.f5489b, jQ);
        }
        return this;
    }

    @Override // g.d
    public d l(long j) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        this.f5489b.l(j);
        return k();
    }

    @Override // g.d
    public d q(int i2) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        this.f5489b.q(i2);
        return k();
    }

    public String toString() {
        return "buffer(" + this.f5490c + ")";
    }

    @Override // g.d
    public d u(int i2) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        this.f5489b.u(i2);
        return k();
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (this.f5491d) {
            throw new IllegalStateException("closed");
        }
        int iWrite = this.f5489b.write(byteBuffer);
        k();
        return iWrite;
    }
}
