package p;

/* JADX INFO: loaded from: classes.dex */
public final class z5 {
    public final w5 a;

    public z5(w5 w5Var) {
        this.a = w5Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof z5) && v03.b(this.a, ((z5) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "AlbumWrapper(data=" + this.a + ')';
    }
}
