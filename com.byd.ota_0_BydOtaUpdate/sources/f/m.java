package f;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import okio.ByteString;

/* JADX INFO: compiled from: RealBufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class m implements e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f3718c = new c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q f3719d;
    public boolean s;

    public m(q qVar) {
        Objects.requireNonNull(qVar, "source == null");
        this.f3719d = qVar;
    }

    @Override // f.e
    public long B(byte b2) throws IOException {
        return c(b2, 0L, Long.MAX_VALUE);
    }

    @Override // f.e
    public byte[] C(long j) throws IOException {
        u(j);
        return this.f3718c.C(j);
    }

    @Override // f.e
    public boolean D(long j, ByteString byteString) throws IOException {
        return d(j, byteString, 0, byteString.size());
    }

    @Override // f.e
    public long E() throws IOException {
        byte bM;
        u(1L);
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (!b(i3)) {
                break;
            }
            bM = this.f3718c.M(i2);
            if ((bM < 48 || bM > 57) && ((bM < 97 || bM > 102) && (bM < 65 || bM > 70))) {
                break;
            }
            i2 = i3;
        }
        if (i2 == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(bM)));
        }
        return this.f3718c.E();
    }

    @Override // f.e
    public InputStream F() {
        return new a();
    }

    @Override // f.e
    public String G() throws IOException {
        this.f3718c.k(this.f3719d);
        return this.f3718c.G();
    }

    @Override // f.e, f.d
    public c a() {
        return this.f3718c;
    }

    @Override // f.e
    public boolean b(long j) throws IOException {
        c cVar;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        do {
            cVar = this.f3718c;
            if (cVar.s >= j) {
                return true;
            }
        } while (this.f3719d.n(cVar, 8192L) != -1);
        return false;
    }

    public long c(byte b2, long j, long j2) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long jN = this.f3718c.N(b2, j, j2);
            if (jN != -1) {
                return jN;
            }
            c cVar = this.f3718c;
            long j3 = cVar.s;
            if (j3 >= j2 || this.f3719d.n(cVar, 8192L) == -1) {
                break;
            }
            j = Math.max(j, j3);
        }
        return -1L;
    }

    @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.s) {
            return;
        }
        this.s = true;
        this.f3719d.close();
        this.f3718c.H();
    }

    public boolean d(long j, ByteString byteString, int i2, int i3) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || i2 < 0 || i3 < 0 || byteString.size() - i2 < i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            long j2 = ((long) i4) + j;
            if (!b(1 + j2) || this.f3718c.M(j2) != byteString.getByte(i2 + i4)) {
                return false;
            }
        }
        return true;
    }

    @Override // f.q
    public r e() {
        return this.f3719d.e();
    }

    @Override // f.e
    public short j() throws IOException {
        u(2L);
        return this.f3718c.j();
    }

    @Override // f.q
    public long n(c cVar, long j) throws IOException {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        c cVar2 = this.f3718c;
        if (cVar2.s == 0 && this.f3719d.n(cVar2, 8192L) == -1) {
            return -1L;
        }
        return this.f3718c.n(cVar, Math.min(j, this.f3718c.s));
    }

    @Override // f.e
    public ByteString o(long j) throws IOException {
        u(j);
        return this.f3718c.o(j);
    }

    @Override // f.e
    public long p(p pVar) throws IOException {
        if (pVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j = 0;
        while (this.f3719d.n(this.f3718c, 8192L) != -1) {
            long J = this.f3718c.J();
            if (J > 0) {
                j += J;
                pVar.h(this.f3718c, J);
            }
        }
        if (this.f3718c.Y() <= 0) {
            return j;
        }
        long jY = j + this.f3718c.Y();
        c cVar = this.f3718c;
        pVar.h(cVar, cVar.Y());
        return jY;
    }

    @Override // f.e
    public byte readByte() throws IOException {
        u(1L);
        return this.f3718c.readByte();
    }

    @Override // f.e
    public void readFully(byte[] bArr) throws IOException {
        try {
            u(bArr.length);
            this.f3718c.readFully(bArr);
        } catch (EOFException e2) {
            int i2 = 0;
            while (true) {
                c cVar = this.f3718c;
                long j = cVar.s;
                if (j <= 0) {
                    throw e2;
                }
                int i3 = cVar.read(bArr, i2, (int) j);
                if (i3 == -1) {
                    throw new AssertionError();
                }
                i2 += i3;
            }
        }
    }

    @Override // f.e
    public int readInt() throws IOException {
        u(4L);
        return this.f3718c.readInt();
    }

    @Override // f.e
    public short readShort() throws IOException {
        u(2L);
        return this.f3718c.readShort();
    }

    public String s(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jC = c((byte) 10, 0L, j2);
        if (jC != -1) {
            return this.f3718c.W(jC);
        }
        if (j2 < Long.MAX_VALUE && b(j2) && this.f3718c.M(j2 - 1) == 13 && b(1 + j2) && this.f3718c.M(j2) == 10) {
            return this.f3718c.W(j2);
        }
        c cVar = new c();
        c cVar2 = this.f3718c;
        cVar2.K(cVar, 0L, Math.min(32L, cVar2.Y()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f3718c.Y(), j) + " content=" + cVar.R().hex() + (char) 8230);
    }

    @Override // f.e
    public void skip(long j) throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            c cVar = this.f3718c;
            if (cVar.s == 0 && this.f3719d.n(cVar, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.f3718c.Y());
            this.f3718c.skip(jMin);
            j -= jMin;
        }
    }

    @Override // f.e
    public String t() throws IOException {
        return s(Long.MAX_VALUE);
    }

    public String toString() {
        return "buffer(" + this.f3719d + ")";
    }

    @Override // f.e
    public void u(long j) throws IOException {
        if (!b(j)) {
            throw new EOFException();
        }
    }

    @Override // f.e
    public int x() throws IOException {
        u(4L);
        return this.f3718c.x();
    }

    @Override // f.e
    public boolean z() throws IOException {
        if (this.s) {
            throw new IllegalStateException("closed");
        }
        return this.f3718c.z() && this.f3719d.n(this.f3718c, 8192L) == -1;
    }

    /* JADX INFO: compiled from: RealBufferedSource.java */
    public class a extends InputStream {
        public a() {
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            m mVar = m.this;
            if (mVar.s) {
                throw new IOException("closed");
            }
            return (int) Math.min(mVar.f3718c.s, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            m.this.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            m mVar = m.this;
            if (mVar.s) {
                throw new IOException("closed");
            }
            c cVar = mVar.f3718c;
            if (cVar.s == 0 && mVar.f3719d.n(cVar, 8192L) == -1) {
                return -1;
            }
            return m.this.f3718c.readByte() & 255;
        }

        public String toString() {
            return m.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            if (!m.this.s) {
                s.b(bArr.length, i2, i3);
                m mVar = m.this;
                c cVar = mVar.f3718c;
                if (cVar.s == 0 && mVar.f3719d.n(cVar, 8192L) == -1) {
                    return -1;
                }
                return m.this.f3718c.read(bArr, i2, i3);
            }
            throw new IOException("closed");
        }
    }
}
