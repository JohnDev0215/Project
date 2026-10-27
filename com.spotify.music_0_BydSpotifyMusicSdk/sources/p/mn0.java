package p;

/* JADX INFO: loaded from: classes.dex */
public final class mn0 {
    public final String a;
    public final m5 b;

    public mn0(String str, m5 m5Var) {
        this.a = str;
        this.b = m5Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mn0)) {
            return false;
        }
        mn0 mn0Var = (mn0) obj;
        return v03.b(this.a, mn0Var.a) && v03.b(this.b, mn0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item1(__typename=" + this.a + ", album=" + this.b + ')';
    }
}
