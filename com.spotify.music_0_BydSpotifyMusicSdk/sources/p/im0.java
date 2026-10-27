package p;

/* JADX INFO: loaded from: classes.dex */
public final class im0 {
    public final String a;
    public final km0 b;

    public im0(String str, km0 km0Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = km0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof im0)) {
            return false;
        }
        im0 im0Var = (im0) obj;
        return v03.b(this.a, im0Var.a) && v03.b(this.b, im0Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        km0 km0Var = this.b;
        return iHashCode + (km0Var == null ? 0 : km0Var.hashCode());
    }

    public final String toString() {
        return "AlbumUnion(__typename=" + this.a + ", onAlbum=" + this.b + ')';
    }
}
