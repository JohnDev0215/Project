package p;

/* JADX INFO: loaded from: classes.dex */
public final class nn0 {
    public final tn0 a;

    public nn0(tn0 tn0Var) {
        this.a = tn0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof nn0) && v03.b(this.a, ((nn0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Item(track=" + this.a + ')';
    }
}
