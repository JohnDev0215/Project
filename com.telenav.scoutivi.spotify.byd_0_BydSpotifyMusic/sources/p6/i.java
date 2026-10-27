package p6;

/* JADX INFO: compiled from: ForwardingSink.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class i implements y {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final y f8874e;

    public i(y delegate) {
        kotlin.jvm.internal.l.e(delegate, "delegate");
        this.f8874e = delegate;
    }

    @Override // p6.y
    public b0 c() {
        return this.f8874e.c();
    }

    @Override // p6.y, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f8874e.close();
    }

    @Override // p6.y, java.io.Flushable
    public void flush() {
        this.f8874e.flush();
    }

    @Override // p6.y
    public void l(e source, long j7) {
        kotlin.jvm.internal.l.e(source, "source");
        this.f8874e.l(source, j7);
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.f8874e + ')';
    }
}
