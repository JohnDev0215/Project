package p;

/* JADX INFO: loaded from: classes.dex */
public final class d92 {
    public final String a;
    public final av1 b;

    public d92(String str, av1 av1Var) {
        this.a = str;
        this.b = av1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d92)) {
            return false;
        }
        d92 d92Var = (d92) obj;
        return v03.b(this.a, d92Var.a) && v03.b(this.b, d92Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnPlaylistResponseWrapper(__typename=" + this.a + ", playlistWrapper=" + this.b + ')';
    }
}
