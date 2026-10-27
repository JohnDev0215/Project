package p;

/* JADX INFO: loaded from: classes.dex */
public final class rn0 {
    public final String a;
    public final on0 b;

    public rn0(String str, on0 on0Var) {
        this.a = str;
        this.b = on0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof rn0)) {
            return false;
        }
        rn0 rn0Var = (rn0) obj;
        return v03.b(this.a, rn0Var.a) && v03.b(this.b, rn0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "PopularReleasesAlbums(__typename=" + this.a + ", onAlbumPage=" + this.b + ')';
    }
}
