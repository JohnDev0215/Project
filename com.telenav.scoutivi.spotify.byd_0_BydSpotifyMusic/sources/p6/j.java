package p6;

/* JADX INFO: compiled from: ForwardingSource.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class j implements a0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final a0 f8875e;

    public j(a0 delegate) {
        kotlin.jvm.internal.l.e(delegate, "delegate");
        this.f8875e = delegate;
    }

    @Override // p6.a0
    public b0 c() {
        return this.f8875e.c();
    }

    @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f8875e.close();
    }

    public final a0 m() {
        return this.f8875e;
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.f8875e + ')';
    }
}
