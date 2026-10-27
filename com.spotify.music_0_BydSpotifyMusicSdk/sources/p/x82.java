package p;

/* JADX INFO: loaded from: classes.dex */
public final class x82 {
    public final w82 a;

    public x82(w82 w82Var) {
        this.a = w82Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof x82) && v03.b(this.a, ((x82) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "ItemsV2(item=" + this.a + ')';
    }
}
