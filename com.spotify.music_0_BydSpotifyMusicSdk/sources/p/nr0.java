package p;

/* JADX INFO: loaded from: classes.dex */
public final class nr0 implements pq1 {
    public final sr0 a;

    public nr0(sr0 sr0Var) {
        this.a = sr0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof nr0) && v03.b(this.a, ((nr0) obj).a);
    }

    public final int hashCode() {
        sr0 sr0Var = this.a;
        if (sr0Var == null) {
            return 0;
        }
        return sr0Var.hashCode();
    }

    public final String toString() {
        return "Data(me=" + this.a + ')';
    }
}
