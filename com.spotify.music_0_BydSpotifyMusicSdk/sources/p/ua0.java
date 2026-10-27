package p;

/* JADX INFO: loaded from: classes.dex */
public final class ua0 implements wy1, u81 {
    public static final Object c = new Object();
    public volatile wy1 a;
    public volatile Object b = c;

    public ua0(wy1 wy1Var) {
        this.a = wy1Var;
    }

    public static u81 a(xy1 xy1Var) {
        xy1Var.getClass();
        wy1 p50Var = new p50(xy1Var);
        return p50Var instanceof u81 ? (u81) p50Var : new ua0(p50Var);
    }

    public static wy1 b(wy1 wy1Var) {
        wy1Var.getClass();
        return wy1Var instanceof ua0 ? wy1Var : new ua0(wy1Var);
    }

    @Override // p.xy1
    public final Object get() {
        Object obj = this.b;
        Object obj2 = c;
        if (obj == obj2) {
            synchronized (this) {
                try {
                    obj = this.b;
                    if (obj == obj2) {
                        obj = this.a.get();
                        Object obj3 = this.b;
                        if (obj3 != obj2 && obj3 != obj) {
                            throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj3 + " & " + obj + ". This is likely due to a circular dependency.");
                        }
                        this.b = obj;
                        this.a = null;
                    }
                } finally {
                }
            }
        }
        return obj;
    }
}
