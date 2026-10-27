package p;

/* JADX INFO: loaded from: classes.dex */
public final class xj0 {
    public final py0 a;

    public xj0(py0 py0Var) {
        this.a = py0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof xj0) && v03.b(this.a, ((xj0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "getCountFunction(listCountsTotalList=" + this.a + ')';
    }
}
