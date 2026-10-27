package p;

/* JADX INFO: loaded from: classes.dex */
public final class qp0 implements pq1 {
    public final tp0 a;

    public qp0(tp0 tp0Var) {
        this.a = tp0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof qp0) && v03.b(this.a, ((qp0) obj).a);
    }

    public final int hashCode() {
        tp0 tp0Var = this.a;
        if (tp0Var == null) {
            return 0;
        }
        return tp0Var.hashCode();
    }

    public final String toString() {
        return "Data(me=" + this.a + ')';
    }
}
