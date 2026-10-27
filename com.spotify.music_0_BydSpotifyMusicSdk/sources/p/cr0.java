package p;

/* JADX INFO: loaded from: classes.dex */
public final class cr0 {
    public final String a;
    public final hn2 b;

    public cr0(String str, hn2 hn2Var) {
        this.a = str;
        this.b = hn2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cr0)) {
            return false;
        }
        cr0 cr0Var = (cr0) obj;
        return v03.b(this.a, cr0Var.a) && v03.b(this.b, cr0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Track(__typename=" + this.a + ", trackWrapper=" + this.b + ')';
    }
}
