package p;

/* JADX INFO: loaded from: classes.dex */
public final class yo0 {
    public final String a;
    public final dp0 b;

    public yo0(String str, dp0 dp0Var) {
        this.a = str;
        this.b = dp0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof yo0)) {
            return false;
        }
        yo0 yo0Var = (yo0) obj;
        return v03.b(this.a, yo0Var.a) && v03.b(this.b, yo0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Artists(__typename=" + this.a + ", onUserLibraryArtistPage=" + this.b + ')';
    }
}
