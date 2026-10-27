package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class GzipSource implements Source {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final BufferedSource f5796c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Inflater f5797d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final InflaterSource f5798e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f5795b = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final CRC32 f5799f = new CRC32();

    public GzipSource(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        Inflater inflater = new Inflater(true);
        this.f5797d = inflater;
        BufferedSource bufferedSourceC = Okio.c(source);
        this.f5796c = bufferedSourceC;
        this.f5798e = new InflaterSource(bufferedSourceC, inflater);
    }

    private void a(String str, int i2, int i3) throws IOException {
        if (i3 != i2) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i3), Integer.valueOf(i2)));
        }
    }

    private void d() throws IOException {
        this.f5796c.B(10L);
        byte bW = this.f5796c.b().W(3L);
        boolean z = ((bW >> 1) & 1) == 1;
        if (z) {
            y(this.f5796c.b(), 0L, 10L);
        }
        a("ID1ID2", 8075, this.f5796c.u());
        this.f5796c.s(8L);
        if (((bW >> 2) & 1) == 1) {
            this.f5796c.B(2L);
            if (z) {
                y(this.f5796c.b(), 0L, 2L);
            }
            long jL = this.f5796c.b().l();
            this.f5796c.B(jL);
            if (z) {
                y(this.f5796c.b(), 0L, jL);
            }
            this.f5796c.s(jL);
        }
        if (((bW >> 3) & 1) == 1) {
            long jK = this.f5796c.K((byte) 0);
            if (jK == -1) {
                throw new EOFException();
            }
            if (z) {
                y(this.f5796c.b(), 0L, jK + 1);
            }
            this.f5796c.s(jK + 1);
        }
        if (((bW >> 4) & 1) == 1) {
            long jK2 = this.f5796c.K((byte) 0);
            if (jK2 == -1) {
                throw new EOFException();
            }
            if (z) {
                y(this.f5796c.b(), 0L, jK2 + 1);
            }
            this.f5796c.s(jK2 + 1);
        }
        if (z) {
            a("FHCRC", this.f5796c.l(), (short) this.f5799f.getValue());
            this.f5799f.reset();
        }
    }

    private void j() throws IOException {
        a("CRC", this.f5796c.E(), (int) this.f5799f.getValue());
        a("ISIZE", this.f5796c.E(), (int) this.f5797d.getBytesWritten());
    }

    private void y(Buffer buffer, long j, long j2) {
        Segment segment = buffer.f5770c;
        while (true) {
            int i2 = segment.f5844c;
            int i3 = segment.f5843b;
            if (j < i2 - i3) {
                break;
            }
            j -= (long) (i2 - i3);
            segment = segment.f5847f;
        }
        while (j2 > 0) {
            int i4 = (int) (((long) segment.f5843b) + j);
            int iMin = (int) Math.min(segment.f5844c - i4, j2);
            this.f5799f.update(segment.f5842a, i4, iMin);
            j2 -= (long) iMin;
            segment = segment.f5847f;
            j = 0;
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5798e.close();
    }

    @Override // okio.Source
    public Timeout e() {
        return this.f5796c.e();
    }

    @Override // okio.Source
    public long p(Buffer buffer, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return 0L;
        }
        if (this.f5795b == 0) {
            d();
            this.f5795b = 1;
        }
        if (this.f5795b == 1) {
            long j2 = buffer.f5771d;
            long jP = this.f5798e.p(buffer, j);
            if (jP != -1) {
                y(buffer, j2, jP);
                return jP;
            }
            this.f5795b = 2;
        }
        if (this.f5795b == 2) {
            j();
            this.f5795b = 3;
            if (!this.f5796c.I()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }
}
