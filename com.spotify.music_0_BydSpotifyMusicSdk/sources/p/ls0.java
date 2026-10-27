package p;

/* JADX INFO: loaded from: classes.dex */
public final class ls0 {
    public final rs0 a;
    public final js0 b;

    public ls0(rs0 rs0Var, js0 js0Var) {
        this.a = rs0Var;
        this.b = js0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ls0)) {
            return false;
        }
        ls0 ls0Var = (ls0) obj;
        return v03.b(this.a, ls0Var.a) && v03.b(this.b, ls0Var.b);
    }

    public final int hashCode() {
        rs0 rs0Var = this.a;
        int iHashCode = (rs0Var == null ? 0 : rs0Var.hashCode()) * 31;
        js0 js0Var = this.b;
        return iHashCode + (js0Var != null ? js0Var.hashCode() : 0);
    }

    public final String toString() {
        return "Library(podcasts=" + this.a + ", episodes=" + this.b + ')';
    }
}
