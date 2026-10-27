package p;

/* JADX INFO: loaded from: classes.dex */
public final class jo0 {
    public final String a;
    public final no0 b;

    public jo0(String str, no0 no0Var) {
        this.a = str;
        this.b = no0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jo0)) {
            return false;
        }
        jo0 jo0Var = (jo0) obj;
        return v03.b(this.a, jo0Var.a) && v03.b(this.b, jo0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item(__typename=" + this.a + ", onUserLibraryAlbumResponse=" + this.b + ')';
    }
}
