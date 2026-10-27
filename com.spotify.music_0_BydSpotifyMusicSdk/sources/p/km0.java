package p;

/* JADX INFO: loaded from: classes.dex */
public final class km0 {
    public final String a;
    public final m5 b;

    public km0(String str, m5 m5Var) {
        this.a = str;
        this.b = m5Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof km0)) {
            return false;
        }
        km0 km0Var = (km0) obj;
        return v03.b(this.a, km0Var.a) && v03.b(this.b, km0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnAlbum(__typename=" + this.a + ", album=" + this.b + ')';
    }
}
