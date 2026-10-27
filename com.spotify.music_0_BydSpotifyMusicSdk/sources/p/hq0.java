package p;

/* JADX INFO: loaded from: classes.dex */
public final class hq0 {
    public final String a;
    public final lq0 b;

    public hq0(String str, lq0 lq0Var) {
        this.a = str;
        this.b = lq0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof hq0)) {
            return false;
        }
        hq0 hq0Var = (hq0) obj;
        return v03.b(this.a, hq0Var.a) && v03.b(this.b, hq0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Episodes(__typename=" + this.a + ", onUserLibraryEpisodePage=" + this.b + ')';
    }
}
