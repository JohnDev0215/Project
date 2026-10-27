package p;

/* JADX INFO: loaded from: classes.dex */
public final class ew1 {
    public final String a;
    public final fw1 b;
    public final aw1 c;

    public ew1(String str, fw1 fw1Var, aw1 aw1Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = fw1Var;
        this.c = aw1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ew1)) {
            return false;
        }
        ew1 ew1Var = (ew1) obj;
        return v03.b(this.a, ew1Var.a) && v03.b(this.b, ew1Var.b) && v03.b(this.c, ew1Var.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        fw1 fw1Var = this.b;
        int iHashCode2 = (iHashCode + (fw1Var == null ? 0 : fw1Var.hashCode())) * 31;
        aw1 aw1Var = this.c;
        return iHashCode2 + (aw1Var != null ? aw1Var.hashCode() : 0);
    }

    public final String toString() {
        return "Data(__typename=" + this.a + ", onError=" + this.b + ", podcastInfo=" + this.c + ')';
    }
}
