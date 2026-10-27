package p;

/* JADX INFO: loaded from: classes.dex */
public final class df0 {
    public final af0 a;

    public df0(af0 af0Var) {
        this.a = af0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof df0) && v03.b(this.a, ((df0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "EpisodeWrapper(data=" + this.a + ')';
    }
}
