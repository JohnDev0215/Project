package p;

/* JADX INFO: loaded from: classes.dex */
public final class rs0 {
    public final String a;
    public final os0 b;

    public rs0(String str, os0 os0Var) {
        this.a = str;
        this.b = os0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof rs0)) {
            return false;
        }
        rs0 rs0Var = (rs0) obj;
        return v03.b(this.a, rs0Var.a) && v03.b(this.b, rs0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Podcasts(__typename=" + this.a + ", onUserLibraryPodcastPage=" + this.b + ')';
    }
}
