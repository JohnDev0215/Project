package p;

import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class mk0 extends v03 {
    public final Set r;
    public final Set s;

    public mk0(Set set, Set set2) {
        v03.j(set2, "permanentErrorIndices");
        this.r = set;
        this.s = set2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mk0)) {
            return false;
        }
        mk0 mk0Var = (mk0) obj;
        return v03.b(this.r, mk0Var.r) && v03.b(this.s, mk0Var.s);
    }

    public final int hashCode() {
        return this.s.hashCode() + (this.r.hashCode() * 31);
    }

    public final String toString() {
        return "Success(successIndices=" + this.r + ", permanentErrorIndices=" + this.s + ')';
    }
}
