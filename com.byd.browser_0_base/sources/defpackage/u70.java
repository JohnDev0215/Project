package defpackage;

/* JADX INFO: compiled from: Dispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class u70 extends le2 {
    public static final u70 g = new u70();

    public u70() {
        super(e73.b, e73.c, e73.d);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // defpackage.p00
    public final String toString() {
        return "Dispatchers.Default";
    }
}
