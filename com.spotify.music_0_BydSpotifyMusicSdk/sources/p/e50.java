package p;

/* JADX INFO: loaded from: classes.dex */
public final class e50 {
    public final dz1 a;
    public final boolean b;

    public e50(dz1 dz1Var, boolean z) {
        this.a = dz1Var;
        this.b = z;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof e50)) {
            return false;
        }
        e50 e50Var = (e50) obj;
        return e50Var.a.equals(this.a) && e50Var.b == this.b;
    }

    public final int hashCode() {
        return ((this.a.hashCode() ^ 1000003) * 1000003) ^ Boolean.valueOf(this.b).hashCode();
    }
}
