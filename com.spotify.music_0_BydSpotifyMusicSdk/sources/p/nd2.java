package p;

/* JADX INFO: loaded from: classes.dex */
public final class nd2 implements wy1 {
    public static final Object c = new Object();
    public volatile wy1 a;
    public volatile Object b;

    public static wy1 a(ul0 ul0Var) {
        if ((ul0Var instanceof nd2) || (ul0Var instanceof ua0)) {
            return ul0Var;
        }
        nd2 nd2Var = new nd2();
        nd2Var.b = c;
        nd2Var.a = ul0Var;
        return nd2Var;
    }

    @Override // p.xy1
    public final Object get() {
        Object obj = this.b;
        if (obj != c) {
            return obj;
        }
        wy1 wy1Var = this.a;
        if (wy1Var == null) {
            return this.b;
        }
        Object obj2 = wy1Var.get();
        this.b = obj2;
        this.a = null;
        return obj2;
    }
}
