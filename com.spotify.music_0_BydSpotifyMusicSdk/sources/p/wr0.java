package p;

/* JADX INFO: loaded from: classes.dex */
public final class wr0 {
    public final String a;
    public final ur0 b;

    public wr0(String str, ur0 ur0Var) {
        this.a = str;
        this.b = ur0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof wr0)) {
            return false;
        }
        wr0 wr0Var = (wr0) obj;
        return v03.b(this.a, wr0Var.a) && v03.b(this.b, wr0Var.b);
    }

    public final int hashCode() {
        return Integer.hashCode(this.b.a) + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Tracks(__typename=" + this.a + ", onUserLibraryTrackPage=" + this.b + ')';
    }
}
