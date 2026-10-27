package p;

/* JADX INFO: loaded from: classes.dex */
public final class rr0 {
    public final vr0 a;

    public rr0(vr0 vr0Var) {
        this.a = vr0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof rr0) && v03.b(this.a, ((rr0) obj).a);
    }

    public final int hashCode() {
        vr0 vr0Var = this.a;
        if (vr0Var == null) {
            return 0;
        }
        return vr0Var.hashCode();
    }

    public final String toString() {
        return "LibraryV2(page=" + this.a + ')';
    }
}
