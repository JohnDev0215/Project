package p;

import com.spotify.automotive.pathfinder.type.PodcastMediaType;

/* JADX INFO: loaded from: classes.dex */
public final class aw1 {
    public final yv1 a;
    public final PodcastMediaType b;
    public final String c;
    public final zv1 d;
    public final String e;

    public aw1(yv1 yv1Var, PodcastMediaType podcastMediaType, String str, zv1 zv1Var, String str2) {
        this.a = yv1Var;
        this.b = podcastMediaType;
        this.c = str;
        this.d = zv1Var;
        this.e = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof aw1)) {
            return false;
        }
        aw1 aw1Var = (aw1) obj;
        return v03.b(this.a, aw1Var.a) && this.b == aw1Var.b && v03.b(this.c, aw1Var.c) && v03.b(this.d, aw1Var.d) && v03.b(this.e, aw1Var.e);
    }

    public final int hashCode() {
        yv1 yv1Var = this.a;
        int iB = hf2.b(this.c, (this.b.hashCode() + ((yv1Var == null ? 0 : yv1Var.hashCode()) * 31)) * 31, 31);
        zv1 zv1Var = this.d;
        return this.e.hashCode() + ((iB + (zv1Var != null ? zv1Var.a.hashCode() : 0)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PodcastInfo(coverArt=");
        sb.append(this.a);
        sb.append(", mediaType=");
        sb.append(this.b);
        sb.append(", name=");
        sb.append(this.c);
        sb.append(", publisher=");
        sb.append(this.d);
        sb.append(", uri=");
        return hf2.d(sb, this.e, ')');
    }
}
