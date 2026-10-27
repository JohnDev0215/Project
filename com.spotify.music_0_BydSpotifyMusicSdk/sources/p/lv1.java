package p;

/* JADX INFO: loaded from: classes.dex */
public final class lv1 {
    public final String a;
    public final df0 b;

    public lv1(String str, df0 df0Var) {
        this.a = str;
        this.b = df0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof lv1)) {
            return false;
        }
        lv1 lv1Var = (lv1) obj;
        return v03.b(this.a, lv1Var.a) && v03.b(this.b, lv1Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Entity(__typename=" + this.a + ", episodeWrapper=" + this.b + ')';
    }
}
