package p;

/* JADX INFO: loaded from: classes.dex */
public final class gq0 {
    public final String a;
    public final df0 b;

    public gq0(String str, df0 df0Var) {
        this.a = str;
        this.b = df0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof gq0)) {
            return false;
        }
        gq0 gq0Var = (gq0) obj;
        return v03.b(this.a, gq0Var.a) && v03.b(this.b, gq0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Episode(__typename=" + this.a + ", episodeWrapper=" + this.b + ')';
    }
}
