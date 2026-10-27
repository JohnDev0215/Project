package p;

/* JADX INFO: loaded from: classes.dex */
public final class rm0 {
    public final tm0 a;

    public rm0(tm0 tm0Var) {
        this.a = tm0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof rm0) && v03.b(this.a, ((rm0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Item(track=" + this.a + ')';
    }
}
