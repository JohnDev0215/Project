package p;

/* JADX INFO: loaded from: classes.dex */
public final class h41 {
    public final int a;
    public final Object b;

    public h41(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h41)) {
            return false;
        }
        h41 h41Var = (h41) obj;
        return this.a == h41Var.a && v03.b(this.b, h41Var.b);
    }

    public final int hashCode() {
        int iHashCode = Integer.hashCode(this.a) * 31;
        Object obj = this.b;
        return iHashCode + (obj == null ? 0 : obj.hashCode());
    }

    public final String toString() {
        return "IndexedValue(index=" + this.a + ", value=" + this.b + ')';
    }
}
