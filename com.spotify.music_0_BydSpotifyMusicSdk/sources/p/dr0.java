package p;

/* JADX INFO: loaded from: classes.dex */
public final class dr0 {
    public final String a;
    public final ar0 b;

    public dr0(String str, ar0 ar0Var) {
        this.a = str;
        this.b = ar0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof dr0)) {
            return false;
        }
        dr0 dr0Var = (dr0) obj;
        return v03.b(this.a, dr0Var.a) && v03.b(this.b, dr0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Tracks(__typename=" + this.a + ", onUserLibraryTrackPage=" + this.b + ')';
    }
}
