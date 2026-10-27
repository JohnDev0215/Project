package p;

/* JADX INFO: loaded from: classes.dex */
public final class c92 {
    public final String a;
    public final av1 b;

    public c92(String str, av1 av1Var) {
        this.a = str;
        this.b = av1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c92)) {
            return false;
        }
        c92 c92Var = (c92) obj;
        return v03.b(this.a, c92Var.a) && v03.b(this.b, c92Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnPlaylistResponseWrapper1(__typename=" + this.a + ", playlistWrapper=" + this.b + ')';
    }
}
