package p6;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: GzipSource.kt */
/* JADX INFO: loaded from: classes.dex */
public final class l implements a0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private byte f8877e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final u f8878g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Inflater f8879h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final m f8880i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final CRC32 f8881j;

    public l(a0 source) {
        kotlin.jvm.internal.l.e(source, "source");
        u uVar = new u(source);
        this.f8878g = uVar;
        Inflater inflater = new Inflater(true);
        this.f8879h = inflater;
        this.f8880i = new m(uVar, inflater);
        this.f8881j = new CRC32();
    }

    private final void A() throws IOException {
        m("CRC", this.f8878g.A(), (int) this.f8881j.getValue());
        m("ISIZE", this.f8878g.A(), (int) this.f8879h.getBytesWritten());
    }

    private final void G(e eVar, long j7, long j8) {
        v vVar = eVar.f8866e;
        kotlin.jvm.internal.l.c(vVar);
        while (true) {
            int i7 = vVar.f8904c;
            int i8 = vVar.f8903b;
            if (j7 < i7 - i8) {
                break;
            }
            j7 -= (long) (i7 - i8);
            vVar = vVar.f8907f;
            kotlin.jvm.internal.l.c(vVar);
        }
        while (j8 > 0) {
            int i9 = (int) (((long) vVar.f8903b) + j7);
            int iMin = (int) Math.min(vVar.f8904c - i9, j8);
            this.f8881j.update(vVar.f8902a, i9, iMin);
            j8 -= (long) iMin;
            vVar = vVar.f8907f;
            kotlin.jvm.internal.l.c(vVar);
            j7 = 0;
        }
    }

    private final void m(String str, int i7, int i8) throws IOException {
        if (i8 == i7) {
            return;
        }
        String str2 = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i8), Integer.valueOf(i7)}, 3));
        kotlin.jvm.internal.l.d(str2, "java.lang.String.format(this, *args)");
        throw new IOException(str2);
    }

    private final void z() throws IOException {
        this.f8878g.F(10L);
        byte bN = this.f8878g.f8897e.N(3L);
        boolean z6 = ((bN >> 1) & 1) == 1;
        if (z6) {
            G(this.f8878g.f8897e, 0L, 10L);
        }
        m("ID1ID2", 8075, this.f8878g.s());
        this.f8878g.a(8L);
        if (((bN >> 2) & 1) == 1) {
            this.f8878g.F(2L);
            if (z6) {
                G(this.f8878g.f8897e, 0L, 2L);
            }
            long jE0 = this.f8878g.f8897e.e0();
            this.f8878g.F(jE0);
            if (z6) {
                G(this.f8878g.f8897e, 0L, jE0);
            }
            this.f8878g.a(jE0);
        }
        if (((bN >> 3) & 1) == 1) {
            long jM = this.f8878g.m((byte) 0);
            if (jM == -1) {
                throw new EOFException();
            }
            if (z6) {
                G(this.f8878g.f8897e, 0L, jM + 1);
            }
            this.f8878g.a(jM + 1);
        }
        if (((bN >> 4) & 1) == 1) {
            long jM2 = this.f8878g.m((byte) 0);
            if (jM2 == -1) {
                throw new EOFException();
            }
            if (z6) {
                G(this.f8878g.f8897e, 0L, jM2 + 1);
            }
            this.f8878g.a(jM2 + 1);
        }
        if (z6) {
            m("FHCRC", this.f8878g.G(), (short) this.f8881j.getValue());
            this.f8881j.reset();
        }
    }

    @Override // p6.a0
    public b0 c() {
        return this.f8878g.c();
    }

    @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f8880i.close();
    }

    @Override // p6.a0
    public long i(e sink, long j7) throws IOException {
        kotlin.jvm.internal.l.e(sink, "sink");
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        if (j7 == 0) {
            return 0L;
        }
        if (this.f8877e == 0) {
            z();
            this.f8877e = (byte) 1;
        }
        if (this.f8877e == 1) {
            long jJ0 = sink.j0();
            long jI = this.f8880i.i(sink, j7);
            if (jI != -1) {
                G(sink, jJ0, jI);
                return jI;
            }
            this.f8877e = (byte) 2;
        }
        if (this.f8877e == 2) {
            A();
            this.f8877e = (byte) 3;
            if (!this.f8878g.J()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }
}
