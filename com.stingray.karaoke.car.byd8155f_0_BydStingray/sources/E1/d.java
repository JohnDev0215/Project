package E1;

import A7.m;

/* JADX INFO: loaded from: classes.dex */
public final class d extends c {
    public final Object c;

    public d(int i7) {
        super(i7);
        this.c = new Object();
    }

    @Override // E1.c
    public final Object a() {
        Object objA;
        synchronized (this.c) {
            objA = super.a();
        }
        return objA;
    }

    @Override // E1.c
    public final boolean c(Object obj) {
        boolean zC;
        m.f(obj, "instance");
        synchronized (this.c) {
            zC = super.c(obj);
        }
        return zC;
    }
}
