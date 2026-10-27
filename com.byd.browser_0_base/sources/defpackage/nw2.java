package defpackage;

/* JADX INFO: compiled from: Supplier.java */
/* JADX INFO: loaded from: classes.dex */
public interface nw2<T> {
    default boolean f() {
        T t = get();
        if (t == get()) {
            return t != null;
        }
        throw new AssertionError("Value provided by #get() must not change.");
    }

    T get();
}
