package p;

/* JADX INFO: loaded from: classes.dex */
public final class va0 implements xy1 {
    public static final Object c = new Object();
    public volatile xy1 a;
    public volatile Object b;

    public static xy1 a(vl0 vl0Var) {
        if (vl0Var instanceof va0) {
            return vl0Var;
        }
        va0 va0Var = new va0();
        va0Var.b = c;
        va0Var.a = vl0Var;
        return va0Var;
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
