package p;

/* JADX INFO: loaded from: classes.dex */
public final class u82 {
    public final v82 a;

    public u82(v82 v82Var) {
        this.a = v82Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof u82) && v03.b(this.a, ((u82) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Item7(item=" + this.a + ')';
    }
}
