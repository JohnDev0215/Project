package p;

/* JADX INFO: loaded from: classes.dex */
public final class hn2 {
    public final en2 a;

    public hn2(en2 en2Var) {
        this.a = en2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof hn2) && v03.b(this.a, ((hn2) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "TrackWrapper(data=" + this.a + ')';
    }
}
