package g;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
import okio.ByteString;

/* JADX INFO: compiled from: RealBufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class l implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f5492b = new c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final p f5493c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5494d;

    public l(p pVar) {
        Objects.requireNonNull(pVar, "source == null");
        this.f5493c = pVar;
    }

    @Override // g.e
    public int B() throws IOException {
        y(4L);
        return this.f5492b.B();
    }

    @Override // g.e
    public boolean D() throws IOException {
        if (this.f5494d) {
            throw new IllegalStateException("closed");
        }
        return this.f5492b.D() && this.f5493c.m(this.f5492b, 8192L) == -1;
    }

    @Override // g.e
    public long F(byte b2) throws IOException {
        return w(b2, 0L, Long.MAX_VALUE);
    }

    @Override // g.e
    public byte[] G(long j) throws IOException {
        y(j);
        return this.f5492b.G(j);
    }

    @Override // g.e
    public boolean H(long j, ByteString byteString) throws IOException {
        return z(j, byteString, 0, byteString.size());
    }

    @Override // g.e
    public long J() throws IOException {
        byte bT;
        y(1L);
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (!P(i3)) {
                break;
            }
            bT = this.f5492b.T(i2);
            if ((bT < 48 || bT > 57) && ((bT < 97 || bT > 102) && (bT < 65 || bT > 70))) {
                break;
            }
            i2 = i3;
        }
        if (i2 == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(bT)));
        }
        return this.f5492b.J();
    }

    @Override // g.e
    public String L(Charset charset) throws IOException {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.f5492b.j(this.f5493c);
        return this.f5492b.L(charset);
    }

    @Override // g.e
    public InputStream M() {
        return new a();
    }

    @Override // g.e
    public byte N() throws IOException {
        y(1L);
        return this.f5492b.N();
    }

    public String O(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jW = w((byte) 10, 0L, j2);
        if (jW != -1) {
            return this.f5492b.d0(jW);
        }
        if (j2 < Long.MAX_VALUE && P(j2) && this.f5492b.T(j2 - 1) == 13 && P(1 + j2) && this.f5492b.T(j2) == 10) {
            return this.f5492b.d0(j2);
        }
        c cVar = new c();
        c cVar2 = this.f5492b;
        cVar2.R(cVar, 0L, Math.min(32L, cVar2.f0()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f5492b.f0(), j) + " content=" + cVar.Y().hex() + (char) 8230);
    }

    public boolean P(long j) throws IOException {
        c cVar;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f5494d) {
            throw new IllegalStateException("closed");
        }
        do {
            cVar = this.f5492b;
            if (cVar.f5471d >= j) {
                return true;
            }
        } while (this.f5493c.m(cVar, 8192L) != -1);
        return false;
    }

    @Override // g.e, g.d
    public c a() {
        return this.f5492b;
    }

    @Override // g.p
    public q b() {
        return this.f5493c.b();
    }

    @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f5494d) {
            return;
        }
        this.f5494d = true;
        this.f5493c.close();
        this.f5492b.O();
    }

    @Override // g.e
    public short i() throws IOException {
        y(2L);
        return this.f5492b.i();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f5494d;
    }

    @Override // g.p
    public long m(c cVar, long j) throws IOException {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f5494d) {
            throw new IllegalStateException("closed");
        }
        c cVar2 = this.f5492b;
        if (cVar2.f5471d == 0 && this.f5493c.m(cVar2, 8192L) == -1) {
            return -1L;
        }
        return this.f5492b.m(cVar, Math.min(j, this.f5492b.f5471d));
    }

    @Override // g.e
    public ByteString n(long j) throws IOException {
        y(j);
        return this.f5492b.n(j);
    }

    @Override // g.e
    public void o(long j) throws IOException {
        if (this.f5494d) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            c cVar = this.f5492b;
            if (cVar.f5471d == 0 && this.f5493c.m(cVar, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.f5492b.f0());
            this.f5492b.o(jMin);
            j -= jMin;
        }
    }

    @Override // g.e
    public short p() throws IOException {
        y(2L);
        return this.f5492b.p();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        c cVar = this.f5492b;
        if (cVar.f5471d == 0 && this.f5493c.m(cVar, 8192L) == -1) {
            return -1;
        }
        return this.f5492b.read(byteBuffer);
    }

    @Override // g.e
    public int t() throws IOException {
        y(4L);
        return this.f5492b.t();
    }

    public String toString() {
        return "buffer(" + this.f5493c + ")";
    }

    public long w(byte b2, long j, long j2) throws IOException {
        if (this.f5494d) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long jU = this.f5492b.U(b2, j, j2);
            if (jU != -1) {
                return jU;
            }
            c cVar = this.f5492b;
            long j3 = cVar.f5471d;
            if (j3 >= j2 || this.f5493c.m(cVar, 8192L) == -1) {
                break;
            }
            j = Math.max(j, j3);
        }
        return -1L;
    }

    @Override // g.e
    public String x() throws IOException {
        return O(Long.MAX_VALUE);
    }

    @Override // g.e
    public void y(long j) throws IOException {
        if (!P(j)) {
            throw new EOFException();
        }
    }

    public boolean z(long j, ByteString byteString, int i2, int i3) throws IOException {
        if (this.f5494d) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || i2 < 0 || i3 < 0 || byteString.size() - i2 < i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            long j2 = ((long) i4) + j;
            if (!P(1 + j2) || this.f5492b.T(j2) != byteString.getByte(i2 + i4)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: compiled from: RealBufferedSource.java */
    public class a extends InputStream {
        public a() {
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            l lVar = l.this;
            if (lVar.f5494d) {
                throw new IOException("closed");
            }
            return (int) Math.min(lVar.f5492b.f5471d, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            l.this.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            l lVar = l.this;
            if (lVar.f5494d) {
                throw new IOException("closed");
            }
            c cVar = lVar.f5492b;
            if (cVar.f5471d == 0 && lVar.f5493c.m(cVar, 8192L) == -1) {
                return -1;
            }
            return l.this.f5492b.N() & 255;
        }

        public String toString() {
            return l.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            if (!l.this.f5494d) {
                r.b(bArr.length, i2, i3);
                l lVar = l.this;
                c cVar = lVar.f5492b;
                if (cVar.f5471d == 0 && lVar.f5493c.m(cVar, 8192L) == -1) {
                    return -1;
                }
                return l.this.f5492b.W(bArr, i2, i3);
            }
            throw new IOException("closed");
        }
    }
}
