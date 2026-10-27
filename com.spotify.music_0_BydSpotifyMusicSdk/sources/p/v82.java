package p;

/* JADX INFO: loaded from: classes.dex */
public final class v82 {
    public final String a;
    public final hn2 b;

    public v82(String str, hn2 hn2Var) {
        this.a = str;
        this.b = hn2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v82)) {
            return false;
        }
        v82 v82Var = (v82) obj;
        return v03.b(this.a, v82Var.a) && v03.b(this.b, v82Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item8(__typename=" + this.a + ", trackWrapper=" + this.b + ')';
    }
}
