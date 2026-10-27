package p;

/* JADX INFO: loaded from: classes.dex */
public final class l82 implements pq1 {
    public final i92 a;

    public l82(i92 i92Var) {
        this.a = i92Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof l82) && v03.b(this.a, ((l82) obj).a);
    }

    public final int hashCode() {
        i92 i92Var = this.a;
        if (i92Var == null) {
            return 0;
        }
        return i92Var.hashCode();
    }

    public final String toString() {
        return "Data(searchV2=" + this.a + ')';
    }
}
