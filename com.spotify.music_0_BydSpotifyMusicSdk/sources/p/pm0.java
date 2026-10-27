package p;

/* JADX INFO: loaded from: classes.dex */
public final class pm0 {
    public final String a;
    public final sm0 b;

    public pm0(String str, sm0 sm0Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = sm0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pm0)) {
            return false;
        }
        pm0 pm0Var = (pm0) obj;
        return v03.b(this.a, pm0Var.a) && v03.b(this.b, pm0Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        sm0 sm0Var = this.b;
        return iHashCode + (sm0Var == null ? 0 : sm0Var.hashCode());
    }

    public final String toString() {
        return "AlbumUnion(__typename=" + this.a + ", onAlbum=" + this.b + ')';
    }
}
