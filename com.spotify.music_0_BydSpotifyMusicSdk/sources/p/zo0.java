package p;

/* JADX INFO: loaded from: classes.dex */
public final class zo0 implements pq1 {
    public final cp0 a;

    public zo0(cp0 cp0Var) {
        this.a = cp0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zo0) && v03.b(this.a, ((zo0) obj).a);
    }

    public final int hashCode() {
        cp0 cp0Var = this.a;
        if (cp0Var == null) {
            return 0;
        }
        return cp0Var.hashCode();
    }

    public final String toString() {
        return "Data(me=" + this.a + ')';
    }
}
