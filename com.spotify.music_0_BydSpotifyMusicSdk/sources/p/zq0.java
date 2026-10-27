package p;

/* JADX INFO: loaded from: classes.dex */
public final class zq0 {
    public final yq0 a;

    public zq0(yq0 yq0Var) {
        this.a = yq0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zq0) && v03.b(this.a, ((zq0) obj).a);
    }

    public final int hashCode() {
        yq0 yq0Var = this.a;
        if (yq0Var == null) {
            return 0;
        }
        return yq0Var.hashCode();
    }

    public final String toString() {
        return "Me(library=" + this.a + ')';
    }
}
