package p;

/* JADX INFO: loaded from: classes.dex */
public final class vr0 {
    public final String a;
    public final tr0 b;

    public vr0(String str, tr0 tr0Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = tr0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof vr0)) {
            return false;
        }
        vr0 vr0Var = (vr0) obj;
        return v03.b(this.a, vr0Var.a) && v03.b(this.b, vr0Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        tr0 tr0Var = this.b;
        return iHashCode + (tr0Var == null ? 0 : tr0Var.a.hashCode());
    }

    public final String toString() {
        return "Page(__typename=" + this.a + ", onLibraryPage=" + this.b + ')';
    }
}
