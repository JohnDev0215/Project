package p;

/* JADX INFO: loaded from: classes.dex */
public final class ln0 {
    public final sn0 a;
    public final rn0 b;

    public ln0(sn0 sn0Var, rn0 rn0Var) {
        this.a = sn0Var;
        this.b = rn0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ln0)) {
            return false;
        }
        ln0 ln0Var = (ln0) obj;
        return v03.b(this.a, ln0Var.a) && v03.b(this.b, ln0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Discography(topTracks=" + this.a + ", popularReleasesAlbums=" + this.b + ')';
    }
}
