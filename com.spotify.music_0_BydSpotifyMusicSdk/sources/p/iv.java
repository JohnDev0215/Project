package p;

/* JADX INFO: loaded from: classes.dex */
public final class iv {
    public final Object a;
    public final ry0 b;

    public iv(Object obj, ry0 ry0Var) {
        this.a = obj;
        this.b = ry0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof iv)) {
            return false;
        }
        iv ivVar = (iv) obj;
        return v03.b(this.a, ivVar.a) && v03.b(this.b, ivVar.b);
    }

    public final int hashCode() {
        Object obj = this.a;
        return this.b.hashCode() + ((obj == null ? 0 : obj.hashCode()) * 31);
    }

    public final String toString() {
        return "CompletedWithCancellation(result=" + this.a + ", onCancellation=" + this.b + ')';
    }
}
