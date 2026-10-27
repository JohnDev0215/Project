package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class RealBufferedSource implements BufferedSource {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Buffer f5838b = new Buffer();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Source f5839c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f5840d;

    RealBufferedSource(Source source) {
        Objects.requireNonNull(source, "source == null");
        this.f5839c = source;
    }

    @Override // okio.BufferedSource
    public byte[] A() {
        this.f5838b.m(this.f5839c);
        return this.f5838b.A();
    }

    @Override // okio.BufferedSource
    public void B(long j) throws EOFException {
        if (!c(j)) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public int E() throws EOFException {
        B(4L);
        return this.f5838b.E();
    }

    @Override // okio.BufferedSource
    public Buffer G() {
        return this.f5838b;
    }

    @Override // okio.BufferedSource
    public boolean I() {
        if (this.f5840d) {
            throw new IllegalStateException("closed");
        }
        return this.f5838b.I() && this.f5839c.p(this.f5838b, 8192L) == -1;
    }

    @Override // okio.BufferedSource
    public long K(byte b2) {
        return a(b2, 0L, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public byte[] L(long j) throws EOFException {
        B(j);
        return this.f5838b.L(j);
    }

    @Override // okio.BufferedSource
    public long M() throws EOFException {
        byte bW;
        B(1L);
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (!c(i3)) {
                break;
            }
            bW = this.f5838b.W(i2);
            if ((bW < 48 || bW > 57) && ((bW < 97 || bW > 102) && (bW < 65 || bW > 70))) {
                break;
            }
            i2 = i3;
        }
        if (i2 == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(bW)));
        }
        return this.f5838b.M();
    }

    @Override // okio.BufferedSource
    public String N(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.f5838b.m(this.f5839c);
        return this.f5838b.N(charset);
    }

    @Override // okio.BufferedSource
    public String O() {
        this.f5838b.m(this.f5839c);
        return this.f5838b.O();
    }

    @Override // okio.BufferedSource
    public byte P() throws EOFException {
        B(1L);
        return this.f5838b.P();
    }

    @Override // okio.BufferedSource
    public int Q(Options options) throws EOFException {
        if (this.f5840d) {
            throw new IllegalStateException("closed");
        }
        do {
            int iF0 = this.f5838b.f0(options, true);
            if (iF0 == -1) {
                return -1;
            }
            if (iF0 != -2) {
                this.f5838b.s(options.f5813b[iF0].s());
                return iF0;
            }
        } while (this.f5839c.p(this.f5838b, 8192L) != -1);
        return -1;
    }

    public long a(byte b2, long j, long j2) {
        if (this.f5840d) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long jX = this.f5838b.X(b2, j, j2);
            if (jX != -1) {
                return jX;
            }
            Buffer buffer = this.f5838b;
            long j3 = buffer.f5771d;
            if (j3 >= j2 || this.f5839c.p(buffer, 8192L) == -1) {
                break;
            }
            j = Math.max(j, j3);
        }
        return -1L;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer b() {
        return this.f5838b;
    }

    @Override // okio.BufferedSource
    public boolean c(long j) {
        Buffer buffer;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f5840d) {
            throw new IllegalStateException("closed");
        }
        do {
            buffer = this.f5838b;
            if (buffer.f5771d >= j) {
                return true;
            }
        } while (this.f5839c.p(buffer, 8192L) != -1);
        return false;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f5840d) {
            return;
        }
        this.f5840d = true;
        this.f5839c.close();
        this.f5838b.R();
    }

    @Override // okio.Source
    public Timeout e() {
        return this.f5839c.e();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f5840d;
    }

    @Override // okio.BufferedSource
    public void k(byte[] bArr) throws EOFException {
        try {
            B(bArr.length);
            this.f5838b.k(bArr);
        } catch (EOFException e2) {
            int i2 = 0;
            while (true) {
                Buffer buffer = this.f5838b;
                long j = buffer.f5771d;
                if (j <= 0) {
                    throw e2;
                }
                int iZ = buffer.Z(bArr, i2, (int) j);
                if (iZ == -1) {
                    throw new AssertionError();
                }
                i2 += iZ;
            }
        }
    }

    @Override // okio.BufferedSource
    public short l() throws EOFException {
        B(2L);
        return this.f5838b.l();
    }

    @Override // okio.Source
    public long p(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f5840d) {
            throw new IllegalStateException("closed");
        }
        Buffer buffer2 = this.f5838b;
        if (buffer2.f5771d == 0 && this.f5839c.p(buffer2, 8192L) == -1) {
            return -1L;
        }
        return this.f5838b.p(buffer, Math.min(j, this.f5838b.f5771d));
    }

    @Override // okio.BufferedSource
    public ByteString q(long j) throws EOFException {
        B(j);
        return this.f5838b.q(j);
    }

    @Override // okio.BufferedSource
    public String r(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jA = a((byte) 10, 0L, j2);
        if (jA != -1) {
            return this.f5838b.e0(jA);
        }
        if (j2 < Long.MAX_VALUE && c(j2) && this.f5838b.W(j2 - 1) == 13 && c(1 + j2) && this.f5838b.W(j2) == 10) {
            return this.f5838b.e0(j2);
        }
        Buffer buffer = new Buffer();
        Buffer buffer2 = this.f5838b;
        buffer2.U(buffer, 0L, Math.min(32L, buffer2.g0()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f5838b.g0(), j) + " content=" + buffer.a0().k() + (char) 8230);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        Buffer buffer = this.f5838b;
        if (buffer.f5771d == 0 && this.f5839c.p(buffer, 8192L) == -1) {
            return -1;
        }
        return this.f5838b.read(byteBuffer);
    }

    @Override // okio.BufferedSource
    public void s(long j) throws EOFException {
        if (this.f5840d) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            Buffer buffer = this.f5838b;
            if (buffer.f5771d == 0 && this.f5839c.p(buffer, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.f5838b.g0());
            this.f5838b.s(jMin);
            j -= jMin;
        }
    }

    @Override // okio.BufferedSource
    public long t(Sink sink) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j = 0;
        while (this.f5839c.p(this.f5838b, 8192L) != -1) {
            long jT = this.f5838b.T();
            if (jT > 0) {
                j += jT;
                sink.h(this.f5838b, jT);
            }
        }
        if (this.f5838b.g0() <= 0) {
            return j;
        }
        long jG0 = j + this.f5838b.g0();
        Buffer buffer = this.f5838b;
        sink.h(buffer, buffer.g0());
        return jG0;
    }

    public String toString() {
        return "buffer(" + this.f5839c + ")";
    }

    @Override // okio.BufferedSource
    public short u() throws EOFException {
        B(2L);
        return this.f5838b.u();
    }

    @Override // okio.BufferedSource
    public int w() throws EOFException {
        B(4L);
        return this.f5838b.w();
    }

    @Override // okio.BufferedSource
    public String z() {
        return r(Long.MAX_VALUE);
    }

    /* JADX INFO: renamed from: okio.RealBufferedSource$1, reason: invalid class name */
    class AnonymousClass1 extends InputStream {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ RealBufferedSource f5841b;

        @Override // java.io.InputStream
        public int available() throws IOException {
            RealBufferedSource realBufferedSource = this.f5841b;
            if (realBufferedSource.f5840d) {
                throw new IOException("closed");
            }
            return (int) Math.min(realBufferedSource.f5838b.f5771d, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f5841b.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            RealBufferedSource realBufferedSource = this.f5841b;
            if (realBufferedSource.f5840d) {
                throw new IOException("closed");
            }
            Buffer buffer = realBufferedSource.f5838b;
            if (buffer.f5771d == 0 && realBufferedSource.f5839c.p(buffer, 8192L) == -1) {
                return -1;
            }
            return this.f5841b.f5838b.P() & 255;
        }

        public String toString() {
            return this.f5841b + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            if (!this.f5841b.f5840d) {
                Util.b(bArr.length, i2, i3);
                RealBufferedSource realBufferedSource = this.f5841b;
                Buffer buffer = realBufferedSource.f5838b;
                if (buffer.f5771d == 0 && realBufferedSource.f5839c.p(buffer, 8192L) == -1) {
                    return -1;
                }
                return this.f5841b.f5838b.Z(bArr, i2, i3);
            }
            throw new IOException("closed");
        }
    }
}
