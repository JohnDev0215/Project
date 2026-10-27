package spi;

/* JADX INFO: compiled from: FutureData.java */
/* JADX INFO: loaded from: classes.dex */
public final class d4<T> {
    public final boolean a;
    public final T b;
    public final T c;

    @Deprecated
    public d4(boolean z, T t) {
        this(z, null, t);
    }

    public static <T> d4<T> a(T t, T t2) {
        return new d4<>(false, t, t2);
    }

    private d4(boolean z, T t, T t2) {
        this.a = z;
        this.b = t;
        this.c = t2;
    }
}
