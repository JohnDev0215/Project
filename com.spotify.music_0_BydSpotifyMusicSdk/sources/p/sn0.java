package p;

/* JADX INFO: loaded from: classes.dex */
public final class sn0 {
    public final String a;
    public final qn0 b;

    public sn0(String str, qn0 qn0Var) {
        this.a = str;
        this.b = qn0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof sn0)) {
            return false;
        }
        sn0 sn0Var = (sn0) obj;
        return v03.b(this.a, sn0Var.a) && v03.b(this.b, sn0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "TopTracks(__typename=" + this.a + ", onTrackPage=" + this.b + ')';
    }
}
