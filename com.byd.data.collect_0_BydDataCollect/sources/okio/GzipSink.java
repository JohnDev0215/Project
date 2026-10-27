package okio;

import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* JADX INFO: loaded from: classes.dex */
public final class GzipSink implements Sink {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final BufferedSink f5790b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Deflater f5791c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final DeflaterSink f5792d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f5793e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final CRC32 f5794f;

    private void a(Buffer buffer, long j) {
        Segment segment = buffer.f5770c;
        while (j > 0) {
            int iMin = (int) Math.min(j, segment.f5844c - segment.f5843b);
            this.f5794f.update(segment.f5842a, segment.f5843b, iMin);
            j -= (long) iMin;
            segment = segment.f5847f;
        }
    }

    private void d() {
        this.f5790b.H((int) this.f5794f.getValue());
        this.f5790b.H((int) this.f5791c.getBytesRead());
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f5793e) {
            return;
        }
        Throwable th = null;
        try {
            this.f5792d.d();
            d();
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.f5791c.end();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        try {
            this.f5790b.close();
        } catch (Throwable th4) {
            if (th == null) {
                th = th4;
            }
        }
        this.f5793e = true;
        if (th != null) {
            Util.e(th);
        }
    }

    @Override // okio.Sink
    public Timeout e() {
        return this.f5790b.e();
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        this.f5792d.flush();
    }

    @Override // okio.Sink
    public void h(Buffer buffer, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return;
        }
        a(buffer, j);
        this.f5792d.h(buffer, j);
    }
}
