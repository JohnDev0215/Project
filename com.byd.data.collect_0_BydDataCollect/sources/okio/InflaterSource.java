package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class InflaterSource implements Source {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final BufferedSource f5804b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Inflater f5805c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f5806d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f5807e;

    InflaterSource(BufferedSource bufferedSource, Inflater inflater) {
        if (bufferedSource == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f5804b = bufferedSource;
        this.f5805c = inflater;
    }

    private void d() {
        int i2 = this.f5806d;
        if (i2 == 0) {
            return;
        }
        int remaining = i2 - this.f5805c.getRemaining();
        this.f5806d -= remaining;
        this.f5804b.s(remaining);
    }

    public final boolean a() {
        if (!this.f5805c.needsInput()) {
            return false;
        }
        d();
        if (this.f5805c.getRemaining() != 0) {
            throw new IllegalStateException("?");
        }
        if (this.f5804b.I()) {
            return true;
        }
        Segment segment = this.f5804b.b().f5770c;
        int i2 = segment.f5844c;
        int i3 = segment.f5843b;
        int i4 = i2 - i3;
        this.f5806d = i4;
        this.f5805c.setInput(segment.f5842a, i3, i4);
        return false;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f5807e) {
            return;
        }
        this.f5805c.end();
        this.f5807e = true;
        this.f5804b.close();
    }

    @Override // okio.Source
    public Timeout e() {
        return this.f5804b.e();
    }

    @Override // okio.Source
    public long p(Buffer buffer, long j) {
        boolean zA;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f5807e) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return 0L;
        }
        do {
            zA = a();
            try {
                Segment segmentJ0 = buffer.j0(1);
                int iInflate = this.f5805c.inflate(segmentJ0.f5842a, segmentJ0.f5844c, (int) Math.min(j, 8192 - segmentJ0.f5844c));
                if (iInflate > 0) {
                    segmentJ0.f5844c += iInflate;
                    long j2 = iInflate;
                    buffer.f5771d += j2;
                    return j2;
                }
                if (!this.f5805c.finished() && !this.f5805c.needsDictionary()) {
                }
                d();
                if (segmentJ0.f5843b != segmentJ0.f5844c) {
                    return -1L;
                }
                buffer.f5770c = segmentJ0.b();
                SegmentPool.a(segmentJ0);
                return -1L;
            } catch (DataFormatException e2) {
                throw new IOException(e2);
            }
        } while (!zA);
        throw new EOFException("source exhausted prematurely");
    }
}
