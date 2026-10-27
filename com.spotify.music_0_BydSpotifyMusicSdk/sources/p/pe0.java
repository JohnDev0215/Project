package p;

/* JADX INFO: loaded from: classes.dex */
public final class pe0 {
    public final String a;
    public final String b;
    public final he0 c;
    public final oe0 d;
    public final je0 e;
    public final ge0 f;
    public final le0 g;
    public final me0 h;
    public final ne0 i;

    public pe0(String str, String str2, he0 he0Var, oe0 oe0Var, je0 je0Var, ge0 ge0Var, le0 le0Var, me0 me0Var, ne0 ne0Var) {
        this.a = str;
        this.b = str2;
        this.c = he0Var;
        this.d = oe0Var;
        this.e = je0Var;
        this.f = ge0Var;
        this.g = le0Var;
        this.h = me0Var;
        this.i = ne0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pe0)) {
            return false;
        }
        pe0 pe0Var = (pe0) obj;
        return v03.b(this.a, pe0Var.a) && v03.b(this.b, pe0Var.b) && v03.b(this.c, pe0Var.c) && v03.b(this.d, pe0Var.d) && v03.b(this.e, pe0Var.e) && v03.b(this.f, pe0Var.f) && v03.b(this.g, pe0Var.g) && v03.b(this.h, pe0Var.h) && v03.b(this.i, pe0Var.i);
    }

    public final int hashCode() {
        int iB = hf2.b(this.b, this.a.hashCode() * 31, 31);
        he0 he0Var = this.c;
        int iHashCode = (iB + (he0Var == null ? 0 : he0Var.hashCode())) * 31;
        oe0 oe0Var = this.d;
        int iA = hf2.a(this.e.a, (iHashCode + (oe0Var == null ? 0 : oe0Var.a.hashCode())) * 31, 31);
        ge0 ge0Var = this.f;
        return this.i.a.hashCode() + ((this.h.hashCode() + ((this.g.hashCode() + ((iA + (ge0Var != null ? ge0Var.hashCode() : 0)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Episode(name=" + this.a + ", uri=" + this.b + ", coverArt=" + this.c + ", releaseDate=" + this.d + ", duration=" + this.e + ", contentRating=" + this.f + ", playability=" + this.g + ", playedState=" + this.h + ", podcastV2=" + this.i + ')';
    }
}
