package spi;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class de implements vr {
    public byte a;
    public final ul b;
    public final Inflater c;
    public final of d;
    public final CRC32 e;

    public de(@NotNull vr source) {
        Intrinsics.checkNotNullParameter(source, "source");
        ul ulVar = new ul(source);
        this.b = ulVar;
        Inflater inflater = new Inflater(true);
        this.c = inflater;
        this.d = new of((l2) ulVar, inflater);
        this.e = new CRC32();
    }

    public final void B(g2 g2Var, long j, long j2) {
        vo voVar = g2Var.a;
        while (true) {
            Intrinsics.checkNotNull(voVar);
            int i = voVar.c;
            int i2 = voVar.b;
            if (j < i - i2) {
                break;
            }
            j -= (long) (i - i2);
            voVar = voVar.f;
        }
        while (j2 > 0) {
            int i3 = (int) (((long) voVar.b) + j);
            int iMin = (int) Math.min(voVar.c - i3, j2);
            this.e.update(voVar.a, i3, iMin);
            j2 -= (long) iMin;
            voVar = voVar.f;
            Intrinsics.checkNotNull(voVar);
            j = 0;
        }
    }

    @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.d.close();
    }

    public final void e(String str, int i, int i2) throws IOException {
        if (i2 == i) {
            return;
        }
        String str2 = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}, 3));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
        throw new IOException(str2);
    }

    @Override // spi.vr
    public final long read(@NotNull g2 sink, long j) throws IOException {
        long j2;
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (!(j >= 0)) {
            throw new IllegalArgumentException(d.d("byteCount < 0: ", j).toString());
        }
        if (j == 0) {
            return 0L;
        }
        if (this.a == 0) {
            this.b.w(10L);
            byte bF = this.b.a.F(3L);
            boolean z = ((bF >> 1) & 1) == 1;
            if (z) {
                B(this.b.a, 0L, 10L);
            }
            ul ulVar = this.b;
            ulVar.w(2L);
            e("ID1ID2", 8075, ulVar.a.readShort());
            this.b.skip(8L);
            if (((bF >> 2) & 1) == 1) {
                this.b.w(2L);
                if (z) {
                    B(this.b.a, 0L, 2L);
                }
                long J = this.b.a.J();
                this.b.w(J);
                if (z) {
                    j2 = J;
                    B(this.b.a, 0L, J);
                } else {
                    j2 = J;
                }
                this.b.skip(j2);
            }
            if (((bF >> 3) & 1) == 1) {
                long jE = this.b.e((byte) 0, 0L, Long.MAX_VALUE);
                if (jE == -1) {
                    throw new EOFException();
                }
                if (z) {
                    B(this.b.a, 0L, jE + 1);
                }
                this.b.skip(jE + 1);
            }
            if (((bF >> 4) & 1) == 1) {
                long jE2 = this.b.e((byte) 0, 0L, Long.MAX_VALUE);
                if (jE2 == -1) {
                    throw new EOFException();
                }
                if (z) {
                    B(this.b.a, 0L, jE2 + 1);
                }
                this.b.skip(jE2 + 1);
            }
            if (z) {
                ul ulVar2 = this.b;
                ulVar2.w(2L);
                e("FHCRC", ulVar2.a.J(), (short) this.e.getValue());
                this.e.reset();
            }
            this.a = (byte) 1;
        }
        if (this.a == 1) {
            long j3 = sink.b;
            long j4 = this.d.read(sink, j);
            if (j4 != -1) {
                B(sink, j3, j4);
                return j4;
            }
            this.a = (byte) 2;
        }
        if (this.a == 2) {
            e("CRC", this.b.C(), (int) this.e.getValue());
            e("ISIZE", this.b.C(), (int) this.c.getBytesWritten());
            this.a = (byte) 3;
            if (!this.b.h()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    @Override // spi.vr
    @NotNull
    public final dt timeout() {
        return this.b.timeout();
    }
}
