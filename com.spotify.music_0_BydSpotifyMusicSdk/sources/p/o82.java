package p;

/* JADX INFO: loaded from: classes.dex */
public final class o82 {
    public final String a;
    public final z5 b;

    public o82(String str, z5 z5Var) {
        this.a = str;
        this.b = z5Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o82)) {
            return false;
        }
        o82 o82Var = (o82) obj;
        return v03.b(this.a, o82Var.a) && v03.b(this.b, o82Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item1(__typename=" + this.a + ", albumWrapper=" + this.b + ')';
    }
}
