package defpackage;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class y8<T> extends x8<T> {
    public final Object c;

    public y8(int i) {
        super(i);
        this.c = new Object();
    }

    @Override // defpackage.x8, defpackage.w8
    public boolean a(T t) {
        boolean zA;
        synchronized (this.c) {
            zA = super.a(t);
        }
        return zA;
    }

    @Override // defpackage.x8, defpackage.w8
    public T b() {
        T t;
        synchronized (this.c) {
            t = (T) super.b();
        }
        return t;
    }
}
