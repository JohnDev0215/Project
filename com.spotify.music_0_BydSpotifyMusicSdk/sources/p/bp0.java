package p;

/* JADX INFO: loaded from: classes.dex */
public final class bp0 {
    public final yo0 a;

    public bp0(yo0 yo0Var) {
        this.a = yo0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof bp0) && v03.b(this.a, ((bp0) obj).a);
    }

    public final int hashCode() {
        yo0 yo0Var = this.a;
        if (yo0Var == null) {
            return 0;
        }
        return yo0Var.hashCode();
    }

    public final String toString() {
        return "Library(artists=" + this.a + ')';
    }
}
