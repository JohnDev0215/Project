package p;

/* JADX INFO: loaded from: classes.dex */
public final class mv1 {
    public final String a;
    public final ov1 b;

    public mv1(String str, ov1 ov1Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = ov1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mv1)) {
            return false;
        }
        mv1 mv1Var = (mv1) obj;
        return v03.b(this.a, mv1Var.a) && v03.b(this.b, mv1Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        ov1 ov1Var = this.b;
        return iHashCode + (ov1Var == null ? 0 : ov1Var.a.hashCode());
    }

    public final String toString() {
        return "EpisodesV2(__typename=" + this.a + ", onContextEpisodePage=" + this.b + ')';
    }
}
