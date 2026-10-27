package p;

/* JADX INFO: loaded from: classes.dex */
public final class dn0 implements pq1 {
    public final cn0 a;

    public dn0(cn0 cn0Var) {
        this.a = cn0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof dn0) && v03.b(this.a, ((dn0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Data(artistUnion=" + this.a + ')';
    }
}
