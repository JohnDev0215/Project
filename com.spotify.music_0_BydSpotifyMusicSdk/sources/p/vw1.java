package p;

/* JADX INFO: loaded from: classes.dex */
public final class vw1 {
    public final String a;
    public final Long b;

    public vw1(String str, Long l) {
        this.a = str;
        this.b = l;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof vw1)) {
            return false;
        }
        vw1 vw1Var = (vw1) obj;
        return v03.b(this.a, vw1Var.a) && v03.b(this.b, vw1Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        Long l = this.b;
        return iHashCode + (l == null ? 0 : l.hashCode());
    }

    public final String toString() {
        return "Preference(key=" + this.a + ", value=" + this.b + ')';
    }
}
