package p;

/* JADX INFO: loaded from: classes.dex */
public final class he0 {
    public final String a;
    public final i31 b;

    public he0(String str, i31 i31Var) {
        this.a = str;
        this.b = i31Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof he0)) {
            return false;
        }
        he0 he0Var = (he0) obj;
        return v03.b(this.a, he0Var.a) && v03.b(this.b, he0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "CoverArt(__typename=" + this.a + ", image=" + this.b + ')';
    }
}
