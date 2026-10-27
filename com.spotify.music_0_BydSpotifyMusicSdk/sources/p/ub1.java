package p;

/* JADX INFO: loaded from: classes.dex */
public final class ub1 {
    public final int a;
    public final int b;

    public ub1(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ub1)) {
            return false;
        }
        ub1 ub1Var = (ub1) obj;
        return this.a == ub1Var.a && this.b == ub1Var.b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b) + (Integer.hashCode(this.a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LoaderExtras(groupLimit=");
        sb.append(this.a);
        sb.append(", listLimit=");
        return u4.k(sb, this.b, ')');
    }
}
