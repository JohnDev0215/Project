package p;

/* JADX INFO: loaded from: classes.dex */
public final class yv1 {
    public final String a;
    public final i31 b;

    public yv1(String str, i31 i31Var) {
        this.a = str;
        this.b = i31Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof yv1)) {
            return false;
        }
        yv1 yv1Var = (yv1) obj;
        return v03.b(this.a, yv1Var.a) && v03.b(this.b, yv1Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "CoverArt(__typename=" + this.a + ", image=" + this.b + ')';
    }
}
