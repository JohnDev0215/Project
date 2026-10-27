package p;

/* JADX INFO: loaded from: classes.dex */
public final class fq0 implements pq1 {
    public final kq0 a;

    public fq0(kq0 kq0Var) {
        this.a = kq0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof fq0) && v03.b(this.a, ((fq0) obj).a);
    }

    public final int hashCode() {
        kq0 kq0Var = this.a;
        if (kq0Var == null) {
            return 0;
        }
        return kq0Var.hashCode();
    }

    public final String toString() {
        return "Data(me=" + this.a + ')';
    }
}
