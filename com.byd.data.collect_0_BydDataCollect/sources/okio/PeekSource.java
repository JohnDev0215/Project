package okio;

/* JADX INFO: loaded from: classes.dex */
final class PeekSource implements Source {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final BufferedSource f5815b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Buffer f5816c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Segment f5817d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f5818e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f5819f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f5820g;

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5819f = true;
    }

    @Override // okio.Source
    public Timeout e() {
        return this.f5815b.e();
    }

    @Override // okio.Source
    public long p(Buffer buffer, long j) {
        Segment segment;
        Segment segment2;
        if (this.f5819f) {
            throw new IllegalStateException("closed");
        }
        Segment segment3 = this.f5817d;
        if (segment3 != null && (segment3 != (segment2 = this.f5816c.f5770c) || this.f5818e != segment2.f5843b)) {
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        this.f5815b.c(this.f5820g + j);
        if (this.f5817d == null && (segment = this.f5816c.f5770c) != null) {
            this.f5817d = segment;
            this.f5818e = segment.f5843b;
        }
        long jMin = Math.min(j, this.f5816c.f5771d - this.f5820g);
        if (jMin <= 0) {
            return -1L;
        }
        this.f5816c.U(buffer, this.f5820g, jMin);
        this.f5820g += jMin;
        return jMin;
    }
}
