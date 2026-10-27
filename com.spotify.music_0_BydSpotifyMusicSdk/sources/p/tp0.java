package p;

/* JADX INFO: loaded from: classes.dex */
public final class tp0 {
    public final sp0 a;

    public tp0(sp0 sp0Var) {
        this.a = sp0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof tp0) && v03.b(this.a, ((tp0) obj).a);
    }

    public final int hashCode() {
        sp0 sp0Var = this.a;
        if (sp0Var == null) {
            return 0;
        }
        return sp0Var.hashCode();
    }

    public final String toString() {
        return "Me(library=" + this.a + ')';
    }
}
