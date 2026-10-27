package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class RealBufferedSink implements BufferedSink {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Buffer f5834b = new Buffer();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Sink f5835c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f5836d;

    RealBufferedSink(Sink sink) {
        Objects.requireNonNull(sink, "sink == null");
        this.f5835c = sink;
    }

    @Override // okio.BufferedSink
    public BufferedSink D(String str) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.D(str);
        return n();
    }

    @Override // okio.BufferedSink
    public BufferedSink F(long j) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.F(j);
        return n();
    }

    @Override // okio.BufferedSink
    public BufferedSink H(int i2) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.H(i2);
        return n();
    }

    @Override // okio.BufferedSink
    public BufferedSink J(int i2) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.J(i2);
        return n();
    }

    @Override // okio.BufferedSink
    public Buffer b() {
        return this.f5834b;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f5836d) {
            return;
        }
        Throwable th = null;
        try {
            Buffer buffer = this.f5834b;
            long j = buffer.f5771d;
            if (j > 0) {
                this.f5835c.h(buffer, j);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.f5835c.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.f5836d = true;
        if (th != null) {
            Util.e(th);
        }
    }

    @Override // okio.Sink
    public Timeout e() {
        return this.f5835c.e();
    }

    @Override // okio.BufferedSink
    public BufferedSink f(byte[] bArr) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.f(bArr);
        return n();
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        Buffer buffer = this.f5834b;
        long j = buffer.f5771d;
        if (j > 0) {
            this.f5835c.h(buffer, j);
        }
        this.f5835c.flush();
    }

    @Override // okio.BufferedSink
    public BufferedSink g(byte[] bArr, int i2, int i3) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.g(bArr, i2, i3);
        return n();
    }

    @Override // okio.Sink
    public void h(Buffer buffer, long j) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.h(buffer, j);
        n();
    }

    @Override // okio.BufferedSink
    public BufferedSink i(ByteString byteString) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.i(byteString);
        return n();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f5836d;
    }

    @Override // okio.BufferedSink
    public long m(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jP = source.p(this.f5834b, 8192L);
            if (jP == -1) {
                return j;
            }
            j += jP;
            n();
        }
    }

    @Override // okio.BufferedSink
    public BufferedSink n() {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        long jT = this.f5834b.T();
        if (jT > 0) {
            this.f5835c.h(this.f5834b, jT);
        }
        return this;
    }

    @Override // okio.BufferedSink
    public BufferedSink o(long j) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.o(j);
        return n();
    }

    public String toString() {
        return "buffer(" + this.f5835c + ")";
    }

    @Override // okio.BufferedSink
    public BufferedSink v(int i2) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.v(i2);
        return n();
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        int iWrite = this.f5834b.write(byteBuffer);
        n();
        return iWrite;
    }

    @Override // okio.BufferedSink
    public BufferedSink x(int i2) {
        if (this.f5836d) {
            throw new IllegalStateException("closed");
        }
        this.f5834b.x(i2);
        return n();
    }

    /* JADX INFO: renamed from: okio.RealBufferedSink$1, reason: invalid class name */
    class AnonymousClass1 extends OutputStream {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ RealBufferedSink f5837b;

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws Throwable {
            this.f5837b.close();
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
            RealBufferedSink realBufferedSink = this.f5837b;
            if (realBufferedSink.f5836d) {
                return;
            }
            realBufferedSink.flush();
        }

        public String toString() {
            return this.f5837b + ".outputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int i2) throws IOException {
            RealBufferedSink realBufferedSink = this.f5837b;
            if (realBufferedSink.f5836d) {
                throw new IOException("closed");
            }
            realBufferedSink.f5834b.J((byte) i2);
            this.f5837b.n();
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) throws IOException {
            RealBufferedSink realBufferedSink = this.f5837b;
            if (!realBufferedSink.f5836d) {
                realBufferedSink.f5834b.g(bArr, i2, i3);
                this.f5837b.n();
                return;
            }
            throw new IOException("closed");
        }
    }
}
