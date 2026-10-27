package p6;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: RealBufferedSource.kt */
/* JADX INFO: loaded from: classes.dex */
public final class u implements g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f8897e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f8898g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a0 f8899h;

    public u(a0 source) {
        kotlin.jvm.internal.l.e(source, "source");
        this.f8899h = source;
        this.f8897e = new e();
    }

    public int A() throws EOFException {
        F(4L);
        return this.f8897e.d0();
    }

    @Override // p6.g
    public String D() {
        return p(Long.MAX_VALUE);
    }

    @Override // p6.g
    public void F(long j7) throws EOFException {
        if (!I(j7)) {
            throw new EOFException();
        }
    }

    public short G() throws EOFException {
        F(2L);
        return this.f8897e.e0();
    }

    public boolean I(long j7) {
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        if (!(!this.f8898g)) {
            throw new IllegalStateException("closed".toString());
        }
        while (this.f8897e.j0() < j7) {
            if (this.f8899h.i(this.f8897e, 8192) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // p6.g
    public boolean J() {
        if (!this.f8898g) {
            return this.f8897e.J() && this.f8899h.i(this.f8897e, (long) 8192) == -1;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // p6.g
    public byte[] S(long j7) throws EOFException {
        F(j7);
        return this.f8897e.S(j7);
    }

    @Override // p6.g
    public long T() throws EOFException {
        byte bN;
        F(1L);
        int i7 = 0;
        while (true) {
            int i8 = i7 + 1;
            if (!I(i8)) {
                break;
            }
            bN = this.f8897e.N(i7);
            if ((bN < ((byte) 48) || bN > ((byte) 57)) && ((bN < ((byte) 97) || bN > ((byte) 102)) && (bN < ((byte) 65) || bN > ((byte) 70)))) {
                break;
            }
            i7 = i8;
        }
        if (i7 == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected leading [0-9a-fA-F] character but was 0x");
            String string = Integer.toString(bN, y5.b.a(y5.b.a(16)));
            kotlin.jvm.internal.l.d(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
            sb.append(string);
            throw new NumberFormatException(sb.toString());
        }
        return this.f8897e.T();
    }

    @Override // p6.g
    public InputStream X() {
        return new a();
    }

    @Override // p6.g
    public byte Y() throws EOFException {
        F(1L);
        return this.f8897e.Y();
    }

    @Override // p6.g
    public void a(long j7) throws EOFException {
        if (!(!this.f8898g)) {
            throw new IllegalStateException("closed".toString());
        }
        while (j7 > 0) {
            if (this.f8897e.j0() == 0 && this.f8899h.i(this.f8897e, 8192) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j7, this.f8897e.j0());
            this.f8897e.a(jMin);
            j7 -= jMin;
        }
    }

    @Override // p6.g, p6.f
    public e b() {
        return this.f8897e;
    }

    @Override // p6.a0
    public b0 c() {
        return this.f8899h.c();
    }

    @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws EOFException {
        if (this.f8898g) {
            return;
        }
        this.f8898g = true;
        this.f8899h.close();
        this.f8897e.z();
    }

    @Override // p6.a0
    public long i(e sink, long j7) {
        kotlin.jvm.internal.l.e(sink, "sink");
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        if (!(!this.f8898g)) {
            throw new IllegalStateException("closed".toString());
        }
        if (this.f8897e.j0() == 0 && this.f8899h.i(this.f8897e, 8192) == -1) {
            return -1L;
        }
        return this.f8897e.i(sink, Math.min(j7, this.f8897e.j0()));
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f8898g;
    }

    public long m(byte b7) {
        return z(b7, 0L, Long.MAX_VALUE);
    }

    @Override // p6.g
    public h n(long j7) throws EOFException {
        F(j7);
        return this.f8897e.n(j7);
    }

    @Override // p6.g
    public String p(long j7) throws EOFException {
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("limit < 0: " + j7).toString());
        }
        long j8 = j7 == Long.MAX_VALUE ? Long.MAX_VALUE : j7 + 1;
        byte b7 = (byte) 10;
        long jZ = z(b7, 0L, j8);
        if (jZ != -1) {
            return q6.a.b(this.f8897e, jZ);
        }
        if (j8 < Long.MAX_VALUE && I(j8) && this.f8897e.N(j8 - 1) == ((byte) 13) && I(1 + j8) && this.f8897e.N(j8) == b7) {
            return q6.a.b(this.f8897e, j8);
        }
        e eVar = new e();
        e eVar2 = this.f8897e;
        eVar2.K(eVar, 0L, Math.min(32, eVar2.j0()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f8897e.j0(), j7) + " content=" + eVar.b0().i() + "…");
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer sink) {
        kotlin.jvm.internal.l.e(sink, "sink");
        if (this.f8897e.j0() == 0 && this.f8899h.i(this.f8897e, 8192) == -1) {
            return -1;
        }
        return this.f8897e.read(sink);
    }

    @Override // p6.g
    public short s() throws EOFException {
        F(2L);
        return this.f8897e.s();
    }

    public String toString() {
        return "buffer(" + this.f8899h + ')';
    }

    @Override // p6.g
    public int x() throws EOFException {
        F(4L);
        return this.f8897e.x();
    }

    public long z(byte b7, long j7, long j8) {
        if (!(!this.f8898g)) {
            throw new IllegalStateException("closed".toString());
        }
        if (!(0 <= j7 && j8 >= j7)) {
            throw new IllegalArgumentException(("fromIndex=" + j7 + " toIndex=" + j8).toString());
        }
        while (j7 < j8) {
            long jP = this.f8897e.P(b7, j7, j8);
            if (jP != -1) {
                return jP;
            }
            long jJ0 = this.f8897e.j0();
            if (jJ0 >= j8 || this.f8899h.i(this.f8897e, 8192) == -1) {
                return -1L;
            }
            j7 = Math.max(j7, jJ0);
        }
        return -1L;
    }

    /* JADX INFO: compiled from: RealBufferedSource.kt */
    public static final class a extends InputStream {
        a() {
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            u uVar = u.this;
            if (uVar.f8898g) {
                throw new IOException("closed");
            }
            return (int) Math.min(uVar.f8897e.j0(), Integer.MAX_VALUE);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws EOFException {
            u.this.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            u uVar = u.this;
            if (uVar.f8898g) {
                throw new IOException("closed");
            }
            if (uVar.f8897e.j0() == 0) {
                u uVar2 = u.this;
                if (uVar2.f8899h.i(uVar2.f8897e, 8192) == -1) {
                    return -1;
                }
            }
            return u.this.f8897e.Y() & 255;
        }

        public String toString() {
            return u.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] data, int i7, int i8) throws IOException {
            kotlin.jvm.internal.l.e(data, "data");
            if (!u.this.f8898g) {
                c.b(data.length, i7, i8);
                if (u.this.f8897e.j0() == 0) {
                    u uVar = u.this;
                    if (uVar.f8899h.i(uVar.f8897e, 8192) == -1) {
                        return -1;
                    }
                }
                return u.this.f8897e.Z(data, i7, i8);
            }
            throw new IOException("closed");
        }
    }
}
