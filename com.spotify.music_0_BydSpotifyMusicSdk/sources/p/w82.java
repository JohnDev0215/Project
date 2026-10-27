package p;

/* JADX INFO: loaded from: classes.dex */
public final class w82 {
    public final String a;
    public final y82 b;
    public final z82 c;
    public final a92 d;
    public final b92 e;
    public final c92 f;
    public final e92 g;
    public final f92 h;

    public w82(String str, y82 y82Var, z82 z82Var, a92 a92Var, b92 b92Var, c92 c92Var, e92 e92Var, f92 f92Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = y82Var;
        this.c = z82Var;
        this.d = a92Var;
        this.e = b92Var;
        this.f = c92Var;
        this.g = e92Var;
        this.h = f92Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w82)) {
            return false;
        }
        w82 w82Var = (w82) obj;
        return v03.b(this.a, w82Var.a) && v03.b(this.b, w82Var.b) && v03.b(this.c, w82Var.c) && v03.b(this.d, w82Var.d) && v03.b(this.e, w82Var.e) && v03.b(this.f, w82Var.f) && v03.b(this.g, w82Var.g) && v03.b(this.h, w82Var.h);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        y82 y82Var = this.b;
        int iHashCode2 = (iHashCode + (y82Var == null ? 0 : y82Var.hashCode())) * 31;
        z82 z82Var = this.c;
        int iHashCode3 = (iHashCode2 + (z82Var == null ? 0 : z82Var.hashCode())) * 31;
        a92 a92Var = this.d;
        int iHashCode4 = (iHashCode3 + (a92Var == null ? 0 : a92Var.hashCode())) * 31;
        b92 b92Var = this.e;
        int iHashCode5 = (iHashCode4 + (b92Var == null ? 0 : b92Var.hashCode())) * 31;
        c92 c92Var = this.f;
        int iHashCode6 = (iHashCode5 + (c92Var == null ? 0 : c92Var.hashCode())) * 31;
        e92 e92Var = this.g;
        int iHashCode7 = (iHashCode6 + (e92Var == null ? 0 : e92Var.hashCode())) * 31;
        f92 f92Var = this.h;
        return iHashCode7 + (f92Var != null ? f92Var.hashCode() : 0);
    }

    public final String toString() {
        return "Item(__typename=" + this.a + ", onAlbumResponseWrapper=" + this.b + ", onArtistResponseWrapper=" + this.c + ", onAudiobookResponseWrapper=" + this.d + ", onEpisodeResponseWrapper=" + this.e + ", onPlaylistResponseWrapper=" + this.f + ", onPodcastResponseWrapper=" + this.g + ", onTrackResponseWrapper=" + this.h + ')';
    }
}
