package p;

/* JADX INFO: loaded from: classes.dex */
public final class ks0 {
    public final String a;
    public final ps0 b;

    public ks0(String str, ps0 ps0Var) {
        this.a = str;
        this.b = ps0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ks0)) {
            return false;
        }
        ks0 ks0Var = (ks0) obj;
        return v03.b(this.a, ks0Var.a) && v03.b(this.b, ks0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item(__typename=" + this.a + ", onUserLibraryPodcastResponse=" + this.b + ')';
    }
}
