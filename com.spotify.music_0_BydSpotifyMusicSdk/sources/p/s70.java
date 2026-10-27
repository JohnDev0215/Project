package p;

/* JADX INFO: loaded from: classes.dex */
public final class s70 extends d72 {
    public static final s70 l = new s70(fj2.c, fj2.d, fj2.e, fj2.a);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // kotlinx.coroutines.b
    public final String toString() {
        return "Dispatchers.Default";
    }
}
