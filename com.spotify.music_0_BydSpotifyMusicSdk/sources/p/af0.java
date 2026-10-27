package p;

/* JADX INFO: loaded from: classes.dex */
public final class af0 {
    public final String a;
    public final cf0 b;
    public final bf0 c;

    public af0(String str, cf0 cf0Var, bf0 bf0Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = cf0Var;
        this.c = bf0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof af0)) {
            return false;
        }
        af0 af0Var = (af0) obj;
        return v03.b(this.a, af0Var.a) && v03.b(this.b, af0Var.b) && v03.b(this.c, af0Var.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        cf0 cf0Var = this.b;
        int iHashCode2 = (iHashCode + (cf0Var == null ? 0 : cf0Var.hashCode())) * 31;
        bf0 bf0Var = this.c;
        return iHashCode2 + (bf0Var != null ? bf0Var.hashCode() : 0);
    }

    public final String toString() {
        return "Data(__typename=" + this.a + ", onError=" + this.b + ", onEpisode=" + this.c + ')';
    }
}
