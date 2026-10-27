package p;

/* JADX INFO: loaded from: classes.dex */
public final class nv1 {
    public final String a;
    public final pv1 b;

    public nv1(String str, pv1 pv1Var) {
        this.a = str;
        this.b = pv1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof nv1)) {
            return false;
        }
        nv1 nv1Var = (nv1) obj;
        return v03.b(this.a, nv1Var.a) && v03.b(this.b, nv1Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item(__typename=" + this.a + ", onContextEpisodeV2=" + this.b + ')';
    }
}
