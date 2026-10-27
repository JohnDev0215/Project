package p;

/* JADX INFO: loaded from: classes.dex */
public final class ru1 {
    public final String a;
    public final vu1 b;
    public final yu1 c;

    public ru1(String str, vu1 vu1Var, yu1 yu1Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = vu1Var;
        this.c = yu1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ru1)) {
            return false;
        }
        ru1 ru1Var = (ru1) obj;
        return v03.b(this.a, ru1Var.a) && v03.b(this.b, ru1Var.b) && v03.b(this.c, ru1Var.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        vu1 vu1Var = this.b;
        int iHashCode2 = (iHashCode + (vu1Var == null ? 0 : vu1Var.hashCode())) * 31;
        yu1 yu1Var = this.c;
        return iHashCode2 + (yu1Var != null ? yu1Var.a.hashCode() : 0);
    }

    public final String toString() {
        return "Data1(__typename=" + this.a + ", onError=" + this.b + ", onUser=" + this.c + ')';
    }
}
