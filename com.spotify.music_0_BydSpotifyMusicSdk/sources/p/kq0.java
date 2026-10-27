package p;

/* JADX INFO: loaded from: classes.dex */
public final class kq0 {
    public final jq0 a;

    public kq0(jq0 jq0Var) {
        this.a = jq0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof kq0) && v03.b(this.a, ((kq0) obj).a);
    }

    public final int hashCode() {
        jq0 jq0Var = this.a;
        if (jq0Var == null) {
            return 0;
        }
        return jq0Var.hashCode();
    }

    public final String toString() {
        return "Me(library=" + this.a + ')';
    }
}
