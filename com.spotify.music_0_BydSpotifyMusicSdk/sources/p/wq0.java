package p;

/* JADX INFO: loaded from: classes.dex */
public final class wq0 implements pq1 {
    public final zq0 a;

    public wq0(zq0 zq0Var) {
        this.a = zq0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof wq0) && v03.b(this.a, ((wq0) obj).a);
    }

    public final int hashCode() {
        zq0 zq0Var = this.a;
        if (zq0Var == null) {
            return 0;
        }
        return zq0Var.hashCode();
    }

    public final String toString() {
        return "Data(me=" + this.a + ')';
    }
}
