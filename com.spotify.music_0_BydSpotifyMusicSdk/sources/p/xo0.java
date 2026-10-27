package p;

/* JADX INFO: loaded from: classes.dex */
public final class xo0 {
    public final String a;
    public final fc b;

    public xo0(String str, fc fcVar) {
        this.a = str;
        this.b = fcVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xo0)) {
            return false;
        }
        xo0 xo0Var = (xo0) obj;
        return v03.b(this.a, xo0Var.a) && v03.b(this.b, xo0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Artist(__typename=" + this.a + ", artistWrapper=" + this.b + ')';
    }
}
