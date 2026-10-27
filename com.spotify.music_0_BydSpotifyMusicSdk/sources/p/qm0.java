package p;

/* JADX INFO: loaded from: classes.dex */
public final class qm0 implements pq1 {
    public final pm0 a;

    public qm0(pm0 pm0Var) {
        this.a = pm0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof qm0) && v03.b(this.a, ((qm0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Data(albumUnion=" + this.a + ')';
    }
}
