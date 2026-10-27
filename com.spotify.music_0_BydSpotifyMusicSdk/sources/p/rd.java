package p;

/* JADX INFO: loaded from: classes.dex */
public final class rd {
    public final qd a;

    public rd(qd qdVar) {
        this.a = qdVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof rd) && v03.b(this.a, ((rd) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Item(entity=" + this.a + ')';
    }
}
