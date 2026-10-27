package p;

/* JADX INFO: loaded from: classes.dex */
public final class ep0 {
    public final xo0 a;

    public ep0(xo0 xo0Var) {
        this.a = xo0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ep0) && v03.b(this.a, ((ep0) obj).a);
    }

    public final int hashCode() {
        xo0 xo0Var = this.a;
        if (xo0Var == null) {
            return 0;
        }
        return xo0Var.hashCode();
    }

    public final String toString() {
        return "OnUserLibraryArtistResponse(artist=" + this.a + ')';
    }
}
