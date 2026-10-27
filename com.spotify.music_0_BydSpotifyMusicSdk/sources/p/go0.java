package p;

/* JADX INFO: loaded from: classes.dex */
public final class go0 {
    public final String a;
    public final z5 b;

    public go0(String str, z5 z5Var) {
        this.a = str;
        this.b = z5Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof go0)) {
            return false;
        }
        go0 go0Var = (go0) obj;
        return v03.b(this.a, go0Var.a) && v03.b(this.b, go0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Album(__typename=" + this.a + ", albumWrapper=" + this.b + ')';
    }
}
