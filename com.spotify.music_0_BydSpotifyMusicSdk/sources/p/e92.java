package p;

/* JADX INFO: loaded from: classes.dex */
public final class e92 {
    public final String a;
    public final gw1 b;

    public e92(String str, gw1 gw1Var) {
        this.a = str;
        this.b = gw1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e92)) {
            return false;
        }
        e92 e92Var = (e92) obj;
        return v03.b(this.a, e92Var.a) && v03.b(this.b, e92Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnPodcastResponseWrapper(__typename=" + this.a + ", podcastWrapper=" + this.b + ')';
    }
}
