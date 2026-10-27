package p;

/* JADX INFO: loaded from: classes.dex */
public final class av1 {
    public final su1 a;

    public av1(su1 su1Var) {
        this.a = su1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof av1) && v03.b(this.a, ((av1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "PlaylistWrapper(data=" + this.a + ')';
    }
}
