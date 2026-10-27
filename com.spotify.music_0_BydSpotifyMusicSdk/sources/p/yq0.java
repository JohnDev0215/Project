package p;

/* JADX INFO: loaded from: classes.dex */
public final class yq0 {
    public final dr0 a;

    public yq0(dr0 dr0Var) {
        this.a = dr0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof yq0) && v03.b(this.a, ((yq0) obj).a);
    }

    public final int hashCode() {
        dr0 dr0Var = this.a;
        if (dr0Var == null) {
            return 0;
        }
        return dr0Var.hashCode();
    }

    public final String toString() {
        return "Library(tracks=" + this.a + ')';
    }
}
