package p;

/* JADX INFO: loaded from: classes.dex */
public final class sp0 {
    public final pp0 a;

    public sp0(pp0 pp0Var) {
        this.a = pp0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof sp0) && v03.b(this.a, ((sp0) obj).a);
    }

    public final int hashCode() {
        pp0 pp0Var = this.a;
        if (pp0Var == null) {
            return 0;
        }
        return pp0Var.hashCode();
    }

    public final String toString() {
        return "Library(audiobooks=" + this.a + ')';
    }
}
