package p;

/* JADX INFO: loaded from: classes.dex */
public final class jn0 {
    public final String a;
    public final pn0 b;

    public jn0(String str, pn0 pn0Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = pn0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jn0)) {
            return false;
        }
        jn0 jn0Var = (jn0) obj;
        return v03.b(this.a, jn0Var.a) && v03.b(this.b, jn0Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        pn0 pn0Var = this.b;
        return iHashCode + (pn0Var == null ? 0 : pn0Var.a.hashCode());
    }

    public final String toString() {
        return "ArtistUnion(__typename=" + this.a + ", onArtist=" + this.b + ')';
    }
}
