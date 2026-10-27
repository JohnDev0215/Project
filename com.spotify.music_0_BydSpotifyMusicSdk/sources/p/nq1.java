package p;

/* JADX INFO: loaded from: classes.dex */
public final class nq1 {
    public final n01 a;
    public final boolean b;

    public nq1(n01 n01Var, boolean z) {
        this.a = n01Var;
        this.b = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof nq1)) {
            return false;
        }
        nq1 nq1Var = (nq1) obj;
        return v03.b(this.a, nq1Var.a) && this.b == nq1Var.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        boolean z = this.b;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("OngoingPoint(point=");
        sb.append(this.a);
        sb.append(", isRequired=");
        return u4.n(sb, this.b, ')');
    }
}
