package p;

/* JADX INFO: loaded from: classes.dex */
public final class k5 {
    public final l5 a;

    public k5(l5 l5Var) {
        this.a = l5Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof k5) && v03.b(this.a, ((k5) obj).a);
    }

    public final int hashCode() {
        return this.a.a.hashCode();
    }

    public final String toString() {
        return "Item(profile=" + this.a + ')';
    }
}
