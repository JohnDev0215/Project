package Z8;

/* JADX INFO: loaded from: classes.dex */
public final class e extends h {
    public static final e o;

    static {
        int i7 = k.c;
        int i10 = k.d;
        long j3 = k.f11017e;
        String str = k.f11015a;
        e eVar = new e();
        eVar.f11012n = new c(i7, i10, j3, str);
        o = eVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // S8.AbstractC0721s
    public final String toString() {
        return "Dispatchers.Default";
    }
}
