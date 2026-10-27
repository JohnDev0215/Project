package p;

/* JADX INFO: loaded from: classes.dex */
public final class kn0 implements pq1 {
    public final jn0 a;

    public kn0(jn0 jn0Var) {
        this.a = jn0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof kn0) && v03.b(this.a, ((kn0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Data(artistUnion=" + this.a + ')';
    }
}
