package p;

/* JADX INFO: loaded from: classes.dex */
public final class s82 {
    public final String a;
    public final av1 b;

    public s82(String str, av1 av1Var) {
        this.a = str;
        this.b = av1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s82)) {
            return false;
        }
        s82 s82Var = (s82) obj;
        return v03.b(this.a, s82Var.a) && v03.b(this.b, s82Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item5(__typename=" + this.a + ", playlistWrapper=" + this.b + ')';
    }
}
