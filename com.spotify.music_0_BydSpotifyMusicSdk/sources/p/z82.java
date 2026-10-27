package p;

/* JADX INFO: loaded from: classes.dex */
public final class z82 {
    public final String a;
    public final fc b;

    public z82(String str, fc fcVar) {
        this.a = str;
        this.b = fcVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z82)) {
            return false;
        }
        z82 z82Var = (z82) obj;
        return v03.b(this.a, z82Var.a) && v03.b(this.b, z82Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnArtistResponseWrapper(__typename=" + this.a + ", artistWrapper=" + this.b + ')';
    }
}
