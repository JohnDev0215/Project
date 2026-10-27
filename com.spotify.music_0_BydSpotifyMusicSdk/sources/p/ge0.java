package p;

/* JADX INFO: loaded from: classes.dex */
public final class ge0 {
    public final String a;
    public final kz b;

    public ge0(String str, kz kzVar) {
        this.a = str;
        this.b = kzVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ge0)) {
            return false;
        }
        ge0 ge0Var = (ge0) obj;
        return v03.b(this.a, ge0Var.a) && v03.b(this.b, ge0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "ContentRating(__typename=" + this.a + ", contentRating=" + this.b + ')';
    }
}
