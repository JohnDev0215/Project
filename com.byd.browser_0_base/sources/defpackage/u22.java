package defpackage;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public final class u22<T> extends t22 {
    public final Object d;

    public u22(int i) {
        super(i, 1);
        this.d = new Object();
    }

    @Override // defpackage.t22
    public final T a() {
        T t;
        synchronized (this.d) {
            t = (T) super.a();
        }
        return t;
    }

    @Override // defpackage.t22
    public final boolean b(T t) {
        boolean zB;
        synchronized (this.d) {
            zB = super.b(t);
        }
        return zB;
    }
}
