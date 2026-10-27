package p;

/* JADX INFO: loaded from: classes.dex */
public final class su1 {
    public final String a;
    public final wu1 b;
    public final xu1 c;

    public su1(String str, wu1 wu1Var, xu1 xu1Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = wu1Var;
        this.c = xu1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof su1)) {
            return false;
        }
        su1 su1Var = (su1) obj;
        return v03.b(this.a, su1Var.a) && v03.b(this.b, su1Var.b) && v03.b(this.c, su1Var.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        wu1 wu1Var = this.b;
        int iHashCode2 = (iHashCode + (wu1Var == null ? 0 : wu1Var.hashCode())) * 31;
        xu1 xu1Var = this.c;
        return iHashCode2 + (xu1Var != null ? xu1Var.hashCode() : 0);
    }

    public final String toString() {
        return "Data(__typename=" + this.a + ", onError=" + this.b + ", onPlaylist=" + this.c + ')';
    }
}
