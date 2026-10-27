package p;

/* JADX INFO: loaded from: classes.dex */
public final class sr0 {
    public final qr0 a;
    public final rr0 b;

    public sr0(qr0 qr0Var, rr0 rr0Var) {
        this.a = qr0Var;
        this.b = rr0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof sr0)) {
            return false;
        }
        sr0 sr0Var = (sr0) obj;
        return v03.b(this.a, sr0Var.a) && v03.b(this.b, sr0Var.b);
    }

    public final int hashCode() {
        qr0 qr0Var = this.a;
        int iHashCode = (qr0Var == null ? 0 : qr0Var.hashCode()) * 31;
        rr0 rr0Var = this.b;
        return iHashCode + (rr0Var != null ? rr0Var.hashCode() : 0);
    }

    public final String toString() {
        return "Me(library=" + this.a + ", libraryV2=" + this.b + ')';
    }
}
