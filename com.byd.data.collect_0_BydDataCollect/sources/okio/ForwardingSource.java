package okio;

/* JADX INFO: loaded from: classes.dex */
public abstract class ForwardingSource implements Source {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Source f5788b;

    public ForwardingSource(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f5788b = source;
    }

    public final Source a() {
        return this.f5788b;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5788b.close();
    }

    @Override // okio.Source
    public Timeout e() {
        return this.f5788b.e();
    }

    @Override // okio.Source
    public long p(Buffer buffer, long j) {
        return this.f5788b.p(buffer, j);
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f5788b.toString() + ")";
    }
}
