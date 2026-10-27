package spi;

/* JADX INFO: compiled from: Singleton.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class fa<T> {
    public T a;

    public abstract T a();

    public final T b() {
        if (this.a == null) {
            synchronized (fa.class) {
                if (this.a == null) {
                    this.a = a();
                }
            }
        }
        return this.a;
    }
}
