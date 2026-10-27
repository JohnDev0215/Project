package p;

/* JADX INFO: loaded from: classes.dex */
public final class ko0 {
    public final ho0 a;

    public ko0(ho0 ho0Var) {
        this.a = ho0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ko0) && v03.b(this.a, ((ko0) obj).a);
    }

    public final int hashCode() {
        ho0 ho0Var = this.a;
        if (ho0Var == null) {
            return 0;
        }
        return ho0Var.hashCode();
    }

    public final String toString() {
        return "Library(albums=" + this.a + ')';
    }
}
