package p;

/* JADX INFO: loaded from: classes.dex */
public final class i92 {
    public final j92 a;
    public final i82 b;
    public final j82 c;
    public final k82 d;
    public final m82 e;
    public final g92 f;
    public final h92 g;
    public final k92 h;

    public i92(j92 j92Var, i82 i82Var, j82 j82Var, k82 k82Var, m82 m82Var, g92 g92Var, h92 h92Var, k92 k92Var) {
        this.a = j92Var;
        this.b = i82Var;
        this.c = j82Var;
        this.d = k82Var;
        this.e = m82Var;
        this.f = g92Var;
        this.g = h92Var;
        this.h = k92Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i92)) {
            return false;
        }
        i92 i92Var = (i92) obj;
        return v03.b(this.a, i92Var.a) && v03.b(this.b, i92Var.b) && v03.b(this.c, i92Var.c) && v03.b(this.d, i92Var.d) && v03.b(this.e, i92Var.e) && v03.b(this.f, i92Var.f) && v03.b(this.g, i92Var.g) && v03.b(this.h, i92Var.h);
    }

    public final int hashCode() {
        j92 j92Var = this.a;
        int iHashCode = (j92Var == null ? 0 : j92Var.hashCode()) * 31;
        i82 i82Var = this.b;
        int iHashCode2 = (iHashCode + (i82Var == null ? 0 : i82Var.a.hashCode())) * 31;
        j82 j82Var = this.c;
        int iHashCode3 = (iHashCode2 + (j82Var == null ? 0 : j82Var.a.hashCode())) * 31;
        k82 k82Var = this.d;
        int iHashCode4 = (iHashCode3 + (k82Var == null ? 0 : k82Var.a.hashCode())) * 31;
        m82 m82Var = this.e;
        int iHashCode5 = (iHashCode4 + (m82Var == null ? 0 : m82Var.a.hashCode())) * 31;
        g92 g92Var = this.f;
        int iHashCode6 = (iHashCode5 + (g92Var == null ? 0 : g92Var.a.hashCode())) * 31;
        h92 h92Var = this.g;
        int iHashCode7 = (iHashCode6 + (h92Var == null ? 0 : h92Var.a.hashCode())) * 31;
        k92 k92Var = this.h;
        return iHashCode7 + (k92Var != null ? k92Var.a.hashCode() : 0);
    }

    public final String toString() {
        return "SearchV2(topResults=" + this.a + ", albums=" + this.b + ", artists=" + this.c + ", audiobooks=" + this.d + ", episodes=" + this.e + ", playlists=" + this.f + ", podcasts=" + this.g + ", tracksV2=" + this.h + ')';
    }
}
