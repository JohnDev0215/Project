package p;

/* JADX INFO: loaded from: classes.dex */
public final class y82 {
    public final String a;
    public final z5 b;

    public y82(String str, z5 z5Var) {
        this.a = str;
        this.b = z5Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y82)) {
            return false;
        }
        y82 y82Var = (y82) obj;
        return v03.b(this.a, y82Var.a) && v03.b(this.b, y82Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnAlbumResponseWrapper(__typename=" + this.a + ", albumWrapper=" + this.b + ')';
    }
}
