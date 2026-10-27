package p;

/* JADX INFO: loaded from: classes.dex */
public final class t82 {
    public final String a;
    public final gw1 b;

    public t82(String str, gw1 gw1Var) {
        this.a = str;
        this.b = gw1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof t82)) {
            return false;
        }
        t82 t82Var = (t82) obj;
        return v03.b(this.a, t82Var.a) && v03.b(this.b, t82Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item6(__typename=" + this.a + ", podcastWrapper=" + this.b + ')';
    }
}
