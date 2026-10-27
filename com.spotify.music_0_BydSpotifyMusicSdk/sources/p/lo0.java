package p;

/* JADX INFO: loaded from: classes.dex */
public final class lo0 {
    public final ko0 a;

    public lo0(ko0 ko0Var) {
        this.a = ko0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof lo0) && v03.b(this.a, ((lo0) obj).a);
    }

    public final int hashCode() {
        ko0 ko0Var = this.a;
        if (ko0Var == null) {
            return 0;
        }
        return ko0Var.hashCode();
    }

    public final String toString() {
        return "Me(library=" + this.a + ')';
    }
}
