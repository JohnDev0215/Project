package p;

/* JADX INFO: loaded from: classes.dex */
public final class ow1 extends nw1 {
    public final Object d;

    public ow1() {
        super(12, 1);
        this.d = new Object();
    }

    @Override // p.nw1
    public final Object a() {
        Object objA;
        synchronized (this.d) {
            objA = super.a();
        }
        return objA;
    }

    @Override // p.nw1
    public final boolean b(Object obj) {
        boolean zB;
        synchronized (this.d) {
            zB = super.b(obj);
        }
        return zB;
    }
}
