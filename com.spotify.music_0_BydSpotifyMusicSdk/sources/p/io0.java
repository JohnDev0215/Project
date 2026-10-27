package p;

/* JADX INFO: loaded from: classes.dex */
public final class io0 implements pq1 {
    public final lo0 a;

    public io0(lo0 lo0Var) {
        this.a = lo0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof io0) && v03.b(this.a, ((io0) obj).a);
    }

    public final int hashCode() {
        lo0 lo0Var = this.a;
        if (lo0Var == null) {
            return 0;
        }
        return lo0Var.hashCode();
    }

    public final String toString() {
        return "Data(me=" + this.a + ')';
    }
}
