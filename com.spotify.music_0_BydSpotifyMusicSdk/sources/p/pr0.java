package p;

/* JADX INFO: loaded from: classes.dex */
public final class pr0 {
    public final or0 a;

    public pr0(or0 or0Var) {
        this.a = or0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof pr0) && v03.b(this.a, ((pr0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Item(item=" + this.a + ')';
    }
}
