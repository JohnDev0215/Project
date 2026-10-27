package p;

/* JADX INFO: loaded from: classes.dex */
public final class r82 {
    public final String a;
    public final df0 b;

    public r82(String str, df0 df0Var) {
        this.a = str;
        this.b = df0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r82)) {
            return false;
        }
        r82 r82Var = (r82) obj;
        return v03.b(this.a, r82Var.a) && v03.b(this.b, r82Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item4(__typename=" + this.a + ", episodeWrapper=" + this.b + ')';
    }
}
