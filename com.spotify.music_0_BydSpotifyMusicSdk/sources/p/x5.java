package p;

/* JADX INFO: loaded from: classes.dex */
public final class x5 {
    public final String a;
    public final m5 b;

    public x5(String str, m5 m5Var) {
        this.a = str;
        this.b = m5Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x5)) {
            return false;
        }
        x5 x5Var = (x5) obj;
        return v03.b(this.a, x5Var.a) && v03.b(this.b, x5Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnAlbum(__typename=" + this.a + ", album=" + this.b + ')';
    }
}
