package p;

/* JADX INFO: loaded from: classes.dex */
public final class qr0 {
    public final wr0 a;

    public qr0(wr0 wr0Var) {
        this.a = wr0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof qr0) && v03.b(this.a, ((qr0) obj).a);
    }

    public final int hashCode() {
        wr0 wr0Var = this.a;
        if (wr0Var == null) {
            return 0;
        }
        return wr0Var.hashCode();
    }

    public final String toString() {
        return "Library(tracks=" + this.a + ')';
    }
}
