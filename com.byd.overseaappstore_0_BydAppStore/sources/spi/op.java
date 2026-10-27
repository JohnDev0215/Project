package spi;

/* JADX INFO: loaded from: classes.dex */
public abstract class op<T> {
    public T a;

    public abstract T a();

    public final T b() {
        if (this.a == null) {
            synchronized (op.class) {
                if (this.a == null) {
                    this.a = a();
                }
            }
        }
        return this.a;
    }
}
