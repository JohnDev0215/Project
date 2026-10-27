package p;

/* JADX INFO: loaded from: classes.dex */
public final class ho0 {
    public final String a;
    public final mo0 b;

    public ho0(String str, mo0 mo0Var) {
        this.a = str;
        this.b = mo0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ho0)) {
            return false;
        }
        ho0 ho0Var = (ho0) obj;
        return v03.b(this.a, ho0Var.a) && v03.b(this.b, ho0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Albums(__typename=" + this.a + ", onUserLibraryAlbumPage=" + this.b + ')';
    }
}
