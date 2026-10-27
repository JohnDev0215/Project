package p;

/* JADX INFO: loaded from: classes.dex */
public final class tn0 {
    public final String a;
    public final um2 b;

    public tn0(String str, um2 um2Var) {
        this.a = str;
        this.b = um2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof tn0)) {
            return false;
        }
        tn0 tn0Var = (tn0) obj;
        return v03.b(this.a, tn0Var.a) && v03.b(this.b, tn0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Track(__typename=" + this.a + ", track=" + this.b + ')';
    }
}
