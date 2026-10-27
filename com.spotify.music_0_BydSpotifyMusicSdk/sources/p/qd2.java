package p;

/* JADX INFO: loaded from: classes.dex */
public final class qd2 {
    public static final qd2 c;
    public final ci1 a;
    public final ci1 b;

    static {
        u90 u90Var = u90.f195p;
        c = new qd2(u90Var, u90Var);
    }

    public qd2(ci1 ci1Var, ci1 ci1Var2) {
        this.a = ci1Var;
        this.b = ci1Var2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof qd2)) {
            return false;
        }
        qd2 qd2Var = (qd2) obj;
        return v03.b(this.a, qd2Var.a) && v03.b(this.b, qd2Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Size(width=" + this.a + ", height=" + this.b + ')';
    }
}
