package p;

/* JADX INFO: loaded from: classes.dex */
public final class n82 {
    public final String a;
    public final d92 b;

    public n82(String str, d92 d92Var) {
        this.a = str;
        this.b = d92Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n82)) {
            return false;
        }
        n82 n82Var = (n82) obj;
        return v03.b(this.a, n82Var.a) && v03.b(this.b, n82Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Featured(__typename=" + this.a + ", onPlaylistResponseWrapper=" + this.b + ')';
    }
}
