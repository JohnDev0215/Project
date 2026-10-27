package p;

/* JADX INFO: loaded from: classes.dex */
public final class qs0 {
    public final String a;
    public final gw1 b;

    public qs0(String str, gw1 gw1Var) {
        this.a = str;
        this.b = gw1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof qs0)) {
            return false;
        }
        qs0 qs0Var = (qs0) obj;
        return v03.b(this.a, qs0Var.a) && v03.b(this.b, qs0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Podcast(__typename=" + this.a + ", podcastWrapper=" + this.b + ')';
    }
}
