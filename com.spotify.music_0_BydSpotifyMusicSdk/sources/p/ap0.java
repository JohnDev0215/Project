package p;

/* JADX INFO: loaded from: classes.dex */
public final class ap0 {
    public final String a;
    public final ep0 b;

    public ap0(String str, ep0 ep0Var) {
        this.a = str;
        this.b = ep0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ap0)) {
            return false;
        }
        ap0 ap0Var = (ap0) obj;
        return v03.b(this.a, ap0Var.a) && v03.b(this.b, ap0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item(__typename=" + this.a + ", onUserLibraryArtistResponse=" + this.b + ')';
    }
}
