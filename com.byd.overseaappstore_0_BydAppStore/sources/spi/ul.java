package spi;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class ul implements l2 {

    @JvmField
    @NotNull
    public final g2 a;

    @JvmField
    public boolean b;

    @JvmField
    @NotNull
    public final vr c;

    public ul(@NotNull vr source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.c = source;
        this.a = new g2();
    }

    @Override // spi.l2
    @NotNull
    public final InputStream A() {
        return new a();
    }

    public final long B() throws EOFException {
        byte bF;
        w(1L);
        long j = 0;
        while (true) {
            long j2 = j + 1;
            if (!request(j2)) {
                break;
            }
            bF = this.a.F(j);
            if ((bF < ((byte) 48) || bF > ((byte) 57)) && !(j == 0 && bF == ((byte) 45))) {
                break;
            }
            j = j2;
        }
        if (j == 0) {
            StringBuilder sbK = e.k("Expected leading [0-9] or '-' character but was 0x");
            String string = Integer.toString(bF, CharsKt.checkRadix(CharsKt.checkRadix(16)));
            Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
            sbK.append(string);
            throw new NumberFormatException(sbK.toString());
        }
        return this.a.I();
    }

    public final int C() throws EOFException {
        w(4L);
        int i = this.a.readInt();
        return ((i & 255) << 24) | (((-16777216) & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8);
    }

    @Override // spi.l2, spi.k2
    @NotNull
    public final g2 a() {
        return this.a;
    }

    @Override // spi.l2
    @NotNull
    public final ByteString b(long j) throws EOFException {
        w(j);
        return this.a.b(j);
    }

    @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.b) {
            return;
        }
        this.b = true;
        this.c.close();
        this.a.B();
    }

    public final long e(byte b, long j, long j2) {
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        long jMax = 0;
        if (!(j2 >= 0)) {
            throw new IllegalArgumentException(("fromIndex=0 toIndex=" + j2).toString());
        }
        while (jMax < j2) {
            long jG = this.a.G(b, jMax, j2);
            if (jG != -1) {
                return jG;
            }
            g2 g2Var = this.a;
            long j3 = g2Var.b;
            if (j3 >= j2 || this.c.read(g2Var, 8192) == -1) {
                return -1L;
            }
            jMax = Math.max(jMax, j3);
        }
        return -1L;
    }

    @Override // spi.l2
    @NotNull
    public final byte[] f() throws IOException {
        this.a.x(this.c);
        return this.a.f();
    }

    @Override // spi.l2
    public final long g(@NotNull rp sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long j = 0;
        while (this.c.read(this.a, 8192) != -1) {
            long jD = this.a.D();
            if (jD > 0) {
                j += jD;
                ((g2) sink).p(this.a, jD);
            }
        }
        g2 g2Var = this.a;
        long j2 = g2Var.b;
        if (j2 <= 0) {
            return j;
        }
        long j3 = j + j2;
        ((g2) sink).p(g2Var, j2);
        return j3;
    }

    @Override // spi.l2
    public final boolean h() {
        if (!this.b) {
            return this.a.h() && this.c.read(this.a, (long) 8192) == -1;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.b;
    }

    @Override // spi.l2
    @NotNull
    public final String k(long j) throws EOFException {
        if (!(j >= 0)) {
            throw new IllegalArgumentException(d.d("limit < 0: ", j).toString());
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        byte b = (byte) 10;
        long jE = e(b, 0L, j2);
        if (jE != -1) {
            return i2.a(this.a, jE);
        }
        if (j2 < Long.MAX_VALUE && request(j2) && this.a.F(j2 - 1) == ((byte) 13) && request(1 + j2) && this.a.F(j2) == b) {
            return i2.a(this.a, j2);
        }
        g2 g2Var = new g2();
        g2 g2Var2 = this.a;
        g2Var2.E(g2Var, 0L, Math.min(32, g2Var2.b));
        StringBuilder sbK = e.k("\\n not found: limit=");
        sbK.append(Math.min(this.a.b, j));
        sbK.append(" content=");
        sbK.append(g2Var.q().hex());
        sbK.append("…");
        throw new EOFException(sbK.toString());
    }

    @Override // spi.l2
    @NotNull
    public final String m(@NotNull Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(charset, "charset");
        this.a.x(this.c);
        return this.a.m(charset);
    }

    @Override // spi.l2
    @NotNull
    public final ByteString q() throws IOException {
        this.a.x(this.c);
        return this.a.q();
    }

    @Override // spi.l2
    @NotNull
    public final String r() {
        return k(Long.MAX_VALUE);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(@NotNull ByteBuffer sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        g2 g2Var = this.a;
        if (g2Var.b == 0 && this.c.read(g2Var, 8192) == -1) {
            return -1;
        }
        return this.a.read(sink);
    }

    @Override // spi.l2
    public final byte readByte() {
        w(1L);
        return this.a.readByte();
    }

    @Override // spi.l2
    public final int readInt() throws EOFException {
        w(4L);
        return this.a.readInt();
    }

    @Override // spi.l2
    public final short readShort() throws EOFException {
        w(2L);
        return this.a.readShort();
    }

    @Override // spi.l2
    public final boolean request(long j) {
        g2 g2Var;
        if (!(j >= 0)) {
            throw new IllegalArgumentException(d.d("byteCount < 0: ", j).toString());
        }
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        do {
            g2Var = this.a;
            if (g2Var.b >= j) {
                return true;
            }
        } while (this.c.read(g2Var, 8192) != -1);
        return false;
    }

    @Override // spi.l2
    @NotNull
    public final byte[] s(long j) throws EOFException {
        w(j);
        return this.a.s(j);
    }

    @Override // spi.l2
    public final void skip(long j) throws EOFException {
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        while (j > 0) {
            g2 g2Var = this.a;
            if (g2Var.b == 0 && this.c.read(g2Var, 8192) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.a.b);
            this.a.skip(jMin);
            j -= jMin;
        }
    }

    @Override // spi.vr
    @NotNull
    public final dt timeout() {
        return this.c.timeout();
    }

    @NotNull
    public final String toString() {
        StringBuilder sbK = e.k("buffer(");
        sbK.append(this.c);
        sbK.append(')');
        return sbK.toString();
    }

    @Override // spi.l2
    public final int v(@NotNull kk options) throws EOFException {
        Intrinsics.checkNotNullParameter(options, "options");
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        while (true) {
            int iB = i2.b(this.a, options, true);
            if (iB != -2) {
                if (iB != -1) {
                    this.a.skip(options.a[iB].size());
                    return iB;
                }
            } else if (this.c.read(this.a, 8192) == -1) {
                break;
            }
        }
        return -1;
    }

    @Override // spi.l2
    public final void w(long j) throws EOFException {
        if (!request(j)) {
            throw new EOFException();
        }
    }

    @Override // spi.l2
    public final long z() throws EOFException {
        byte bF;
        w(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request(i2)) {
                break;
            }
            bF = this.a.F(i);
            if ((bF < ((byte) 48) || bF > ((byte) 57)) && ((bF < ((byte) 97) || bF > ((byte) 102)) && (bF < ((byte) 65) || bF > ((byte) 70)))) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            StringBuilder sbK = e.k("Expected leading [0-9a-fA-F] character but was 0x");
            String string = Integer.toString(bF, CharsKt.checkRadix(CharsKt.checkRadix(16)));
            Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
            sbK.append(string);
            throw new NumberFormatException(sbK.toString());
        }
        return this.a.z();
    }

    public static final class a extends InputStream {
        public a() {
        }

        @Override // java.io.InputStream
        public final int available() throws IOException {
            ul ulVar = ul.this;
            if (ulVar.b) {
                throw new IOException("closed");
            }
            return (int) Math.min(ulVar.a.b, Integer.MAX_VALUE);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            ul.this.close();
        }

        @Override // java.io.InputStream
        public final int read() throws IOException {
            ul ulVar = ul.this;
            if (ulVar.b) {
                throw new IOException("closed");
            }
            g2 g2Var = ulVar.a;
            if (g2Var.b == 0 && ulVar.c.read(g2Var, 8192) == -1) {
                return -1;
            }
            return ul.this.a.readByte() & 255;
        }

        @NotNull
        public final String toString() {
            return ul.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public final int read(@NotNull byte[] data, int i, int i2) throws IOException {
            Intrinsics.checkNotNullParameter(data, "data");
            if (ul.this.b) {
                throw new IOException("closed");
            }
            c.d(data.length, i, i2);
            ul ulVar = ul.this;
            g2 g2Var = ulVar.a;
            if (g2Var.b == 0 && ulVar.c.read(g2Var, 8192) == -1) {
                return -1;
            }
            return ul.this.a.read(data, i, i2);
        }
    }

    @Override // spi.vr
    public final long read(@NotNull g2 sink, long j) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (!(j >= 0)) {
            throw new IllegalArgumentException(d.d("byteCount < 0: ", j).toString());
        }
        if (!(!this.b)) {
            throw new IllegalStateException("closed".toString());
        }
        g2 g2Var = this.a;
        if (g2Var.b == 0 && this.c.read(g2Var, 8192) == -1) {
            return -1L;
        }
        return this.a.read(sink, Math.min(j, this.a.b));
    }
}
