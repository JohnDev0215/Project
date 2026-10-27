package p6;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: RealBufferedSink.kt */
/* JADX INFO: loaded from: classes.dex */
public final class t implements f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f8894e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f8895g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final y f8896h;

    public t(y sink) {
        kotlin.jvm.internal.l.e(sink, "sink");
        this.f8896h = sink;
        this.f8894e = new e();
    }

    @Override // p6.f
    public f H(String string) {
        kotlin.jvm.internal.l.e(string, "string");
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f8894e.H(string);
        return m();
    }

    @Override // p6.f
    public f M(int i7) {
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f8894e.M(i7);
        return m();
    }

    @Override // p6.f
    public f Q(h byteString) {
        kotlin.jvm.internal.l.e(byteString, "byteString");
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f8894e.Q(byteString);
        return m();
    }

    @Override // p6.f
    public e b() {
        return this.f8894e;
    }

    @Override // p6.y
    public b0 c() {
        return this.f8896h.c();
    }

    @Override // p6.y, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f8895g) {
            return;
        }
        Throwable th = null;
        try {
            if (this.f8894e.j0() > 0) {
                y yVar = this.f8896h;
                e eVar = this.f8894e;
                yVar.l(eVar, eVar.j0());
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.f8896h.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.f8895g = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // p6.f
    public f d(byte[] source) {
        kotlin.jvm.internal.l.e(source, "source");
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f8894e.d(source);
        return m();
    }

    @Override // p6.f
    public f e(byte[] source, int i7, int i8) {
        kotlin.jvm.internal.l.e(source, "source");
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f8894e.e(source, i7, i8);
        return m();
    }

    @Override // p6.f, p6.y, java.io.Flushable
    public void flush() {
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        if (this.f8894e.j0() > 0) {
            y yVar = this.f8896h;
            e eVar = this.f8894e;
            yVar.l(eVar, eVar.j0());
        }
        this.f8896h.flush();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f8895g;
    }

    @Override // p6.f
    public f j(long j7) {
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f8894e.j(j7);
        return m();
    }

    @Override // p6.y
    public void l(e source, long j7) {
        kotlin.jvm.internal.l.e(source, "source");
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f8894e.l(source, j7);
        m();
    }

    public f m() {
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        long jG = this.f8894e.G();
        if (jG > 0) {
            this.f8896h.l(this.f8894e, jG);
        }
        return this;
    }

    @Override // p6.f
    public f t(int i7) {
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f8894e.t(i7);
        return m();
    }

    public String toString() {
        return "buffer(" + this.f8896h + ')';
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer source) {
        kotlin.jvm.internal.l.e(source, "source");
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        int iWrite = this.f8894e.write(source);
        m();
        return iWrite;
    }

    @Override // p6.f
    public f y(int i7) {
        if (!(!this.f8895g)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f8894e.y(i7);
        return m();
    }
}
