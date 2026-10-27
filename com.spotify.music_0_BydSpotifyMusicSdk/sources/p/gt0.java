package p;

/* JADX INFO: loaded from: classes.dex */
public final class gt0 {
    public final String a;
    public final fe b;
    public final aw1 c;

    public gt0(String str, fe feVar, aw1 aw1Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = feVar;
        this.c = aw1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof gt0)) {
            return false;
        }
        gt0 gt0Var = (gt0) obj;
        return v03.b(this.a, gt0Var.a) && v03.b(this.b, gt0Var.b) && v03.b(this.c, gt0Var.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        fe feVar = this.b;
        int iHashCode2 = (iHashCode + (feVar == null ? 0 : feVar.hashCode())) * 31;
        aw1 aw1Var = this.c;
        return iHashCode2 + (aw1Var != null ? aw1Var.hashCode() : 0);
    }

    public final String toString() {
        return "PodcastUnionV2(__typename=" + this.a + ", audiobookInfo=" + this.b + ", podcastInfo=" + this.c + ')';
    }
}
