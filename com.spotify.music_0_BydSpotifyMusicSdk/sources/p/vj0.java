package p;

/* JADX INFO: loaded from: classes.dex */
public final class vj0 {
    public final ry0 a;

    public vj0(ry0 ry0Var) {
        this.a = ry0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof vj0) && v03.b(this.a, ((vj0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "SaveTotalCountFunction(addListCountsTotal=" + this.a + ')';
    }
}
