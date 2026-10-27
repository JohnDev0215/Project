package p;

/* JADX INFO: loaded from: classes.dex */
public final class ms0 {
    public final ls0 a;

    public ms0(ls0 ls0Var) {
        this.a = ls0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ms0) && v03.b(this.a, ((ms0) obj).a);
    }

    public final int hashCode() {
        ls0 ls0Var = this.a;
        if (ls0Var == null) {
            return 0;
        }
        return ls0Var.hashCode();
    }

    public final String toString() {
        return "Me(library=" + this.a + ')';
    }
}
