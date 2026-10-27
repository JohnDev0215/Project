package okio;

import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* JADX INFO: loaded from: classes.dex */
public final class DeflaterSink implements Sink {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final BufferedSink f5784b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Deflater f5785c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f5786d;

    @IgnoreJRERequirement
    private void a(boolean z) {
        Segment segmentJ0;
        int iDeflate;
        Buffer bufferB = this.f5784b.b();
        while (true) {
            segmentJ0 = bufferB.j0(1);
            if (z) {
                Deflater deflater = this.f5785c;
                byte[] bArr = segmentJ0.f5842a;
                int i2 = segmentJ0.f5844c;
                iDeflate = deflater.deflate(bArr, i2, 8192 - i2, 2);
            } else {
                Deflater deflater2 = this.f5785c;
                byte[] bArr2 = segmentJ0.f5842a;
                int i3 = segmentJ0.f5844c;
                iDeflate = deflater2.deflate(bArr2, i3, 8192 - i3);
            }
            if (iDeflate > 0) {
                segmentJ0.f5844c += iDeflate;
                bufferB.f5771d += (long) iDeflate;
                this.f5784b.n();
            } else if (this.f5785c.needsInput()) {
                break;
            }
        }
        if (segmentJ0.f5843b == segmentJ0.f5844c) {
            bufferB.f5770c = segmentJ0.b();
            SegmentPool.a(segmentJ0);
        }
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f5786d) {
            return;
        }
        Throwable th = null;
        try {
            d();
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.f5785c.end();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        try {
            this.f5784b.close();
        } catch (Throwable th4) {
            if (th == null) {
                th = th4;
            }
        }
        this.f5786d = true;
        if (th != null) {
            Util.e(th);
        }
    }

    void d() {
        this.f5785c.finish();
        a(false);
    }

    @Override // okio.Sink
    public Timeout e() {
        return this.f5784b.e();
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        a(true);
        this.f5784b.flush();
    }

    @Override // okio.Sink
    public void h(Buffer buffer, long j) {
        Util.b(buffer.f5771d, 0L, j);
        while (j > 0) {
            Segment segment = buffer.f5770c;
            int iMin = (int) Math.min(j, segment.f5844c - segment.f5843b);
            this.f5785c.setInput(segment.f5842a, segment.f5843b, iMin);
            a(false);
            long j2 = iMin;
            buffer.f5771d -= j2;
            int i2 = segment.f5843b + iMin;
            segment.f5843b = i2;
            if (i2 == segment.f5844c) {
                buffer.f5770c = segment.b();
                SegmentPool.a(segment);
            }
            j -= j2;
        }
    }

    public String toString() {
        return "DeflaterSink(" + this.f5784b + ")";
    }
}
