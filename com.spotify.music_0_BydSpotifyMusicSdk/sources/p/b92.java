package p;

/* JADX INFO: loaded from: classes.dex */
public final class b92 {
    public final String a;
    public final df0 b;

    public b92(String str, df0 df0Var) {
        this.a = str;
        this.b = df0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b92)) {
            return false;
        }
        b92 b92Var = (b92) obj;
        return v03.b(this.a, b92Var.a) && v03.b(this.b, b92Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnEpisodeResponseWrapper(__typename=" + this.a + ", episodeWrapper=" + this.b + ')';
    }
}
