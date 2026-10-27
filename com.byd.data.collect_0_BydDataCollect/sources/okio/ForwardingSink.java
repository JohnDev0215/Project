package okio;

/* JADX INFO: loaded from: classes.dex */
public abstract class ForwardingSink implements Sink {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Sink f5787b;

    public ForwardingSink(Sink sink) {
        if (sink == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f5787b = sink;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5787b.close();
    }

    @Override // okio.Sink
    public Timeout e() {
        return this.f5787b.e();
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        this.f5787b.flush();
    }

    @Override // okio.Sink
    public void h(Buffer buffer, long j) {
        this.f5787b.h(buffer, j);
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f5787b.toString() + ")";
    }
}
