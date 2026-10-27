package p;

/* JADX INFO: loaded from: classes.dex */
public final class w81 implements yy1 {
    public static final Object c = new Object();
    public volatile Object a = c;
    public volatile yy1 b;

    public w81(yy1 yy1Var) {
        this.b = yy1Var;
    }

    @Override // p.yy1
    public final Object get() {
        Object obj = this.a;
        Object obj2 = c;
        if (obj == obj2) {
            synchronized (this) {
                try {
                    obj = this.a;
                    if (obj == obj2) {
                        obj = this.b.get();
                        this.a = obj;
                        this.b = null;
                    }
                } finally {
                }
            }
        }
        return obj;
    }
}
