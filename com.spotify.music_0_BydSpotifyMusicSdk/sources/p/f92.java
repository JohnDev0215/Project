package p;

/* JADX INFO: loaded from: classes.dex */
public final class f92 {
    public final String a;
    public final hn2 b;

    public f92(String str, hn2 hn2Var) {
        this.a = str;
        this.b = hn2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f92)) {
            return false;
        }
        f92 f92Var = (f92) obj;
        return v03.b(this.a, f92Var.a) && v03.b(this.b, f92Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        hn2 hn2Var = this.b;
        return iHashCode + (hn2Var == null ? 0 : hn2Var.a.hashCode());
    }

    public final String toString() {
        return "OnTrackResponseWrapper(__typename=" + this.a + ", trackWrapper=" + this.b + ')';
    }
}
