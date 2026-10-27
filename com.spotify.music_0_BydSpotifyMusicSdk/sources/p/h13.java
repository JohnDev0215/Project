package p;

/* JADX INFO: loaded from: classes.dex */
public final class h13 implements i13, f13 {
    public static final Object k = new Object();
    public volatile i13 c;
    public volatile Object e = k;

    public h13(i13 i13Var) {
        this.c = i13Var;
    }

    public static f13 b(i13 i13Var) {
        if (i13Var instanceof f13) {
            return (f13) i13Var;
        }
        i13Var.getClass();
        return new h13(i13Var);
    }

    public static i13 c(i13 i13Var) {
        return i13Var instanceof h13 ? i13Var : new h13(i13Var);
    }

    @Override // p.i13
    public final Object a() {
        Object objA = this.e;
        Object obj = k;
        if (objA == obj) {
            synchronized (this) {
                try {
                    objA = this.e;
                    if (objA == obj) {
                        objA = this.c.a();
                        Object obj2 = this.e;
                        if (obj2 != obj && obj2 != objA) {
                            String strValueOf = String.valueOf(obj2);
                            String strValueOf2 = String.valueOf(objA);
                            StringBuilder sb = new StringBuilder(strValueOf.length() + 118 + strValueOf2.length());
                            sb.append("Scoped provider was invoked recursively returning different results: ");
                            sb.append(strValueOf);
                            sb.append(" & ");
                            sb.append(strValueOf2);
                            sb.append(". This is likely due to a circular dependency.");
                            throw new IllegalStateException(sb.toString());
                        }
                        this.e = objA;
                        this.c = null;
                    }
                } finally {
                }
            }
        }
        return objA;
    }
}
