package p;

/* JADX INFO: loaded from: classes.dex */
public final class p82 {
    public final String a;
    public final fc b;

    public p82(String str, fc fcVar) {
        this.a = str;
        this.b = fcVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p82)) {
            return false;
        }
        p82 p82Var = (p82) obj;
        return v03.b(this.a, p82Var.a) && v03.b(this.b, p82Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item2(__typename=" + this.a + ", artistWrapper=" + this.b + ')';
    }
}
