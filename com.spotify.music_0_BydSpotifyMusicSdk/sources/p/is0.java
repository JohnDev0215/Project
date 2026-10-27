package p;

/* JADX INFO: loaded from: classes.dex */
public final class is0 implements pq1 {
    public final ms0 a;

    public is0(ms0 ms0Var) {
        this.a = ms0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof is0) && v03.b(this.a, ((is0) obj).a);
    }

    public final int hashCode() {
        ms0 ms0Var = this.a;
        if (ms0Var == null) {
            return 0;
        }
        return ms0Var.hashCode();
    }

    public final String toString() {
        return "Data(me=" + this.a + ')';
    }
}
