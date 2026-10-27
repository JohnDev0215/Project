package p;

/* JADX INFO: loaded from: classes.dex */
public final class tm0 {
    public final String a;
    public final um2 b;

    public tm0(String str, um2 um2Var) {
        this.a = str;
        this.b = um2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof tm0)) {
            return false;
        }
        tm0 tm0Var = (tm0) obj;
        return v03.b(this.a, tm0Var.a) && v03.b(this.b, tm0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Track(__typename=" + this.a + ", track=" + this.b + ')';
    }
}
