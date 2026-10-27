package p;

/* JADX INFO: loaded from: classes.dex */
public final class cp0 {
    public final bp0 a;

    public cp0(bp0 bp0Var) {
        this.a = bp0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof cp0) && v03.b(this.a, ((cp0) obj).a);
    }

    public final int hashCode() {
        bp0 bp0Var = this.a;
        if (bp0Var == null) {
            return 0;
        }
        return bp0Var.hashCode();
    }

    public final String toString() {
        return "Me(library=" + this.a + ')';
    }
}
