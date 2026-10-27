package p;

/* JADX INFO: loaded from: classes.dex */
public final class jq0 {
    public final hq0 a;

    public jq0(hq0 hq0Var) {
        this.a = hq0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof jq0) && v03.b(this.a, ((jq0) obj).a);
    }

    public final int hashCode() {
        hq0 hq0Var = this.a;
        if (hq0Var == null) {
            return 0;
        }
        return hq0Var.hashCode();
    }

    public final String toString() {
        return "Library(episodes=" + this.a + ')';
    }
}
