package p;

/* JADX INFO: loaded from: classes.dex */
public final class rm2 {
    public final tm2 a;

    public rm2(tm2 tm2Var) {
        this.a = tm2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof rm2) && v03.b(this.a, ((rm2) obj).a);
    }

    public final int hashCode() {
        return this.a.a.hashCode();
    }

    public final String toString() {
        return "Item(profile=" + this.a + ')';
    }
}
