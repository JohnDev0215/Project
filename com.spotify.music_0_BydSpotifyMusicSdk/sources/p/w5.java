package p;

/* JADX INFO: loaded from: classes.dex */
public final class w5 {
    public final String a;
    public final y5 b;
    public final x5 c;

    public w5(String str, y5 y5Var, x5 x5Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = y5Var;
        this.c = x5Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w5)) {
            return false;
        }
        w5 w5Var = (w5) obj;
        return v03.b(this.a, w5Var.a) && v03.b(this.b, w5Var.b) && v03.b(this.c, w5Var.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        y5 y5Var = this.b;
        int iHashCode2 = (iHashCode + (y5Var == null ? 0 : y5Var.hashCode())) * 31;
        x5 x5Var = this.c;
        return iHashCode2 + (x5Var != null ? x5Var.hashCode() : 0);
    }

    public final String toString() {
        return "Data(__typename=" + this.a + ", onError=" + this.b + ", onAlbum=" + this.c + ')';
    }
}
