package p;

/* JADX INFO: loaded from: classes.dex */
public final class cn0 {
    public final String a;
    public final en0 b;

    public cn0(String str, en0 en0Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = en0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cn0)) {
            return false;
        }
        cn0 cn0Var = (cn0) obj;
        return v03.b(this.a, cn0Var.a) && v03.b(this.b, cn0Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        en0 en0Var = this.b;
        return iHashCode + (en0Var == null ? 0 : en0Var.hashCode());
    }

    public final String toString() {
        return "ArtistUnion(__typename=" + this.a + ", onArtist=" + this.b + ')';
    }
}
