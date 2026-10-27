package p;

/* JADX INFO: loaded from: classes.dex */
public final class or0 {
    public final String a;
    public final av1 b;

    public or0(String str, av1 av1Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = av1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof or0)) {
            return false;
        }
        or0 or0Var = (or0) obj;
        return v03.b(this.a, or0Var.a) && v03.b(this.b, or0Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        av1 av1Var = this.b;
        return iHashCode + (av1Var == null ? 0 : av1Var.a.hashCode());
    }

    public final String toString() {
        return "Item1(__typename=" + this.a + ", playlistWrapper=" + this.b + ')';
    }
}
